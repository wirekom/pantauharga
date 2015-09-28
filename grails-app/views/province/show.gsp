<%@ page import="com.pantau.core.Province" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'province.label', default: 'Province')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<!-- Page Heading -->
<div class="row">
    <div class="col-lg-12">
        <ol class="breadcrumb">
            <li><i class="fa fa-dashboard"></i> <a class="home" href="${createLink(uri: '/')}"><g:message
                    code="default.home.label"/></a></li>
            <li class="active"><i class="fa fa-list"></i> <g:link class="list" action="index"><g:message
                    code="default.list.label" args="[entityName]"/></g:link></li>
            <li class="active"><i class="fa fa-plus"></i> <g:link class="create" action="create"><g:message
                    code="default.new.label" args="[entityName]"/></g:link></li>
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
                    <i class="fa fa-eye"></i>
                    <g:message code="default.show.label" args="[entityName]"/>
                </h3>
            </div>
            <div id="map" style="height: 550px;"></div>
            <table class="table table-striped table-bordered detail-view province">

                <g:if test="${provinceInstance?.description}">
                    <tr><th>
                        <span id="description-label" class="property-label"><g:message code="province.description.label"
                                                                                       default="Description"/></span>
                    </th><td>

                        <span class="property-value" aria-labelledby="description-label"><g:fieldValue
                                bean="${provinceInstance}" field="description"/></span>

                    </td></tr>
                </g:if>

                <g:if test="${provinceInstance?.geometry}">
                    <tr><th>
                        <span id="geometry-label" class="property-label"><g:message code="province.geometry.label"
                                                                                    default="Geometry"/></span></th><td>

                    </td></tr>
                </g:if>

                <g:if test="${provinceInstance?.dateCreated}">
                    <tr><th>
                        <span id="dateCreated-label" class="property-label"><g:message code="province.dateCreated.label"
                                                                                       default="Date Created"/></span>
                    </th><td>

                        <span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate
                                date="${provinceInstance?.dateCreated}"/></span>

                    </td></tr>
                </g:if>

                <g:if test="${provinceInstance?.district}">
                    <tr><th>
                        <span id="district-label" class="property-label"><g:message code="province.district.label"
                                                                                    default="District"/></span></th><td>

                        <g:each in="${provinceInstance.district}" var="d">
                            <span class="property-value" aria-labelledby="district-label"><g:link controller="district"
                                                                                                  action="show"
                                                                                                  id="${d.id}">${d?.encodeAsHTML()}</g:link></span>
                        </g:each>

                    </td></tr>
                </g:if>

                <g:if test="${provinceInstance?.lastUpdated}">
                    <tr><th>
                        <span id="lastUpdated-label" class="property-label"><g:message code="province.lastUpdated.label"
                                                                                       default="Last Updated"/></span>
                    </th><td>

                        <span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate
                                date="${provinceInstance?.lastUpdated}"/></span>

                    </td></tr>
                </g:if>

                <g:if test="${provinceInstance?.name}">
                    <tr><th>
                        <span id="name-label" class="property-label"><g:message code="province.name.label"
                                                                                default="Name"/></span></th><td>

                        <span class="property-value" aria-labelledby="name-label"><g:fieldValue
                                bean="${provinceInstance}" field="name"/></span>

                    </td></tr>
                </g:if>

            </table>

            <div class="clearfix"></div>

            <div class="panel-footer">
                <g:form url="[resource: provinceInstance, action: 'delete']" method="DELETE">
                    <fieldset class="buttons">
                        <g:link class="btn btn-primary edit" action="edit" resource="${provinceInstance}"><g:message
                                code="default.button.edit.label" default="Edit"/></g:link>
                        <g:actionSubmit class="btn btn-primary delete" action="delete"
                                        value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
                    </fieldset>
                </g:form>
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
        map.data.loadGeoJson('${createLink(action: 'listGeoJSON')}');
        map.data.setStyle(function (feature) {
            var color = 'gray';
            if (feature.getProperty('isColorful')) {
                color = feature.getProperty('color');
            }
            return /** @type {google.maps.Data.StyleOptions} */
            ({
                fillColor: color,
                strokeColor: color,
                strokeWeight: 2
            });
        });
        // balloon
        var infoWindow = new google.maps.InfoWindow({
            content: "",
            pixelOffset: new google.maps.Size(0, -30)
        });
        // When the user clicks, set 'isColorful', changing the color of the letters.
        map.data.addListener('click', function (event) {

            event.feature.setProperty('isColorful', true);



            infoWindow.setContent('<div>' + event.feature.getProperty('name') + '</div>');

            var anchor = new google.maps.MVCObject();
            anchor.setValues({ //position of the point
                position: event.latLng,
                anchorPoint: new google.maps.Point(0, -40)
            });

            infoWindow.open(map, anchor);

        });

        // When the user hovers, tempt them to click by outlining the letters.
        // Call revertStyle() to remove all overrides. This will use the style rules
        // defined in the function passed to setStyle()
        map.data.addListener('mouseover', function (event) {
            map.data.revertStyle();
            map.data.overrideStyle(event.feature, {
                strokeWeight: 8
            });
        });

        map.data.addListener('mouseout', function (event) {
            map.data.revertStyle();
        });
        // Try HTML5 geolocation.
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                var pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };
                var geocoder = new google.maps.Geocoder;
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
    }

</script>
<script src="https://maps.googleapis.com/maps/api/js?signed_in=true&callback=initMap&region=id&language=id_ID"
        async defer>
</script>
</body>
</html>
