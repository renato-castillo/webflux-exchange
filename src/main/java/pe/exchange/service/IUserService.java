package pe.exchange.service;

import pe.exchange.domain.User;
import pe.exchange.util.ICRUD;
import reactor.core.publisher.Mono;

public interface IUserService extends ICRUD<User, Long> {

    Mono<User> saveHash(User user);
    Mono<User> searchByUser(String user);

}
