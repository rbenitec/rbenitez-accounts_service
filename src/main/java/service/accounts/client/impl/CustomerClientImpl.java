package service.accounts.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import service.accounts.client.CustomerClientConnector;
import service.accounts.client.dto.ResponseCustomerDto;
import service.accounts.rest.HttpInterface;

@Service
@RequiredArgsConstructor
public class CustomerClientImpl implements CustomerClientConnector {

    private final HttpInterface httpInterface;

    @Value("${spring.config.client.customer.url}")
    private String url;

    @Override
    public Mono<ResponseCustomerDto> getCustomerById(String customerId) {
        return httpInterface.getHttpMono(url.concat("/").concat(customerId), ResponseCustomerDto.class);
    }
}
