$(document).ready(function () {

});

function validateCreate(){


}
function doSend() {

        let describe = document.getElementById("desc").value;
        let mark1 = document.getElementById("mark").value;
        let carBody1 = document.getElementById("carBody").value;
        let money1 = document.getElementById("money").value;
        let run1 = document.getElementById("run").value;
    if (describe === ''
        || money1 === ''|| run1 === '' ) {
        alert('Заполните все поля');
        return;
    }
    if (!/^[1-9]\d*$/.test(money1)){
        alert('Стоимость укажите цифрами');
        return;
    }
    if ( !/^[1-9]\d*$/.test(run1)){
        alert('Пробег укажите цифрами');
        return;
    }
        console.log(describe);
        console.log(mark1);
        console.log(carBody1);
        console.log(money1);
        console.log(run1);
        $.ajax({
            type: 'POST',
            crossdomain: true,
            url: 'http://localhost:8080/job4j_cars/addPost.do',
            data: {
                desc : describe,
                mark : mark1,
                carBody : carBody1,
                money : money1,
                run : run1
            }
        }).done(function () {
            window.location = 'http://localhost:8080/job4j_cars/index.do';

        }).fail(function () {
            alert("err");
        });

}