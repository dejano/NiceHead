(function (angular, undefined) {
    'use strict';
    function InvoicesController($scope, DataSource) {

        //This is callback on success
        var setData = function (result) {
            if (result.data.resultWrapper !== undefined) {
                $scope.invoices = result.data.resultWrapper.invoice;
                console.log($scope.invoices);
                console.log($scope.invoices[0].invoice_header.bill.toString());
                console.log("run success callback");
            }
        }

        //This is callback on error
        var onError = function (reason) {
            console.log(reason);
            console.log("Could not fetch the data.");
        };

        DataSource.Invoices.get('http://localhost:8080/partneri/' + $scope.setupData.pib + '/fakture/').then(setData, onError);
    }

    angular.module("company").controller("InvoicesCtrl", InvoicesController);
}(angular));