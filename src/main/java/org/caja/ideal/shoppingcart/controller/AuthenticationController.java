package org.caja.ideal.shoppingcart.controller;

import jakarta.validation.Valid;
import org.caja.ideal.shoppingcart.entity.dto.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationResponse request){

        return  null;

    }

}
