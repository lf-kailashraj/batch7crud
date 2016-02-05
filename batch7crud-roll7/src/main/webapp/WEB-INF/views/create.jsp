<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Prajjwal Raj Kandel <prajjwalkandel@lftechnology.com>
  Date: 2/3/16
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <base href="${pageContext.request.contextPath}/">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="static/css/reset.css" />
  <link rel="stylesheet" type="text/css" href="static/css/layout.css" />

  <title>New Entry</title>
</head>

<body>

<div class="header">
  <div class="header-text">
    SHREE KAMANA BOARDING HIGH SCHOOL
  </div>

</div>

<div class="mainbody clr">
  <div class="navigation">
    <ul>
      <li><a href="students">View All</a></li>
      <li><a href="logOut">Log Out</a></li>
    </ul>
  </div>
  <div class = "content">
    <div class="login-container">
      NEW ENTRY FORM
      <form id="ajaxForm">
        <input id="name" type="text" name="name" placeholder="Name" value="${param.name}"/><span id="nameError" class="error">${error.name}</span>
        <input id="address" type="text" name="address" placeholder="Address" value="${param.address}"/><span id="addressError" class="error">${error.address}</span>
        <input id="roll" type="number" name="roll" placeholder="Roll" value = "${param.roll}"/><span id ="rollError" class="error">${error.roll}</span>
        <input type="submit"/>
      </form>
    </div>
  </div>

</div>

<div class="footer">
  <div class="copyright">Copyright</div>
  <div class="contact">Contact</div>
</div>
<script type="text/javascript" src="static/js/jquery-2.2.0.js"></script>
<script>
  $('#ajaxForm').submit(function (e) {
    console.log("hello");
    e.preventDefault();
    submitForm();
  });


  function submitForm() {
    var name = $('#name').val();
    var address = $('#address').val();
    var roll = $('#roll').val();


    var jsonObject = new Object();
    jsonObject.name = name;
    jsonObject.address = address;
    jsonObject.roll = roll;

    var jsonData = JSON.stringify(jsonObject);
    console.log("jsonObject",jsonObject);
    console.log("jsonData ",jsonData);

    $.ajax(
      {
        url: "students/create",
        type: "POST",
        data: jsonData,

        success: function (data) {
          console.log("data ",data);
          if(data) {
            $(".error").empty();
            $("#nameError").text(data.name);
            $("#addressError").text(data.address);
            $("#rollError").text(data.roll);
          }
          else
            window.location.replace("students");
        }
      });
  }

</script>

</body>
</html>

