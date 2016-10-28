/**
 * @author JASON GAO[jason.gao@adara.com ]
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listCtrlFacebook', function ($scope, $location, $anchorScroll, facebookDataProviderFacebookPixelsService, facebookDataProvidersService, facebookFacebookDpkeysService) {
    facebookDataProviderFacebookPixelsService.getMappings().success(function (backendData) {
        $scope.frontendDataMappingsPixel = backendData.list;
    });

    facebookDataProvidersService.getMappings().success(function (backendData) {
        $scope.frontendDataMappingsDp = backendData.list;
    });
    facebookFacebookDpkeysService.getMappings().success(function (backendData) {
        $scope.frontendDataMappingsKey = backendData.list;
    });


    $scope.scrollTo = function (position) {
        var old = $location.hash();
        $location.hash(position);
        $anchorScroll();
        $location.hash(old);
    };
});

app.controller('editCtrlFacebookPixel', function ($scope, $rootScope, $location, $routeParams, facebookDataProviderFacebookPixelsService, backendData) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : 0;
    $rootScope.title = (mappingID > 0) ? 'Edit Data Provider Pixel' : 'Add Data Provider Pixel';
    $scope.buttonText = (mappingID > 0) ? 'Update Data Provider Pixel' : 'Add New Data Provider Pixel';
    $scope.isUpdate = (mappingID > 0) ? true : false; // false to get rid of "Delete" button
    $scope.keyIdDisable = (mappingID > 0) ? true : false;
    $scope.frontendData = angular.copy(backendData.data);

    // because enable_dat is integer passed from backend code so that we need to set it as String to be able to see in select form in html
    if(mappingID > 0){
        $scope.frontendData.enable_dat = String(backendData.data.enable_dat);
    }

    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        // $location.path('/facebook');
        if (confirm("Are you sure to delete mapping number: " + frontendData.dp_id) == true)
            facebookDataProviderFacebookPixelsService.deleteMapping($rootScope.base + 'facebook', frontendData.dp_id);
    };

    $scope.saveMapping = function (frontendData) {
        // $location.path('/facebook');
        if (mappingID > 0) {
            facebookDataProviderFacebookPixelsService.updateMapping($rootScope.base + 'facebook', mappingID, frontendData);
        } else {
            facebookDataProviderFacebookPixelsService.insertMapping($rootScope.base + 'facebook', frontendData);
        }
    };
});

app.controller('editCtrlFacebookDp', function ($scope, $rootScope, $location, $routeParams, facebookDataProvidersService, backendData) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : '0';
    $rootScope.title = 'Edit Facebook DP Mapping';
    $scope.buttonText = 'Update Facebook DP Mapping';
    $scope.isUpdate = (mappingID > 0) ? true : false;

    $scope.frontendData = angular.copy(backendData.data);

    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    }

    $scope.saveMapping = function (frontendData) {
        // $location.path('/facebook');
        facebookDataProvidersService.updateMapping($rootScope.base + 'facebook', mappingID, frontendData);
    };
});

app.controller('editCtrlFacebookKey', function ($scope, $rootScope, $location, $routeParams, facebookFacebookDpkeysService, backendData) {
    var mappingID = ($routeParams.mappingID) ? $routeParams.mappingID : 0;
    $rootScope.title = (mappingID > 0) ? 'Edit Facebook Key Mapping' : 'Add Facebook Key Mapping';
    $scope.buttonText = (mappingID > 0) ? 'Update Facebook Key Mapping' : 'Add New Facebook Key Mapping';
    $scope.isUpdate = (mappingID > 0) ? true : false;
    $scope.keyIdDisable = (mappingID > 0) ? true : false;
    $scope.frontendData = angular.copy(backendData.data);


    $scope.isClean = function () {
        return angular.equals(backendData.data, $scope.frontendData);
    }

    $scope.deleteMapping = function (frontendData) {
        // $location.path('/facebook');
        if (confirm("Are you sure to delete mapping number: " + frontendData.key_id) == true)
            facebookFacebookDpkeysService.deleteMapping($rootScope.base + 'facebook', frontendData.key_id);
    };

    $scope.saveMapping = function (frontendData) {
        if (frontendData.update_interval == undefined || frontendData.update_interval == '') {
            frontendData.update_interval = -1;
        }
        // $location.path('/facebook');
        if (mappingID > 0) {
            facebookFacebookDpkeysService.updateMapping($rootScope.base + 'facebook', mappingID, frontendData);
        } else {
            facebookFacebookDpkeysService.insertMapping($rootScope.base + 'facebook', frontendData);
        }
    };
});