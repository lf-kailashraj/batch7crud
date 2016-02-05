function mapToJson() {

  var jsonData = new Object(); // NOSONAR
  jsonData.firstname = $("#firstname").val();
  jsonData.surname = $("#surname").val();
  jsonData.username = $("#username").val();
  jsonData.password = $("#password").val();
  jsonData.age = $("#age").val();

  var jsonStr = JSON.stringify(jsonData);

  $.ajax({
    url : "users/add",
    data : jsonStr,
    type : "POST",
    success : function(response) {
      if (response) {
        $('.error').empty();
        $('.error:eq(0)').html(response.firstname);
        $('.error:eq(1)').html(response.surname);
        $('.error:eq(2)').html(response.username);
        $('.error:eq(3)').html(response.password);
        $('.error:eq(4)').html(response.age);
      }else{
        window.location.replace("users");

      }
    }
  });
};
