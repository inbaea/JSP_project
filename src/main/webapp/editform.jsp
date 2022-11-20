<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="com.crud.dao.BoardDAO, com.crud.bean.BoardVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Form</title>
</head>
<body>

<%
	BoardDAO boardDAO = new BoardDAO();
	String id=request.getParameter("id");	
	BoardVO u=boardDAO.getBoard(Integer.parseInt(id));
%>

<h1>Edit Form</h1>
<form action="editpost.jsp" method="post">
<input type="hidden" name="id" value="<%=u.getId() %>"/>
<table>
<tr><td>Name:</td><td><input type="text" name="name"/></td></tr>
<tr><td>Address:</td><td><input type="text" name="address"/></td></tr>
<tr><td>Food:</td><td><input type="text" name="foodName"/></td></tr>
<tr><td>Flavor:</td><td><input type="text" name="flavor"/></td></tr>
<tr><td>TakeOut:</td><td><input type="number" name="takeOut"/></td></tr>
<tr><td>Price:</td><td><input type="number" name="price"/></td></tr>
<tr><td>Request:</td><td><textarea cols="50" rows="5" name="request"></textarea></td></tr>
<tr><td colspan="2"><input type="submit" value="Edit Post"/>
<input type="button" value="Cancel" onclick="history.back()"/></td></tr>
</table>
</form>

</body>
</html>