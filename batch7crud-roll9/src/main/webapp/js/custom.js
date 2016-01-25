/**
 * Created by sanjay on 1/21/16.
 */
var deleteElement = document.getElementsByClassName("delete");
for (var i = 0; i < deleteElement.length; i++) {
    deleteElement[i].onclick = function (e) {
        e.preventDefault();
        var form = document.createElement('form');
        var destinationLink = e.target.getAttribute("href");
        form.setAttribute("method", "POST");
        form.setAttribute("action", destinationLink);
        if (confirm("Are you sure you want to delete this?") == true) {
            form.submit();
        }
    };
}

var editElement = document.getElementsByClassName("edit");
for (var i = 0; i < deleteElement.length; i++) {
    editElement[i].onclick = function (e) {
        e.preventDefault();
        var form = document.createElement('form');
        var destinationLink = e.target.getAttribute("href");
        form.setAttribute("method", "GET");
        form.setAttribute("action", destinationLink);
        form.submit();
    };
}

var viewElement = document.getElementsByClassName("view");
for (var i = 0; i < viewElement.length; i++) {
    viewElement[i].onclick = function (e) {
        e.preventDefault();
        var form = document.createElement('form');
        var destinationLink = e.target.getAttribute("href");
        form.setAttribute("method", "GET");
        form.setAttribute("action", destinationLink);
        form.submit();
    };
}
