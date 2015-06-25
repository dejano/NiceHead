(function (angular, undefined) {
    'use strict';
    function CreateInvoiceCtrl($scope, $location, DataSource, $log) {

        //Date picker config
        $scope.today = function() {
            $scope.dt = new Date();
        };
        $scope.today();
        $scope.dateFormat = 'yyyy-MM-dd';
        $scope.clear = function () {
            $scope.dt = null;
        };
        $scope.openDatepicker = function($event, opened) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope[opened] = true;
        };

        $scope.items = [];
        //This is callback on success
        var setData = function (result,status,  headers, config) {
            console.log(result);
            console.log(result.headers(['Content-Location']));
            console.log(result.headers('Content-Location'));
            console.log(result.headers());
            $location.path('/invoices');
        };

        //This is callback on error
        var onError = function (reason) {
            console.log(reason);
            console.log("Could not fetch the data.");
        };

        $scope.addItem = function () {
            var newItemId = $scope.items.length+1;
            $scope.items.push({_id: newItemId});
        };

        $scope.submit = function() {
            $scope.errorMessage = null;

            if (!$scope.items.length) {
                $scope.errorMessage = "please add at least one item.";
                return;
            }
            $scope.invoice.item = $scope.items;
            console.log($scope.invoice);
            $scope.invoice.invoice_header.supplier.pib = $scope.pib;
            $scope.invoice.invoice_header.buyer.pib = 'pib0';
            $scope.invoice.invoice_header.payment.currency._date = $scope.invoice.invoice_header.payment.currency._date.format($scope.dateFormat.toLowerCase());
            $scope.invoice.invoice_header.bill._date = $scope.invoice.invoice_header.bill._date.format($scope.dateFormat.toLowerCase());
            $scope.invoice.invoice_header.bill.toString = function() {
                return $scope.invoice.invoice_header.bill.__text;
            };
            $scope.invoice = {invoice : $scope.invoice};
            var X2js = new X2JS();
            var invoiceXmlString = X2js.json2xml_str($scope.invoice);

            var txt2 = invoiceXmlString.slice(0, 8) + ' xmlns="http://www.ftn.uns.ac.rs/xmlbsep/company/invoice"' + invoiceXmlString.slice(8);
            txt2 = txt2.split("'").join('"');
            DataSource.Invoices.post('http://localhost:8080/partneri/' + $scope.setupData.pib + '/fakture/', txt2).then(setData, onError);
        };

        //var data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><invoice xmlns=\"http://www.ftn.uns.ac.rs/xmlbsep/company/invoice\"><invoice_header><supplier><name>123</name><address>123123</address><pib>pib0</pib></supplier><buyer><name>12</name><address>3123</address><pib>pib1</pib></buyer><bill date='2015-00-18'>23123</bill><price><services>12312</services><total>3123</total><total_taxes>123</total_taxes><total_discount>123</total_discount></price><payment><currency date='2015-00-18'>123</currency><amount>123</amount><account_number>123123</account_number></payment></invoice_header><item/></invoice>";
        //DataSource.Invoices.post('http://localhost:8080/partneri/pib1/fakture/', data).then(setData, onError);
    }

    angular.module("company").controller("CreateInvoiceCtrl", CreateInvoiceCtrl);
}(angular));