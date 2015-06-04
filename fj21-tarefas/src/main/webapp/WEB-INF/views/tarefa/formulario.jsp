<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jquery-ui.css" />" /> 
<script type="text/javascript" src="<c:url value="/resources/js/jquery.js" />"></script> 
<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.js" />"></script> 
<script>
  $(function() {
    $( "#dataFinalizacao" ).datepicker({
    	  dateFormat: "dd/mm/yy"
    });
  });
  </script>
<title>Insert title here</title>
</head>
<body>
	<h3>Adicionar tarefas</h3>
	<form:errors path="tarefa.descricao" cssStyle="color:red" />
	<form action="adicionarTarefa" method="post">
		Descrição: <br />
		<textarea name="descricao" rows="5" cols="100"></textarea><br />
		
		<p>Data: <input type="text" id="dataFinalizacao" name="dataFinalizacao"></p>
		
		<input type="submit" value="Adicionar">
		
	</form>
</body>
</html>