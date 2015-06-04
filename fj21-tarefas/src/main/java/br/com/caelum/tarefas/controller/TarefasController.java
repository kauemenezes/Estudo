package br.com.caelum.tarefas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.bean.Tarefa;
import br.com.caelum.tarefas.dao.JdbcTarefaDao;
import br.com.caelum.tarefas.dao.TarefaDao;

@Transactional
@Controller
public class TarefasController {
	
	@Autowired
	@Qualifier("jpaTarefaDao")
	private TarefaDao dao;
	
	@RequestMapping("novaTarefa")
	public String form() {
		return "tarefa/formulario";
	}

	@RequestMapping("adicionarTarefa")
	public String adicionar(@Valid Tarefa tarefa, BindingResult result) {

		if (result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}

		dao.adicionar(tarefa);
		return "tarefa/adicionada";
	}

	@RequestMapping("listarTarefas")
	public String listar(Model model) {

		model.addAttribute("tarefas", dao.getLista());

		return "tarefa/lista";
	}

	@RequestMapping("removerTarefa")
	public String remover(Tarefa tarefa) {
		dao.remover(tarefa);
		return "redirect:listarTarefas";
	}

	@RequestMapping("mostrarTarefa")
	public String mostrar(Long id, Model model) {
		model.addAttribute("tarefa", dao.buscarPorId(id));
		return "tarefa/mostrar";
	}

	@RequestMapping("alterarTarefa")
	public String alterar(Tarefa tarefa) {
		dao.alterar(tarefa);
		return "redirect:listaTarefas";
	}
	
	@RequestMapping("finalizarTarefa")
	public String finalizar(Long id, Model model){
		dao.finalizar(id);
		model.addAttribute("tarefa", dao.buscarPorId(id));
		return "tarefa/finalizada";
	}

}
