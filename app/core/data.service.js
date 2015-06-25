(function () {
    'use strict';

    angular.module('app.core')
        .factory('dataService', dataService);

    dataService.$inject = ['$http', '$rootScope', '$log', 'toastr'];
    function dataService($http, $rootScope, $log, toastr) {
        var X2js = new X2JS();
        var dateFormat = 'yyyy-MM-dd';
        var invoicesUrl = 'http://localhost:8080/partneri/' + $rootScope.setupData.pib + '/fakture';
        var invoiceUrl = 'http://localhost:8080/partneri/' + $rootScope.setupData.pib + '/fakture/';
        var itemsUrl = 'http://localhost:8080/partneri/' + $rootScope.setupData.pib + '/fakture/{0}/stavke';

        var Invoice = {
            getAll: getInvoices,
            get: getInvoice,
            create: createInvoice
        };

        var Item = {
            getAll: getItems,
            get: getItem,
            create: createItem,
            update: updateItem,
            delete: deleteItem
        };

        var service = {
            Invoice: Invoice,
            Item: Item
        };

        return service;
        ////////////////////

        function getInvoices() {
            return $http.get(
                invoicesUrl, {
                    withCredentials: true,
                    transformResponse: function (data) {
                        if (data == null || data == "") {
                            return data;
                        }
                        data = X2js.xml_str2json(data);
                        return data.resultWrapper.invoice;
                    }
                });
        }

        function getInvoice(invoiceId) {
            return $http.get(
                invoiceUrl + invoiceId, {
                    withCredentials: true,
                    transformResponse: function (data) {
                        if (data == null || data == "") {
                            return data;
                        }
                        data = X2js.xml_str2json(data);
                        return data.invoice;
                    }
                });
        }

        function createInvoice(invoice) {
            return $http.post(invoicesUrl, invoice, {
                withCredentials: true,
                transformRequest: function (obj) {

                    obj.invoice.invoice_header.supplier.pib = $rootScope.setupData.pib;
                    obj.invoice.invoice_header.buyer.pib = 'pib0';
                    obj.invoice.invoice_header.payment.currency._date = obj.invoice.invoice_header.payment.currency._date.format(dateFormat.toLowerCase());
                    obj.invoice.invoice_header.bill._date = obj.invoice.invoice_header.bill._date.format(dateFormat.toLowerCase());
                    obj.invoice.invoice_header.bill.toString = function () {
                        return obj.invoice.invoice_header.bill.__text;
                    };

                    var invoiceXmlString = X2js.json2xml_str(obj);
                    var txt2 = invoiceXmlString.slice(0, 8) + ' xmlns="http://www.ftn.uns.ac.rs/xmlbsep/company/invoice"' + invoiceXmlString.slice(8);
                    txt2 = txt2.split("'").join('"');
                    return txt2;
                },
                headers: {
                    'Content-Type': 'application/xml',
                    'Accept': "application/xml, text/plain, */*"
                }
            });
        }

        function getItems(invoiceId) {
            return $http.get(
                itemsUrl.format(invoiceId), {
                    withCredentials: true,
                    transformResponse: function (data) {
                        if (data == null || data == "") {
                            return data;
                        }
                        var result = [];
                        data = X2js.xml_str2json(data);
                        var item = data.resultWrapper.item;
                        if (item !== undefined) {
                            if (!Array.isArray(item)) {
                                result = new Array(item);
                            } else {
                                result = item;
                            }
                        }
                        return result;
                    }
                });
        }

        function getItem() {

        }

        function createItem(invoiceId, item) {
            return $http.post(itemsUrl.format(invoiceId), item, {
                withCredentials: true,
                transformRequest: function (object) {
                    object._id = 0;
                    var itemToSave = {item: object};
                    var itemXmlString = X2js.json2xml_str(itemToSave);
                    var txt2 = itemXmlString.slice(0, 5) + ' xmlns="http://www.ftn.uns.ac.rs/xmlbsep/company/invoice"' + itemXmlString.slice(5);
                    console.log(txt2);
                    return txt2;
                },
                headers: {
                    'Content-Type': 'application/xml',
                    'Accept': "application/xml, text/plain, */*"
                }
            }).success(function (data) {
                toastr.info("Item has been created.", "Successfully created");
            }).error(function (data) {
                toastr.info("Oups, something went wrong! \nDetails: " + data, "Error");
            });
        }

        function updateItem(invoiceId, item) {
            return $http.put(itemsUrl.format(invoiceId) + "/" + item._id, item, {
                withCredentials: true,
                transformRequest: function (item) {
                    item._xmlns = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice";
                    item = {item: item};
                    console.log(item);
                    var itemXmlString = X2js.json2xml_str(item);
                    console.log(itemXmlString);
                    return itemXmlString;
                },
                headers: {
                    'Content-Type': 'application/xml',
                    'Accept': "application/xml, text/plain, */*"
                }
            }).success(function (data) {
                toastr.info("Item has been updated.", "Successfully updated");
            }).error(function (data, status) {
                toastr.info("Oups, something went wrong! \nDetails: " + status, "Error");
            });
        }

        function deleteItem(invoiceId, itemId) {
            return $http.delete(itemsUrl.format(invoiceId) + "/" + itemId, {
                withCredentials: true
            }).success(function (data) {
                toastr.info("Item has been deleted.", "Successfully Deleted");
            }).error(function (data) {
                toastr.info("Oups, something went wrong! \nDetails: " + data, "Error");
            });
        }

    }
})();