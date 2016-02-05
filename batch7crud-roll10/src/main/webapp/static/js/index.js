var deleteBtn = document.querySelectorAll("a.delete");

for (var i = 0; i < deleteBtn.length; i++) {
    deleteBtn[i].onclick = function (e) { // NOSONAR
        e.preventDefault();
        var href = this.getAttribute("href");
        var confirmation = confirm("Do you want to delete?");

        if (confirmation) {
            var form = document.createElement("form");
            form.action = href;
            form.method = "post";
            form.submit();
        }
    };
}