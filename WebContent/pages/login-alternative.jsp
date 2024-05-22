<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/registration_style.css">
</head>
<body>
    <div class="container">
      <form  id="form" action="./login" method="post" class="form">
        <h2>Login</h2>
        <div class="form-control">
          <label for="username">Username</label>
          <input type="text" id="username" placeholder="Inserisci l'username" />
          <small>Error message</small>
        </div>
        <div class="form-control">
          <label for="password">Password</label>
          <input type="password" id="password" placeholder="Inserisci la password" />
          <small>Error message</small>
        </div>
        <button type="submit" value="login" class="actionBtn">Login</button>
      </form>
    </div>

	<% if(request.getAttribute("Messaggio") != null) { %>
	<div class="container" style="">
		<div class="alert alert-danger" role="alert">
			<%=(String) request.getAttribute("Messaggio")%>
		</div>
	</div>
	<% } %>	
</body>
<script>
//handle form submit with ajax (if login fails, the page is not reloaded and the error message is displayed, else you're redirected by the foward attribute)

	
	$(document).ready(function() {
    $("#form").submit(function(e) {
        e.preventDefault();
        $.ajax({
                url: $(this).attr("action"),
                type: $(this).attr("method"),
                data: $(this).serialize(),
                success: function(response) {
                    if (response == "true") {
                    	//get the request attribute "forward" and redirect to that page
                    	//if the attribute is not set, redirect to the home page
                    	var forward = "${requestScope.forward}";
                    	if(forward != null){
                    		window.location.href = forward;
                    	} else {
                    		window.location.href = "./home.jsp"; 
                    	}
                    } else {
                    	
           
                        $(".alert").remove();
                        $(".container").prepend('<div class="alert alert-danger" role="alert">Credenziali errate</div>');
                    }
                }
            });
    });
});
</script>
</html>