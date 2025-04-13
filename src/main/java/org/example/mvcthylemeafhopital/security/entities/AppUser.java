package org.example.mvcthylemeafhopital.security.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {
    @Id
    private String userId;
    @Column(unique = true)
    private String userName;
    private String password;
    private String email;
    // @ManyToMany(fetch= FetchType.LAZY)  il ne charge pas les roles dans la mémoire que lorsqu'on ai besoin
    @ManyToMany(fetch= FetchType.EAGER)
    private List<AppRole> roles;
}
