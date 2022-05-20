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
            <c:if test="${teacher != null}">
            <form action="save" method="post">
                </c:if>
                <c:if test="${teacher == null}">
                <form action="save" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${teacher != null}">
                                Edit teacher
                            </c:if>
                            <c:if test="${teacher == null}">
                                Add New teacher
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${teacher != null}">
                        <input type="hidden" name="id" value="<c:out value='${teacher.id}' />"/>
                    </c:if>

                    <fieldset class="form-group" style="margin-top: 20px;">
                        <label>First Name: </label> <input type="text"
                                                           value="<c:out value='${teacher.firstName}' />"
                                                           class="form-control"
                                                           name="first_name" required="required">
                    </fieldset>

                    <fieldset class="form-group" style="margin-top: 20px;">
                        <label>Last Name: </label> <input type="text"
                                                          value="<c:out value='${teacher.lastName}' />"
                                                          class="form-control"
                                                          name="last_name">
                    </fieldset>

                    <fieldset class="form-group" style="margin-top: 20px;">
                        <label>About: </label> <input type="text"
                                                      value="<c:out value='${teacher.bio}' />" class="form-control"
                                                      name="bio">
                    </fieldset>

                        <button style="margin-top: 20px;background-color: coral" type="submit" class="btn btn-success">
                            Save
                        </button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
