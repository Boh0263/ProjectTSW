<%@ page import="java.util.List" %>
<%
 String[] customStyles = (String[]) request.getAttribute("custom_styles");
    if(customStyles != null) {
    	for(String style : customStyles) {
    		//rimuovi il punto iniziale.
    		style = style.substring(1);
    		%> <link rel="stylesheet" href="${pageContext.request.contextPath}<%=style%>"> <%
    	}
    	 }
%>