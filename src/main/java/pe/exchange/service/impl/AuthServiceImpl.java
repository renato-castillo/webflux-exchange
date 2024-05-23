package pe.exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import pe.exchange.model.request.AuthRequest;
import pe.exchange.model.response.AuthResponse;
import pe.exchange.model.response.ErrorResponse;
import pe.exchange.security.JWTUtil;
import pe.exchange.service.IAuthService;
import pe.exchange.service.IUserService;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private IUserService userService;


    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public Mono<ResponseEntity<?>> login(AuthRequest authRequest) {
        return userService.searchByUser(authRequest.getUsername())
                .map((userDetails) -> {

                    if(BCrypt.checkpw(authRequest.getPassword(), userDetails.getPassword())) {
                        String token = jwtUtil.generateToken(userDetails);
                        Date expiration = jwtUtil.getExpirationDateFromToken(token);

                        return ResponseEntity.ok(new AuthResponse(token, expiration));
                    }else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("Credenciales incorrectas", new Date()));
                    }
                }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}
