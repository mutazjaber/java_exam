<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
		.max-width-300 {
		  width: 100%;
		  align-items :center ;
		}
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6">
                <h1>Register</h1>
                <form:form action="/register" method="post" modelAttribute="newUser">
                    <div class="mb-3">
                        <form:label class="form-label" for="userName" path="userName">User Name:</form:label>
                        <form:input class="form-control" id="userName" path="userName" />
                    </div>

                    <div class="mb-3">
                        <form:label class="form-label" for="email" path="email">Email:</form:label>
                        <form:input class="form-control" id="email" path="email" />
                    </div>

                    <div class="mb-3">
                        <form:label class="form-label" for="password" path="password">Password:</form:label>
                        <form:input class="form-control" id="password" path="password" type="password" />
                    </div>
                    <div class="mb-3">
                        <form:label class="form-label" for="confirm" path="confirm">Confirm Password:</form:label>
                        <form:input class="form-control" id="confirm" path="confirm" type="password" />
                    </div>
                    <div>
                     <form:errors class="form-control form-control-sm" for="userName" path="userName" />
                        <form:errors class="form-control form-control-sm" for="email" path="email" />
                        <form:errors class="form-control form-control-sm" for="password" path="password" />
                        <form:errors class="form-control form-control-sm" for="confirm" path="confirm" />
                    </div>
                    <div class="mb-3">
                        <button type="submit" class="btn btn-dark w-100">Register Now !</button>
                    </div>
                </form:form>
            </div>

            <div class="col-md-6">
                <h1 class="mb-3">Login</h1>
                <form:form action="/login" method="post" modelAttribute="user">

                    <div class="mb-3">
                        <form:label class="form-label" for="email" path="email">Email</form:label>
                        <form:input class="form-control" id="email" path="email" />
                        
                    </div>

                    <div class="mb-3">
                        <form:label class="form-label" for="password" path="password">Password</form:label>
                        <form:input class="form-control" id="password" path="password" type="password" />
                        
                    </div>
                    <div>
                    	<form:errors class="form-text" for="email" path="email" /><br>
                        <form:errors class="form-text" for="password" path="password" /><br>
                     </div>
					<div class="mb-3">
                        <button type="submit" class="btn btn-dark w-100">Submit</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>
