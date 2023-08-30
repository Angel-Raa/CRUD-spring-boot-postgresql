package org.caja.ideal.shoppingcart.service.impl;

import org.caja.ideal.shoppingcart.configuration.jwt.JwtService;
import org.caja.ideal.shoppingcart.entity.dto.AuthenticationRequest;
import org.caja.ideal.shoppingcart.entity.dto.AuthenticationResponse;
import org.caja.ideal.shoppingcart.entity.models.Users;
import org.caja.ideal.shoppingcart.exeptions.UsernameAlreadyExistsException;
import org.caja.ideal.shoppingcart.repository.IUsersRepository;
import org.caja.ideal.shoppingcart.service.AuthenticationService;
import org.caja.ideal.shoppingcart.utils.Message;
import org.caja.ideal.shoppingcart.utils.enums.Permission;
import org.caja.ideal.shoppingcart.utils.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private IUsersRepository repository;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder encoder;
    @Override
    @Transactional
    public AuthenticationResponse login(AuthenticationRequest authentication) {
        UsernamePasswordAuthenticationToken autToken = new UsernamePasswordAuthenticationToken(
                authentication.getUsername(), authentication.getPassword());
        manager.authenticate(autToken);
        Users user = repository.findByUsername(authentication.getUsername())
                .orElseThrow(( ) -> new UsernameNotFoundException("Not found username " + authentication.getUsername()));
        String jwtDto = jwtService.generateToken(user, jwtService.generateExtraClaims(user));
        AuthenticationResponse jwt = new AuthenticationResponse(jwtDto);
        return  jwt;
    }

    @Override
    @Transactional
    public AuthenticationResponse sign(AuthenticationRequest authentication) {
        String username = authentication.getUsername();
        String password = authentication.getPassword();
        if(repository.existsByUsername(username)){
           throw  new UsernameAlreadyExistsException(Message.USERNAME_ALREADY_EXISTS
           , HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
        }
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole(Role.CUSTOMER);
        repository.save(user);
        String jwtDto = jwtService.generateToken(user, jwtService.generateExtraClaims(user));
        AuthenticationResponse jwt = new AuthenticationResponse(jwtDto);
        return jwt;
    }


}
