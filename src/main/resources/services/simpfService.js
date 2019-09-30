angular.module('simpf').factory('simpfService', ['$injector',
    function ($injector) {

        var $window = $injector.get('$window');
        var authenticationService = $injector.get('authenticationService');
        var requestService = $injector.get('requestService');
        var service = {};

        service.isActive = function () {
            return authenticationService.getCurrentToken() !== null;
        }

        service.ping = function () {
            var httpParameters = {
                token: authenticationService.getCurrentToken()
            };
            return requestService({
                method: 'GET',
                url: 'api/session/ext/simpf/isalive',
                params: httpParameters
            });
        };

        service.logout = function () {
            var username = authenticationService.getCurrentUsername();
            if (username === null) {
                return;
            }
            authenticationService.logout()
            ['catch'](requestService.IGNORE)
            ['finally'](function () {
                $window.location.href = '/gateway/?download=' + username;
            });
        };

        return service;
    }]);
