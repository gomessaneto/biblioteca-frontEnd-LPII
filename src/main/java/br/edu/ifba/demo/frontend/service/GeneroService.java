package br.edu.ifba.demo.frontend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.edu.ifba.demo.frontend.dto.GeneroDTO;
import reactor.core.publisher.Mono;

@Service
public class GeneroService {
    @Autowired
    private WebClient webClient;

    public GeneroService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build(); // URL do backend
    }

    public List<GeneroDTO> listAllGeneros() {
        Mono<List<GeneroDTO>> listObj = this.webClient
                .method(HttpMethod.GET)
                .uri("/Genero/listall")
                .retrieve()
                .bodyToFlux(GeneroDTO.class)
                .collectList();

        List<GeneroDTO> list = listObj.block();
        return list;
    }

    public boolean delete(Long id_genero) {
        Mono<GeneroDTO> obj = this.webClient
                .method(HttpMethod.DELETE)
                .uri("/Genero/deleteGenero/{id_genero}", id_genero)
                .retrieve()
                .bodyToMono(GeneroDTO.class);

        GeneroDTO usu = obj.block();
        if (usu != null) {
            return true;
        }
        return false;
    }

    public boolean salvarOuAtualizar(GeneroDTO generoDTO) {
        Mono<GeneroDTO> obj = this.webClient
                .method(HttpMethod.POST)
                .uri("/Genero", generoDTO)
                .bodyValue(generoDTO)
                .retrieve()
                .bodyToMono(GeneroDTO.class);

        GeneroDTO livro = obj.block();
        if (livro != null) {
            return true;
        }
        return false;
    }

    public GeneroDTO getById(Long id_genero) {
        Mono<GeneroDTO> monoObj = this.webClient
                .method(HttpMethod.GET)
                .uri("/Genero/getById/{id}", id_genero) // Path variable
                .retrieve()
                .bodyToMono(GeneroDTO.class); // Retorna um Mono<LivroDTO>

        // Converte a chamada reativa em chamada bloqueante (sincronamente):
        GeneroDTO genero = monoObj.block();
        return genero;
    }

}
