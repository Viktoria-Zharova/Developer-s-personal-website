<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Вход</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/login/login.css">
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/home">Главная страница</a></li>
        </ul>
    </nav>
</header>

<main class="main">
    <section>
        <h2>Вход</h2>
        <form method="post">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" >
            <label for="password">Пароль</label>
            <input type="password" id="password" name="password" >
            <a class="button" id="login" >Войти</a>
        </form>
        <p>Нет аккаунта? <a href="${pageContext.request.contextPath}/registration" class="a-reg">Зарегистрироваться</a></p>
    </section>
</main>

<script>
    $('#login').on('click', function () {
        $.ajax({
            type: "POST",
            url: 'http://localhost:8080/login',
            data: {
                email: $('#email').val(),
                password: $('#password').val()
            },
            success: function (){
                location.reload()
            }
        })
    });
</script>
</body>
</html>