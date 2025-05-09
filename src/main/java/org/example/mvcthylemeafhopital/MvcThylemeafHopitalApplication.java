package org.example.mvcthylemeafhopital;

import org.example.mvcthylemeafhopital.entities.Patient;
import org.example.mvcthylemeafhopital.repository.PatientRepository;
import org.example.mvcthylemeafhopital.security.repo.sercvice.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.util.Date;

@SpringBootApplication
public class MvcThylemeafHopitalApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(MvcThylemeafHopitalApplication.class, args);
    }

    public void run(String ...args) {
       /* Patient patient = new Patient();
        patient.setId(null);
        patient.setNom("Nouhaila");
        patient.setDateNaissance(new Date());
        patient.setScore(21);
        patient.setMalade(false);
        patientRepository.save(patient);


        patientRepository.save(new Patient(null,"ahmed",new Date(),true,22));
*/

    }
    //@Bean
    CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager){
        return args -> {
            jdbcUserDetailsManager.createUser(User.withUsername("user1").password(passwordEncoder().encode("1234")).roles("USER").build());
            jdbcUserDetailsManager.createUser(User.withUsername("user22").password(passwordEncoder().encode("1234")).roles("USER").build());
            jdbcUserDetailsManager.createUser(User.withUsername("admin2").password(passwordEncoder().encode("1234")).roles("USER","ADMIN").build());
            UserDetails u1=jdbcUserDetailsManager.loadUserByUsername("user11");
            if(u1==null)
                jdbcUserDetailsManager.createUser(User.withUsername("user11").password(passwordEncoder().encode("1234")).roles("USER").build());
            UserDetails u2=jdbcUserDetailsManager.loadUserByUsername("user22");
            if(u2==null)
                jdbcUserDetailsManager.createUser(User.withUsername("user22").password(passwordEncoder().encode("1234")).roles("USER").build());
            UserDetails u3=jdbcUserDetailsManager.loadUserByUsername("admin2");
            if(u3==null)
                jdbcUserDetailsManager.createUser(User.withUsername("admin2").password(passwordEncoder().encode("1234")).roles("USER","ADMIN").build());
        };
    }
    @Bean
    PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService) {
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user23","1234","1234","user23@gmail.com");
            accountService.addNewUser("user24","1234","1234","user24@gmail.com");
            accountService.addRoleToUser("user23","USER");
            accountService.addRoleToUser("user24","USER");
            accountService.addRoleToUser("user24","ADMIN");
        };
    }
}
