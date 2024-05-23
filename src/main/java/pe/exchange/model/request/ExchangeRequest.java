package pe.exchange.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeRequest {

    private BigDecimal monto;

    private String monedaOrigen;

    private String monedaDestino;

}
