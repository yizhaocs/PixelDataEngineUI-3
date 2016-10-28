/**
 * @author JASON GAO[jason.gao@adara.com ]
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlLiveramp', function ($scope, liverampDpService, liverampDpKeyService) {
    liverampDpService.getMappings().success(function (backendData) {
        $scope.mappingsDp = backendData.list;
    });
    liverampDpKeyService.getMappings().success(function (backendData) {
        $scope.mappingsKey = backendData.list;
    });
});

app.controller('editCtrlLiverampDp', function ($scope, $rootScope, $location, $routeParams, liverampDpService, backendData) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : '0';
    $rootScope.title = (mappingID > 0) ? 'Edit Liveramp DP Mapping' : 'Add Liveramp DP Mapping';
    $scope.buttonText = (mappingID > 0) ? 'Update Liveramp DP Mapping' : 'Add New Liveramp DP Mapping';
    $scope.isUpdate = (mappingID > 0) ? true : false; // false to get rid of "Delete" button
    $scope.keyIdDisable = (mappingID > 0) ? true : false;
    $scope.frontendData = angular.copy(backendData.data);

    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        // $location.path('/liveramp');
        if (confirm("Are you sure to delete mapping number: " + frontendData.dp_name) == true)
            liverampDpService.deleteMapping($rootScope.base + 'liveramp', frontendData.dp_name);
    };

    $scope.saveMapping = function (frontendData) {
        if (frontendData.threshold_mb > 100) {
            alert("Threshold can't be greater than 100MB");
        } else {
            // $location.path('/liveramp');
            if (mappingID > 0) {
                liverampDpService.updateMapping($rootScope.base + 'liveramp', mappingID, frontendData);
            } else {
                liverampDpService.insertMapping($rootScope.base + 'liveramp', frontendData);
            }
        }
    };
});

app.controller('editCtrlLiverampKey', function ($scope, $rootScope, $location, $routeParams, liverampDpKeyService, backendData) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : '0';
    $rootScope.title = (mappingID != '0') ? 'Edit Liveramp Key Mapping' : 'Add Liveramp Key Mapping';
    $scope.buttonText = (mappingID != '0') ? 'Update Liveramp Key Mapping' : 'Add New Liveramp Key Mapping';
    $scope.isUpdate = (mappingID > 0) ? true : false;
    $scope.keyIdDisable = (mappingID > 0) ? true : false;
    $scope.frontendData = angular.copy(backendData.data);


    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        // $location.path('/liveramp');
        if (confirm("Are you sure to delete mapping number: " + frontendData.liveramp_segment_id) == true)
            liverampDpKeyService.deleteMapping($rootScope.base + 'liveramp', frontendData.liveramp_segment_id);
    };

    $scope.saveMapping = function (frontendData) {
        // $location.path('/liveramp');
        if (mappingID > 0) {
            liverampDpKeyService.updateMapping($rootScope.base + 'liveramp', mappingID, frontendData);
        } else {
            liverampDpKeyService.insertMapping($rootScope.base + 'liveramp', frontendData);
        }
    };
});