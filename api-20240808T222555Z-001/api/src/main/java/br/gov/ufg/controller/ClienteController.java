package br.gov.ufg.controller;

import br.gov.ufg.dto.ClienteDTO;
import br.gov.ufg.dto.ProdutoDTO;
import br.gov.ufg.entity.Cliente;
import br.gov.ufg.entity.Produto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class ClienteController {

    @GetMapping("/listarClientes")
    public List<Cliente> listar() {

        List<Cliente> clientes = new ArrayList<>();

        try {
            clientes = ClienteDTO.lerClientesDoArquivo();
        } catch (URISyntaxException | IOException e) {
            System.out.println("Não foi possível abrir o arquivo de clientes: " + e);
            throw new RuntimeException("Não foi possível abrir o arquivo de clientes");
        }
        return clientes;
    }



}
