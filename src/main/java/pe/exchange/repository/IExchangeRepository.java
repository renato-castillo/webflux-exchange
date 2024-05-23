package pe.exchange.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import pe.exchange.domain.Exchange;
import pe.exchange.repository.generic.IGenericRepo;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Repository
public interface IExchangeRepository extends IGenericRepo<Exchange, Long> {

    @Query(value = "SELECT e.* FROM Exchanges e where e.created_by = :username order by created_at desc")
    Flux<Exchange> findByUsername(String username);


}
