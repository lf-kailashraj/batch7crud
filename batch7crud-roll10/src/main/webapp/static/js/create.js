/**
 * Created by binodnme on 2/3/16.
 */
$('#createForm').submit(function (e) {
    e.preventDefault();
    submitForm();
});


function submitForm() {
    var name = $('#name').val();
    var address = $('#address').val();
    var date = $('#date').val();
    var department = $('#department').val();
    var batch = $('#batch').val();
    var roll = $('#roll').val();

    var jsonObject = new Object();
    jsonObject.name = name;
    jsonObject.address = address;
    jsonObject.dob = date;
    jsonObject.department = department;
    jsonObject.batch = batch;
    jsonObject.roll = roll;

    var jsonData = JSON.stringify(jsonObject);

    $.ajax(
        {
            url: "students/createTest",
            type: "POST",
            dataType: 'json',
            data: jsonData,

            success: function (response) {
                if(response.errors == null){
                    location.href = "students/list";
                }else{
                    var errors = response.errors;

                    $("#name-error").text("");
                    $("#address-error").text("");
                    $("#dob-error").text("");
                    $("#department-error").text("");
                    $("#batch-error").text("");
                    $("#roll-error").text("");

                    $("#name-error").text(errors.name);
                    $("#address-error").text(errors.address);
                    $("#dob-error").text(errors.dob);
                    $("#department-error").text(errors.department);
                    $("#batch-error").text(errors.batch);
                    $("#roll-error").text(errors.roll);


                }
            },
            error: function (xhr, ajaxOptions, thrownError) {
                console.log(xhr + " " + ajaxOptions + " " + thrownError);
            }
        });
}