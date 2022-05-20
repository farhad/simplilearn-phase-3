<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Dashboard | Learners Academy</title>
</head>

<style>
    body {
        margin: 36px;
        font-size: 15px;
    }

</style>

<body style="background-color: ghostwhite">
<jsp:include page="header.jsp"/>
<div>
    <div class="container">
        <div class="container text-left">
            <a href="${requestScope['javax.servlet.forward.request_uri']}/add" class="btn btn-success"
               style="background-color: coral">Add New
                Teacher</a>
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
            <c:forEach var="studentItem" items="${teachersList}">
                <tr>
                    <td><c:out value="${studentItem.id}"/></td>
                    <td><c:out value="${studentItem.firstName}"/></td>
                    <td><c:out value="${studentItem.lastName}"/></td>
                    <td><c:out value="${studentItem.bio}"/></td>
                    <td>
                        <div style="float:left;">
                            <form method="post" action="${requestScope['javax.servlet.forward.request_uri']}/edit">
                                <input type="hidden" value="${studentItem.id}" name="id">
                                <input type="hidden" value="${studentItem.firstName}" name="first_name">
                                <input type="hidden" value="${studentItem.lastName}" name="last_name">
                                <input type="hidden" value="${studentItem.bio}" name="bio">
                                <input type="submit" value="Edit" class="btn btn-warning">
                            </form>
                        </div>
                        <div style="float:left;margin-left: 20px;">
                            <form method="post" action="${requestScope['javax.servlet.forward.request_uri']}/delete">
                                <input type="hidden" value="${studentItem.id}" name="id">
                                <input type="hidden" value="${studentItem.firstName}" name="first_name">
                                <input type="hidden" value="${studentItem.lastName}" name="last_name">
                                <input type="hidden" value="${studentItem.bio}" name="bio">
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