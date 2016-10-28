/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("referUrlKeyMappingsService", ['$http', '$location', '$rootScope',
    function ($http, $location, $rootScope) {
        var service = {};

        // data mapping services
        service.getMappings = getMappings;
        service.getMapping = getMapping;
        service.insertMapping = insertMapping;
        service.updateMapping = updateMapping;
        service.deleteMapping = deleteMapping;

        /**
         *   data mapping services
         **/
        function getMappings() {
            var body = $http.get($rootScope.base + 'mappings/rukm');
            return body;
        }

        function getMapping(mappingID) {
            return $http.get($rootScope.base + 'mapping/rukm?id=' + mappingID);
        }

        function insertMapping(redirectPath, frontendData) {
            return $http.post($rootScope.base + 'insertMapping/rukm', {dp_id: frontendData.dp_id, refer_url_key_id: frontendData.refer_url_key_id, enabled: frontendData.enabled}).success(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        function updateMapping(redirectPath, id, frontendData) {
            return $http.post($rootScope.base + 'updateMapping/rukm', {dp_id: frontendData.dp_id, refer_url_key_id: frontendData.refer_url_key_id, enabled: frontendData.enabled}).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function deleteMapping(redirectPath, id) {
            return $http.delete($rootScope.base + 'deleteMapping/rukm?id=' + id).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        return service;
    }
]);

