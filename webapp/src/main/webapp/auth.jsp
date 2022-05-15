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
<body style="background-color: darkgray;">
<center>
    <div style="margin-top: 50px;">
        <h2>Learners Academy | Authentication</h2>
        <form action="auth" method="post">
            <table>
                <tr>
                    <td>username:</td>
                    <td><input type="text" name="username" placeholder="username" class="form-control"></td>
                </tr>
                <tr>
                    <td>password:</td>
                    <td><input type="password" name="password" placeholder="password" class="form-control"></td>
                </tr>
                <tr>
                    <td colspan="4" style="text-align: center"><input type="submit" value="Login"
                                                                      class="btn btn-success"></td>
                </tr>
            </table>
        </form>
    </div>
</center>

</body>
</html>
