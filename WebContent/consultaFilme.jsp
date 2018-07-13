<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="rich" uri="http://richfaces.org/rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consulta de Filme</title>
</head>
<body>
		<f:view>
			<jsp:include page="/menu.jsp"></jsp:include>
			<h:form>
				<h:panelGrid columns="1">
					<h:messages />
				</h:panelGrid>

				<h:panelGrid columns="1">
					<h1>Consulta de Filmes</h1>
				</h:panelGrid>
	
				<h:panelGrid columns="3">
					<h:outputText value="Nome:" />
					<h:inputText value="#{filmeBacking.nome}" maxlength="45" size="40" />
					<h:commandButton value="Pesquisar Filme"
						action="#{filmeBacking.filmePesquisar}" />
				</h:panelGrid>
				<br>
				<h:panelGrid columns="1" width="700">
					<rich:dataTable id="listaFilme" value="#{filmeBacking.listaFilme}"
						var="filme" width="100%">
						<f:facet name="caption">
							<h:outputText value="" />
						</f:facet>
						<f:facet name="header">
							<rich:columnGroup>
								<rich:column>
									<h:outputText value="Código" />
								</rich:column>
								<rich:column>
									<h:outputText value="Nome" />
								</rich:column>
								<rich:column>
									<h:outputText value="Genero" />
								</rich:column>
								<rich:column colspan="2">
									<h:outputText value="Ações" />
								</rich:column>
	
							</rich:columnGroup>
						</f:facet>
						<rich:column>
							<h:outputText value="#{filme.codigo}" />
						</rich:column>
						<rich:column>
							<h:outputText value="#{filme.nome}" />
						</rich:column>
						<rich:column>
							<h:outputText value="#{filme.genero}" />
						</rich:column>
						<rich:column>
							<h:commandLink value="Alterar"
								action="#{filmeBacking.filmeAlterar}">
								<f:setPropertyActionListener value="#{filme}"
									target="#{filmeBacking.filmeSelecionado}" />
							</h:commandLink>
						</rich:column>
						<rich:column>
							<h:commandLink value="Excluir"
								action="#{filmeBacking.filmeExcluir}">
								<f:setPropertyActionListener value="#{filme}"
									target="#{filmeBacking.filmeSelecionado}" />
							</h:commandLink>
						</rich:column>
					</rich:dataTable>
				</h:panelGrid>
				<h:panelGrid columns="1">
					<h:commandButton value="GERAR RELATÓRIO" 
	                 				 action="#{relatorioBacking.geraRelatorio}" />
				</h:panelGrid>
			</h:form>
		</f:view>
</body>
</html>