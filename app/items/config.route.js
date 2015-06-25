(function() {
    'use strict';

    angular
        .module('app.invoices.items')
        .run(appRun);

     appRun.$inject = ['routeHelper'];
    function appRun(routeHelper) {
        routeHelper.configureRoutes(getRoutes());
    }

    function getRoutes() {
        return [
            {
                url: '/invoices/:id/items',
                config: {
                    templateUrl: 'items/items.html',
                    controller: 'itemsController',
                    title: 'Invoices',
                    resolve: {
                        items: getItems
                    }
                }
            }
        ];
    }


    getItems.$inject =['dataService', '$route'];
    function getItems(dataService, $route) {
        return dataService.Item.getAll($route.current.params.id);
    }

})();
