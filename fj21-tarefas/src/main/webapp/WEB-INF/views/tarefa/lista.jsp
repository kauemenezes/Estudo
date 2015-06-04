<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="resources/js/jquery.js"></script>
<title>Lista Tarefas</title>
</head>
<body>

	<script type="text/javascript">
		function finalizarAgora(id) {
			$.post("finalizarTarefa", {'id' : id}, function(resposta) {
				// selecionando o elemento html através da
				// ID e altera o HTML dele
				$("#tarefa_"+id).html(resposta);
			});
		}
    </script>

	<a href="novaTarefa">Criar nova tarefa</a>
	<br />
	<br />
	<table>
		<tr>
			<th>Id</th>
			<th>Descrição</th>
			<th>Finalizado?</th>
			<th>Data de finalização</th>
		</tr>
		<c:forEach items="${tarefas}" var="tarefa">
			<tr id="tarefa_${tarefa.id}">
				<td>${tarefa.id}</td>
				<td>${tarefa.descricao}</td>
				<c:if test="${tarefa.finalizado eq false}">
					<td><a href="#"
						onClick="finalizarAgora(${tarefa.id})"> Finalizar agora! </a></td>
				</c:if>
				<c:if test="${tarefa.finalizado eq true}">
					<td>Finalizado</td>
				</c:if>
				<td><fmt:formatDate value="${tarefa.dataFinalizacao.time}"
						pattern="dd/MM/yyyy" /></td>
				<td><a href="removerTarefa?id=${tarefa.id}">Remover</a></td>
				<td><a href="mostrarTarefa?id=${tarefa.id}">Alterar</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>