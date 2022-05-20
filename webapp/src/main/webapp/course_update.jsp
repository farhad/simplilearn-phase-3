<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Learners Academy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: ghostwhite;">
<div class="container col-md-5" style="margin-top: 30px;">
    <div class="card">
        <div class="card-body">
            <c:if test="${course != null}">
            <form action="save" method="post">
                </c:if>
                <c:if test="${course == null}">
                <form action="save" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${course != null}">
                                Edit Class
                            </c:if>
                            <c:if test="${course == null}">
                                Add New Class
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${course != null}">
                        <input type="hidden" name="id" value="<c:out value='${course.id}' />"/>
                    </c:if>

                        <fieldset class="form-group" style="margin-top: 20px;">
                            <label>Title: </label> <input type="text"
                                                          value="<c:out value='${course.title}' />"
                                                          class="form-control"
                                                          name="course_title" required="required">
                        </fieldset>

                        <fieldset class="form-group" style="margin-top: 20px;">
                            <label>Description: </label> <input type="text"
                                                                value="<c:out value='${course.description}' />"
                                                                   class="form-control"
                                                                   name="course_description" required="required">
                    </fieldset>

                    <fieldset class="form-group" style="margin-top: 20px;">
                        <label>Subject: </label>
                        <select name="subject_id" required>
                            <c:forEach items="${subjectsList}" var="subjectItem">
                                <option
                                        <c:if test="${subjectItem.id eq selectedSubjectId}">selected="selected"</c:if>
                                        value="${subjectItem.id}">${subjectItem.title}</option>
                            </c:forEach>
                        </select>
                    </fieldset>

                    <fieldset class="form-group" style="margin-top: 20px;">
                        <label>Teacher: </label>
                        <select name="teacher_id" required>
                            <c:forEach items="${teachersList}" var="teacherItem">
                                <option
                                        <c:if test="${teacherItem.id eq selectedTeacherId}">selected="selected"</c:if>
                                        value="${teacherItem.id}">${teacherItem.lastName}, ${teacherItem.firstName}</option>
                            </c:forEach>
                        </select>
                    </fieldset>

                        <button style="margin-top: 20px;background-color: coral;" type="submit" class="btn btn-success">
                            Save
                        </button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
