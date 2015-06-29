(function () {
    'use strict';

    angular.module('app.core')
        .factory('dataService', dataService);

    dataService.$inject = ['$http', '$rootScope', '$log', 'toastr'];
    function dataService($http, $rootScope, $log, toastr) {
        var X2js = new X2JS();
        var dateFormat = 'yyyy-MM-dd';
        var partnersUrl = 'http://localhost:8080/partneri/';
        var invoicesUrl = 'http://localhost:8080/partneri/' + $rootScope.setupData.pib + '/fakture';
        var invoiceUrl = 'http://localhost:8080/partneri/' + $rootScope.setupData.pib + '/fakture/';
        var itemsUrl = 'http://localhost:8080/partneri/' + $rootScope.setupData.pib + '/fakture/{0}/stavke';

        var Invoice = {
            getAll: getInvoices,
            getPending: getPendingInvoices,
            getSent: getSentInvoices,
            getPendingWithState: getPendingWithState,
            getReceived: getReceivedInvoices,
            update: updateInvoice,
            get: getInvoice,
            create: createInvoice,
            approve: approveInvoice,
            reject: rejectInvoice
        };

        var Item = {
            getAll: getItems,
            get: getItem,
            create: createItem,
            update: updateItem,
            delete: deleteItem
        };

        var Partner = {
            getAll: getPartners,
            get: getPartner
        };

        var service = {
            Invoice: Invoice,
            Item: Item,
            Partner: Partner
        };

        return service;
        ////////////////////

        function getPartners() {
            return $http.get(
                partnersUrl, {
                    withCredentials: true,
                    transformResponse: function (data) {
                        if (data == null || data == "") {
                            return data;
                        }

                        var result = [];
                        data = X2js.xml_str2json(data);
                        console.log(data.resultWrapper.partner);
                        return data.resultWrapper.partner;
                    }
                });
        }

        function getPartner(pib) {

        }

        function getInvoices(httpUrl) {
            var url = httpUrl || invoicesUrl;
            return $http.get(
                url, {
                    withCredentials: true,
                    transformResponse: function (data) {
                        if (data == null || data == "") {
                            return data;
                        }

                        var result = [];
                        data = X2js.xml_str2json(data);
                        var item = data.resultWrapper.invoice;
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

        function getPendingInvoices() {
            return getInvoices(invoicesUrl + '/pending').error(function (data, status) {
                toastr.info("Oups, something went wrong! \nDetails: " + status, "Error");
            });
        }

        function getSentInvoices() {
            return getInvoices(invoicesUrl + '/sent');
        }

        function getPendingWithState(state) {
            return getInvoices(invoicesUrl + '/pending/' + state);
        }

        function getReceivedInvoices() {
            return getInvoices(invoicesUrl + '/received');
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
                        console.log(data);
                        return data.invoice;
                    }
                });
        }

        function createInvoice(invoice, httpUrl) {
            var url = httpUrl || invoicesUrl;
            return $http.post(url, invoice, {
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
        function approveInvoice(invoice) {
            console.log(invoice);
            return $http.put(invoiceUrl + "approve" + '/' + invoice._id, invoice, {
                withCredentials: true,
                transformRequest: function (obj) {
                    obj = {invoice: obj};
                    obj.invoice.invoice_header.supplier.pib = $rootScope.setupData.pib;
                    obj.invoice.invoice_header.buyer.pib = 'pib0';
                    obj.invoice.invoice_header.payment.currency._date = obj.invoice.invoice_header.payment.currency._date.format(dateFormat.toLowerCase());
                    obj.invoice.invoice_header.bill._date = obj.invoice.invoice_header.bill._date.format(dateFormat.toLowerCase());
                    obj.invoice.invoice_header.bill.toString = function () {
                        return obj.invoice.invoice_header.bill.__text;
                    };

                    return X2js.json2xml_str(obj);
                },
                headers: {
                    'Content-Type': 'application/xml',
                    'Accept': "application/xml, text/plain, */*"
                }
            });
        }

        function rejectInvoice(invoiceId) {
            return $http.delete(invoiceUrl + invoiceId, {
                withCredentials: true
            }).success(function (data) {
                toastr.info("Item has been deleted.", "Successfully Deleted");
            }).error(function (data) {
                toastr.info("Oups, something went wrong! \nDetails: " + data, "Error");
            });
        }

        function updateInvoice(invoice) {
            return $http.put(invoiceUrl + invoice._id, invoice, {
                withCredentials: true,
                transformRequest: function (invoice) {
                    invoice._xmlns = "http://www.ftn.uns.ac.rs/xmlbsep/company/invoice";
                    invoice = {invoice: invoice};
                    console.log(invoice);
                    var itemXmlString = X2js.json2xml_str(invoice);
                    console.log(itemXmlString);
                    return itemXmlString;
                },
                headers: {
                    'Content-Type': 'application/xml',
                    'Accept': "application/xml, text/plain, */*"
                }
            }).success(function (data) {
                toastr.info("Invoice has been updated.", "Successfully updated");
            }).error(function (data, status) {
                toastr.info("Oups, something went wrong! \nDetails: " + status, "Error");
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