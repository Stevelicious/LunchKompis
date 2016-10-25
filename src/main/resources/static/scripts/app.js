angular.module('lunchApp', [])
    .controller('groupCtrl', ['$scope', '$http', '$window', lunchApp.controllers.AppController])
    .controller('mapCtrl', ['$scope', '$http', lunchApp.controllers.MapController]);
