(function (angular) {
    'use strict';

    angular.module("app.invoices").controller("updateInvoiceModalController", updateInvoiceModalController);

    updateInvoiceModalController.$inject = ['$scope', '$modalInstance', 'invoice']
    function updateInvoiceModalController($scope, $modalInstance, invoice) {
        $scope.invoice = invoice;
        $scope.update = function () {
            $modalInstance.close($scope.invoice);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }
    
}(angular));