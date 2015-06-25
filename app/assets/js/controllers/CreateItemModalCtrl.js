(function (angular, undefined) {
    'use strict';
    var CreateItemModalCtrl = function ($scope, $modalInstance) {
        $scope.save = function () {
            $modalInstance.close($scope.item);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    };

    angular.module("company").controller("CreateItemModalCtrl", CreateItemModalCtrl);
}(angular));