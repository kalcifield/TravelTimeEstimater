
$(document).ready(function () {
    getLocation();
});

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
    } else {
        alert("Geolocation is not supported by this browser.");
    }
}

function showPosition(position) {
    var latitude = position.coords.latitude;
    var longitude = position.coords.longitude;
    var apiQuery = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," +longitude+"&sensor=false";

    $.getJSON(apiQuery, function (data) {
        var address = data.results[0].formatted_address;
        alert(address);
        $("body").append("<h1>"+ address +"<h1/>");
    });
}
