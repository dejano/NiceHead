(function() {
    'use strict';

    angular
        .module('app.invoices')
        .run(appRun);

     appRun.$inject = ['routeHelper'];
    function appRun(routeHelper) {
        routeHelper.configureRoutes(getRoutes());
    }

    function getRoutes() {
        return [
            {
                url: '/invoices',
                config: {
                    templateUrl: 'invoices/invoices.html',
                    controller: 'invoicesController',
                    title: 'Invoices',
                    resolve: {
                        invoiceService: getInvoices
                    }
                }
            },
            {
                url: '/invoices/create',
                config: {
                    templateUrl: 'invoices/create-invoice.html',
                    controller: 'createInvoiceController',
                    title: 'Create Invoice'
                }
            },
            {
                url: '/invoices/:id',
                config: {
                    templateUrl: 'invoices/invoice.html',
                    controller: 'invoiceController',
                    title: 'Invoice',
                    resolve: {
                        invoiceService: getInvoice
                    }
                }
            }

        ];
    }

    getInvoices.$inject =['dataService'];
    function getInvoices(dataService) {
        return dataService.Invoice.getAll();
    }

    getInvoice.$inject =['dataService', '$route'];
    function getInvoice(dataService, $route) {
        return dataService.Invoice.get($route.current.params.id);
    }

})();
