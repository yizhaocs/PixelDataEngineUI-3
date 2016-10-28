/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.controller('listPixelGroups', function ($scope, pixelmappingService) {
    pixelmappingService.getGroups().success(function (backendData) {
        for (var i = 0; i < backendData.list.length; i++) {
            if (backendData.list[i].group_type == 1) {
                backendData.list[i].group_type = 'independent';
            } else if (backendData.list[i].group_type == 2) {
                backendData.list[i].group_type = 'sequential';
            }
        }

        $scope.frontendData = backendData.list;
    });
});


app.controller('editPixelGroup', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var keyId = ($routeParams.keyId) ? parseInt($routeParams.keyId) : 0;
    $rootScope.title = (keyId > 0) ? 'Edit Group' : 'Add Group';
    $scope.buttonText = (keyId > 0) ? 'Update Group' : 'Add New Group';
    $scope.isUpdate = (keyId > 0) ? true : false;
    $scope.keyIdDisable = (keyId > 0) ? true : false;

    if (backendData.data != "") {
        if (backendData.data.group_type == 1) {
            backendData.data.group_type = 'independent';
        } else if (backendData.data.group_type == 2) {
            backendData.data.group_type = 'sequential';
        }

        $scope.frontendData = angular.copy(backendData.data);
    }


    $scope.isClean = function () {
        return angular.equals(backendData.data.body, $scope.frontendData);
    };

    $scope.deleteGroup = function (frontendData) {
        if (confirm("Are you sure to delete mapping number: " + frontendData.trigger_key_id) == true)
            pixelmappingService.deleteGroup($rootScope.base + 'pixel-data-engine-group', frontendData.trigger_key_id, frontendData.gid);
    };

    $scope.saveGroup = function (frontendData) {
        if (frontendData.group_type == 'independent') {
            frontendData.group_type = 1;
        } else if (frontendData.group_type == 'sequential') {
            frontendData.group_type = 2;
        }
        if (keyId > 0) {
            pixelmappingService.updateGroup($rootScope.base + 'pixel-data-engine-group', keyId, frontendData);
        } else {
            pixelmappingService.insertGroup($rootScope.base + 'pixel-data-engine-group', frontendData);
        }
    };
});

app.controller('editSameGroup', function ($scope, $rootScope, $location, $routeParams, pixelmappingService, backendData) {
    var gid = ($routeParams.gid) ? parseInt($routeParams.gid) : 0;
    var triggerKeyId = ($routeParams.trigger_key_id) ? parseInt($routeParams.trigger_key_id) : 0;

    $scope.title = 'Group ID:' + gid;
    $scope.buttonText = 'Add New Rule';
    $scope.isUpdate = false; // false to get rid of "Delete" button
    $scope.groupIdInputDisable = true;
    $scope.keyIdDisable = false;
    var defaultValueOfRule = 'a:';
    var frontendRightHandPanelDataForClean = '';

    $scope.initRule= function () {
        $scope.frontendRightHandPanelData = initRuleData();
        $scope.frontendRightHandPanelData.gid = gid;
        initRuleArray($scope.frontendRightHandPanelData, defaultValueOfRule);
    };

    $scope.initRule();

    // we need to order the priority col in the front, so we have to parse the 'priority' from string to int type

    sortArray(backendData.data.list);
    $scope.frontendLeftHandPanelData = angular.copy(backendData.data.list);


    /*
     *
     * functions
     * */
    // button fuctions
    $scope.addLenRule = function () {
        $scope.frontendRightHandPanelData.lenArray.push({
            column1: $scope.frontendRightHandPanelData.lenArray.length,
            column2: '' + defaultValueOfRule
        });

    };

    $scope.removeLenRule = function () {
        $scope.frontendRightHandPanelData.lenArray.pop();
    };

    $scope.addRangeRule = function () {
        $scope.frontendRightHandPanelData.rangeArray.push({
            column1: $scope.frontendRightHandPanelData.rangeArray.length,
            column2: '' + defaultValueOfRule
        });

    };

    $scope.removeRangeRule = function () {
        $scope.frontendRightHandPanelData.rangeArray.pop();
    };

    $scope.addContainsRule = function () {
        $scope.frontendRightHandPanelData.containsArray.push({
            column1: $scope.frontendRightHandPanelData.containsArray.length,
            column2: '' + defaultValueOfRule
        });

    };

    $scope.removeContainsRule = function () {
        $scope.frontendRightHandPanelData.containsArray.pop();
    };

    $scope.addSetRule = function () {
        $scope.frontendRightHandPanelData.setRuleArray.push({
            column1: $scope.frontendRightHandPanelData.setRuleArray.length,
            column2: ''
        });

    };

    $scope.removeSetRule = function () {
        $scope.frontendRightHandPanelData.setRuleArray.pop();
    };

    $scope.addInElement = function () {
        $scope.frontendRightHandPanelData.inArray.push({
            column1: $scope.frontendRightHandPanelData.inArray.length,
            column2: '' + defaultValueOfRule
        });
    };

    $scope.removeInElement = function () {
        $scope.frontendRightHandPanelData.inArray.pop();
    };

    $scope.isClean = function () {
        return angular.equals(frontendRightHandPanelDataForClean, $scope.frontendRightHandPanelData);
    }

    // when use click on "Add new rule" button
    $scope.addRule = function () {
        $scope.buttonText = 'Add New Rule';
        $scope.isUpdate = false; // false to get rid of "Delete" button
        $scope.groupIdInputDisable = true;
        $scope.keyIdDisable = false;
        $scope.initRule();
    };

    $scope.deleteRule = function (frontendRightHandPanelData) {
        if (confirm("Are you sure to delete rule number: " + frontendRightHandPanelData.keyId) == true) {
            pixelmappingService.deleteRule(
                $rootScope.base + 'group/edit-rules/triggerkeyid=' + triggerKeyId + '&gid=' + gid,
                frontendRightHandPanelData.gid,
                frontendRightHandPanelData.keyId,
                $scope.leftPanelSelectedPriority
            ).success(function (backendData) {
                $scope.refreshLeftPanel();
                $scope.frontendRightHandPanelData = '';
            });
        }
    };

    $scope.saveRule = function (frontendRightHandPanelData) {
        // true if "Add new rule"
        if ($scope.isUpdate == false) {
            if (insertRuleDuplicatePrioritiesValidator($scope.frontendLeftHandPanelData, frontendRightHandPanelData) == false) {
                return;
            }

            if (ruleValidator(frontendRightHandPanelData) == false) {
                return;
            }

            pixelmappingService.insertRule(
                $rootScope.base + 'group/edit-rules/triggerkeyid=' + triggerKeyId + '&gid=' + gid,
                frontendRightHandPanelData
            ).success(function (backendData) {
                $scope.refreshLeftPanel();
            });
        } else {
            if (updateRuleDuplicatePrioritiesValidator($scope.frontendLeftHandPanelData, frontendRightHandPanelData) == false) {
                return;
            }

            if (ruleValidator(frontendRightHandPanelData) == false) {
                return;
            }

            pixelmappingService.updateRule(
                $rootScope.base + 'group/edit-rules/triggerkeyid=' + triggerKeyId + '&gid=' + gid,
                frontendRightHandPanelData,
                $scope.leftPanelSelectedPriority).success(function (backendData) {
                $scope.leftPanelSelectedPriority = frontendRightHandPanelData.priority, // add this for fixing bug when updated the priority more than once
                    $scope.refreshLeftPanel();
                alert("Rule Updated");
            });
        }
    };

    $scope.refreshLeftPanel = function () {
        pixelmappingService.getSameGroup(triggerKeyId, gid).success(function (backendData) {
            sortArray(backendData.list);
            $scope.frontendLeftHandPanelData = angular.copy(backendData.list);
        });
    };

    $scope.selectKeyId = function (frontendData) {
        //  If the user clicks on a <div>, we can get the ng-click to call this function, to set a new selected Customer.
        $scope.leftPanelSelectedkeyid = frontendData.key_id;
        $scope.leftPanelSelectedPriority = frontendData.priority;
        $scope.leftPanelSelectedGid = frontendData.gid;

        $scope.getRule();
    };

    $scope.testRule = function (frontendRightHandPanelData) {
        pixelmappingService.testRule(
            $rootScope.base + 'group/edit-rules/triggerkeyid=' + triggerKeyId + '&gid=' + gid,
            frontendRightHandPanelData,
            $scope.leftPanelSelectedPriority
        ).success(function (backendData) {
            var result = "";
            if (backendData != '') {
                for (var i = 0; i < backendData.list.length; i++) {
                    var key = backendData.list[i].key;
                    var value = backendData.list[i].value;
                    result += key + "=" + value + "\n";
                }

                if (backendData.list.length == 0) {
                    result = "null/empty result";
                }

            } else {
                result = "null/empty result";
            }

            $scope.frontendRightHandPanelData.testResult = result;
        });
    };

    $scope.getRule = function () {
        $scope.buttonText = 'Update Rule';
        $scope.isUpdate = true; // true to display "Delete" button
        $scope.groupIdInputDisable = true;
        $scope.groupTypeDisable = true;
        $scope.keyIdDisable = true;
        pixelmappingService.getRule($scope.leftPanelSelectedGid, $scope.leftPanelSelectedkeyid, $scope.leftPanelSelectedPriority).success(function (backendData) {
            // frontendRightHandPanelDataForClean = backendData;

            $scope.initRule();
            // true if user click on the "edit button"
            if (gid != 0) {
                ruleParser($scope.frontendRightHandPanelData, backendData, defaultValueOfRule)
            }

            /*
             * This is for hard setting for the Group Type on the frontend Right Hand Panel
             * */
            pixelmappingService.getGroup(triggerKeyId).success(function (backendData) {
                $scope.frontendRightHandPanelData.triggerKeyId = triggerKeyId;
                var group = backendData;
                if (group.group_type == 1) {
                    $scope.frontendRightHandPanelData.groupType = "independent";
                } else if (group.group_type == 2) {
                    $scope.frontendRightHandPanelData.groupType = "sequential";
                }
            });

            //----------------------------------------------------------------------------------------------------------------

            frontendRightHandPanelDataForClean = angular.copy($scope.frontendRightHandPanelData);
        });
    }
});

