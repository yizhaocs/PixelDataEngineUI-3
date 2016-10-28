/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("dbmService", ['$http', '$location', '$rootScope',
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
            var body = $http.get($rootScope.base + 'mappings/dbm');
            return body;
        }

        function getMapping(mappingID) {
            return $http.get($rootScope.base + 'mapping/dbm?id=' + mappingID);
        }

        function insertMapping(redirectPath, frontendData) {
            frontendData.dbm_url = dbmURLValidator(frontendData.dbm_url);
            return $http.post($rootScope.base + 'insertMapping/dbm', {conversion_pixel_id: frontendData.conversion_pixel_id, dbm_url:frontendData.dbm_url}).success(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        function updateMapping(redirectPath, id, frontendData) {
            frontendData.dbm_url = dbmURLValidator(frontendData.dbm_url);
            return $http.post($rootScope.base + 'updateMapping/dbm', {conversion_pixel_id: frontendData.conversion_pixel_id, dbm_url:frontendData.dbm_url}).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function deleteMapping(redirectPath, id) {
            return $http.delete($rootScope.base + 'deleteMapping/dbm?id=' + id).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        return service;
    }
]);

