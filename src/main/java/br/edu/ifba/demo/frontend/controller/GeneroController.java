package br.edu.ifba.demo.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifba.demo.frontend.dto.GeneroDTO;
import br.edu.ifba.demo.frontend.service.GeneroService;

@Controller
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ModelAndView listarGeneros() {
        ModelAndView mv = new ModelAndView("generos/indexGenero");
        mv.addObject("generos", generoService.listAllGeneros());
        return mv;
    }

    @PostMapping
    public ModelAndView salvarGenero(@ModelAttribute("genero") GeneroDTO generoDTO) {
        generoService.salvarOuAtualizar(generoDTO);
        return new ModelAndView("redirect:/generos");
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluirGenero(@PathVariable("id") Long id) {
        generoService.delete(id);
        return new ModelAndView("redirect:/generos");
    }

    @GetMapping("/novo")
    public ModelAndView novoGenero() {
        ModelAndView mv = new ModelAndView("generos/form");
        mv.addObject("genero", new GeneroDTO());
        return mv;
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView visualizarGenero(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("generos/form");
        mv.addObject("genero", generoService.getById(id));
        mv.addObject("modoVisualizacao", true);
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editarGenero(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("generos/form");
        mv.addObject("genero", generoService.getById(id));
        mv.addObject("modoVisualizacao", false);
        return mv;
    }
}
