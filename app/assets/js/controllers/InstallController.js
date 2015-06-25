(function (angular, undefined) {
    'use strict';
    function InstallController($scope, $location,$rootScope, $log, $http) {
        if ($scope.setupData) {
            $location.path("/dashboard");
        }
        $scope.config = {};
        $scope.config.accounts = [""];
        $scope.addAccount = function () {
            $scope.config.accounts.push("");
        };

        $scope.removeAccount = function() {
            if ($scope.config.accounts.length > 1)
                $scope.config.accounts.pop();
        };

        $scope.next = function() {
            $http.post("http://localhost:8080/config/create", $scope.config);
            $rootScope.setupData = $scope.config;
            $location.path('/dashboard');
        }

    }

    angular.module("company").controller("InstallController", InstallController);
}(angular));