<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>




<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>${celebrity.name}Details</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

</head>
<body>

	<h1>${celebrity.name}</h1>
	<a href="/dashboard">home</a>
	<p>Page Manager :: ${celebrity.user.userName}</p>
	<p>Contact :  ${user.email}</p>
	<p>Details   :   ${celebrity.details}</p>

	<form:form action="/celebrities/${celebrity.id}/edit" method="get">
					<button type="submit" class="btn btn-primary">Edit Celebrity</button>
				</form:form>
	
	<c:if test="${celebrity.user.id.equals(user.id)}">
		<div class="mt-2 d-flex justify-content-center">
			<div class="col-auto  ">
				<form:form action="/celebrities/${celebrity.id}/edit" method="get">
					<button type="submit" class="btn btn-primary">Edit Celebrity</button>
				</form:form>
			</div>

		</div>

	</c:if>

</body>
</html>