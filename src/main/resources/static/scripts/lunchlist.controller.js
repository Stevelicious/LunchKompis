if (!lunchKompis)
    var lunchKompis = {};

if (!lunchKompis.lunchlist)
    lunchKompis.lunchlist = {};

lunchKompis.lunchlist.AppController = function ($scope, $http, $window){

    var self = this;

    $http.get('/api/groups').success(function(data) {
        $scope.lunchgroups = data;
    });
    $scope.addToGroup = function(lunch) {
        console.log("clicked add to group");
        $http.put('/api/groups/' + lunch.lunchid).success(function(data) {
        });
        // $window.location.reload();
    };
    $scope.createLunch = function(title) {
        var lunch = {
            title: title
        }
        $http.post('/api/groups/', lunch).success(function(data) {
            console.log(data);
        });
    };
}