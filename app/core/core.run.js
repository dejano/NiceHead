(function () {
    'use strict';

    angular.module('app.core').run(appRun);

    appRun.$inject = ['$rootScope'];
    function appRun($rootScope) {
        $rootScope.setupData = {
            "name": "YoLo Company",
            "address": "1",
            "url": "1",
            "pib": "pib1",
            "accounts": [
                "1",
                "2",
                "3"
            ]
        };
    }

})();