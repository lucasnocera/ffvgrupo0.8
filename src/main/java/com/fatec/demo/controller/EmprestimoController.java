package com.fatec.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.demo.model.Aluno;
import com.fatec.demo.model.AlunoRepository;
import com.fatec.demo.model.Emprestimo;
import com.fatec.demo.model.EmprestimoRepository;
import com.fatec.demo.model.Livro;
import com.fatec.demo.model.LivroRepository;
import com.fatec.demo.model.Servico;

@RestController
@RequestMapping(path = "/emprestimos")
public class EmprestimoController {
	@Autowired
	private EmprestimoRepository emprestimoRepository;
	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private AlunoRepository alunoRepository;

	/**
	 * quando o usuario digita localhost:8080/emprestimo/cadastrar
	 *
	 * @param emprestimo
	 * @return o html /RegistrarEmprestimo
	 */
	@GetMapping("/cadastrar")
	public ModelAndView registrarEmprestimo(Emprestimo emprestimo) {
		ModelAndView mv = new ModelAndView("RegistrarEmprestimo");
		mv.addObject("emprestimos", emprestimo);
		return mv;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		emprestimoRepository.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("ConsultarEmprestimo");
		modelAndView.addObject("emprestimos", emprestimoRepository.findAll());
		return modelAndView;
	}
	
	@GetMapping("/consulta")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("ConsultarEmprestimo");
		modelAndView.addObject("emprestimos", emprestimoRepository.findAll());
		return modelAndView;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Emprestimo emprestimo, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("ConsultarEmprestimo");
		if (result.hasErrors()) {
			return new ModelAndView("RegistrarEmprestimo");
		}
		try {
			Livro livro = null;
			Aluno aluno = null;
			livro = livroRepository.findByIsbn(emprestimo.getIsbn());
			aluno = alunoRepository.findByRa(emprestimo.getRa());
			if (livro != null && aluno != null) {
				emprestimo.setDataEmprestimo();
				emprestimoRepository.save(emprestimo);
				modelAndView = new ModelAndView("ConsultarEmprestimo");
				modelAndView.addObject("emprestimos", emprestimoRepository.findAll());
				return modelAndView;
			} else {
				return new ModelAndView("RegistrarEmprestimo");
			}
		} catch (Exception e) {
			System.out.println("erro ===> " + e.getMessage());
			return modelAndView; // captura o erro mas nao informa o motivo.
		}
	}

}