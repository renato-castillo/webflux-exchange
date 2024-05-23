package pe.exchange.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import pe.exchange.model.response.ExchangeResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "exchanges")
@NoArgsConstructor
@AllArgsConstructor
public class Exchange  {

    @Id
    @Column(value = "exchange_id")
    private Long exchangeId;

    @Column(value = "currency_origin")
    private String currencyOrigin;

    @Column(value = "currency_target")
    private String currencyTarget;

    @Column(value = "amount")
    private BigDecimal amount;

    @Column(value = "exchange_amount")
    private BigDecimal exchangeAmount;

    @Column(value = "exchange_rate")
    private BigDecimal exchangeRate;

    @Column(value = "created_at")
    private LocalDateTime createdAt;

    @Column(value = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(value = "created_by")
    private String createdBy;

    @Column(value = "modified_by")
    private String modifiedBy;

    public Exchange(ExchangeResponse exchangeResponse) {
        this.currencyOrigin = exchangeResponse.getMonedaOrigen();
        this.currencyTarget = exchangeResponse.getMonedaDestino();
        this.amount = exchangeResponse.getMonto();
        this.exchangeAmount = exchangeResponse.getMontoTipoCambio();
        this.exchangeRate = exchangeResponse.getTipoCambio();
    }


}
