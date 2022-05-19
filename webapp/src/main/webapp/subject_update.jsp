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
            <c:if test="${subject != null}">
            <form action="save" method="post">
                </c:if>
                <c:if test="${subject == null}">
                <form action="save" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${subject != null}">
                                Edit subject
                            </c:if>
                            <c:if test="${subject == null}">
                                Add New Subject
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${subject != null}">
                        <input type="hidden" name="id" value="<c:out value='${subject.id}' />"/>
                    </c:if>

                    <fieldset class="form-group" style="margin-top: 20px;">
                        <label>Title: </label> <input type="text"
                                                      value="<c:out value='${subject.title}' />"
                                                      class="form-control"
                                                      name="title" required="required">
                    </fieldset>

                    <fieldset class="form-group" style="margin-top: 20px;">
                        <label>Description: </label> <input type="text"
                                                            value="<c:out value='${subject.description}' />"
                                                            class="form-control"
                                                            name="description">
                    </fieldset>

                    <button style="margin-top: 20px;" type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
