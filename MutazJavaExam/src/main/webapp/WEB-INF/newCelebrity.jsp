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
<title>New Celebrity</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>

<body>

	<div class="mt-2 d-flex justify-content-center">
		<div class="col-4  ">
			<h1 class="mb-3">New Celebrity Page</h1>
			<a href="/dashboard">home</a>
			<p>${user.userName}</p>

			<form:form action="/celebrities/createCelebrity" method="post"
				modelAttribute="celebrity">

				<div class="mb-3">
					<form:label class="form-label" for="name" path="name">Name :</form:label>
					<form:input class="form-control" id="name" path="name" />
					<form:errors class="form-text" for="name" path="name" />
				</div>
				<div class="mb-3">
					<form:label class="form-label" for="details" path="details">Details :</form:label>
					<form:textarea class="form-control" id="details" path="details" />
					<form:errors class="form-text" for="details" path="details" />
				</div>

				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>



		</div>
	</div>

</body>
</html>