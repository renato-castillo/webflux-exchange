package pe.exchange.service;

import org.springframework.http.ResponseEntity;
import pe.exchange.model.request.AuthRequest;
import reactor.core.publisher.Mono;

public interface IAuthService {

    Mono<ResponseEntity<?>> login(AuthRequest authRequest);
}
