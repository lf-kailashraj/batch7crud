/**
 * Created by kiran on 2/1/16.
 */
var deleteButton = document.getElementsByClassName("delete");
for (var i = 0; i < deleteButton.length; i++) {
    deleteButton[i].onclick = function(e) {
        e.preventDefault();
        var href = this.getAttribute("href");
        var confirmation = confirm("do you want to delete?");

        if(confirmation === true) {
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