package org.caja.ideal.shoppingcart.service;

import org.caja.ideal.shoppingcart.entity.dto.AuthenticationRequest;
import org.caja.ideal.shoppingcart.entity.dto.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest authentication);
    AuthenticationResponse sign(AuthenticationRequest authentication);
}
