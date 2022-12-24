package com.springboot;

import com.springboot.domain.entity.Cliente;
import com.springboot.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Autowired
    public VendasApplication(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    private final ClienteRepository clienteRepository;

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            clienteRepository.save(new Cliente("Lucca"));
            clienteRepository.save(new Cliente("Braz Barros"));

            System.out.println("RECUPERA TUDO");
            var todosClientes = clienteRepository.recuperar();
            todosClientes.forEach(System.out::println);
            System.out.println("");

            System.out.println("ATUALIZA TUDO");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " Atualizado");
                clienteRepository.update(c);
            });

            todosClientes = clienteRepository.recuperar();
            todosClientes.forEach(System.out::println);
            System.out.println("");

            System.out.println("RECUPERA POR NOME TUDO");
            var listaByName = clienteRepository.findByName(new Cliente("Lucca"));
            listaByName.forEach(System.out::println);
            System.out.println("");

            System.out.println("DELETE POR NOME");

            clienteRepository.delete(new Cliente("Lucca", 1));

            todosClientes = clienteRepository.recuperar();
            todosClientes.forEach(System.out::println);
            System.out.println("");
        };
    }

}
