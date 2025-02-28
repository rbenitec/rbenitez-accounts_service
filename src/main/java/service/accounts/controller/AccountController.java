package service.accounts.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import service.accounts.model.RequestAccountDto;
import service.accounts.model.ResponseAccountDto;
import service.accounts.model.ResponseDeleteDto;
import service.accounts.service.AccountsService;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountsService accountsService;

    /**
     * Operation by created Account.
     *
     * @param accountDto
     * @return ResponseCustomerDto
     */
    @PostMapping()
    Mono<ResponseEntity<ResponseAccountDto>> createdAccount(@RequestBody Mono<RequestAccountDto> accountDto) {
        return accountsService.saveAccount(accountDto)
                .map(ResponseEntity.status(HttpStatus.CREATED)::body);
    }

    /**
     * Operation for Account by id.
     *
     * @param accountId
     * @return
     */
    @GetMapping("/{accountId}")
    public Mono<ResponseEntity<ResponseAccountDto>> getAccountById(@PathVariable("accountId") String accountId) {
        return accountsService.findAccountById(accountId)
                .map(ResponseEntity.status(HttpStatus.OK)::body);
    }

    /**
     * Operation by update Account.
     *
     * @param accountId
     * @param accountDto
     * @return
     */
    @PutMapping("/{accountId}")
    Mono<ResponseEntity<ResponseAccountDto>> updateAccount(@PathVariable("accountId") String accountId,
                                                            @RequestBody Mono<RequestAccountDto> accountDto) {
        return accountsService.updateAccount(accountId, accountDto)
                .map(ResponseEntity.status(HttpStatus.OK)::body);
    }

    /**
     * Operation by delete Account.
     *
     * @param accountId
     * @return
     */
    @DeleteMapping("/{accountId}")
    Mono<ResponseEntity<ResponseDeleteDto>> deleteAccount(@PathVariable("accountId") String accountId) {
        return accountsService.deleteAccount(accountId)
                .map(ResponseEntity.status(HttpStatus.OK)::body);
    }

}
