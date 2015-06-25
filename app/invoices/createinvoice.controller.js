(function () {
    'use strict';

    angular.module('app.invoices')
        .controller('createInvoiceController', createInvoiceController)

    createInvoiceController.$inject = ['$scope', '$log', 'dataService', '$location', 'toastr'];
    function createInvoiceController($scope, $log, dataService, $location, toastr) {
        $scope.items = [];
        $scope.dateFormat = 'yyyy-MM-dd';

        //Date picker config
        $scope.today = function() {
            $scope.dt = new Date();
        };
        $scope.today();

        $scope.clear = function () {
            $scope.dt = null;
        };

        $scope.openDatepicker = function($event, opened) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope[opened] = true;
        };

        $scope.addItem = function () {
            var newItemId = $scope.items.length+1;
            $scope.items.push({_id: newItemId});
        };

        $scope.submit = function() {
            $scope.errorMessage = null;

            if (!$scope.items.length) {
                $scope.errorMessage = "please add at least one item.";
                return;
            }
            $scope.invoice.item = $scope.items;
            $scope.invoice = {invoice : $scope.invoice};
            dataService.Invoice.create($scope.invoice).success(function() {
                $location.path('/invoices');
            }).error(function() {
                toastr.error('Something went wrong. Provided data might not be valid. Is it?', 'Ops!');
            });

        };
    }

})();