(function () {
    'use strict';

    angular.module('app.invoices')
        .controller('invoiceController', invoiceController)

    invoiceController.$inject = ['$scope', 'invoiceService', '$log'];
    function invoiceController($scope, invoiceService, $log) {
        $scope.invoice = invoiceService.data;
    }

})();