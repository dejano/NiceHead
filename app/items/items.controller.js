(function () {
    'use strict';

    angular.module('app.invoices.items')
        .controller('itemsController', itemsController);

    itemsController.$inject = ['$scope', 'items', '$log', '$routeParams', '$modal', 'dataService', '$route'];
    function itemsController($scope, items, $log, $routeParams, $modal, dataService, $route) {
        $scope.routeId = $routeParams.id;
        $scope.items = items.data;

        $scope.dropdown = [
            {'key': '>', value: 'Greater'},
            {'key': '==', value: 'Equals'},
            {'key': '<', value: 'Below'}
        ];

        $scope.compareOperatorQuantity = $scope.dropdown[0];
        $scope.compareOperatorDiscount = $scope.dropdown[0];

        $scope.quantityProperty = "quantity";
        $scope.discountProperty = "price_with_discount";

        // create item modal
        $scope.openCreateModal = openCreateModal;

        // update item modal
        $scope.openUpdateModal = openUpdateModal;

        // delete item modal
        $scope.openDeleteModal = openDeleteModal;


        ///////////////////////////////
        function openCreateModal() {
            var modalInstance = $modal.open({
                animation: true,
                templateUrl: 'items/create-item.html',
                controller: 'createItemModalController'
            });

            modalInstance.result.then(function (item) {
                dataService.Item.create($routeParams.id, item).success(function (data) {
                    $route.reload();
                });
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        }

        function openUpdateModal(item) {
            var itemToEdit = angular.copy(item);
            var modalInstance = $modal.open({
                animation: true,
                templateUrl: 'items/update-item.html',
                controller: 'updateItemModalController',
                resolve: {
                    item: function () {
                        return itemToEdit;
                    }
                }
            });

            modalInstance.result.then(function (item) {
                dataService.Item.update($routeParams.id, item).success(function (data) {
                    $route.reload();
                });
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        }

        function openDeleteModal(item) {
            var modalInstance = $modal.open({
                animation: true,
                templateUrl: 'items/delete-item-modal.html',
                controller: 'deleteItemModalController',
                resolve: {
                    item: function () {
                        return item;
                    }
                }
            });

            modalInstance.result.then(function (item) {
                if (item._id !== undefined) {
                    var itemToDelete = item;
                    dataService.Item.delete($routeParams.id, item._id).success(function (data) {
                        var index = $scope.items.indexOf(itemToDelete);
                        $scope.items.splice(index, 1);
                    });
                }
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        }
    }

})();