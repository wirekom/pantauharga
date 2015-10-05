<%@ page import="com.pantau.core.ComodityInput" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="login">
    <g:set var="entityName"
           value="${message(code: 'comodityInput.label', default: 'ComodityInput')}"/>
    <title><g:message code="default.map.label" args="[entityName]"/></title>
</head>

<body>
<!-- Page Heading -->
<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><i class="fa fa-dashboard"></i> <a class="home"
                                                   href="${createLink(uri: '/')}"><g:message
                        code="default.home.label"/></a></li>
            <li class="active"><i class="fa fa-list"></i> <g:link class="list" action="index"><g:message
                    code="default.list.label" args="[entityName]"/></g:link></li>
            <li class="active"><i class="fa fa-plus"></i> <g:link
                    class="create" action="create">
                <g:message code="default.new.label" args="[entityName]"/>
            </g:link></li>
        </ol>
    </div>
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <g:if test="${flash.message}">
            <div class="alert alert-info" role="status">${flash.message}</div>
        </g:if>
        HEHEHE >> ${location}
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <i class="fa fa-list"></i>
                    <g:message code="default.map.label" args="[entityName]"/>
                </h3>
            </div>

            <div class="panel-body map">
                <div id="map" style="height: 550px;"></div>
            </div>
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
                var geocoder = new google.maps.Geocoder;
                var infoWindow = new google.maps.InfoWindow;
                geocodeLatLng(geocoder, map, pos, infoWindow);

            }, function () {
                console.log('error geolocation not supported');
            });
        } else {
            // Browser doesn't support Geolocation
            console.log('error geolocation not supported');
        }
        function geocodeLatLng(geocoder, map, location, infowindow) {
            geocoder.geocode({'location': location}, function (results, status) {
                if (status === google.maps.GeocoderStatus.OK) {
                    if (results[1]) {
                        map.setZoom(5);
                        map.setCenter(location);
                        var marker = new google.maps.Marker({
                            position: location,
                            map: map
                        });
                        var info = '';
                        for(var i = 0;i < results.length; i++)
                            info += i + ' > ' + results[i].formatted_address + '<br />';
                        infowindow.setContent('Your Location!<br />' + info);
                        infowindow.open(map, marker);
                    } else {
                        window.alert('No results found');
                    }
                } else {
                    window.alert('Geocoder failed due to: ' + status);
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
