package service.accounts.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import service.accounts.model.AccountDto;
import service.accounts.service.AccountsService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {


    private final AccountsService iclientService;


    @GetMapping("/getClient")   // Mostrar todos los clientes
    public Flux<AccountDto> getClient(){

        return iclientService.findAll();
    }



    @GetMapping("/getClientByAge/{age}")  //--> Encontrar cliente por la edad
    public Flux<AccountDto> getClientByAge(@PathVariable("age") int age){

        return iclientService.findClientByAge(age);
    }



    @PostMapping("/postClient")  //--> Ingresar datos del cliente
    Mono<AccountDto> postClient(@RequestBody AccountDto client){
        return iclientService.save(client);
    }

    @PostMapping("/updateClient")  //--> Actualizar datos del cliente
    Mono<AccountDto> updClient(@RequestBody AccountDto client){
        return iclientService.update(client);
    }

    @GetMapping("/deleteClient/{id}")
    Mono<Void> deleteClient(@PathVariable("id") String id){
        return iclientService.delete(id);
    }

    @PostMapping("/deleteClient")
    Flux<AccountDto> deleteClient(@PathVariable("id") String id){
        return iclientService.delete(id);
    }


}
