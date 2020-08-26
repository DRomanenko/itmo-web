window.notify = function(message) {
    $.notify(message, {position: "bottom right"})
};

ajax = function (extensionRequest, error) {
    var basicRequest = {
        type: "POST",
        url: "",
        dataType: "json",

        // data: expected to be extended

        success: function (response) {
            if (response["error"]) {
                error.text(response["error"]);
            } else {
                location.href = response["redirect"];
            }
        }
    };

    $.extend(basicRequest, extensionRequest);
    $.ajax(basicRequest);
};
