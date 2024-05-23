package pe.exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pe.exchange.domain.Exchange;
import pe.exchange.client.feign.OpenerApiClient;
import pe.exchange.model.request.ExchangeRequest;
import pe.exchange.model.response.ExchangeResponse;
import pe.exchange.repository.IExchangeRepository;
import pe.exchange.repository.generic.IGenericRepo;
import pe.exchange.service.IExchangeService;
import pe.exchange.util.impl.CRUDImpl;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExchangeServiceImpl extends CRUDImpl<Exchange, Long> implements IExchangeService {

    @Autowired
    private IExchangeRepository exchangeRepository;

    @Autowired
    private OpenerApiClient openerApiClient;

    @Override
    protected IGenericRepo<Exchange, Long> getRepo() {
        return exchangeRepository;
    }

    @Override
    public Mono<ResponseEntity<?>> doExchange(ExchangeRequest exchangeRequest, Authentication authentication) {

        return Mono.just(openerApiClient.getExchangeRate(exchangeRequest.getMonedaOrigen()))
                .flatMap(exchangeOpenerResponse -> {

                    if (!Objects.equals(exchangeOpenerResponse.result, "success")) {
                        return Mono.error(new RuntimeException("No se pudo encontrar tasa de cambio con la moneda solicitada"));
                    }

                    BigDecimal exchangeRate = Optional.ofNullable(exchangeOpenerResponse.getRates().get(exchangeRequest.getMonedaDestino()))
                            .orElseThrow(() -> new RuntimeException("No se pudo encontrar tasa de cambio con la moneda de origen solicitada"));

                    BigDecimal amountExchange = exchangeRate.multiply(exchangeRequest.getMonto());

                    ExchangeResponse exchangeResponse = new ExchangeResponse();
                    exchangeResponse.setTipoCambio(exchangeRate);
                    exchangeResponse.setMonto(exchangeRequest.getMonto());
                    exchangeResponse.setMonedaDestino(exchangeRequest.getMonedaDestino());
                    exchangeResponse.setMonedaOrigen(exchangeRequest.getMonedaOrigen());
                    exchangeResponse.setMontoTipoCambio(amountExchange);

                    Exchange exchange = new Exchange(exchangeResponse);
                    exchange.setCreatedAt(LocalDateTime.now());
                    exchange.setCreatedBy(authentication.getName());

                    return exchangeRepository.save(exchange).map(entitySaved -> ResponseEntity.ok().body(exchangeResponse));

                });

    }

    @Override
    public Mono<ResponseEntity<?>> getMyExchanges(Authentication userDetails) {
        return exchangeRepository.findByUsername(userDetails.getName())
                .map(exchange -> {
                    ExchangeResponse exchangeResponse = new ExchangeResponse();
                    exchangeResponse.setTipoCambio(exchange.getExchangeRate());
                    exchangeResponse.setMonto(exchange.getAmount());
                    exchangeResponse.setMonedaDestino(exchange.getCurrencyTarget());
                    exchangeResponse.setMonedaOrigen(exchange.getCurrencyOrigin());
                    exchangeResponse.setMontoTipoCambio(exchange.getExchangeAmount());

                    return exchangeResponse;
                })
                .collectList()
                .map(exchanges -> ResponseEntity.ok().body(exchanges));
    }

    @Override
    public Mono<ResponseEntity<?>> getAllExchanges() {
        return exchangeRepository.findAll()
                .map(exchange -> {
                    ExchangeResponse exchangeResponse = new ExchangeResponse();
                    exchangeResponse.setTipoCambio(exchange.getExchangeRate());
                    exchangeResponse.setMonto(exchange.getAmount());
                    exchangeResponse.setMonedaDestino(exchange.getCurrencyTarget());
                    exchangeResponse.setMonedaOrigen(exchange.getCurrencyOrigin());
                    exchangeResponse.setMontoTipoCambio(exchange.getExchangeAmount());

                    return exchangeResponse;
                })
                .collectList()
                .map(exchanges -> ResponseEntity.ok().body(exchanges));
    }

}
