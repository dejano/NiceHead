(function () {
    'use strict';

    var core = angular.module('app.core');

    core.config(configure);

    function configure($routeProvider, routeHelperConfigProvider, authProvider) {

        //$cookiesProvider.
        // Configure auth provider
        authProvider.setLoginUrl("http://localhost:8081/auth");
        authProvider.setLogoutUrl("http://localhost:8081/logout");
        authProvider.setAfterLogoutUrl("/");

        // Configure the common route provider
        routeHelperConfigProvider.config.$routeProvider = $routeProvider;
        routeHelperConfigProvider.config.docTitle = 'YoLo Company - ';
    }
})();