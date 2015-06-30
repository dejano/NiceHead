(function () {
    'use strict';

    angular.module('app.core').run(appRun);

    appRun.$inject = ['$rootScope', '$http'];
    function appRun($rootScope) {
        $rootScope.setupData = {
            "name": "Ciao Bella Company",
            "address": "1",
            "url": "http://localhost:8084/",
            "pib": "pib1",
            "accounts": [
                "223-2222222222222-22",
                "2",
                "3"
            ]
        };
    }

})();