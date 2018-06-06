<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>X si 0</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <style>
        #game {
            display: grid;
            grid-template: repeat(3, 50px) / repeat(3, 50px);
            grid-gap: .5em;
        }

        #game > * {
            border-radius: 1em;
            background: #aaaaff;
            display: flex;
            justify-content: center;
            align-items: center;
            text-transform: uppercase;
        }
    </style>
</head>
<body>
<div class="container mt-5"></div>
<div id="message" class="d-flex justify-content-center">

</div>
<div class="d-flex justify-content-center">
    <div id="game">
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/x0.js"></script>
</body>
</html>
