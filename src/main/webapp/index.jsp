<%@ page contentType="text/html; charset=UTF-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <title>Площадка для продажи машин</title>
</head>
<body>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js'></script>
<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css'>
<script src="https://code.jquery.com/jquery-3.5.1.slim.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="script.js" charset="utf-8"></script>
<link rel="stylesheet" href="style.css" type="text/css"/>
<div><label></label></div>
<div class="container">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <h3>
                    Площадка для продажи машин
                </h3>
            </li>
        </ul>
    </div>
</div>
<div class="container">
<div class="row">
<c:set var="user" scope="session" value="${user}"/>
<c:if test="${user.name != null}">
    <ul class="nav">
        <li class="nav-item">
    <a users="users" class="nav-link" href="<%=request.getContextPath()%>/exit.do">
        Текущий пользователь: <c:out value="${user.name}"/> | Выйти</a>
        </li>
    </ul>
    <ul class="nav">
        <li class="nav-item">
    <a class="nav-link" href="<%=request.getContextPath()%>/createPhoto.jsp">Добавить объявление</a>
        </li>
    </ul>

</c:if>
<c:if test="${user.name == null}">
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/reg.jsp">Регистрация</a>
        </li>
    </ul>
    <ul class="nav">
        <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Авторизация </a>
        </li>
    </ul>

</c:if>
    </div>
</div>
<div class="container">
    <div class="row">
        <c:if test="${not empty error}">
            <div style="color:red; font-weight: bold; margin-left:20px ">
                    ${error}
            </div>
        </c:if>
    </div>
</div>

<div class="container">
    <div class="row">

            <div>
                <label style="margin-left:20px; margin-top:20px;">Выберите фильтр</label>
                <div>
                    <select class="form-group custom-select" name="filter" id="filter" style="margin-left:20px; width: 280px" placeholder="Выберите фильтр">
                        <option value="lastDay" selected>За последний день</option>
                        <option value="withPhoto" selected>С фото</option>
                        <option value="all" selected>Все объявления</option>
                    </select>
                </div>
                <s>
                    <button type="submit" id = "button1" class="btn btn-primary" style="margin-left:20px" onclick="dayFilter()">Показать</button>
                </s>
            </div>

            <div style="margin-left:20px">
                <label style="margin-left:20px; margin-top:20px;">Выберите марку</label>
                <div>
                    <select class="form-group custom-select" name="mark" id="mark" style="margin-left:20px; width: 280px" >
                    <c:forEach items="${markAuto}" var="mark">
                        <option value='<c:out value="${mark.id}"/>'><c:out value="${mark.name}"/></option>
                    </c:forEach>
                    </select>
                </div>
                <s>
                    <button type="submit" id = "button2" class="btn btn-primary" style="margin-left:20px" onclick="markFilter()">Показать</button>
                </s>
            </div>
            <div class="form-group" style="margin-left:20px">
                <label style="margin-left:20px; margin-top:20px;">Выберите объявление </label>
                <div>
                    <input type="text" class="form-control" style="margin-left:20px; width: 280px" name="deletePost" id="deletePost" placeholder="Введите ID объявления">
                </div>

                <div>
                    <button type="submit" id = "button3" class="btn btn-primary" style="margin-left:20px; margin-top:18px" onclick="deletePost()">Удалить</button>
                </div>
            </div>
    </div>
</div>

<div class="table table-sm table-bordered" >
    <table class="table" id = "table" style="margin-left:400px">
        <thead style="font-size:14px;">
        <tr style="background-color: rgb(0,150,350)">
            <th scope="col" style="text-align: center;">Фото</th>
            <th scope="col" style="text-align: center;">Объявление</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${allPost}" var="post">
            <tr>
                <td style="image-orientation: flip">
                    <center>
                        <img src="<c:url value='/upload.do?id=${post.photoCar.id}'/>" width="300px" height="250px"/>
                    </center>

                </td>
                <td style="text-align: left">
                    <table class="table">
                        <thead style="font-size:14px;">
                        <th scope="col" style="text-align: center; background-color: rgb(0,150,350)">Параметр</th>
                        <th  scope="col" style="text-align: center; background-color: rgb(0,150,350)">Показатель</th>
                        </thead>
                        <tbody>

                        <tr>
                            <td style="text-align: right;">
                                Дата объявления:
                            </td>
                            <td style="text-align: center;">
                                <c:out value="${post.created}"/>
                            </td>
                        </tr>

                        <tr >
                            <td style="text-align: right;">
                                ID:
                            </td>
                            <td names="postID" style="text-align: center;"  >
                                <c:out value="${post.id}"/>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: right;">
                                Марка:
                            </td>
                            <td style="text-align: center;">
                                <c:out value="${post.car.markAuto.name}"/>
                            </td>
                        </tr>



                        <tr>
                            <td style="text-align: right;">
                                Тип кузова:
                            </td>
                            <td style="text-align: center;">
                                <c:out value="${post.car.carBody.name}"/>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: right;">
                                Пробег в км:
                            </td>
                            <td style="text-align: center;">
                                <c:out value="${post.run}"/>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: right;">
                                Стоимость в рублях
                            </td>
                            <td style="text-align: center;">
                                <c:out value="${post.cost}"/>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: right;">
                                Подробное описание:
                            </td>
                            <td style="text-align: center;">
                                <c:out value="${post.car.desc}"/>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: right;">
                                Продавец:
                            </td>
                            <td userPost="userPost" style="text-align: center;">
                                <c:out value="${post.car.user.name}"/>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: right;">
                                Телефон:
                            </td>
                            <td style="text-align: center;">
                                <c:out value="${post.car.user.phone}"/>
                            </td>
                        </tr>

                        </td>
                        </tbody>
                    </table>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>





</body>
</html>
