(function (angular, undefined) {
    'use strict';
    var UpdateItemModalCtrl = function ($scope, $modalInstance, item) {
        $scope.item = item;
        $scope.update = function () {
            $modalInstance.close($scope.item);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    };

    angular.module("company").controller("UpdateItemModalCtrl", UpdateItemModalCtrl);
}(angular));