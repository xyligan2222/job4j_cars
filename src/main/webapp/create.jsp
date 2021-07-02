<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%--<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>--%>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js'></script>
    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css'>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="createScript.js" charset="utf-8"></script>

    <title>Площадка для продажи машин</title>
</head>
<body>
<div class="container pt-1">

    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header" style="font-weight: bold; font-size: larger">
                Форма для добавления объявления
            </div>
            <div class="card-body">
                <div class="form-group row">
                    <label class="col-form-label col-sm-3" for="desc" style="font-weight: 900">Подробное описание</label>
                    <div class="col-sm-5">
                        <input type='text' id="desc" name="desc" placeholder="Подробное описание" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-form-label col-sm-3" for="mark" style="font-weight: 900">Марка Автомобиля</label>
                    <div class="col-sm-5">
                        <select class="form-control" name="mark" id="mark" >
                            <c:forEach items="${markAuto}" var="mark">
                                <option value='<c:out value="${mark.id}"/>'><c:out value="${mark.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-form-label col-sm-3" for="carBody" style="font-weight: 900">Тип Кузова</label>
                    <div class="col-sm-5">
                        <select class="form-control" name="carBody" id="carBody" >
                            <c:forEach items="${carBody}" var="body">
                                <option value='<c:out value="${body.id}"/>'><c:out value="${body.name}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-form-label col-sm-3" for="run" style="font-weight: 900">Пробег в км</label>
                    <div class="col-sm-5">
                        <input type='text' id="run" name="run" placeholder="Пробег в км" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-form-label col-sm-3" for="money" style="font-weight: 900">Стоимость в рублях</label>
                    <div class="col-sm-5">
                        <input type='text' id="money" name="money" placeholder="Стоимость авто" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-form-label col-sm-3" style="font-weight: 900"></label>
                    <div class="col-sm-5">
                        <button type="submit" class="btn btn-dark" onclick="doSend()">Добавить</button>
                    </div>
                </div>


                <div> <label></label> </div>

            </div>
        </div>
    </div>

</div>
</body>
</html>