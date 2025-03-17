package org.example.mvcthylemeafhopital.repository;

import org.example.mvcthylemeafhopital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
