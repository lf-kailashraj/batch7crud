var delBtn = document.getElementsByClassName('deleteBtn');

for (var i = 0; i < delBtn.length; i++) {
	delBtn[i].onclick = function(e) {
		e.preventDefault();

		var href = this.getAttribute('href');
		var doConfirm = confirm('Are you sure to delete??');

		if (doConfirm == true) {
			var form = document.createElement('form');

			form.action = href;
			form.method = 'post';

			document.body.appendChild(form);

			form.submit();

		}
	}

}