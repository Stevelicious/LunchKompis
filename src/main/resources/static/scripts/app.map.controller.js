if (!lunchApp)
    var lunchApp = {};

if (!lunchApp.controllers)
    lunchApp.controllers = {};


lunchApp.controllers.MapController = function ($scope, $http) {
    var usedTitles = [];
    Array.prototype.contains = function(obj) {
        var i = this.length;
        while (i--) {
            if (this[i] == obj) {
                return true;
            }
        }
        return false;
    }


    var query = "vegabaren";
    var osmid;
    $http.get('http://nominatim.openstreetmap.org/search.php?format=json&q='+encodeURI(query)).success(function (data) {
        osmid = data[0].osm_id;
        console.log(osmid);
    });

    $scope.setLoc = function (lunch) {
        if(!(usedTitles.contains(lunch.lunchid))){
            $http.get('http://nominatim.openstreetmap.org/reverse?format=json&osm_type=N&osm_id=' + encodeURI(osmid)).success(function (data) {
                console.log(lunch.lunchid);
                console.log(data);
                init(data.lat, data.lon, lunch.lunchid);
                usedTitles.push(lunch.lunchid);
            })
        }
    }

    var zoom = 18;
    var map;

    function init(lat, lon, lunchid) {
        map = new OpenLayers.Map(lunchid.toString(), {
            controls: [
                new OpenLayers.Control.Navigation(),
                new OpenLayers.Control.MousePosition(),
                new OpenLayers.Control.Attribution()],
            maxExtent: new OpenLayers.Bounds(-20037508.34, -20037508.34, 20037508.34, 20037508.34),
            maxResolution: 156543.0339,
            numZoomLevels: 19,
            units: 'm',
            projection: new OpenLayers.Projection("EPSG:900913"),
            displayProjection: new OpenLayers.Projection("EPSG:4326")
        });

        layerMapnik = new OpenLayers.Layer.OSM.Mapnik("Mapnik");
        map.addLayer(layerMapnik);

        if (!map.getCenter()) {
            var lonLat = new OpenLayers.LonLat(lon, lat).transform(new OpenLayers.Projection("EPSG:4326"), map.getProjectionObject());
            map.setCenter(lonLat, zoom);
            var marker = new OpenLayers.Layer.Markers("Markers");
            var size = new OpenLayers.Size(20, 20);
            var offset = new OpenLayers.Pixel(-(size.w / 2), -(size.h / 2));
            var icon = new OpenLayers.Icon('/scripts/marker.png', size, offset);
            map.addLayer(marker);
            marker.addMarker(new OpenLayers.Marker(lonLat, icon));
        }
    }

};