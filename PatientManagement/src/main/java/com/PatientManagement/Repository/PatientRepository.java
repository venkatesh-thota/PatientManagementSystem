package com.PatientManagement.Repository;

import com.PatientManagement.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


public interface PatientRepository extends JpaRepository<Patient,Long> {
}
