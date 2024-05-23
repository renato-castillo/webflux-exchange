package pe.exchange.service;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import pe.exchange.domain.Exchange;
import pe.exchange.model.request.ExchangeRequest;
import pe.exchange.model.response.ExchangeResponse;
import pe.exchange.util.ICRUD;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IExchangeService extends ICRUD<Exchange, Long> {

    Mono<ResponseEntity<?>> doExchange(ExchangeRequest exchangeRequest, Authentication userDetails);

    Mono<ResponseEntity<?>> getMyExchanges(Authentication userDetails);

    Mono<ResponseEntity<?>> getAllExchanges();

}
