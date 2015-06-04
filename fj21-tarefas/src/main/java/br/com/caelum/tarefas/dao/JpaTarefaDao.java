package br.com.caelum.tarefas.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.bean.Tarefa;

@Repository
public class JpaTarefaDao implements TarefaDao {
	
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public Tarefa buscarPorId(Long id) {
		return manager.find(Tarefa.class, id);
	}

	@Override
	public List<Tarefa> getLista() {
		return manager.createQuery("select t from Tarefa t").getResultList();
	}

	@Override
	public void adicionar(Tarefa tarefa) {
		manager.persist(tarefa);
		
	}

	@Override
	public void alterar(Tarefa tarefa) {
		manager.merge(tarefa);
		
	}

	@Override
	public void remover(Tarefa tarefa) {
		Tarefa tarefaARemover = buscarPorId(tarefa.getId());
		manager.remove(tarefaARemover);
		
	}

	@Override
	public void finalizar(Long id) {
		Tarefa tarefa = buscarPorId(id);
		tarefa.setFinalizado(true);
		tarefa.setDataFinalizacao(Calendar.getInstance());
		alterar(tarefa);
	}

}
