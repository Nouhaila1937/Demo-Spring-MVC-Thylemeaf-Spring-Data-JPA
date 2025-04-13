package org.example.mvcthylemeafhopital.security.repo.sercvice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.example.mvcthylemeafhopital.security.entities.AppRole;
import org.example.mvcthylemeafhopital.security.entities.AppUser;
import org.example.mvcthylemeafhopital.security.repo.AppRoleRepository;
import org.example.mvcthylemeafhopital.security.repo.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final PasswordEncoder passwordEncoder;
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    @Override
    public AppUser addNewUser(String username, String password, String confirmpassword, String email) {
       AppUser appUser = appUserRepository.findByUsername(username);
       if(appUser != null) throw new RuntimeException("User already exists");
       if(!password.equals(confirmpassword)) throw new RuntimeException("Passwords do not match");
        appUser = AppUser.builder()
               .userId(UUID.randomUUID().toString())
               .userName(username)
               .password(passwordEncoder.encode(password))
               .email(email)
               .build();

    AppUser savedUser = appUserRepository.save(appUser);
        return savedUser;
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole=appRoleRepository.findById(role).orElse(null);
        if(appRole != null) throw new RuntimeException("Role already exists");
        appRole = AppRole.builder().role(role).build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findById(role).get();
        appUser.getRoles().add(appRole);
        //appUserRepository.save(appUser); la méthode il est transactionnel donc cette ligne n'est pas obligatoire
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);

    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return null;
    }
}


















