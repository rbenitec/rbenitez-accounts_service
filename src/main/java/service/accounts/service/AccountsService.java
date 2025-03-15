package service.accounts.service;


import reactor.core.publisher.Mono;
import service.accounts.model.RequestAccountDto;
import service.accounts.model.RequestUpdateAccountDto;
import service.accounts.model.ResponseAccountDto;
import service.accounts.model.ResponseDeleteDto;

public interface AccountsService {
	//Mono<ResponseAccountDto> saveAccount(Mono<RequestAccountDto> customerDto);
	Mono<ResponseAccountDto> createdAccount(Mono<RequestAccountDto> customerDto);

	Mono<ResponseAccountDto> findAccountById(String accountId);

	Mono<ResponseAccountDto> updateAccount(String accountId, Mono<RequestUpdateAccountDto> customerDto);

	Mono<ResponseDeleteDto> deleteAccount(String accountId);

}
