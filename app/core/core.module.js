(function() {
    'use strict';

    angular.module('app.core', [
        'ngRoute',
        'app.layout',
        'blocks.filter',
        'blocks.router', 'blocks.auth'
    ]);

})();