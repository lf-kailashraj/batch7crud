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
    var employee = {
        userName : $('#username').val(),
        password : $('#password').val(),
        fullName : $('#fullname').val(),
        department : $("#department").val(),
        address : $("#address").val(),
        age : $("#age").val()
    };

    var employeeData = JSON.stringify(employee);

    $.ajax({
        url: "employees/create",
        type: "POST",
        dataType: "json",
        data: employeeData,
        success: function (response) {
            if(response.errors == null) {
                location.href = "employees";
            } else{
                $("#username-error").text("");
                $("#password-error").text("");
                $("#fullname-error").text("");
                $("#department-error").text("");
                $("#address-error").text("");
                $("#age-error").text("");

                var errors = response.errors;
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

