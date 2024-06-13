<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Website</title>
 <script type="importmap">
    {
		"imports": {
		"three": "https://cdn.jsdelivr.net/npm/three@0.163.0/build/three.module.js",
		"orbitcontrols": "https://cdn.jsdelivr.net/npm/three@0.163.0/examples/jsm/controls/OrbitControls.js",
		"gltfloader": "https://cdn.jsdelivr.net/npm/three@0.163.0/examples/jsm/loaders/GLTFLoader.js"
				   }
	}
    </script>
     <style>
     body {
        margin: 0;
     }
     </style>
   <!-- <script type="module" resources="./js/scripts.js"></script>  commentato per prima task--> 
</head>
<body>
 <% 
 RequestDispatcher rd = request.getRequestDispatcher("pages/home.jsp");
 request.setAttribute("custom_styles", new String[] { "./resources/styles/main_styles.css","./resources/styles/responsive.css" });
 rd.forward(request, response);
 %>
</body>
</html>
