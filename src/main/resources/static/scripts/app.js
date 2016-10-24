angular.module('lunchApp', [])
    .controller('groupCtrl', ['$scope', '$http', lunchApp.controllers.AppController])
    .controller('mapCtrl', ['$scope', '$http', lunchApp.controllers.MapController]);
