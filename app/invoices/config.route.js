(function () {
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
                        invoiceService: getReceivedInvoices
                    }
                }
            },
            {
                url: '/invoices/pending',
                config: {
                    templateUrl: 'invoices/invoices.html',
                    controller: 'invoicesController',
                    title: 'Invoices',
                    resolve: {
                        invoiceService: getPendingInvoices
                    }
                }
            },
            {
                url: '/invoices/sent',
                config: {
                    templateUrl: 'invoices/invoices.html',
                    controller: 'invoicesController',
                    title: 'Invoices',
                    resolve: {
                        invoiceService: getSentInvoices
                    }
                }
            },
            {
                url: '/invoices/create',
                config: {
                    templateUrl: 'invoices/create-invoice.html',
                    controller: 'createInvoiceController',
                    title: 'Create Invoice',
                    resolve: {
                        partners: getPartners
                    }
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

    getPartners.$inject = ['dataService'];
    function getPartners(dataService) {
        return dataService.Partner.getAll();
    }

    getSentInvoices.$inject = ['dataService'];
    function getSentInvoices(dataService) {
        return dataService.Invoice.getSent();
    }

    getReceivedInvoices.$inject = ['dataService'];
    function getReceivedInvoices(dataService) {
        return dataService.Invoice.getReceived();
    }

    getPendingInvoices.$inject = ['dataService', '$cookies'];
    function getPendingInvoices(dataService, $cookies) {
        var user = JSON.parse($cookies.user);
        for(var i = 0; i < user.roles.length; i++) {
            var role = user.roles[i];
            if (role.name == 'employee') {
                return dataService.Invoice.getPending();
            }
            return dataService.Invoice.getPendingWithState(role.name.trim());
        }
    }

    //getPendingInvoicesWithState.$inject = ['dataService', '$route'];
    //function getPendingInvoicesWithState(dataService, $route) {
    //    return dataService.Invoice.getPendingWithState($route.current.params.state);
    //}

    getInvoice.$inject = ['dataService', '$route'];
    function getInvoice(dataService, $route) {
        return dataService.Invoice.get($route.current.params.id);
    }

})();
