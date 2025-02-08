package br.edu.ifba.demo.frontend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifba.demo.frontend.dto.GeneroDTO;
import br.edu.ifba.demo.frontend.dto.LivroDTO;
import br.edu.ifba.demo.frontend.service.LivroService;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/")
    public ModelAndView index() {
        var obj = livroService.listAllLivros();
        ModelAndView mv = new ModelAndView();
        mv.addObject("livros", obj);
        mv.setViewName("index");
        return mv;
    }

    @PostMapping("/")
    public ModelAndView salvarLivro(@ModelAttribute("livro") LivroDTO livroDTO) {
        livroService.salvarOuAtualizar(livroDTO);
        return new ModelAndView("redirect:/livros/");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        livroService.delete(id);
        return new ModelAndView("redirect:/livros/");
    }

    @GetMapping("/novo")
    public ModelAndView novoLivro() {
        List<GeneroDTO> generos = livroService.listAllGeneros();
        ModelAndView mv = new ModelAndView("livros/form");
        mv.addObject("livro", new LivroDTO());
        mv.addObject("generos", generos);
        return mv;
    }

    @GetMapping("/view/{id}")
    public ModelAndView exibirLivro(@PathVariable("id") Long id) {
        LivroDTO livro = livroService.getById(id);
        List<GeneroDTO> generos = livroService.listAllGeneros();
        ModelAndView mv = new ModelAndView("livros/form");
        mv.addObject("livro", livro);
        mv.addObject("generos", generos);
        mv.addObject("view", true);
        return mv;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editarLivro(@PathVariable("id") Long id) {
        LivroDTO livro = livroService.getById(id);
        List<GeneroDTO> generos = livroService.listAllGeneros();
        ModelAndView mv = new ModelAndView("livros/form");
        mv.addObject("livro", livro);
        mv.addObject("generos", generos);
        mv.addObject("view", false);
        return mv;
    }
}
