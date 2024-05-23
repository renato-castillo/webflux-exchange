package pe.exchange.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExchangeResponse {

    private BigDecimal monto;

    private BigDecimal montoTipoCambio;

    private String monedaOrigen;

    private String monedaDestino;

    private BigDecimal tipoCambio;

}
