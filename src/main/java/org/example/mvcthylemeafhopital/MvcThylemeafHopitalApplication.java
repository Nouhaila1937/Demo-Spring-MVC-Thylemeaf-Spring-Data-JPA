package org.example.mvcthylemeafhopital;

import org.example.mvcthylemeafhopital.entities.Patient;
import org.example.mvcthylemeafhopital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class MvcThylemeafHopitalApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(MvcThylemeafHopitalApplication.class, args);
    }

    public void run(String ...args){
        Patient patient = new Patient();
        patient.setId(null);
        patient.setNom("Nouhaila");
        patient.setDateNaissance(new Date());
        patient.setScore(21);
        patient.setMalade(false);
    }
}
