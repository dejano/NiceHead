(function () {
    'use strict';

    angular.module('app.invoices')
        .controller('invoiceController', invoiceController)

    invoiceController.$inject = ['$scope', 'invoiceService', 'dataService', '$log', '$modal', '$route', 'toastr', 'location'];
    function invoiceController($scope, invoiceService, dataService, $log, $modal, $route, toastr, $location) {
        $scope.invoice = invoiceService.data;

        $scope.approve = approve;

        $scope.reject = reject;

        // update invoice modal
        $scope.openUpdateModal = openUpdateModal;

        ///////////////

        function successCallback(title, message) {
            toastr.success(message, title);
            $location.path( "/dashboard" );
        }

        function reject() {
            dataService.Invoice.reject($scope.invoice._id).success(successCallback('Invoice successfully rejected'))
        }

        function approve() {
            dataService.Invoice.approve($scope.invoice).success(successCallback('Invoice successfully approved'));
        }

        function openUpdateModal(invoice) {
            var invoiceToEdit = angular.copy(invoice);
            var modalInstance = $modal.open({
                animation: true,
                templateUrl: 'invoices/update-invoice.html',
                controller: 'updateInvoiceModalController',
                resolve: {
                    invoice: function () {
                        return invoiceToEdit;
                    }
                }
            });

            modalInstance.result.then(function (invoice) {
                dataService.Invoice.update(invoice).success(function (data) {
                    console.log(data);
                    console.log($scope.invoice);
                    $route.reload();
                });
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        }
    }

})();