if (!lunchKompis)
    var lunchKompis = {};

if (!lunchKompis.lunchlist)
    lunchKompis.lunchlist = {};

lunchKompis.lunchlist.lunchListDirective = function () {
    return {
        scope: {
            lunch: '='
        },
        restrict: 'A',
        templateUrl: '../../scripts/lunch-template.html',
        controller: ['$scope', '$http', lunchKompis.lunchlist.MapController]
    }
};