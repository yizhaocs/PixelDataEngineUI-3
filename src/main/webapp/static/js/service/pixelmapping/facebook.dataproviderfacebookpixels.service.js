/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("facebookDataProviderFacebookPixelsService", ['$http', '$location', '$rootScope',
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
            var body = $http.get($rootScope.base + 'mappings/data-provider-facebook-pixels');
            return body;
        }

        function getMapping(mappingID) {
            return $http.get($rootScope.base + 'mapping/data-provider-facebook-pixels?id=' + mappingID);
        }

        function insertMapping(redirectPath, frontendData) {
            return $http.post($rootScope.base + 'insertMapping/data-provider-facebook-pixels', {dp_id: frontendData.dp_id, facebook_pixel_id: frontendData.facebook_pixel_id, enable_dat: frontendData.enable_dat}).success(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        function updateMapping(redirectPath, id, frontendData) {
            return $http.post($rootScope.base + 'updateMapping/data-provider-facebook-pixels', {dp_id: frontendData.dp_id, facebook_pixel_id: frontendData.facebook_pixel_id, enable_dat: frontendData.enable_dat}).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function deleteMapping(redirectPath, id) {
            return $http.delete($rootScope.base + 'deleteMapping/data-provider-facebook-pixels?id=' + id).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        return service;
    }
]);
