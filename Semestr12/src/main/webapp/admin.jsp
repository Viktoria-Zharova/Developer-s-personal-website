<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/admin/admin.css">
    <title>Администратор</title>
</head>

<body>
<header class="header">
    <p><a href="home">Главная</a></p>
</header>

<main class="admin">
    <div class="admin-container">
        <h1>Администрирование</h1>

        <div class="incoming-applications">
            <h2>Входящие заявки</h2>
            <div class="applications">
                <c:forEach items="${tasks}" var="task">
                <ul>
                    <li class="application">
                        <p class="application-date">${task.getDescription()}</p>
                    </li>
                </ul>
                </c:forEach>
            </div>
        </div>

        <div class="portfolio-management">
            <h2>Редактирование портфолио</h2>
            <form id="portfolio-form" enctype="multipart/form-data" method="post">
                <form action="<c:url value="/forma"/>" method="post">
                <label for="title">Добавить проект:</label>
                <textarea id="title" name="title" required
                              placeholder="Название проекта"></textarea>
                <label for="description">Добавить описание:</label>
                <textarea id="description" name="description" required
                          placeholder="Описание проекта"></textarea>
                <label for="file">Добавить фото:</label>
                <input type="file" id="file" name="file" accept="image/*">
                <button type="submit">Сохранить</button>
                </form>
            </form>
            <section class="portfolio">
                <c:forEach items="${projects}" var="project">
                <article class="project">
                    <img src="/image/project/${project.getId()}" alt="Проект 1">
                    <h2>${project.getTitle()}</h2>
                    <p>${project.getDescription()}</p>
                    <button class="delete-button delete-project" data-target="${project.getId()}">Удалить</button>
                </article>
                </c:forEach>
            </section>
        </div>

        <div class="reviews">
            <h2>Отзывы</h2>
            <c:forEach items="${feedbacks}" var="feedback">
            <div class="review">
                <div class="profile">
                    <img src="/image/user/${feedback.getAuthor_id()}" alt="Отзыв">
                </div>
                <div class="review-content">
                    <h4>${feedback.getAuthor()}</h4>
                    <p>${feedback.getDescription()}</p>
                    <button class="delete-button delete-feedback" data-target="${feedback.getId()}">Удалить</button>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>
</main>

<footer>
</footer>

<script>
    $('.delete-project').on('click', function () {
        const id = $(this).data('target');
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/ajax/project/delete',
            data: {
                project_id: id
            },
            success: function () {
                location.reload()
            }
        })
    });
</script>

<script>
    $('.delete-feedback').on('click', function () {
        const id = $(this).data('target');
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/ajax/feedback/delete',
            data: {
                feedback_id: id
            },
            success: function () {
                location.reload()
            }
        })
    });
</script>

</body>

</html>
