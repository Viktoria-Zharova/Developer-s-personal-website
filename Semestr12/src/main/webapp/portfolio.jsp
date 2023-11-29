<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/styles/portfolio/portfolio.css">
    <title>портфолио</title>
</head>
<body>
<header class="header">
    <h1>Портфолио</h1>

    <a href="home" class="button">Главная страница</a>

</header>
<main>
    <section class="portfolio">
        <c:forEach items="${projects}" var="project">
            <article class="project">
                <img src="/image/project/${project.getId()}" alt="Проект 1">
                <h2>${project.getTitle()}</h2>
                <p>${project.getDescription()}</p>
                <button class="delete-button" onclick="deleteProject(this)">Удалить</button>
            </article>
        </c:forEach>
    </section>
</main>
</body>
</html>