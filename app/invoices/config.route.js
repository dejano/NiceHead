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

    getSentInvoices.$inject = ['dataService', "$location"];
    function getSentInvoices(dataService, $location) {
        return dataService.Invoice.getSent().error(function () {
            $location.path('/');
        });
    }

    getReceivedInvoices.$inject = ['dataService', '$location'];
    function getReceivedInvoices(dataService, $location) {
        return dataService.Invoice.getReceived().error(function () {
            $location.path('/');
        });
    }

    getPendingInvoices.$inject = ['dataService', '$cookies', '$location'];
    function getPendingInvoices(dataService, $cookies, $location) {
        var user = JSON.parse($cookies.user);
        for (var i = 0; i < user.roles.length; i++) {
            var role = user.roles[i];
            if (role.name == 'employee' || role.name == 'super') {
                return dataService.Invoice.getPending().error(function () {
                    $location.path('/');
                });
            }
            return dataService.Invoice.getPendingWithState(role.name.trim()).error(function () {
                $location.path('/');
            });
        }
    }

    //getPendingInvoicesWithState.$inject = ['dataService', '$route'];
    //function getPendingInvoicesWithState(dataService, $route) {
    //    return dataService.Invoice.getPendingWithState($route.current.params.state);
    //}

    getInvoice.$inject = ['dataService', '$route', '$location'];
    function getInvoice(dataService, $route, $location) {
        return dataService.Invoice.get($route.current.params.id).error(function () {
            $location.path('/');
        });
    }

})();
