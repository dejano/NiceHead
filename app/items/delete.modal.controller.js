(function (angular, undefined) {
    'use strict';

    angular.module("app.invoices.items").controller("deleteItemModalController", deleteItemModalController);

    deleteItemModalController.$inject = ['$scope', '$modalInstance', 'item']
    function deleteItemModalController($scope, $modalInstance, item) {
        $scope.item = item;
        $scope.yes = function () {
            $modalInstance.close(item);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }
}(angular));