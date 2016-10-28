
function ruleParser(frontendRightHandPanelData, backendData, defaultValueOfRule) {


    frontendRightHandPanelData.gid = backendData.gid;
    frontendRightHandPanelData.keyId = backendData.key_id;
    frontendRightHandPanelData.type = backendData.type;
    frontendRightHandPanelData.priority = parseInt(backendData.priority);


    parseRuleParser(frontendRightHandPanelData, backendData);
    conditionRuleParser(frontendRightHandPanelData, backendData);
    actionRuleParser(frontendRightHandPanelData, backendData);

    initRuleArray(frontendRightHandPanelData, defaultValueOfRule);

}


function parseRuleParser(frontendRightHandPanelData, backendData) {
    var parseRuleSplit = backendData.parse_rule.split("|");
    frontendRightHandPanelData.parseRule = parseRuleSplit[0];
    if (parseRuleSplit[0] == 'split1') {
        // true if -> -> split1|"|"
        if (parseRuleSplit.length == 3) {
            frontendRightHandPanelData.split1.column1 = "\"|\"";
        } else {
            frontendRightHandPanelData.split1.column1 = parseRuleSplit[1];
        }
    } else if (parseRuleSplit[0] == 'split2') {
        /*
         * handle case of -> split2|"|"|"|"
         * handle case of -> split2|"|"|,
         * handle case of -> split2|,|"|"
         * */
        if (parseRuleSplit.length == 5) { // true if -> split2|"|"|"|"
            frontendRightHandPanelData.split2.column1 = "\"|\"";
            frontendRightHandPanelData.split2.column2 = "\"|\"";
        } else if (parseRuleSplit.length == 4) {
            // true if -> split2|"|"|,
            // false if -> split2|,|"|"
            if (parseRuleSplit[1] == "\"") {
                frontendRightHandPanelData.split2.column1 = "\"|\"";
                frontendRightHandPanelData.split2.column2 = parseRuleSplit[3];
            } else {
                frontendRightHandPanelData.split2.column1 = parseRuleSplit[1];
                frontendRightHandPanelData.split2.column2 = "\"|\"";
            }
        } else {
            frontendRightHandPanelData.split2.column1 = parseRuleSplit[1];
            frontendRightHandPanelData.split2.column2 = parseRuleSplit[2];
        }
    }
}

function conditionRuleParser(frontendRightHandPanelData, backendData) {
    var conditionRuleSplit = backendData.condition_rule.split("|");
    frontendRightHandPanelData.conditionRule = conditionRuleSplit[0];
    if (conditionRuleSplit[0] == 'len') {
        // remove the init index at 0 in cleanArray function
        cleanArray(frontendRightHandPanelData.lenArray);
        for (var i = 1; i < conditionRuleSplit.length; i++) {
            var inObject = {
                column1: i - 1,
                column2: conditionRuleSplit[i]
            }
            frontendRightHandPanelData.lenArray.push(inObject);
        }

        //frontendRightHandPanelData.len.rangeFrom = conditionRuleSplit[conditionRuleSplit.length - 2];
        //frontendRightHandPanelData.len.rangeTo = conditionRuleSplit[conditionRuleSplit.length - 1];
    } else if (conditionRuleSplit[0] == 'range') {
        // remove the init index at 0 in cleanArray function
        cleanArray(frontendRightHandPanelData.rangeArray);
        for (var i = 1; i < conditionRuleSplit.length; i++) {
            var inObject = {
                column1: i - 1,
                column2: conditionRuleSplit[i]
            }
            frontendRightHandPanelData.rangeArray.push(inObject);
        }
    } else if (conditionRuleSplit[0] == 'in') {
        // remove the init index at 0 in cleanArray function
        cleanArray(frontendRightHandPanelData.inArray);
        for (var i = 1; i < conditionRuleSplit.length; i++) {
            var inObject = {
                column1: i - 1,
                column2: conditionRuleSplit[i]
            }
            frontendRightHandPanelData.inArray.push(inObject);
        }
    } else if (conditionRuleSplit[0] == 'seg') {
        frontendRightHandPanelData.seg.column1 = conditionRuleSplit[1];
    } else if (conditionRuleSplit[0] == 'contains') {
        // remove the init index at 0 in cleanArray function
        cleanArray(frontendRightHandPanelData.containsArray);
        for (var i = 1; i < conditionRuleSplit.length; i++) {
            var inObject = {
                column1: i - 1,
                column2: conditionRuleSplit[i]
            }
            frontendRightHandPanelData.containsArray.push(inObject);
        }
    }
}

function actionRuleParser(frontendRightHandPanelData, backendData) {
    var actionRuleSplit = backendData.action_rule.split("|");
    frontendRightHandPanelData.actionRule = actionRuleSplit[0];
    if (actionRuleSplit[0] == 'substr') {
        var splitedSubstr = actionRuleSplit[1].split(":");
        frontendRightHandPanelData.substr.column1 = splitedSubstr[0];
        var splitedSubstrLevel2 = splitedSubstr[1].split(",");
        frontendRightHandPanelData.substr.column2 = splitedSubstrLevel2[0];
        frontendRightHandPanelData.substr.column3 = splitedSubstrLevel2[1];
        frontendRightHandPanelData.substr.column4 = splitedSubstrLevel2[2];

    } else if (actionRuleSplit[0] == 'set') {
        // remove the init index at 0 in cleanArray function
        cleanArray(frontendRightHandPanelData.setRuleArray);
        for (var i = 1; i < actionRuleSplit.length; i++) {
            var setObject = {
                column1: i - 1,
                column2: actionRuleSplit[i]
            }
            frontendRightHandPanelData.setRuleArray.push(setObject);
        }
    } else if (actionRuleSplit[0] == 'dec') {
        var splitedDec = actionRuleSplit[1].split(":");
        frontendRightHandPanelData.dec.column1 = splitedDec[0];
        frontendRightHandPanelData.dec.column2 = splitedDec[1];
    } else if (actionRuleSplit[0] == 'map') {
        frontendRightHandPanelData.map.column1 = actionRuleSplit[1];
        var splitedMap = actionRuleSplit[2].split(":");
        frontendRightHandPanelData.map.column2 = splitedMap[0];
        frontendRightHandPanelData.map.column3 = splitedMap[1];
    }
}

