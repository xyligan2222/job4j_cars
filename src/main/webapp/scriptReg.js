function validateReg() {
    let addBtn3 = document.getElementById("myInputEmail").value;
    let addBtn4 = document.getElementById("myInputName").value;
    let addBtn5 = document.getElementById("myInputPassword").value;
    let addBtn6 = document.getElementById("myInputNumber").value;


    if (addBtn3 === '' || addBtn4 === ''
        || addBtn5 === ''|| addBtn6 === '') {
        console.log(addBtn4);
        console.log(addBtn3);
        console.log(addBtn5);
        alert('Заполните все поля');
        return false;
    }
    $.ajax({
        async: false,
        type: 'POST',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_cars/check.do',
        data : {
            email : addBtn3
        }
    });
    $.ajax({
        async: false,
        type: 'GET',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_cars/check.do',
    }).done(function (data) {
        if (data === "true") {
            console.log("data GET reg " + data);
            alert('Пользователь с данным email уже зарегистрирован');
            //result = data.id;
            return false;
        } else {
            console.log("data  GET2 reg else " + data);
            alert('Все корректно');
            $.ajax({
                async: false,
                type: 'POST',
                crossdomain: true,
                url: 'http://localhost:8080/job4j_cars/reg.do',
                data : {
                    email : addBtn3,
                    name : addBtn4,
                    password : addBtn5,
                    phone : addBtn6
                }
            })
            window.location = 'http://localhost:8080/job4j_cars/login.jsp';
        }

    });
    return true;
}
