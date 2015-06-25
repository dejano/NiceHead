(function (angular, undefined) {
    'use strict';
    var DeleteItemModalCtrl = function ($scope, $modalInstance, item) {
        $scope.item = item;
        $scope.yes = function () {
            $modalInstance.close(item);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    };

    angular.module("company").controller("DeleteItemModalCtrl", DeleteItemModalCtrl);
}(angular));