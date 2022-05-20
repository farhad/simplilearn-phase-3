<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Learners Academy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container col-md-5" style="margin-top: 30px;">
    <div class="card">
        <div class="card-body">
            <c:if test="${enrollment != null}">
            <form action="save" method="post">
                </c:if>
                <c:if test="${enrollment == null}">
                <form action="save" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${enrollment != null}">
                                Edit Enrollment
                            </c:if>
                            <c:if test="${enrollment == null}">
                                Add New Enrollment
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${enrollment != null}">
                        <input type="hidden" name="id" value="<c:out value='${enrollment.id}' />"/>
                    </c:if>

                    <fieldset class="form-group" style="margin-top: 20px;">
                        <label>Student: </label>
                        <select name="student_id" required>
                            <c:forEach items="${studentsList}" var="studentItem">
                                <option
                                        <c:if test="${studentItem.id eq selectedStudentId}">selected="selected"</c:if>
                                        value="${studentItem.id}">${studentItem.firstName} ${studentItem.lastName}</option>
                            </c:forEach>
                        </select>
                    </fieldset>

                    <fieldset class="form-group" style="margin-top: 20px;">
                        <label>Course: </label>
                        <select name="course_id" required>
                            <c:forEach items="${coursesList}" var="courseItem">
                                <option
                                        <c:if test="${courseItem.id eq selectedCourseId}">selected="selected"</c:if>
                                        value="${courseItem.id}">${courseItem.title}</option>
                            </c:forEach>
                        </select>
                    </fieldset>

                    <button style="margin-top: 20px;" type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
