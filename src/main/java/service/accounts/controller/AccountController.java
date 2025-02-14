package service.accounts.controller;


//@RestController
//@RequestMapping("/api")
//@RequiredArgsConstructor
public class AccountController {

    /*
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


    /*
    @PostMapping("/deleteClient")
    Flux<AccountDto> deleteClient(@PathVariable("id") String id){
        return iclientService.delete(id);
    }

     */
}
