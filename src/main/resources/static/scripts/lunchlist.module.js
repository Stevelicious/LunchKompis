angular.module('lunchKompis.lunchlist', [])
    .controller('lunchlistCtrl', ['$scope', '$http', '$window', lunchKompis.lunchlist.AppController])
    .directive('lunch', lunchKompis.lunchlist.lunchListDirective);