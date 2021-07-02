
    $(document).ready(function () {
})




function markFilter(){
    let mark1 = document.getElementById("mark").value;
    $.ajax({
        type: 'GET',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_cars/markFilter',
        data: {
            mark : mark1
        }
    }).done(function () {
       console.log("zbs");
        window.location = 'http://localhost:8080/job4j_cars/markFilter' +'?'+ 'mark=' + mark1;

    }).fail(function () {
        alert("err");
    });
}

    function dayFilter(){
        let filter1 = document.getElementById("filter").value;
        $.ajax({
            type: 'GET',
            crossdomain: true,
            url: 'http://localhost:8080/job4j_cars/dayFilter',
            data: {
                filter : filter1
            }
        }).done(function () {
            console.log("zbs");
            window.location = 'http://localhost:8080/job4j_cars/dayFilter' +'?'+ 'filter=' + filter1;

        }).fail(function () {
            alert("err");
        });
    }
    function showDesc() {
}

    function deletePost() {
        if (validateDelete()) {
            let delete1 = document.getElementById("deletePost").value;
            $.ajax({
                type: 'POST',
                crossdomain: true,
                url: 'http://localhost:8080/job4j_cars/deletePost.do',
                data: {
                    delete: delete1
                }
            }).done(function () {
                console.log("zbs");
                window.location = 'http://localhost:8080/job4j_cars/index.do';

            }).fail(function () {
                alert("err");
            });
        }
    }

    function validateDelete(){
        let temp = 0;
        let delete1 = document.getElementById("deletePost").value;
        let delete2 = document.querySelectorAll('[users]')[0].innerText
        console.log("sdsd", delete2);
        let id = document.querySelectorAll('[names]').length;
        for (let i = 0; i < id; i++) {
            if (delete1 == document.querySelectorAll('[names]')[i].innerText
                && document.querySelectorAll('[users]')[0].innerText == "Текущий пользователь: " + document.querySelectorAll('[userPost]')[i].innerText + " | Выйти"){
                temp = document.querySelectorAll('[names]')[i].innerText
                console.log(document.querySelectorAll('[names]')[i].innerText)
            }
        }
        if (temp === 0){
            alert("Объявление с указанным ID не найдено или Объявление создавали не вы ");
            return false;
        }
        return true;

    }