(function (angular, undefined) {
    'use strict';
    function ItemsController($scope, $routeParams, $modal, DataSource, $log, $route, $filter) {
        $scope.routeId = $routeParams.id;
        $scope.predicate = '_id';
        $scope.reverse = false;

        //This is callback on success
        var setData = function (result) {
            var item = result.data.resultWrapper.item;
            if (item !== undefined) {
                if (!Array.isArray(item)) {
                    $log.info('not an array');
                    $scope.items = new Array(item);
                } else {
                    $log.info('an array');
                    $scope.items = item;
                }
                $log.info("run success callback");
            }
        };

        var deleteCallback = function (result) {
            var index = $scope.items.indexOf($scope.itemToDelete);
            $scope.items.splice(index, 1);
        };

        // Modal dialog
        $scope.open = function (item) {
            var modalInstance = $modal.open({
                animation: true,
                templateUrl: '../../../views/delete-item-modal.html',
                controller: 'DeleteItemModalCtrl',
                resolve: {
                    item: function () {
                        return item;
                    }
                }
            });

            modalInstance.result.then(function (item) {
                $log.info(item);
                if (item._id !== undefined) {
                    $scope.itemToDelete = item;
                    DataSource.Items.delete('http://localhost:8080/partneri/' + $scope.pib + '/fakture/' + $routeParams.id + '/stavke/' + item._id).then(deleteCallback, onError);
                }
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        $scope.openCreate = function () {
            var modalInstance = $modal.open({
                animation: true,
                templateUrl: '../../../views/create_item.html',
                controller: 'CreateItemModalCtrl'
            });

            modalInstance.result.then(function (item) {
                $log.info(item);
                item._id = 0;
                var itemToSave = {item: item};
                var X2js = new X2JS();
                var itemXmlString = X2js.json2xml_str(itemToSave);

                var txt2 = itemXmlString.slice(0, 5) + ' xmlns="http://www.ftn.uns.ac.rs/xmlbsep/company/invoice"' + itemXmlString.slice(5);
                console.log(txt2);
                DataSource.Invoices.addItem('http://localhost:8080/partneri/' + $scope.setupData.pib + '/fakture/' + $routeParams.id + '/stavke/', txt2).then(addItemCallback, onError);
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        $scope.openUpdate = function (item) {
            var itemToEdit = angular.copy(item);
            var modalInstance = $modal.open({
                animation: true,
                templateUrl: '../../../views/update_item.html',
                controller: 'UpdateItemModalCtrl',
                resolve: {
                    item: function () {
                        return itemToEdit ;
                    }
                }
            });

            modalInstance.result.then(function (item) {
                var id = item._id;
                item._xmlns ="http://www.ftn.uns.ac.rs/xmlbsep/company/invoice";
                item = {item: item};
                console.log(item);
                //item._id = 0;
                //var itemToSave = {item: item};
                var X2js = new X2JS();
                var itemXmlString = X2js.json2xml_str(item);
                //
                //var txt2 = itemXmlString.slice(0, 5) + ' xmlns="http://www.ftn.uns.ac.rs/xmlbsep/company/invoice"' + itemXmlString.slice(5);
                console.log(itemXmlString);
                DataSource.Items.update('http://localhost:8080/partneri/' + $scope.setupData.pib + '/fakture/' + $routeParams.id + '/stavke/' + id, itemXmlString).then(updateItemCallback, onError);
            }, function () {
                $log.info('Modal dismissed at: ' + new Date());
            });
        };

        var addItemCallback = function(result) {
            $log.info(result);
            //are you really that lazy? Meh :/
            $route.reload();
        };

        var updateItemCallback = function(result) {
            $log.info(result);
            //are you really that lazy? Meh :/
            $route.reload();
        };

        //This is callback on error
        var onError = function (reason) {
            console.log(reason);
            console.log("Could not fetch the data.");
        };

        DataSource.Invoices.get('http://localhost:8080/partneri/' + $scope.setupData.pib + '/fakture/' + $routeParams.id + '/stavke').then(setData, onError);
    }

    angular.module("company").controller("ItemsController", ItemsController);
}(angular));