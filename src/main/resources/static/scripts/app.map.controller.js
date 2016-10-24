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

    $http.get('/scripts/groups.json').success(function (data) {
        $scope.locations = data.lunches;
    });
    $scope.setLoc = function (lunch) {
        if(!(usedTitles.contains(lunch.title))){
            console.log(usedTitles);
            init(lunch.lat, lunch.lon, lunch.title);
            usedTitles.push(lunch.title);
        }
    }

    var zoom = 18;
    var map; //complex object of type OpenLayers.Map

    //Initialise the 'map' object

    function init(lat, lon, title) {
        map = new OpenLayers.Map(title, {
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
        }
    }

};