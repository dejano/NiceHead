(function (angular) {
    'use strict';

    angular.module("app.invoices.items").controller("createItemModalController", createItemModalController);

    createItemModalController.$inject = ['$scope', '$modalInstance'];
    function createItemModalController($scope, $modalInstance) {
        $scope.save = function () {
            $modalInstance.close($scope.item);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

}(angular));