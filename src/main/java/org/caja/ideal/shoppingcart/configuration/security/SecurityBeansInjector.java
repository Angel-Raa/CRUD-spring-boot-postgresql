package org.caja.ideal.shoppingcart.configuration.security;

import org.caja.ideal.shoppingcart.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityBeansInjector {

    @Autowired
    private IUsersRepository repositories;
    @Bean
    public AuthenticationManager manager (AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager(); // ProviderManager implementation AuthenticationManager
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthentication = new DaoAuthenticationProvider();
        daoAuthentication.setUserDetailsService(userDetailsService());
        daoAuthentication.setPasswordEncoder(passwordEncoder());
        return  daoAuthentication;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
          return repositories.findByUsername(username)
                  .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        };
    }
}
