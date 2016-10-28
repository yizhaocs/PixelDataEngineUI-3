/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("facebookDataProvidersService", ['$http', '$location', '$rootScope',
    function ($http, $location, $rootScope) {
        var service = {};

        // data mapping services
        service.getMappings = getMappings;
        service.getMapping = getMapping;
        service.updateMapping = updateMapping;

        /**
         *   data mapping services
         **/
        function getMappings() {
            var body = $http.get($rootScope.base + 'mappings/data-providers');
            return body;
        }

        function getMapping(mappingID) {
            return $http.get($rootScope.base + 'mapping/data-providers?id=' + mappingID);
        }

        function updateMapping(redirectPath, id, frontendData) {
            return $http.post($rootScope.base + 'updateMapping/data-providers', {dp_id: frontendData.dp_id, name: frontendData.name, sync_facebook: frontendData.sync_facebook}).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        return service;
    }
]);
