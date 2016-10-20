angular.module('lunchApp', [])
.controller('groupCtrl', ['$scope', '$http', lunchApp.controllers.AppController])
    .controller('mapCtrl', function ($scope, $http) {
        $http.get('/scripts/groups.json').success(function (data) {
            $scope.locations = data.lunches;
        });
        $scope.setLoc = function (location) {
            init(location.lat,location.lon);
            console.log("funkar");
            console.log(location.lat);
            console.log(location.lon);
        }

        var zoom=18;

        var map; //complex object of type OpenLayers.Map

        //Initialise the 'map' object
        function init(lat, lon) {
            console.log(lat);
            console.log(lon);

            map = new OpenLayers.Map ("map", {
                controls:[
                    new OpenLayers.Control.Navigation(),
                    new OpenLayers.Control.MousePosition(),
                    new OpenLayers.Control.Attribution()],
                maxExtent: new OpenLayers.Bounds(-20037508.34,-20037508.34,20037508.34,20037508.34),
                maxResolution: 156543.0339,
                numZoomLevels: 19,
                units: 'm',
                projection: new OpenLayers.Projection("EPSG:900913"),
                displayProjection: new OpenLayers.Projection("EPSG:4326")
            } );

            layerMapnik = new OpenLayers.Layer.OSM.Mapnik("Mapnik");
            map.addLayer(layerMapnik);

            if( ! map.getCenter() ){
                var lonLat = new OpenLayers.LonLat(lon, lat).transform(new OpenLayers.Projection("EPSG:4326"), map.getProjectionObject());
                map.setCenter (lonLat, zoom);
            }
        }

    });