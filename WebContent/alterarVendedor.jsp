<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alterar Filme</title>
<script language="javascript">
			function masc_nome(objeto){
				
			}

			function masc_idade(objeto){
				var numero="";
				var padrao=/\D/g;
				
				numero = objeto.value.replace(padrao, "");
				objeto.value=numero;
			}

			function masc_cep(objeto) {
				var retorno="";
				var numero;
				var padrao=/\D/g;
				
				numero = objeto.value.replace(padrao, "");
				parte1=numero.substr(0,2);
				if (parte1.length>0) {
					retorno=parte1;
				}
				
				parte2=numero.substr(2,3);
				if (parte2.length>0) {
					retorno += "." + parte2;
				}

				parte3=numero.substr(5,3);
				if (parte3.length>0) {
					retorno += "-" + parte3;
				}

				objeto.value=retorno;
			}
			
			function masc_cpf(objeto) {
				var retorno="";
				var numero;
				var padrao=/\D/g;
				
				numero = objeto.value.replace(padrao, "");
				parte1=numero.substr(0,3);
				if (parte1.length>0) {
					retorno=parte1;
				}
				
				parte2=numero.substr(3,3);
				if (parte2.length>0) {
					retorno += "." + parte2;
				}

				parte3=numero.substr(6,3);
				if (parte3.length>0) {
					retorno += "." + parte3;
				}

				parte4=numero.substr(9,2);
				if (parte4.length>0) {
					retorno += "-" + parte4;
				}
				objeto.value=retorno;
			}
		</script>
</head>
<body>
	<f:view>
		<jsp:include page="/menu.jsp" />
		<h:form>
			<h1>Alterar Vendedor</h1>
			<h:panelGrid columns="1">
				<h:messages />
			</h:panelGrid>
			<h:panelGrid columns="2">
				<h:outputText value="Nome: " />
				<h:inputText value="#{vendedorBacking.nome}" maxlength="40"
					size="40"></h:inputText>

				<h:outputText value="Idade: " />
				<h:inputText value="#{vendedorBacking.idade}" maxlength="3"
					size="10" onkeyup="masc_idade(this);"></h:inputText>

				<h:outputText value=" CEP: " />
				<h:inputText value="#{vendedorBacking.cep}" maxlength="10" size="20"
					onkeyup="masc_cep(this);"></h:inputText>

				<h:outputText value=" C.P.F: " />
				<h:inputText value="#{vendedorBacking.cpf}" maxlength="14" size="20"
					onkeyup="masc_cpf(this);"></h:inputText>

				<h:commandButton value="Alterar"
					action="#{vendedorBacking.alterar}"></h:commandButton>
				<h:commandButton value="Cancelar"
					action="#{vendedorBacking.cancelar}"></h:commandButton>
			</h:panelGrid>
		</h:form>
	</f:view>
</body>
</html>