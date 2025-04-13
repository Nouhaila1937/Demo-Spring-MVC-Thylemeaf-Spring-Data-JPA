package org.example.mvcthylemeafhopital.security.repo.sercvice;

import org.example.mvcthylemeafhopital.security.entities.AppRole;
import org.example.mvcthylemeafhopital.security.entities.AppUser;

public interface AccountService {
    // ils sont des méthodes
    AppUser addNewUser(String username, String password, String confirmpassword, String email);
    AppRole addNewRole  ( String role);
    void addRoleToUser(String username, String role);
    void removeRoleFromUser(String username, String role);
    AppUser loadUserByUsername(String username);
}
