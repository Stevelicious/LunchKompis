if (!lunchApp)
    var lunchApp = {};

if (!lunchApp.controllers)
    lunchApp.controllers = {};

lunchApp.controllers.AppController = function ($scope, $http){
    $http.get('/scripts/groups.json').success(function(data) {
        $scope.lunchgroups = data.lunches;
    });
    $scope.addToGroup = function(group) {
        $scope.activeGroup = group;
    }
}
