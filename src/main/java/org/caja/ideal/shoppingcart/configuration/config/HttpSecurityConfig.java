package org.caja.ideal.shoppingcart.configuration.config;

import org.caja.ideal.shoppingcart.configuration.filter.JwtAuthenticationFilter;
import org.caja.ideal.shoppingcart.utils.enums.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class HttpSecurityConfig {
    @Autowired
    private JwtAuthenticationFilter authenticationFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(getAuthorizationManagerRequestMatcherRegistryCustomizer());
        return http.build();

    }

    private static Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> getAuthorizationManagerRequestMatcherRegistryCustomizer() {
        return (api) -> {
            // public
            api.requestMatchers(HttpMethod.GET, "/product/list").permitAll();
            api.requestMatchers(HttpMethod.GET, "/product/{id}").permitAll();
            api.requestMatchers(HttpMethod.POST, "/authentication/login").permitAll();
            api.requestMatchers(HttpMethod.POST, "/authentication/sign").permitAll();
            api.requestMatchers("/error").permitAll();
            // Private
            api.requestMatchers(HttpMethod.POST, "/product/add/").hasAnyAuthority(Permission.SAVE_ONE_PRODUCT.name());
            api.requestMatchers(HttpMethod.PUT, "/product/update/{id}/").hasAnyAuthority(Permission.SAVE_ONE_PRODUCT.name());
            api.requestMatchers(HttpMethod.DELETE, "/product/{id}/").hasAnyAuthority(Permission.SAVE_ONE_PRODUCT.name());
            api.anyRequest().authenticated();
        };
    }

}
