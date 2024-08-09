package br.gov.ufg.dto;

import br.gov.ufg.api.Main;
import br.gov.ufg.entity.Cliente;
import br.gov.ufg.entity.PessoaFisica;
import br.gov.ufg.entity.PessoaJuridica;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClienteDTO {

    private static final String CAMINHO_ARQUIVO = "database/Clientes.csv";

    public static List<Cliente> lerClientesDoArquivo() throws IOException, URISyntaxException {

        // Tentar obter o caminho do arquivo como um recurso
        java.net.URL resource = Main.class.getClassLoader().getResource(CAMINHO_ARQUIVO);

        // Converter URL para URI e obter o caminho absoluto
        java.nio.file.Path caminhoArquivoAbsoluto = Paths.get(resource.toURI());

        if (resource != null) {

            return Files.lines(caminhoArquivoAbsoluto)
                    .map(line -> {
                        String[] prod = line.split(",");
                        return new Cliente(Integer.parseInt(prod[0]), prod[1], prod[2], prod[3], prod[4], prod[5], prod[6]);
                    })
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

}

