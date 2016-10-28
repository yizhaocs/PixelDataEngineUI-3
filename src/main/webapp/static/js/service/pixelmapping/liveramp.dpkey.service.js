/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("liverampDpKeyService", ['$http', '$location', '$rootScope',
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
            var body = $http.get($rootScope.base + 'mappings/liveramp-dpkey-mappings');
            return body;
        }

        function getMapping(mappingID) {
            return $http.get($rootScope.base + 'mapping/liveramp-dpkey-mappings?id=' + mappingID);
        }

        function insertMapping(redirectPath, frontendData) {
            return $http.post($rootScope.base + 'insertMapping/liveramp-dpkey-mappings', {liveramp_segment_id: frontendData.liveramp_segment_id, dp_key_id: frontendData.dp_key_id, value:frontendData.value}).success(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        function updateMapping(redirectPath, id, frontendData) {
            return $http.post($rootScope.base + 'updateMapping/liveramp-dpkey-mappings', {liveramp_segment_id: frontendData.liveramp_segment_id, dp_key_id: frontendData.dp_key_id, value:frontendData.value}).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function deleteMapping(redirectPath, id) {
            return $http.delete($rootScope.base + 'deleteMapping/liveramp-dpkey-mappings?id=' + id).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        return service;
    }
]);
