/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlReferUrlKeyMappings', function ($scope, referUrlKeyMappingsService) {
    referUrlKeyMappingsService.getMappings('refer-url-key-mappings').success(function (backendData) {
        $scope.frontendData = backendData.list;
    });
});

app.controller('editCtrlReferUrlKeyMappings', function ($scope, $rootScope, $location, $routeParams, referUrlKeyMappingsService, backendData) {
    var mappingID = ($routeParams.mappingID) ? parseInt($routeParams.mappingID) : 0;
    $rootScope.title = (mappingID > 0) ? 'Edit Mapping' : 'Add Mapping';
    $scope.buttonText = (mappingID > 0) ? 'Update Mapping' : 'Add New Mapping';
    $scope.isUpdate = (mappingID > 0) ? true : false; // false to get rid of "Delete" button
    $scope.keyIdDisable = (mappingID > 0) ? true : false;
    $scope.frontendData = angular.copy(backendData.data);

    // because enabled is integer passed from backend code so that we need to set it as String to be able to see in select form in html
    if (mappingID > 0) {
        $scope.frontendData.enabled = String(backendData.data.enabled);
    }

    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        if (confirm("Are you sure to delete mapping number: " + frontendData.dp_id) == true)
            referUrlKeyMappingsService.deleteMapping($rootScope.base + 'refer-url-key-mappings', frontendData.dp_id);
    };

    $scope.saveMapping = function (frontendData) {
        if (mappingID > 0) {
            referUrlKeyMappingsService.updateMapping($rootScope.base + 'refer-url-key-mappings', mappingID, frontendData);
        } else {
            referUrlKeyMappingsService.insertMapping($rootScope.base + 'refer-url-key-mappings', frontendData);
        }
    };
});