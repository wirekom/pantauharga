<%@ page import="com.pantau.core.ComodityInput" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="login">
    <g:set var="entityName"
           value="${message(code: 'comodity.label', default: 'ComodityMarshaller')}"/>
    <title><g:message code="default.map.label" args="[entityName]"/></title>
</head>

<body>
<br />
<div class="row">
    <div class="col-lg-12">
        <g:if test="${flash.message}">
            <div class="alert alert-info" role="status">${flash.message}</div>
        </g:if>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <i class="fa fa-map-marker"></i>
                    <g:message code="default.map.label" args="[entityName]"/>
                </h3>
            </div>

            <div id="map" style="height: 550px;"></div>
        </div>
    </div>
</div>

<script>
    // Note: This example requires that you consent to location sharing when
    // prompted by your browser. If you see the error "The Geolocation service
    // failed.", it means you probably did not give permission for the browser to
    // locate you.

    function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: -1.269160, lng: 116.825264},
            zoom: 5
        });

        // Try HTML5 geolocation.
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                var pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };


                map.setZoom(5);
                map.setCenter(pos);
                var marker = new google.maps.Marker({
                    draggable: true,
                    position: pos,
                    map: map
                });

                google.maps.event.addListener(marker, 'click', function () {
                    geocodeLatLng(geocoder, map, pos, marker, infoWindow);
                });
                google.maps.event.addListener(marker, 'dragend', function (event) {
                    var pos = {
                        lat: this.getPosition().lat(),
                        lng: this.getPosition().lng()
                    };
                    geocodeLatLng(geocoder, map, pos, marker, infoWindow);
                    console.log('Latitude ' + this.getPosition().lat());
                    console.log('Latitude ' + this.getPosition().lng());
                });
                var geocoder = new google.maps.Geocoder;
                var infoWindow = new google.maps.InfoWindow;
                geocodeLatLng(geocoder, map, pos, marker, infoWindow);

            }, function () {
                console.log('error geolocation not supported');
            });
        } else {
            // Browser doesn't support Geolocation
            console.log('error geolocation not supported');
        }
        function geocodeLatLng(geocoder, map, location, marker, infowindow) {
            geocoder.geocode({'location': location}, function (results, status) {
                if (status === google.maps.GeocoderStatus.OK) {
                    if (results.length > 0) {
                        var info = '';
                        for (var i = 0; i < results.length; i++)
                            info += i + ' > ' + results[i].formatted_address + '<br />';
                        info += 'Latitude : ' + location.lat + '<br />';
                        info += 'Longitude : ' + location.lng + '<br />';
                        infowindow.setContent('Your Location!<br />' + info);
                        infowindow.open(map, marker);
                    } else {
                        console.log('No results found');
                    }
                } else {
                    console.log('Geocoder failed due to: ' + status);
                }
            });
        }
    }

</script>
<script src="https://maps.googleapis.com/maps/api/js?signed_in=true&callback=initMap&region=id&language=id_ID"
        async defer>
</script>

</body>
</html>
