<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="styles/forma/forma.css">
    <title>заявки</title>
</head>
<body>
<header>
    <p><a href="home">Главная</a></p>
    <p><a href="profile">Профиль</a></p>
</header>
<main class="job-applications">
    <div class="job-applications-container">
        <h2>Отправить предложение о сотрудничестве</h2>
        <form action="<c:url value="/forma"/>" method="post">
            <label for="description">Описание работы:</label>
            <textarea id="description" name="description" required placeholder="Укажите название заявки, сроки, бюджет и подробное описание с Вашим представлением о сотрудничестве, иначе заявка не будет рассмотрена."></textarea>
            <button type="submit">Отправить заявку</button>
        </form>

        <list class="applications-list">
            <h2>Список отправленных заявок</h2>
            <c:forEach items="${tasks}" var="task">
                <ul>
                    <li class="application">
                        <p class="application-date">${task.getDescription()}</p>
                    </li>
                </ul>
            </c:forEach>
        </div>

    </div>
</main>
</body>
</html>