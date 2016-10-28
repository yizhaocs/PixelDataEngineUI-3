/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlDbm', function ($scope, dbmService) {
    dbmService.getMappings('dbm').success(function (backendData) {
        $scope.frontendData = backendData.list;
    });
});

app.controller('editCtrlDbm', function ($scope, $rootScope, $location, $routeParams, dbmService, backendData) {
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
        if (confirm("Are you sure to delete mapping number: " + frontendData.conversion_pixel_id) == true)
            dbmService.deleteMapping($rootScope.base + 'dbm', frontendData.conversion_pixel_id);
    };

    $scope.saveMapping = function (frontendData) {
        if (mappingID > 0) {
            dbmService.updateMapping($rootScope.base + 'dbm', mappingID, frontendData);
        } else {
            dbmService.insertMapping($rootScope.base + 'dbm', frontendData);
        }
    };
});