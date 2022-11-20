<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Add New Post</h1>
<form action="addpost.jsp" method="post" enctype="multipart/form-data">
<table>
<tr><td>Name:</td><td><input type="text" name="name"/></td></tr>
<tr><td>Address:</td><td><input type="text" name="address"/></td></tr>
<tr><td>Food:</td><td><input type="text" name="foodName"/></td></tr>
<tr><td>Flavor:</td><td><input type="text" name="flavor"/></td></tr>
<tr><td>TakeOut:</td><td><input type="number" name="takeOut"/></td></tr>
<tr><td>Price:</td><td><input type="number" name="price"/></td></tr>
<tr><td>Photo:</td><td><input type="file" name="photo"/></td></tr>
<tr><td>Request:</td><td><textarea cols="50" rows="5" name="request"></textarea></td></tr>
<tr><td><a href="posts.jsp">View All Records</a></td><td align="right"><input type="submit" value="Add Post"/></td></tr>
</table>
</form>

</body>
</html>