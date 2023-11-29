<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="styles/registration/registration.css">
    <title>Регистрация</title>
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="home">Главная страница</a></li>
        </ul>
    </nav>
</header>

<main>
    <section>
        <h2>Регистрация</h2>
        <form method="post">
            <label for="first_name">Имя</label>
            <input type="text" id="first_name" name="first_name" required>
            <label for="second_name">Фамилия</label>
            <input type="text" id="second_name" name="second_name" required>
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
            <label for="password">Пароль</label>
            <input type="password" id="password" name="password" required>
            <button type="submit">Зарегистрироваться</button>
        </form>
    </section>
</main>

</body>
</html>