package service.accounts.client;


import reactor.core.publisher.Mono;
import service.accounts.client.dto.ResponseCustomerDto;

public interface CustomerClientConnector {
    Mono<ResponseCustomerDto> getCustomerById(String customerId);
    //Mono<CreditServiceResponseDto> updateCreditBalanceByProductId(String productId, UpdateCreditRequestDto updateCreditRequestDto);

    //Flux<CreditServiceResponseDto> getCreditTransactionsByProductId(String productId);
}
