<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>X si 0</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5"></div>

<div class="col-4 offset-4">
    <div class="card">
        <div class="card-body">
            <h5 class="card-title">Login to play</h5>
            <form action="${pageContext.request.contextPath}/login" method="post" class="m-0">
                <div class="card-text">
                    <div class="form-group">
                        <input type="text" class="form-control" name="username" placeholder="Username">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Password">
                    </div>
                </div>
                <input type="submit" class="btn btn-outline-primary btn-block card-link" value="Login">
            </form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</body>
</html>
