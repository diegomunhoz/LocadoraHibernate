<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro de Filme</title>
</head>
<body>
	<f:view>
		<jsp:include page="/menu.jsp" />
		<h:form>
			<h1>Cadastrar Filme</h1>
			<h:panelGrid columns="1">
				<h:messages />
			</h:panelGrid>
			<h:panelGrid columns="2">
				<h:outputText value="* Nome: " />
				<h:inputText value="#{filmeBacking.nome}" maxlength="40" size="40"></h:inputText>

				<h:outputText value="* Valor: " />
				<h:inputText value="#{filmeBacking.valor}" maxlength="8" size="15"></h:inputText>

				<h:outputText value=" Genero: " />
				<h:inputText value="#{filmeBacking.genero}" maxlength="40" size="40"></h:inputText>

				<h:outputText value="Disponível: " />
				<h:selectOneRadio value="#{filmeBacking.disponivel}">
					<f:selectItem itemValue="S" itemLabel="Sim" />
					<f:selectItem itemValue="N" itemLabel="Não" />
				</h:selectOneRadio>

				<h:commandButton value="Cadastrar" action="#{filmeBacking.salvar}"></h:commandButton>
				<h:commandButton value="Cancelar" action="#{filmeBacking.cancelar}"></h:commandButton>
			</h:panelGrid>
			<p:breadcrumb item="Cadastro Filme"></p:breadcrumb>
			<a href="index.jsp">Voltar</a>
		</h:form>
	</f:view>
</body>
</html>