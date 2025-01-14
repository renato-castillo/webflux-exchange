package pe.exchange.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.exchange.domain.User;
import pe.exchange.repository.IUserRepository;
import pe.exchange.repository.generic.IGenericRepo;
import pe.exchange.service.IUserService;
import pe.exchange.util.impl.CRUDImpl;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl extends CRUDImpl<User, Long> implements IUserService {

    @Autowired
    private IUserRepository repo;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Override
    protected IGenericRepo<User, Long> getRepo() {
        return repo;
    }

    @Override
    public Mono<User> searchByUser(String username) {
        Mono<User> monoUser = repo.findByUsername(username);

        return monoUser.flatMap(u -> Mono.just(new User(u.getId(), u.getUsername(), u.getPassword(), u.getRol())));
    }

    @Override
    public Mono<User> saveHash(User user) {
        user.setPassword(bcrypt.encode(user.getPassword()));
        return repo.save(user);
    }
}
