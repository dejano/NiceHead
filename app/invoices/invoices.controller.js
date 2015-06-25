(function () {
    'use strict';

    angular.module('app.invoices')
        .controller('invoicesController', invoicesController);

    invoicesController.$inject = ['$scope', 'invoiceService', '$log'];
    function invoicesController($scope, invoiceService, $log) {
        $scope.invoices = invoiceService.data;
        $scope.dropdownPrice = [
            {'key': '>', value: 'Greater'},
            {'key': '==', value: 'Equals'},
            {'key': '<', value: 'Below'}
        ];

        $scope.dropdownDate = [
            {'key': '<=', value: 'Before'},
            {'key': '>=', value: 'After'}
        ];

        $scope.compareOperatorPrice = $scope.dropdownPrice[0];
        $scope.compareOperatorBillingDate = $scope.dropdownDate[0];
        $scope.compareOperatorCurrencyDate = $scope.dropdownDate[0];

        $scope.billingDateProperty = "invoice_header.bill._date";
        $scope.currencyDateProperty = "invoice_header.payment.currency._date";
        $scope.priceProperty = "invoice_header.payment.amount";
    }
})();