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
            <c:if test="${student != null}">
            <form action="save" method="post">
                </c:if>
                <c:if test="${student == null}">
                <form action="save" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${student != null}">
                                Edit Student
                            </c:if>
                            <c:if test="${student == null}">
                                Add New Student
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${student != null}">
                        <input type="hidden" name="id" value="<c:out value='${student.id}' />"/>
                    </c:if>

                    <fieldset class="form-group" style="margin-top: 20px;">
                        <label>First Name: </label> <input type="text"
                                                           value="<c:out value='${student.firstName}' />"
                                                           class="form-control"
                                                           name="first_name" required="required">
                    </fieldset>

                    <fieldset class="form-group" style="margin-top: 20px;">
                        <label>Last Name: </label> <input type="text"
                                                          value="<c:out value='${student.lastName}' />"
                                                          class="form-control"
                                                          name="last_name">
                    </fieldset>

                    <button style="margin-top: 20px;" type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
