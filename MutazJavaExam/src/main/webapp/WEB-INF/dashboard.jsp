<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Home page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<style>
body {
	margin: 5%
}

.full-width-btn {
	width: 100%;
}
</style>
</head>

<body>

	<div class="container-fluid">
		<h1>Welcome ${userName}!</h1>
		<a href="/logout">Log Out</a>
		<form action="/Celebrities/new" method="get">
			<button type="submit" class="btn btn-primary full-width-btn">Add
				Celebrity Page</button>
		</form>
 <ul class="list-unstyled">
        <c:forEach var="celebrity" items="${celebrities}">
            <li>
                <h2>
                    <a href="/celebrities/${celebrity.id}">${celebrity.name}</a>
                </h2>
                <p>Page Manager :: ${celebrity.user.userName}</p>
                <p style ="color: blue;">Followers: </p>
            </li>
        </c:forEach>
    </ul>
</div>

</body>

</html>
