angular.module('lunchKompis', ['ngRoute', 'lunchKompis.lunchlist', 'lunchKompis.newLunch'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: "../../scripts/lunchlist-template.html",
                controller: 'lunchlistCtrl',
                controllerAs: "vm"
            })
            .when('/newlunch', {
                templateUrl: "../../scripts/new-lunch.template.html",
                controller: 'newLunchCtrl'
            });

    }]);

// .controller('groupCtrl', ['$scope', '$http', '$window', lunchApp.controllers.AppController])
// .controller('mapCtrl', ['$scope', '$http', lunchApp.controllers.MapController]);
