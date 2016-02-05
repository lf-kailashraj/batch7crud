$('#create').click(function(e) {
	e.preventDefault();
	var firstName = $('#firstName').val()
	var lastName = $('#lastName').val()
	var password = $('#password').val()
	var department = $('#department').val();
	var address = $('#address').val();
	var jsonDataObject = new Object();
	jsonDataObject.firstName = firstName;
	jsonDataObject.lastName = lastName;
	jsonDataObject.password = password;
	jsonDataObject.department = department;
	jsonDataObject.address = address;
	var jsonData = JSON.stringify(jsonDataObject);

	$.ajax({
		url : 'employees/createUsingAjax',
		type : 'POST',
		dataType : 'json',
		data : jsonData,

		success : function(message) {
			if (message.success) {
				console.log('success');
				window.location.replace("employees");
			} else {
				$('.error').empty();
				$('.error:eq(0)').html(message.firstName);
				$('.error:eq(1)').html(message.lastName);
				$('.error:eq(2)').html(message.password);
				$('.error:eq(3)').html(message.department);
				$('.error:eq(4)').html(message.address);
			}
		}
	});
});