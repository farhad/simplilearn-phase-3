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
                Class</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Subject</th>
                <th>Teacher</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="enrollmentItem" items="${coursesList}">
                <tr>
                    <td><c:out value="${enrollmentItem.id}"/></td>
                    <td><c:out value="${enrollmentItem.title}"/></td>
                    <td><c:out value="${enrollmentItem.description}"/></td>
                    <td><c:out value="${enrollmentItem.subject.title}"/></td>
                    <td><c:out value="${enrollmentItem.teacher.firstName} ${enrollmentItem.teacher.lastName}"/></td>
                    <td>
                        <div style="float:left;">
                            <form method="post" action="${requestScope['javax.servlet.forward.request_uri']}/edit">
                                <input type="hidden" value="${enrollmentItem.id}" name="id">
                                <input type="hidden" value="${enrollmentItem.subject.id}" name="subject_id">
                                <input type="hidden" value="${enrollmentItem.teacher.id}" name="teacher_id">
                                <input type="hidden" value="${enrollmentItem.title}" name="course_title">
                                <input type="hidden" value="${enrollmentItem.description}" name="course_description">
                                <input type="submit" value="Edit" class="btn btn-warning">
                            </form>
                        </div>
                        <div style="float:left;margin-left: 20px;">
                            <form method="post" action="${requestScope['javax.servlet.forward.request_uri']}/delete">
                                <input type="hidden" value="${enrollmentItem.id}" name="id">
                                <input type="hidden" value="${enrollmentItem.subject.id}" name="subject_id">
                                <input type="hidden" value="${enrollmentItem.teacher.id}" name="teacher_id">
                                <input type="hidden" value="${enrollmentItem.title}" name="course_title">
                                <input type="hidden" value="${enrollmentItem.description}" name="course_description">
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