package br.com.caelum.tarefas.dao;

import java.util.List;

import br.com.caelum.tarefas.bean.Tarefa;

public interface TarefaDao {
	
	Tarefa buscarPorId(Long id);
	List<Tarefa> getLista();
	void adicionar(Tarefa tarefa);
	void alterar(Tarefa tarefa);
	void remover(Tarefa tarefa);
	void finalizar(Long id);
}
