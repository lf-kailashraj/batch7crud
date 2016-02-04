var deleteUser = document.getElementsByClassName("deleteUser");

for (var i = 0; i < deleteUser.length; i++) {

	deleteUser[i].onclick = function(event) { //NOSONAR
		event.preventDefault();
		var href = this.getAttribute("href");
		var confirmation = confirm("Do you want to delete?");

		if (confirmation === true) {
			var form = document.createElement("form");
			form.action = href;
			form.method = "post";
			form.submit();
		}
	}
}