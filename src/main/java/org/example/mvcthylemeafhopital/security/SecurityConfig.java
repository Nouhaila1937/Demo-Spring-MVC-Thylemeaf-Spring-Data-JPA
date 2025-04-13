package org.example.mvcthylemeafhopital.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // il a drna hadi mandiroch  httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
public class SecurityConfig {
  @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

   //@Bean si on met le bean en commentaire il ne va pas etre créé
   // c'est ici ou on met des utilisateur qui vont se connecter au forum de spring en premier
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                // password("{noop}1234") = no password encoder parceque si je laisse le password comme il est il ne vas pas travailler 7itach spring kay3ref pass encoder safi
                User.withUsername("user").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1234")).roles("USER", "ADMIN").build()
        );
    }

    @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.formLogin();
        // pour rediriger automatiquement vers le home lorsque se connecte
//        httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll();
//        httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
//        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
        return httpSecurity.build();

    }
}