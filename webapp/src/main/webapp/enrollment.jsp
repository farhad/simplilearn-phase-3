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
        <h3 class="text-center">Enrollments</h3>
        <hr>
        <div class="container text-left">
            <a href="${requestScope['javax.servlet.forward.request_uri']}/add" class="btn btn-success">Add New
                Enrollment</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Student Name</th>
                <th>Course Info</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="enrollmentItem" items="${enrollmentsList}">
                <tr>
                    <td><c:out value="${enrollmentItem.id}"/></td>
                    <td><c:out value="${enrollmentItem.student.firstName} ${enrollmentItem.student.lastName}"/></td>
                    <td><c:out value="${enrollmentItem.course.title}"/></td>
                    <td>
                        <div style="float:left;">
                            <form method="post" action="${requestScope['javax.servlet.forward.request_uri']}/edit">
                                <input type="hidden" value="${enrollmentItem.id}" name="id">
                                <input type="hidden" value="${enrollmentItem.student.id}" name="student_id">
                                <input type="hidden" value="${enrollmentItem.course.id}" name="course_id">
                                <input type="submit" value="Edit" class="btn btn-warning">
                            </form>
                        </div>
                        <div style="float:left;margin-left: 20px;">
                            <form method="post" action="${requestScope['javax.servlet.forward.request_uri']}/delete">
                                <input type="hidden" value="${enrollmentItem.id}" name="id">
                                <input type="hidden" value="${enrollmentItem.student.id}" name="student_id">
                                <input type="hidden" value="${enrollmentItem.course.id}" name="course_id">
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