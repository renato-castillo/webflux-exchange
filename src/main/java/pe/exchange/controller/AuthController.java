package pe.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.exchange.service.IAuthService;
import pe.exchange.model.request.AuthRequest;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private IAuthService authService;

    @PostMapping
    public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest authRequest){
        return authService.login(authRequest);
    }
}
