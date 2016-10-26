angular.module('lunchKompis', ['ngRoute', 'lunchKompis.lunchlist'])
    .config(['$routeProvider', function ($routeProvider){
        $routeProvider
            .when('/', {
                templateUrl: "../../scripts/lunchlist-template.html",
                controller: 'lunchlistCtrl',
                controllerAs: "vm"
            });
    }]);

    // .controller('groupCtrl', ['$scope', '$http', '$window', lunchApp.controllers.AppController])
    // .controller('mapCtrl', ['$scope', '$http', lunchApp.controllers.MapController]);
