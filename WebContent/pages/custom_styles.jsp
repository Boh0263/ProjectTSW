<%@ page import="java.util.List" %>
<%
 String[] customStyles = (String[]) request.getAttribute("custom_styles");
    if(customStyles != null) {
    	for(String style : customStyles) {
    		%> <link rel="stylesheet" href="<%=style%>"> <%
    	}
    	 }
%>