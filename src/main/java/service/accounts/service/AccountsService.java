package service.accounts.service;


public interface AccountsService {
	Flux<AccountDto> findAll();
	Mono<AccountDto> save(AccountDto client);

	Flux<AccountDto> findClientByAge(int age);

	Mono<AccountDto> update(AccountDto client);

	Mono<Void> delete(String id);

}
