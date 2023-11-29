<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="styles/rewiews/rewiews.css">
    <title>Отзывы</title>
</head>
<body>
<header class="header">
    <div class="menu-links">
        <p><a href="home" class="button">Главная страница</a></p>
    </div>
    <div class="menu-for-profile">
        <p><a href="profile">Профиль</a></p>
    </div>
</header>
<main class="profile">
    <div class="form-container">
        <form method="post">
            <label for="description">Оставьте свой отзыв:</label>
            <textarea id="description" name="description" rows="4" cols="50"></textarea>
            <button id="submit-review" type="submit">Отправить отзыв</button>
        </form>
    </div>
    <div class="reviews">
        <h3>Отзывы</h3>
        <c:forEach items="${feedbacks}" var="feedback">
            <div class="review">
                <div class="profile">
                    <img src="/image/user/${feedback.getAuthor_id()}" alt="Отзыв">
                </div>
                <div class="review-content">
                    <h4>${feedback.getAuthor()}</h4>
                    <p>${feedback.getDescription()}</p>
                </div>
            </div>
        </c:forEach>
    </div>







</main>
</body>
</html>
