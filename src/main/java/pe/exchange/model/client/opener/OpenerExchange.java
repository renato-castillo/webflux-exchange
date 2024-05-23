package pe.exchange.model.client.opener;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class OpenerExchange {

    public String result;
    public String provider;
    public String documentation;

    @JsonProperty("terms_of_use")
    public String termsOfUse;

    @JsonProperty("time_last_update_unix")
    public int timeLastUpdateUnix;

    @JsonProperty("time_last_update_utc")
    public String timeLastUpdateUtc;

    @JsonProperty("time_next_update_unix")
    public int timeNextUpdateUnix;

    @JsonProperty("time_next_update_utc")
    public String timeNextUpdateUtc;

    @JsonProperty("time_eol_unix")
    public int timeEolUnix;

    @JsonProperty("base_code")
    public String baseCode;

    public Map<String, BigDecimal> rates;

}
