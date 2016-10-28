/**
 * Created by yzhao on 9/23/16.
 */


function insertRuleDuplicatePrioritiesValidator(frontendLeftHandPanelData, frontendRightHandPanelData) {
    // validation for Duplicate Priorities in "insertion" scenario
    if (frontendLeftHandPanelData) {
        for (var i = 0; i < frontendLeftHandPanelData.length; i++) {
            if (frontendLeftHandPanelData[i].priority == frontendRightHandPanelData.priority) {
                alert("Disallow Duplicate Priorities");
                return false;
            }
        }
    }

    return true;
}

function updateRuleDuplicatePrioritiesValidator(frontendLeftHandPanelData, frontendRightHandPanelData) {
    // validation for Duplicate Priorities in "updation" scenario
    for (var i = 0; i < frontendLeftHandPanelData.length; i++) {
        // true if updating the current rule so that it's okay for current priority to match left priority left side. We just break the for loop to not to verify the priority.
        if (frontendLeftHandPanelData[i].key_id === frontendRightHandPanelData.keyId && frontendLeftHandPanelData[i].priority === frontendRightHandPanelData.priority) {
            break;
        }
        if (frontendLeftHandPanelData[i].priority == frontendRightHandPanelData.priority) {
            alert("Disallow Duplicate Priorities");
            return false;
        }
    }

    return true;
}

function ruleValidator(frontendRightHandPanelData) {
    //right side data
    var parseRule = frontendRightHandPanelData.parseRule;
    var conditionRule = frontendRightHandPanelData.conditionRule;
    var actionRule = frontendRightHandPanelData.actionRule;
    var gid = frontendRightHandPanelData.gid;
    var keyId = frontendRightHandPanelData.keyId;
    var priority = frontendRightHandPanelData.priority;
    var type = frontendRightHandPanelData.type;
    var split1 = frontendRightHandPanelData.split1;
    var split2 = frontendRightHandPanelData.split2;
    var lenArray = frontendRightHandPanelData.lenArray;
    var seg = frontendRightHandPanelData.seg;
    var containsArray = frontendRightHandPanelData.containsArray;
    var rangeArray = frontendRightHandPanelData.rangeArray;
    var substr = frontendRightHandPanelData.substr;
    var dec = frontendRightHandPanelData.dec;
    var map = frontendRightHandPanelData.map;
    var inArray = frontendRightHandPanelData.inArray;
    var setRuleArray = frontendRightHandPanelData.setRuleArray;

    if (parseRuleValidator(parseRule, split1, split2) == false) {
        return false;
    }

    if (conditionRuleValidator(parseRule, conditionRule, lenArray, rangeArray, inArray, seg, containsArray) == false) {
        return false;
    }

    if (actionRuleValidator(parseRule, actionRule, substr, setRuleArray, dec, map) == false) {
        return false;
    }


    return true;


};


function parseRuleValidator(parseRule, split1, split2) {
    if (parseRule == 'split1') {
        if (!split1 || !split1.column1) {
            alert("parse rule split1 is invalid");
            return false;
        }
    }

    if (parseRule == 'split2') {
        if (!split2 || !split2.column1 || !split2.column2) {
            alert("parse rule split2 is invalid");
            return false;
        }
    }
    return true;
}

function conditionRuleValidator(parseRule, conditionRule, lenArray, rangeArray, inArray, seg, containsArray) {
    if (conditionRule == 'len') {
        if (ruleTestGeneral(parseRule, lenArray, 'condition', conditionRule) == false) {
            return false;
        }
    }

    else if (conditionRule == 'range') {
        if (ruleTestGeneral(parseRule, rangeArray, 'condition', conditionRule) == false) {
            return false;
        }
    }

    else if (conditionRule == 'in') {
        if (ruleTestGeneral(parseRule, inArray, 'condition', conditionRule) == false) {
            return false;
        }
    }

    else if (conditionRule == 'seg') {
        if (!seg || !seg.column1) {
            alert("condition" + " rule " + conditionRule + " is invalid");
            return false;
        }
        var numberOfSegments = seg.column1;
        // true if numberOfSegments is not-a-number
        if (isNaN(numberOfSegments)) {
            alert("condition" + " rule " + conditionRule + " is invalid");
            return false;
        }
    }

    else if (conditionRule == 'contains') {
        if (ruleTestGeneral(parseRule, containsArray, 'condition', conditionRule) == false) {
            return false;
        }
    }
    return true;
}

function actionRuleValidator(parseRule, actionRule, substr, setRuleArray, dec, map) {
    if (actionRule == 'substr') {
        if (!substr
            || !substr.column1
            || !substr.column2
            || !substr.column3
            || !substr.column4) {
            alert("action" + " rule " + actionRule + " is invalid");
            return false;
        }

        var position = substr.column1;
        var direction = substr.column2;
        var startIndex = substr.column3;
        var length = substr.column4;


        if (testForConflictWithParseRuleInActionRules(parseRule, actionRule, position)) {
            return false;
        }

        // true if direction is a number
        if (isNaN(direction) == false || (direction != 'L' && direction != 'R')) {
            alert("action" + " rule " + actionRule + " is invalid");
            return false;
        }

        // true if startIndex is not a number
        if (isNaN(startIndex)) {
            alert("action" + " rule " + actionRule + " is invalid");
            return false;
        }

        // true if length is not a number
        if (isNaN(length)) {
            alert("action" + " rule " + actionRule + " is invalid");
            return false;
        }
    }

    else if (actionRule == 'set') {
        if(arrayValidator(setRuleArray) == false){
            alert("action" + " rule " + actionRule + " is invalid");
            return false;
        }
    }

    else if (actionRule == 'dec') {
        if (!dec
            || !dec.column1
            || !dec.column2) {
            alert("action" + " rule " + actionRule + " is invalid");
            return false;
        }

        var position = dec.column1;
        var numberOfDecimals = dec.column2;

        if(testForConflictWithParseRuleInActionRules(parseRule, actionRule, position)){
            return false;
        }

        // true if numberOfDecimals is not a number
        if (isNaN(numberOfDecimals)) {
            alert("action" + " rule " + actionRule + " is invalid");
            return false;
        }
    }

    else if (actionRule == 'map') {
        if (!map
            || !map.column1
            || !map.column2
            || !map.column3) {
            alert("action" + " rule " + actionRule + " is invalid");
            return false;
        }

        var mapName = map.column1;
        var key = map.column2;
        var position = map.column3;


        // true if mapName is a number
        if (isNaN(mapName) == false) {
            alert("action" + " rule " + actionRule + " is invalid");
            return false;
        }

        // true if key is-not-number
        if (isNaN(key)) {
            alert("action" + " rule " + actionRule + " is invalid");
            return false;
        }

        if(testForConflictWithParseRuleInActionRules(parseRule, actionRule, position)){
            return false;
        }
    }
    return true;
}

function testForConflictWithParseRuleInActionRules(parseRule, actionRule, position){
    if (parseRule == 'orig') {
        /* *
         * split1 case can only check "a:" case, the following is true if "0:" or "0,0:"
         * */
        if (position != 'a') {
            alert("action" + " rule " + actionRule + " is invalid");
            return false;
        }
    }

    else if (parseRule == 'split1') {
        /* *
         * split1 case can only check "a:" or "0:" cases, the following is true if "0,0:"
         * */
        if (position.charAt(1) == ',') {
            alert("action" + " rule " + actionRule + " is invalid");
            return false;
        }
    }

    else if (parseRule == 'split2') {
        /* *
         * split2 case can only check "a:" or "0,0": cases, the following is true if "0:"
         * */
        if (position.charAt(0) != 'a') {
            if (position.charAt(1) != ',' && position.charAt(2) != ',') {
                alert("action" + " rule " + actionRule + " is invalid");
                return false;
            }
        }
    }
}

function arrayValidator(array){
    if(!array || array.length == 0){
        return false;
    }

    for (var i = 0; i < array.length; i++) {
        var column2 = array[i].column2;
        if (column2.length == 0) {
            return false;
        }
    }

    return true;
}

function ruleTestGeneral(parseRule, array, ruleType, whichRule) {
    if (arrayValidator(array) == false) {
        alert(ruleType + " rule " + whichRule + " is invalid");
        return false;
    } else {
        for (var i = 0; i < array.length; i++) {
            var input = array[i].column2;
            if (testForConflictWithParseRuleInConditionRules(parseRule, input, ruleType, whichRule) == false) {
                return false;
            }
        }
    }
    return true;
}

function testForConflictWithParseRuleInConditionRules(parseRule, input, ruleType, whichRule) {
    if (!input || input.length < 2) {
        alert(ruleType + " rule " + whichRule + " is invalid");
        return false;
    }

    if (parseRule == 'orig') {
        /* *
         * split1 case can only check "a:" case, the following is true if "0:" or "0,0:"
         * */
        if (input.substring(0, 2) != 'a:' || input == 'a:') {
            alert(ruleType + " rule " + whichRule + " is invalid");
            return false;
        }
    }

    else if (parseRule == 'split1') {
        /* *
         * split1 case can only check "a:" or "0:" cases, the following is true if "0,0:"
         * */
        if (input.charAt(1) == ',') {
            alert(ruleType + " rule " + whichRule + " is invalid");
            return false;
        }
    }

    else if (parseRule == 'split2') {
        /* *
         * split2 case can only check "a:" or "0,0": cases, the following is true if "0:"
         * */
        if (input.charAt(0) != 'a') {
            if (input.charAt(1) != ',' && input.charAt(2) != ',') {
                alert(ruleType + " rule " + whichRule + " is invalid");
                return false;
            }
        }
    }
}
