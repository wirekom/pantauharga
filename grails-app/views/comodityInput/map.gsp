<%@ page import="com.pantau.core.ComodityInput" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName"
           value="${message(code: 'comodity.label', default: 'ComodityMarshaller')}"/>
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
        </ol>
    </div>
</div>
<!-- /.row -->

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

            <g:form url="[action: 'map', controller: 'ComodityInput']" method="GET" role="form">
                <div class="panel-body">
                    <div class="form-group">
                        <label for="region">
                            <g:message code="comodityInput.start.label" default="Pilih Propinsi"/>
                            <span class="required-indicator">*</span>
                        </label>
                        <g:select name="inflationCommandModelInstance.region.id"
                                  from="${com.pantau.core.Region.where { parent == null }}"
                                  value="${idparent}"
                                  noSelection="['null': '-Nasional-']"
                                  optionKey="id"/>
                    </div>
                </div>

                <div class="panel-footer">
                    <g:submitButton name="calculate" class="btn btn-primary save"
                                    value="${message(code: 'default.button.calculates.label', default: 'Calculate')}"/>
                </div>
            </g:form>
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
                        infowindow.setContent('Your Location!<br />' + results[0].formatted_address);
                        infowindow.open(map, marker);
                        google.maps.event.addListener(marker, 'click', function () {
                            infowindow.open(map, marker);
                        });
                    } else {
                        window.alert('No results found');
                    }
                } else {
                    window.alert('Geocoder failed due to: ' + status);
                }
            });
        }

        <g:each in="${comodityInputInstanceList}" status="i"
							var="comodityInputInstance">
        var marker${i} = new google.maps.Marker({
            map: map,
            position: ${comodityInputInstance.location},
            clickable: true
        });

        marker${i}.info = new google.maps.InfoWindow({
            content: '<b>ComodityMarshaller:</b> ${comodityInputInstance?.comodityName?.name}</br>' + '<b>Price:</b> Rp. ${comodityInputInstance?.price}</br>'
        });

        google.maps.event.addListener(marker${i}, 'click', function () {
            marker${i}.info.open(map, marker${i});
        });
        </g:each>
    }

</script>
<script src="https://maps.googleapis.com/maps/api/js?signed_in=true&callback=initMap&region=id&language=id_ID"
        async defer>
</script>

</body>
</html>
