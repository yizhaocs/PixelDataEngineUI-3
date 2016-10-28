/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("deriveConversionService", ['$http', '$location', '$rootScope',
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
            var body = $http.get($rootScope.base + 'mappings/deriveconversion');
            return body;
        }

        function getMapping(mappingID) {
            return $http.get($rootScope.base + 'mapping/deriveconversion?id=' + mappingID);
        }

        function insertMapping(redirectPath, frontendData) {
            return $http.post($rootScope.base + 'insertMapping/deriveconversion', {dp_key_id: frontendData.dp_key_id, advertiser_id: frontendData.advertiser_id, cp_id: frontendData.cp_id}).success(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        function updateMapping(redirectPath, id, frontendData) {
            return $http.post($rootScope.base + 'updateMapping/deriveconversion', {dp_key_id: frontendData.dp_key_id, advertiser_id: frontendData.advertiser_id, cp_id: frontendData.cp_id}).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function deleteMapping(redirectPath, id) {
            return $http.delete($rootScope.base + 'deleteMapping/deriveconversion?id=' + id).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        return service;
    }
]);
