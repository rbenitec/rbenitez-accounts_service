package service.accounts.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import service.accounts.entities.Account;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String> {

}
