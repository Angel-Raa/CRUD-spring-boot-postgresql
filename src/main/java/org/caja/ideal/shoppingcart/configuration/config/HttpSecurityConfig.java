package org.caja.ideal.shoppingcart.configuration.config;

import org.caja.ideal.shoppingcart.utils.enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((api) -> {
                    // public
                    api.requestMatchers(HttpMethod.GET, "/product/list").permitAll();
                    api.requestMatchers(HttpMethod.POST, "/authentication/login").permitAll();
                    api.requestMatchers("/error").permitAll();
                    // Private
                    api.requestMatchers(HttpMethod.POST, "/product/add").hasAnyAuthority(Permission.SAVE_ONE_PRODUCT.name());
                    api.requestMatchers(HttpMethod.GET, "/product/{id}").hasAnyAuthority(Permission.READ_ALL_PRODUCT.name());
                    api.requestMatchers(HttpMethod.PUT, "/product/update/{id}").hasAnyAuthority(Permission.SAVE_ONE_PRODUCT.name());
                    api.requestMatchers(HttpMethod.DELETE, "/product/{id}").hasAnyAuthority(Permission.SAVE_ONE_PRODUCT.name());
                    api.anyRequest().denyAll();
                });


        return http.build();

    }

}
