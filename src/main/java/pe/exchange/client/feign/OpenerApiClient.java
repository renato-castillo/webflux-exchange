package pe.exchange.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.exchange.model.client.opener.OpenerExchange;

@FeignClient(name = "openerApiClient", url = "https://open.er-api.com/v6")
public interface OpenerApiClient {

    @GetMapping("/latest/{currency}")
    OpenerExchange getExchangeRate(@PathVariable("currency") String currency);
}
