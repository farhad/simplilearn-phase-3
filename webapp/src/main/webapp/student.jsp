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
        <h3 class="text-center">Students</h3>
        <hr>
        <div class="container text-left">
            <a href="${requestScope['javax.servlet.forward.request_uri']}/add" class="btn btn-success">Add New
                Student</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="studentItem" items="${studentsList}">
                <tr>
                    <td><c:out value="${studentItem.id}"/></td>
                    <td><c:out value="${studentItem.firstName}"/></td>
                    <td><c:out value="${studentItem.lastName}"/></td>
                    <td>
                        <div style="float:left;">
                            <form method="post" action="${requestScope['javax.servlet.forward.request_uri']}/edit">
                                <input type="hidden" value="${studentItem.id}" name="id">
                                <input type="hidden" value="${studentItem.firstName}" name="first_name">
                                <input type="hidden" value="${studentItem.lastName}" name="last_name">
                                <input type="submit" value="Edit" class="btn btn-warning">
                            </form>
                        </div>
                        <div style="float:left;margin-left: 20px;">
                            <form method="post" action="${requestScope['javax.servlet.forward.request_uri']}/delete">
                                <input type="hidden" value="${studentItem.id}" name="id">
                                <input type="hidden" value="${studentItem.firstName}" name="first_name">
                                <input type="hidden" value="${studentItem.lastName}" name="last_name">
                                <input type="submit" value="Delete" class="btn btn-danger">
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>

</html>