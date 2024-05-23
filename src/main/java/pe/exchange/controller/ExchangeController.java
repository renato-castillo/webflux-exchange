package pe.exchange.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.exchange.model.request.ExchangeRequest;
import pe.exchange.service.IExchangeService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private IExchangeService exchangeService;

    @PostMapping
    public Mono<ResponseEntity<?>> doExchange(@RequestBody ExchangeRequest exchangeRequest, Authentication authentication){
        return exchangeService.doExchange(exchangeRequest, authentication);
    }

    @GetMapping("/my-exchanges")
    public Mono<ResponseEntity<?>> getMyExchanges(Authentication authentication){
        return exchangeService.getMyExchanges(authentication);
    }

    @GetMapping
    public Mono<ResponseEntity<?>> getAllExchanges() {
        return exchangeService.getAllExchanges();
    }


}
