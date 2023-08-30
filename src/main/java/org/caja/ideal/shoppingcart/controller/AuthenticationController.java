package org.caja.ideal.shoppingcart.controller;

import jakarta.validation.Valid;
import org.caja.ideal.shoppingcart.entity.dto.AuthenticationRequest;
import org.caja.ideal.shoppingcart.entity.dto.AuthenticationResponse;
import org.caja.ideal.shoppingcart.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Validated
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request){
        return ResponseEntity.ok(service.login(request));

    }
    @PostMapping("/sign")
    public ResponseEntity<AuthenticationResponse> sign(@RequestBody @Valid AuthenticationRequest request){
        return ResponseEntity.ok(service.sign(request));
    }

}
