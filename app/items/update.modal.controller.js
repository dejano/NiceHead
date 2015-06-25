(function (angular) {
    'use strict';

    angular.module("app.invoices.items").controller("updateItemModalController", updateItemModalController);

    updateItemModalController.$inject = ['$scope', '$modalInstance', 'item']
    function updateItemModalController($scope, $modalInstance, item) {
        $scope.item = item;
        $scope.update = function () {
            $modalInstance.close($scope.item);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    };
}(angular));