<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
    <title>Dashboard | Learners Academy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<style>
    body {
        margin: 36px;
        font-size: 15px;
    }

</style>

<body style="background-color: darkgray">

<div>
    <div class="container">
        <h3 class="text-center">Teachers</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/teachers/add" class="btn btn-success">Add New Teacher</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Bio</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="teacher" items="${teachersList}">

                <tr>
                    <td>
                        <c:out value="${teacher.id}"/>
                    </td>
                    <td>
                        <c:out value="${teacher.firstName}"/>
                    </td>
                    <td>
                        <c:out value="${teacher.lastName}"/>
                    </td>
                    <td>
                        <c:out value="${teacher.bio}"/>
                    </td>
                    <td><a href="edit?id=<c:out value='${teacher.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
                            href="delete?id=<c:out value='${teacher.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>

</html>