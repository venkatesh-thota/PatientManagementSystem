# PatientManagementSystem
PatientManagementSystem

API:
Register User: http://localhost:8090/pms/saveUser
Sign In : http://localhost:8090/pms/authenticate (will get JWT in response)
Save Patient: http://localhost:8090/pms/savepatient (add JWT in Authorization Header)
Upload Doc for a patient with given ID: http://localhost:8090/pms/uploadFile/:patientID (add JWT in Authorization Header, patientID as pathVariable)
View All Patients: http://localhost:8090/pms/all (add JWT in Authorization Header)
Download Docs for a given Patient with ID: http://localhost:8090/pms/downloadFile/:patientID (add JWT in Authorization Header)
