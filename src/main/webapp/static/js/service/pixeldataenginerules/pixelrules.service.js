/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */

'use strict';

app.factory("pixelmappingService", ['$http', '$location', '$rootScope',
    function ($http, $location, $rootScope) {
        var service = {};

        // data mapping services
        service.getMappings = getMappings;
        service.getMapping = getMapping;
        service.insertMapping = insertMapping;
        service.updateMapping = updateMapping;
        service.deleteMapping = deleteMapping;

        // group services
        service.getGroups = getGroups;
        service.getGroup = getGroup;
        service.insertGroup = insertGroup;
        service.updateGroup = updateGroup;
        service.deleteGroup = deleteGroup;
        service.getSameGroup = getSameGroup;

        // rule services
        service.getRules = getRules;
        service.getRule = getRule;
        service.updateRule = updateRule;
        service.deleteRule = deleteRule;
        service.insertRule = insertRule;
        service.testRule = testRule;


        /**
         *   data mapping services
         **/
        function getMappings(type) {
            var body = $http.get($rootScope.base + 'mappings?type=' + type);
            return body;
        }

        function getMapping(mappingID, type) {
            return $http.get($rootScope.base + 'mapping?id=' + mappingID + '&type=' + type);
        }

        function insertMapping(redirectPath, frontendData, type) {
            return $http.post($rootScope.base + 'insertMapping', {mapping: frontendData, type: type}).success(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        function updateMapping(redirectPath, id, frontendData, type) {
            return $http.post($rootScope.base + 'updateMapping', {id: id, mapping: frontendData, type: type}).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function deleteMapping(redirectPath, id, type) {
            return $http.delete($rootScope.base + 'deleteMapping?id=' + id + '&type=' + type).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        /*
         * group services
         * */
        function getGroups() {
            return $http.get($rootScope.base + 'getGroups');
        }

        function getGroup(keyId) {
            return $http.get($rootScope.base + 'group?id=' + keyId);
        }

        function insertGroup(redirectPath, frontendData) {
            return $http.post($rootScope.base + 'insertGroup', {trigger_key_id: frontendData.trigger_key_id, gid:frontendData.gid, group_type:frontendData.group_type}).success(function (results) {
                $location.path(redirectPath);
                return results;
            });
        };

        function updateGroup(redirectPath, id, frontendData) {
            return $http.post($rootScope.base + 'updateGroup', {trigger_key_id: frontendData.trigger_key_id, gid:frontendData.gid, group_type:frontendData.group_type}).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function deleteGroup(redirectPath, triggerKeyId, gid) {
            return $http.delete($rootScope.base + 'deleteGroup?triggerkeyid=' + triggerKeyId + '&gid=' + gid).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function getSameGroup(triggerKeyId, gid) {
            return $http.get($rootScope.base + 'samegroup?id=' + gid);
        }


        /*
         * rule services
         * */
        function getRules() {
            return $http.get($rootScope.base + 'getRules');
        }

        function getRule(gid, keyID, priority) {
            return $http.get($rootScope.base + 'getRule?gid=' + gid + '&keyid=' + keyID + '&priority=' + priority);
        }

        function updateRule(redirectPath, frontendRightHandPanelData, newPriority) {
            var gid = frontendRightHandPanelData.gid;
            var keyId = frontendRightHandPanelData.keyId;
            var priority = newPriority;
            var newPriority = frontendRightHandPanelData.priority;
            var type = frontendRightHandPanelData.type;
            var insertRule = ruleEncoder(frontendRightHandPanelData);
            var parseRule = insertRule.parseRule;
            var conditionRule = insertRule.conditionRule;
            var actionRule = insertRule.actionRule;

            if(!gid || !keyId || !priority || !newPriority || !type || !parseRule || !conditionRule || !actionRule){
                alert("function updateRule failed because one of parameter is null");
                return;
            }

            return $http.post($rootScope.base + 'updateRule?newPriority=' + newPriority, {
                gid: gid,
                key_id: keyId,
                priority: priority,
                type: type,
                parse_rule: parseRule,
                condition_rule: conditionRule,
                action_rule: actionRule
            }).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function deleteRule(redirectPath, gid, keyID, priority) {
            return $http.delete($rootScope.base + 'deleteRule?gid=' + gid + '&keyid=' + keyID + '&priority=' + priority).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        function insertRule(redirectPath, frontendRightHandPanelData) {
            var gid = frontendRightHandPanelData.gid;
            var keyId = frontendRightHandPanelData.keyId;
            var priority = frontendRightHandPanelData.priority;
            var type = frontendRightHandPanelData.type;
            var insertRule = ruleEncoder(frontendRightHandPanelData);
            var parseRule = insertRule.parseRule;
            var conditionRule = insertRule.conditionRule;
            var actionRule = insertRule.actionRule;

            if(!gid || !keyId || !priority || !type || !parseRule || !conditionRule || !actionRule){
                alert("function insertRule failed because one of parameter is null");
                return;
            }


            return $http.post($rootScope.base + 'insertRule', {
                gid: gid,
                key_id: keyId,
                priority: priority,
                type: type,
                parse_rule: parseRule,
                condition_rule: conditionRule,
                action_rule: actionRule,
            }).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };


        function testRule(redirectPath, frontendRightHandPanelData, newPriority) {
            var gid = frontendRightHandPanelData.gid;
            var triggerKeyId = frontendRightHandPanelData.triggerKeyId;
            var keyId = frontendRightHandPanelData.keyId;
            var priority = newPriority;
            var newPriority = frontendRightHandPanelData.priority;
            var type = frontendRightHandPanelData.type;
            var insertRule = ruleEncoder(frontendRightHandPanelData);
            var parseRule = insertRule.parseRule;
            var conditionRule = insertRule.conditionRule;
            var actionRule = insertRule.actionRule;
            var testValue = frontendRightHandPanelData.testValue;
            var testOption = frontendRightHandPanelData.testOption;


            if(!gid || !triggerKeyId || !keyId || !priority || !newPriority || !type || !insertRule || !parseRule || !conditionRule || !actionRule || !testValue || !testOption){
                alert("function testRule failed because one of parameter is null");
                return;
            }

            var pixelDataEngineConfigsDTO = {gid: gid,key_id: keyId,priority: priority,type: type,parse_rule: parseRule,condition_rule: conditionRule,action_rule: actionRule};

            return $http.post($rootScope.base + 'testRule', {
                pixelDataEngineConfigsDTO:pixelDataEngineConfigsDTO,
                triggerKeyId: triggerKeyId,
                newPriority: newPriority,
                testValue: testValue,
                testOption: testOption
            }).success(function (status) {
                $location.path(redirectPath);
                return status.data;
            });
        };

        return service;
    }
]);
