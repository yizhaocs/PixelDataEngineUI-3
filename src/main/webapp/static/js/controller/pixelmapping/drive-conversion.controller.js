/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlDeriveConversion', function ($scope, deriveConversionService) {
    deriveConversionService.getMappings().success(function (backendData) {
        $scope.frontendData = backendData.list;
    });
});

app.controller('editCtrlDeriveConversion', function ($scope, $rootScope, $location, $routeParams, deriveConversionService, backendData) {
    var mappingID = ($routeParams.mappingID) ? parseInt($routeParams.mappingID) : 0;
    $rootScope.title = (mappingID > 0) ? 'Edit Mapping' : 'Add Mapping';
    $scope.buttonText = (mappingID > 0) ? 'Update Mapping' : 'Add New Mapping';
    $scope.isUpdate = (mappingID > 0) ? true : false; // false to get rid of "Delete" button
    $scope.keyIdDisable = (mappingID > 0) ? true : false;
    $scope.frontendData = angular.copy(backendData.data);

    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        // $location.path('/adobe');
        if (confirm("Are you sure to delete mapping number: " + frontendData.dp_key_id) == true)
            deriveConversionService.deleteMapping($rootScope.base + 'derive-conversion', frontendData.dp_key_id);
    };

    $scope.saveMapping = function (frontendData) {
        // $location.path('/adobe');
        if (mappingID > 0) {
            deriveConversionService.updateMapping($rootScope.base + 'derive-conversion', mappingID, frontendData);
        } else {
            deriveConversionService.insertMapping($rootScope.base + 'derive-conversion', frontendData);
        }
    };
});