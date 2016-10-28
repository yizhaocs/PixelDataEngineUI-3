/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("liverampDpService", ['$http', '$location', '$rootScope',
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
            var body = $http.get($rootScope.base + 'mappings/liveramp-dp-mappings');
            return body;
        }

        function getMapping(mappingID) {
            return $http.get($rootScope.base + 'mapping/liveramp-dp-mappings?id=' + mappingID);
        }

        function insertMapping(redirectPath, frontendData) {
            return $http.post($rootScope.base + 'insertMapping/liveramp-dp-mappings', {dp_name: frontendData.dp_name, dp_id: frontendData.dp_id, threshold_mb: frontendData.threshold_mb}).success(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        function updateMapping(redirectPath, id, frontendData) {
            return $http.post($rootScope.base + 'updateMapping/liveramp-dp-mappings', {dp_name: frontendData.dp_name, dp_id: frontendData.dp_id, threshold_mb: frontendData.threshold_mb}).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function deleteMapping(redirectPath, id) {
            return $http.delete($rootScope.base + 'deleteMapping/liveramp-dp-mappings?id=' + id).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        return service;
    }
]);
