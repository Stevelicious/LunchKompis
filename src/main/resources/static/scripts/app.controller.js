if (!lunchApp)
    var lunchApp = {};

if (!lunchApp.controllers)
    lunchApp.controllers = {};

lunchApp.controllers.AppController = function ($scope, $http, $window){
    $http.get('/api/groups').success(function(data) {
        $scope.lunchgroups = data;
    });
    $scope.addToGroup = function(group) {
        $http.put('/api/groups/' + group.lunchid).success(function(data) {
        });
        $window.location.reload();
    }
}
