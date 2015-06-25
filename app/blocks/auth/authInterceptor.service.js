(function () {
    'use strict';

    angular.module('blocks.auth').factory('authInterceptor', authInterceptor);

    authInterceptor.$inject = ['$q', '$location', '$injector'];
    function authInterceptor($q, $location, $injector) {
        var service = {
            request: request,
            response: response,
            responseError: responseError
        };

        return service;
        ////////////////////

        function request(req) {
            req.headers = req.headers || {};
            return req;
        }

        function response(response) {
            if (response.status === 401 || response.status === 403) {
                $injector.invoke(function(toastr) {
                    // $http is already constructed at the time and you may
                    // use it, just as any other service registered in your
                    // app module and modules on which app depends on.
                    toastr.warning('Please login first.', 'Unauthorized access');
                });

                console.log("Response " + response.status);
            }
            return response || $q.when(response);
        }

        function responseError(rejection) {
            if (rejection.status == 401 || rejection.status == 403) {
                $injector.invoke(function(toastr) {
                    toastr.warning('Please login first.', 'Unauthorized access');
                });
                console.log("response error: " + rejection.status);
                $location.path('/login');
            }

            return $q.reject(rejection);
        }
    }

})();