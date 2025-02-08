package br.edu.ifba.demo.frontend.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class GeneroDTO implements Serializable {
    private Long id_genero;
    private String nomeGenero;
    private Boolean status;
    private List<LivroDTO> livros;
}
