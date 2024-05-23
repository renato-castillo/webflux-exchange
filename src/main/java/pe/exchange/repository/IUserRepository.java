package pe.exchange.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.stereotype.Repository;
import pe.exchange.repository.generic.IGenericRepo;
import pe.exchange.domain.User;
import reactor.core.publisher.Mono;

@Repository
public interface IUserRepository extends IGenericRepo<User, Long> {

    @Query("SELECT u.* FROM users u where u.username = :username")
    Mono<User> findByUsername(String username);

}
