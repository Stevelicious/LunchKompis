if (!lunchKompis)
    var lunchKompis = {};

if (!lunchKompis.lunchlist)
    lunchKompis.lunchlist = {};

lunchKompis.lunchlist.MapController = function ($scope, $http, $window) {
    var usedTitles = [];
    Array.prototype.contains = function (obj) {
        var i = this.length;
        while (i--) {
            if (this[i] == obj) {
                return true;
            }
        }
        return false;
    }

    $scope.setLoc = function (lunch) {

        if (!(usedTitles.contains(lunch.lunchid))) {
            $http.get('http://nominatim.openstreetmap.org/reverse?format=json&osm_type=' + encodeURI(lunch.osm_type).toUpperCase().charAt(0) + '&osm_id=' + encodeURI(lunch.osm_id)).success(function (data) {
                init(data.lat, data.lon, lunch.lunchid);
                usedTitles.push(lunch.lunchid);
            })
        }
    }

    $scope.addToGroup = function(lunch) {
        console.log("clicked add to group");
        console.log(lunch.lunchid);
        $http.post('/api/groups/' + lunch.lunchid).success(function(data) {
        });
        $window.location.reload();
    };

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