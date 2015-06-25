(function (angular, undefined) {
    'use strict';
    function InvoiceController($scope, $routeParams, DataSource) {

        //This is callback on success
        var setData = function (result) {
            console.log(result.data);
            if (result.data.invoice !== undefined) {
                $scope.invoice = result.data.invoice;
                console.log("run success callback");
            }
        }

        //This is callback on error
        var onError = function (reason) {
            console.log(reason);
            console.log("Could not fetch the data.");
        };

        DataSource.Invoices.get('http://localhost:8080/partneri/' + $scope.setupData.pib + '/fakture/' + $routeParams.id).then(setData, onError);
    }

    angular.module("company").controller("InvoiceController", InvoiceController);
}(angular));