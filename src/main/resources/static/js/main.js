$(document).ready(function () {

    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

    /* var parsedJson = jQuery.parseJSON('{"name":"john","hello"}');

     var names = JSON.stringify(parsedJson.name).split(",");
     alert(names[0]);
     $("#auditor").append("<option value=1>" + "My option</option>");*/

    load_dropdown();


});

function fire_ajax_submit() {

    // Get form
    var form = $('#exampleForm')[0];

    //var fd = new FormData(form).serialize();

    var fd = "abc";

    //data.append("CustomField", "This is some extra data, testing");

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/test/postrequest",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        data: fd,
               //http://api.jquery.com/jQuery.ajax/
               //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
               processData: false, //prevent jQuery from automatically transforming the data into a query string
               contentType: false,
               cache: false,
               timeout: 600000,
          success: function (data) {

            $("#result").text(data);
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);

        },
       error: function (e) {
                  $("#result").text(e.responseText);
                  console.log("ERROR : ", e);
                  $("#btnSubmit").prop("disabled", false);

              }
    });

}
function load_dropdown() {

    var data;

    $.ajax({
        type: "GET",
        url: "/api/getAuditorList",
        data:data,
        processData: false,
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
            var auditors = data.auditors;
            console.log(auditors);
                        for (index = 0; index < auditors.length; index++) {
                            $("#auditor").append("<option value="+index+">" + auditors[index] + "</option>");
                        }
        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}