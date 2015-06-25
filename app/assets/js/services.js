'use strict';

var services = angular.module('company.services', []);

var DataSource = function ($http) {
    var Invoices,
        Items;
    var X2js = new X2JS();

    var responseTransformer = function (data) {
        return X2js.xml_str2json(data);
    };

    Items = {
        delete: function (url) {
            return $http.delete(url, {
                transformResponse: function (data) {
                    return responseTransformer(data);
                }
            });
        }, update: function (url, data) {
            return $http.put(url, data, {
                headers: {
                    'Content-Type': 'application/xml',
                    'Accept': "application/xml, text/plain, */*"
                }
            });
        }
    };

    Invoices = {
        get: function (url) {
            return $http.get(
                url
                , {
                    transformResponse: function (data) {
                        return responseTransformer(data);
                    }
                });
        },
        post: function (url, data) {
            console.log(url);
            console.log(data);

            return $http.post(url, data, {
                headers: {
                    'Content-Type': 'application/xml',
                    'Accept': "application/xml, text/plain, */*"
                }
            });
        },
        update: function () {
        },
        delete: function () {
            return $http.delete(
                url
                , {
                    transformResponse: function (data) {
                        return responseTransformer(data);
                    }
                });
        },
        addItem: function (url, item) {
            return $http.post(url, item, {
                headers: {
                    'Content-Type': 'application/xml',
                    'Accept': "application/xml, text/plain, */*"
                }
            });
        }
    };

    return {
        Invoices: Invoices,
        Items: Items
    };
};

var AuthInterceptor = function ($q, $location) {
    return {
        request: function (req) {
            req.headers = req.headers || {};
            //if ($cookies.token) {
            //    req.headers.Authorization = 'Bearer ' + $cookies.token;
            //}

            return req;
        },
        responseError: function (rejection) {
            if (rejection.status == 401 || rejection.status == 403) {
                $location.path('/login');
            }

            return $q.reject(rejection);
        }
    }
};

var AuthentificationService = function($http, $location) {

};

services.factory("AuthInterceptor", AuthInterceptor);
services.factory("AuthentificationService", AuthentificationService);
services.factory("DataSource", DataSource);

