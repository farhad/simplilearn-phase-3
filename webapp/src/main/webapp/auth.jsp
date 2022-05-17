<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Learners Academy | Authentication</title>
    <style>
        td {
            padding: 10px;
        }
    </style>
</head>
<body style="background-color: lightgray;">
<center>
    <div style="margin-top: 50px;">
        <h2>Learners Academy | Authentication</h2>
        <form action="auth" method="post">
            <table>
                <tr>
                    <%
                        String populatedUsername = request.getAttribute("username") != null ? request.getAttribute("username").toString() : "";
                    %>
                    <td>username:</td>
                    <td><input type="text" name="username" placeholder="username" class="form-control" required
                               value=<%= populatedUsername%>></td>
                </tr>
                <tr>
                    <td>password:</td>
                    <td><input type="password" name="password" placeholder="password" class="form-control" required>
                    </td>

                </tr>
                <tr>
                    <td colspan="4" style="text-align: center"><input type="submit" value="Sign in"
                                                                      class="btn btn-success"></td>
                </tr>
            </table>
        </form>

        <table>
            <tr>
                <td><%= request.getAttribute("error_auth") != null ? request.getAttribute("error_auth") : "" %>
                </td>
            </tr>
        </table>
    </div>
</center>

</body>
</html>
