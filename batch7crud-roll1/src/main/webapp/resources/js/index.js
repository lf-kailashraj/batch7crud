/**
 * Created by kiran on 2/1/16.
 */
var deleteButton = document.getElementsByClassName("delete");
for (var i = 0; i < deleteButton.length; i++) {
    deleteButton[i].onclick = function (e) {
        e.preventDefault();
        var href = this.getAttribute("href");
        var confirmation = confirm("do you want to delete?");

        if (confirmation === true) {
            var form = document.createElement("form");
            form.action = href;
            form.method = "post";
            form.submit();
        }
    };
}

var logoutButton = document.getElementById("logout");
logoutButton.onclick = function (e) {
    e.preventDefault();
    var href = this.getAttribute("href");
    var form = document.createElement("form");
    form.action = href;
    form.method = "post";
    form.submit();
};


$('#ajaxForm').submit(function (e) {
    e.preventDefault();
    submitForm();

});


function submitForm() {
    var userName = $('#username').val();
    var password = $('#password').val();
    var fullName = $('#fullname').val();
    var department = $("#department").val();
    var address = $("#address").val();
    var age = $("#age").val();

    var jsonObject = new Object();
    jsonObject.userName = userName;
    jsonObject.password = password;
    jsonObject.fullName = fullName;
    jsonObject.department = department;
    jsonObject.address = address;
    jsonObject.age = age;

    jsonData = JSON.stringify(jsonObject);

    $.ajax({
        url: "employees/create",
        type: "POST",
        dataType: "json",
        data: jsonData,
        success: function (response) {
            if(response.errors == null) {
                console.log("success");
                location.href = "employees";
            } else{
                console.log("errors");
                var errors = response.errors;
                $("#username-error").text("");
                $("#password-error").text("");
                $("#fullname-error").text("");
                $("#department-error").text("");
                $("#address-error").text("");
                $("#age-error").text("");

                $("#username-error").text(errors.userName);
                $("#password-error").text(errors.password);
                $("#fullname-error").text(errors.fullName);
                $("#department-error").text(errors.department);
                $("#address-error").text(errors.address);
                $("#age-error").text(errors.age);
            }
        },

        error:function() {
            alert("error");
        }
    });
}

