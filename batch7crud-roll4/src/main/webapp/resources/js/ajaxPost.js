/**
 * Created by pratishshr on 2/3/16.
 */

$('#ajaxForm').submit(function (e) {
    e.preventDefault();
    submitForm(this.action);
});


function submitForm(submitUrl) {
    var firstName = $('#first-name').val();
    var lastName = $('#last-name').val();
    var station = $('#station').val();

    var jsonObject = {
        'firstName': firstName,
        'lastName': lastName,
        'station': station
    }

    var jsonData = JSON.stringify(jsonObject);

    $.ajax(
        {
            url: submitUrl,
            type: "POST",
            data: jsonData,

            success: function (errors) {
                if (errors) {
                    $('.error').empty();
                    $('.error:eq(0)').html(errors.firstName);
                    $('.error:eq(1)').html(errors.lastName);
                    $('.error:eq(2)').html(errors.station);
                } else {
                    window.location.replace("employees");
                }
            }
        });
}