<!-- Start map block -->
<section id="map" class="content_wrapper_gray">
    <div class="container content_wrapper_section">
        <div class="main_title_wrapper">
            <div class="block_tile_wrapper">
                <h1 class="block_tile_1">Commodity Map</h1>
                <div class="icon_separator"></div>
            </div>

            <div class="clear"></div>
        </div>

        <div class="goo_wrapper">
            <div id="googlemaps">
                <div id="googlemap"></div>
            </div>
        </div>
    </div> <!-- End content wrapper section -->
</section>
<!-- End map block -->

<script>

    function initMap() {
        var tmpCenter = {lat: -1.269160, lng: 116.825264};
        var map = new google.maps.Map(document.getElementById('googlemap'), {
            center: tmpCenter,
            zoom: 5
        });

        var infowindow = new google.maps.InfoWindow();
        map.data.loadGeoJson('${createLink(controller: 'page', action: 'comodityInputGeoJSON')}');
        map.data.addListener('click', function (event) {
            var content = '';
            content += '<b>Comodity Type : </b>' + event.feature.getProperty("comodityType") + '<br />';
            content += '<b>Comodity Name : </b>' + event.feature.getProperty("comodityName") + '<br />';
            if( event.feature.getProperty("amount") > 0){
                content += '<b>Username : </b>' + event.feature.getProperty("username") + '<br />';
                content += '<b>Phone : </b>' + event.feature.getProperty("phone") + '<br />';
            }
            infowindow.setContent("<div style='width:250px;'>" + content + "</div>");
            // position the infowindow on the marker
            infowindow.setPosition(event.feature.getGeometry().get());
            // anchor the infowindow on the marker
            infowindow.setOptions({pixelOffset: new google.maps.Size(0, -30)});
            infowindow.open(map);
        });

        // Try HTML5 geolocation.
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                var pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };
                var geocoder = new google.maps.Geocoder;
                var info = new google.maps.InfoWindow;
                geocodeLatLng(geocoder, map, pos, info);

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