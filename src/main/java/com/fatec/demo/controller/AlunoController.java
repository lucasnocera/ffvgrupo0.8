package com.fatec.demo.controller;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.demo.model.*;


@RestController
@RequestMapping(path = "/alunos")
public class AlunoController {
//insert into aluno values ('1', 'Lucas','1111111111111' )
	@Autowired
	private AlunoRepository repository;

	@GetMapping("/consulta")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("ConsultarAluno");
		modelAndView.addObject("alunos", repository.findAll());
		return modelAndView;
	}

	/**
	 * quando o usuario digita localhost:8080/api/add
	 *
	 * @param aluno
	 * @return o html /CadastrarUsuario
	 */
	@GetMapping("/cadastrar")
	public ModelAndView cadastraAluno(Aluno aluno) {
		ModelAndView mv = new ModelAndView("CadastrarAluno");
		mv.addObject("alunos", aluno);
		return mv;
	}

	@GetMapping("/edit/{ra}") // diz ao metodo que ira responder a uma requisicao do tipo get
	public ModelAndView mostraFormAdd(@PathVariable("ra") String ra) {
		ModelAndView modelAndView = new ModelAndView("AtualizaAluno");
		modelAndView.addObject("aluno", repository.findByRa(ra)); // o repositorio e injetado no controller
		return modelAndView; // addObject adiciona objetos para view
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		repository.deleteById(id);
		ModelAndView modelAndView = new ModelAndView("ConsultarAluno");
		modelAndView.addObject("alunos", repository.findAll());
		return modelAndView;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Aluno aluno, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("ConsultarAluno");
		if (result.hasErrors()) {
			return new ModelAndView("CadastrarAluno");
		}
		try {
			Aluno jaExiste = null;
			jaExiste = repository.findByRa(aluno.getRa());
			if (jaExiste == null) {
				repository.save(aluno);
				modelAndView = new ModelAndView("ConsultarAluno");
				modelAndView.addObject("alunos", repository.findAll());
				return modelAndView;
			} else {
				return new ModelAndView("CadastrarAluno");
			}
		} catch (Exception e) {
			System.out.println("erro ===> " + e.getMessage());
			return modelAndView; // captura o erro mas nao informa o motivo.
		}
	}

	@PostMapping("/update/{id}")
	public ModelAndView atualiza(@PathVariable("id") Long id, @Valid Aluno aluno, BindingResult result) {
		if (result.hasErrors()) {
			aluno.setId(id);
			return new ModelAndView("AtualizaAluno");
		}
		Aluno umAluno = repository.findById(id).get();
		umAluno.setNome(aluno.getNome());
		umAluno.setId(aluno.getId());
		umAluno.setRa(aluno.getRa());
		umAluno.setCep(aluno.getCep());
		repository.save(umAluno);
		ModelAndView modelAndView = new ModelAndView("ConsultarAluno");
		modelAndView.addObject("alunos", repository.findAll());
		return modelAndView;
	}
}