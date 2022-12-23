package com.springboot.service;

import com.springboot.model.Cliente;
import com.springboot.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

//    @Autowired
    private ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    private ClienteRepository clienteRepository;

    public void salvar(Cliente cliente){
        validar(cliente);
        this.clienteRepository.save(cliente);

    }

    public void validar(Cliente cliente){

    }
}
