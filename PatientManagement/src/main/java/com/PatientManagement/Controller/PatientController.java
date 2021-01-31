package com.PatientManagement.Controller;

import com.PatientManagement.Exception.FileStorageException;
import com.PatientManagement.Exception.MyFileNotFoundException;
import com.PatientManagement.Repository.PatientRepository;
import com.PatientManagement.Service.FileStorageService;
import com.PatientManagement.domain.Patient;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    PatientRepository respository;
    @Autowired
    private FileStorageService fileStorageService;

    public PatientController(PatientRepository respository) {
        this.respository = respository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getpatients(){
        return new ResponseEntity<List<Patient>>(respository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity savePatients(@RequestBody Patient patient){
        Patient res =   respository.save(patient);
        return new ResponseEntity<Patient>(res,HttpStatus.CREATED);
    }

    @PostMapping("/uploadFile/{patientID}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String patientID) throws FileStorageException {
        String fileName = fileStorageService.storeFile(file);
        Optional<Patient> patient =  respository.findById(Long.parseLong(patientID));
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        System.out.println(patient.get().getPatientName());
        patient.get().setReport(fileDownloadUri);
        Patient res = respository.save(patient.get());
//        return new UploadFileResponse(fileName, fileDownloadUri,
//                file.getContentType(), file.getSize());
        return new ResponseEntity<String>(fileDownloadUri,HttpStatus.ACCEPTED);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws MyFileNotFoundException {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
           // logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
