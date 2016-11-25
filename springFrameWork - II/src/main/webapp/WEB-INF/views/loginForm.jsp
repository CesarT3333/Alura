<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Produtos</title>
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath }/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath }/css/bootstrap-theme.min.css" />
</head>
<body>

	<div class="container">
		<h1>Login</h1>
		<form:form servletRelativeAction="/login" method="POST">
			<div class="form-group">
				<label>Email</label> <input name="username" type="text"
					cssClass="form-control" />
			</div>

			<div class="form-group">
				<label>senha</label> <input name="password" type="password"
					cssClass="form-control" />
			</div>

			<button type="submit" class="btn btn-primary">Logar</button>

		</form:form>

	</div>
</body>
</html>