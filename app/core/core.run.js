(function () {
    'use strict';

    angular.module('app.core').run(appRun);

    appRun.$inject = ['$rootScope'];
    function appRun($rootScope) {
        $rootScope.setupData = {
            "name": "YoLo Company",
            "address": "1",
            "url": "http://localhost:8081/",
            "pib": "pib1",
            "accounts": [
                "1",
                "2",
                "3"
            ]
        };
    }

})();