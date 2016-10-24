if (!lunchApp)
    var lunchApp = {};

if (!lunchApp.controllers)
    lunchApp.controllers = {};

lunchApp.controllers.AppController = function ($scope, $http){
    $http.get('/api/groups').success(function(data) {
        $scope.lunchgroups = data;
    });
    $scope.addToGroup = function(group) {
        $scope.activeGroup = group;
    }
}
