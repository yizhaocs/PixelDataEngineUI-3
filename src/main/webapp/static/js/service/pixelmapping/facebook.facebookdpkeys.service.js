/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("facebookFacebookDpkeysService", ['$http', '$location', '$rootScope',
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
            var body = $http.get($rootScope.base + 'mappings/facebook-dp-keys');
            return body;
        }

        function getMapping(mappingID) {
            return $http.get($rootScope.base + 'mapping/facebook-dp-keys?id=' + mappingID);
        }

        function insertMapping(redirectPath, frontendData) {
            if (frontendData.update_interval == -1) {
                frontendData.update_interval = null;
            }


            frontendData.use_image_pixel = frontendData.use_image_pixel == "false" ? 0 : 1;

            return $http.post($rootScope.base + 'insertMapping/facebook-dp-keys', {key_id: frontendData.key_id, enabled: frontendData.enabled, update_interval: frontendData.update_interval, use_image_pixel: frontendData.use_image_pixel}).success(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        function updateMapping(redirectPath, id, frontendData) {
            if (frontendData.update_interval == -1) {
                frontendData.update_interval = null;
            }
            frontendData.use_image_pixel = frontendData.use_image_pixel == "false" ? 0 : 1;

            return $http.post($rootScope.base + 'updateMapping/facebook-dp-keys', {key_id: frontendData.key_id, enabled: frontendData.enabled, update_interval: frontendData.update_interval, use_image_pixel: frontendData.use_image_pixel}).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function deleteMapping(redirectPath, id) {
            return $http.delete($rootScope.base + 'deleteMapping/facebook-dp-keys?id=' + id).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        return service;
    }
]);
