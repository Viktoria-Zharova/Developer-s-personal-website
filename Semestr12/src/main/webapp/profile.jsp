<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="styles/profile/profile.css">
    <title>Профиль</title>
</head>
<body>
<header>
    <div>
        <p><a href="home">Главная страница</a></p>
    </div>
    <div class="button_group">
        <p><a href="reviews">Отзывы</a></p>
        <p><a href="forma">Мои заявки</a></p>
    </div>


</header>
<main class="profile">
    <div class="profile-container">
        <div class="profile-info">
            <img src="/image/user/${user.getId()}" alt="Фотография пользователя">

            <h2><c:out value="${user.getFirstName()}"/> <c:out value="${user.getSecondName()}"/></h2>
            <form id="profile-form" enctype="multipart/form-data" method="post">
                <label for="first_name">Имя:</label>
                <input type="text" id="first_name" name="first_name" value="${user.getFirstName()}"/>
                <label for="second_name">Фамилия:</label>
                <input type="text" id="second_name" name="second_name" value="${user.getSecondName()}"/>
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${user.getEmail()}"/>
                <label for="password">Пароль:</label>
                <input type="password" id="password" name="password" value="********">
                <label for="file">Добавить фото:</label>
                <input type="file" id="file" name="file" accept="image/*">
                <button id="save-profile" type="submit">Сохранить изменения</button>
            </form>
        </div>
    </div>
</main>
</body>
</html>