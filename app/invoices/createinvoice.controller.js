(function () {
    'use strict';

    angular.module('app.invoices')
        .controller('createInvoiceController', createInvoiceController)

    createInvoiceController.$inject = ['$scope', '$log', 'dataService', '$location', 'toastr', 'partners'];
    function createInvoiceController($scope, $log, dataService, $location, toastr, partners) {

        //Date picker config
        $scope.today = today;

        $scope.clear = clear;

        $scope.openDatepicker = openDatepicker;

        $scope.addItem = addItem;

        $scope.submit = submit;

        activate();
        ////////////////

        function activate() {
            $scope.items = [];
            $scope.dateFormat = 'yyyy-MM-dd';
            $scope.partners = partners.data;
            $scope.invoice = {invoice_header: {buyer: $scope.partners[0], supplier: {pib: $scope.setupData.pib, name: $scope.setupData.name, address: $scope.setupData.address}}};
            $scope.today();
        }

        function today() {
            $scope.dt = new Date();
        }

        function clear() {
            $scope.dt = null;
        }

        function openDatepicker($event, opened) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope[opened] = true;
        }

        function addItem() {
            var newItemId = $scope.items.length + 1;
            $scope.items.push({_id: newItemId});
        }

        function submit() {
            $scope.errorMessage = null;

            if (!$scope.items.length) {
                $scope.errorMessage = "please add at least one item.";
                return;
            }
            $scope.invoice.item = $scope.items;
            $scope.invoice = {invoice: $scope.invoice};
            dataService.Invoice.create($scope.invoice).success(function () {
                $location.path('/invoices');
            }).error(function () {
                toastr.error('Something went wrong. Provided data might not be valid. Is it?', 'Ops!');
            });

        }
    }

})();