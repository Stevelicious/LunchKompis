if (!lunchKompis)
    var lunchKompis = {};

if (!lunchKompis.lunchlist)
    lunchKompis.lunchlist = {};

lunchKompis.lunchlist.AppController = function ($scope, $http, $window){

    var self = this;

    $http.get('/api/groups').success(function(data) {
        $scope.lunchgroups = data;
    });
    $scope.addToGroup = function(group) {
        $http.put('/api/groups/' + group.lunchid).success(function(data) {
        });
        $window.location.reload();
    };
    $scope.createLunch = function() {
        $http.post('/api/groups/').success(function(data) {
            console.log(data);
        });
    };
}