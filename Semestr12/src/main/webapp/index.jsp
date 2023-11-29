<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Владимир Морозов</title>
    <link rel="stylesheet" href="styles/index/index.css">

</head>
<body>
<header class="header">
    <div class="header-content">
        <div>
            <h2>"Программирую идеи в реальность"</h2>
        </div>
        <div>
            <c:if test="${empty user}">
                <a href="login" class="login-button">Войти</a>
            </c:if>
            <c:if test="${not empty user}">
                <a href="profile" class="login-button">Профиль</a>
                <c:if test="${user.getRole().getValue() == 'admin'}">
                    <a href="admin" class="login-button">Админ</a>
                </c:if>
                <a id="logout" class="login-button">Выйти</a>
            </c:if>
        </div>
    </div>
</header>
<main class="main">
    <div class="main-content">
        <div class="main-left">
            <img src="img/GettyImages-1150528651.jpg" alt="Работник">
        </div>
        <div class="main-right">
            <h2>Владимир Морозов</h2>
            <p>Здравствуйте, меня зовут Владимир, и я являюсь опытным фуллстек разработчиком, специализирующимся на
                JavaScript. Свыше 5 лет я работаю в области веб-разработки, и мой опыт позволяет мне успешно создавать
                веб-приложения и сайты разной сложности.

                Я ориентирован на применение передовых методов и технологий в веб-разработке. Моя экспертиза в
                JavaScript позволяет мне создавать динамичные и интерактивные веб-приложения, обеспечивая при этом
                оптимальную производительность и удобство использования для пользователей.

                Помимо этого, я имею опыт работы с различными фреймворками и библиотеками, что позволяет мне выбирать
                наилучшие инструменты для каждого проекта. Я всегда открыт для новых вызовов и готов внести свой вклад в
                разработку веб-проектов. Моя цель - предоставлять высококачественные решения и помогать клиентам достичь
                своих целей в сети.</p>
            <p>После просмотра портфолио, можете отправить свои предложения через личный кабинет, предварительно
                авторизовавшись на сайте.</p>
            <a href="portfolio" class="login-button">Портфолио</a>
        </div>
    </div>

    <div class="portfolio-examples">
        <h3>Примеры работ</h3>
        <c:forEach items="${examples_list}" var="project">
        <div class="portfolio-example">
            <article class="project">
                <img src="/image/project/${project.getId()}" alt="Проект 1">
                <h2>${project.getTitle()}</h2>
                <p>${project.getDescription()}</p>
            </article>
        </div>
        </c:forEach>


    </div>
    <div class="reviews">
        <h3>Отзывы</h3>
        <c:forEach items="${feedback_list}" var="feedback">
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


<footer class="footer">
    <div class="footer-content">
        <h3>Контактная информация</h3>
        <p>Адрес: Улица, Город, Страна</p>
        <p>Телефон: +7 (123) 456-7890</p>
        <p>Email: info@example.com</p>
    </div>
</footer>

<script>
    $('#logout').on('click', function () {
        $.ajax({
            type: "POST",
            url: 'http://localhost:8080/home',
            data: {
            },
            success: function (){
                location.reload()
            }
        })
    });
</script>
</body>
</html>