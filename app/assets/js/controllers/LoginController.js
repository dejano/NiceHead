(function (angular, undefined) {
    'use strict';
    function LoginController($scope, $routeParams, DataSource, $http) {
        $scope.auth = function () {
            var password = $scope.password;
            var username = $scope.username;
            var data = "username=" + encodeURIComponent(username) +
                "&password=" + encodeURIComponent(password);
            //$http.get('http://localhost:8080/login', data, {headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8'}})
            $http({withCredentials: true, method: 'GET', url: 'http://localhost:8080/login?username=user&password=pass'})
                .success(function (data, status,aw) {
                    console.log(data);
                    console.log(status);
                    console.log(aw);
                }).error(function (data, status) {

                })
        }

        $scope.test = function() {
            $http({withCredentials: true, method: 'GET', url: 'http://localhost:8080/partneri/test'})
                .success(function (data, status,aw) {
                    console.log(data);
                    console.log(status);
                    console.log(aw);
                }).error(function (data, status) {
                    console.log(data);
                    console.log(status);
                })
        }
    }

    angular.module("company").controller("LoginController", LoginController);
}(angular));