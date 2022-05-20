<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>

<html lang="en">
<head>
    <title>Dashboard | Learner's Academy</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body style="background-color: ghostwhite;">

<nav class="navbar navbar-default" style="background-color: coral">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">Learners Academy</a>
        </div>
        <ul class="nav navbar-nav">
            <%
                String name = request.getContextPath();
            %>
            <li><a style="color: black" href=<%= name + "/subject"%>>Subjects</a></li>
            <li><a style="color: black" href=<%= name + "/teacher"%>>Teachers</a></li>
            <li><a style="color: black" href=<%= name + "/course"%>>Classes</a></li>
            <li><a style="color: black" href=<%= name + "/student"%>>Students</a></li>
            <li><a style="color: black" href=<%= name + "/enrollment"%>>Enrollment</a></li>
        </ul>
    </div>
</nav>

</body>
</html>
