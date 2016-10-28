/**
 * @author JASON GAO[jason.gao@adara.com ]
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlAdobe', function ($scope, adobeService) {
    adobeService.getMappings().success(function (backendData) {
        $scope.frontendData = backendData.list;
    });
});

app.controller('editCtrlAdobe', function ($scope, $rootScope, $location, $routeParams, adobeService, backendData) {
    var mappingID = ($routeParams.mappingID) ? parseInt($routeParams.mappingID) : 0;
    $rootScope.title = (mappingID > 0) ? 'Edit Mapping' : 'Add Mapping';
    $scope.buttonText = (mappingID > 0) ? 'Update Mapping' : 'Add New Mapping';
    $scope.isUpdate = (mappingID > 0) ? true : false; // false to get rid of "Delete" button
    $scope.frontendData = angular.copy(backendData.data);
    $scope.keyIdDisable = (mappingID > 0) ? true : false;


    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        if (confirm("Are you sure to delete mapping number: " + frontendData.adobe_segment_id) == true)
            adobeService.deleteMapping($rootScope.base + 'adobe', frontendData.adobe_segment_id);
    };

    $scope.saveMapping = function (frontendData) {
        if (mappingID > 0) {
            adobeService.updateMapping($rootScope.base + 'adobe', mappingID, frontendData);
        } else {
            adobeService.insertMapping($rootScope.base + 'adobe', frontendData);
        }
    };
});