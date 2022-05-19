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
            <c:if test="${course != null}">
            <form action="save" method="post">
                </c:if>
                <c:if test="${course == null}">
                <form action="save" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${course != null}">
                                Edit Course
                            </c:if>
                            <c:if test="${course == null}">
                                Add New Course
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${course != null}">
                        <input type="hidden" name="id" value="<c:out value='${course.id}' />"/>
                    </c:if>

                        <fieldset class="form-group" style="margin-top: 20px;">
                            <label>Course Title: </label> <input type="text"
                                                                 value="<c:out value='${course.title}' />"
                                                                 class="form-control"
                                                                 name="course_title" required="required">
                        </fieldset>

                        <fieldset class="form-group" style="margin-top: 20px;">
                            <label>Course Description: </label> <input type="text"
                                                                       value="<c:out value='${course.description}' />"
                                                                       class="form-control"
                                                                       name="course_description" required="required">
                        </fieldset>

                        <fieldset class="form-group" style="margin-top: 20px;">
                            <label>Subject: </label> <input type="text"
                                                            value="<c:out value='${course.subjectId}' />"
                                                            class="form-control"
                                                            name="subject_id" required="required">
                        </fieldset>

                        <fieldset class="form-group" style="margin-top: 20px;">
                            <label>Teacher: </label> <input type="text"
                                                            value="<c:out value='${course.teacherId}' />"
                                                           class="form-control"
                                                           name="teacher_id" required="required">
                    </fieldset>

                    <button style="margin-top: 20px;" type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
