/**
 * Created by yzhao on 9/28/16.
 */


function ruleEncoder(frontendRightHandPanelData){

    var parseRule = parseRuleEncoder(frontendRightHandPanelData);
    var conditionRule = conditionRuleEncoder(frontendRightHandPanelData);
    var actionRule = actionRuleEncoder(frontendRightHandPanelData);

    var allRules = {parseRule:parseRule, conditionRule:conditionRule, actionRule:actionRule};

    return allRules;
}


function parseRuleEncoder(frontendRightHandPanelData){
        var parseRuleKey = frontendRightHandPanelData.parseRule;
        var parseRuleValue = parseRuleKey;
        var split1 = "";
        var split2Level1SplitString = "";
        var split2Level2SplitString = "";
        if (parseRuleKey == "split1") {
            split1 = frontendRightHandPanelData.split1.column1;
            if (split1) {
                parseRuleValue += "|";
                if (split1 == "|") {
                    split1 = "\"" + split1 + "\"";
                }
                parseRuleValue+=split1;
            }

        } else if (parseRuleKey == "split2") {
            split2Level1SplitString = frontendRightHandPanelData.split2.column1;
            split2Level2SplitString = frontendRightHandPanelData.split2.column2;

            if (split2Level1SplitString) {
                parseRuleValue += "|";
                if (split2Level1SplitString == "|") {
                    split2Level1SplitString = "\"" + split2Level1SplitString + "\"";
                }
                parseRuleValue += split2Level1SplitString;
            }

            if (split2Level2SplitString) {
                parseRuleValue += "|";
                if (split2Level2SplitString == "|") {
                    split2Level2SplitString = "\"" + split2Level2SplitString + "\"";
                }
                parseRuleValue += split2Level2SplitString;
            }

        }

        return parseRuleValue;
}


function conditionRuleEncoder(frontendRightHandPanelData){
    var conditionRuleKey = frontendRightHandPanelData.conditionRule;
    var conditionRuleValue = conditionRuleKey;
    var seg = null;
    var inElementArrayList = [];
    var lenArrayList = [];
    var rangeArrayList = [];
    var containsArrayList = [];
    if (conditionRuleKey == "len") {
        lenArrayList = frontendRightHandPanelData.lenArray;
        conditionRuleValue+="|";
        for (var i = 0; i < lenArrayList.length; i++) {
            if (lenArrayList[i].column2) {
                conditionRuleValue += lenArrayList[i].column2;
                conditionRuleValue += "|";
            }
        }
        conditionRuleValue = conditionRuleValue.substring(0, conditionRuleValue.length - 1);
    } else if (conditionRuleKey == "range") {
        rangeArrayList = frontendRightHandPanelData.rangeArray;
        conditionRuleValue += "|";
        for (var i = 0; i < rangeArrayList.length; i++) {
            if (rangeArrayList[i].column2) {
                conditionRuleValue += rangeArrayList[i].column2;
                conditionRuleValue += "|";
            }
        }
        conditionRuleValue = conditionRuleValue.substring(0, conditionRuleValue.length - 1);

    } else if (conditionRuleKey == "in") {
        inElementArrayList = frontendRightHandPanelData.inArray;
        conditionRuleValue += "|";
        for (var i = 0; i < inElementArrayList.length; i++) {
            if (inElementArrayList[i].column2) {
                conditionRuleValue += inElementArrayList[i].column2;
                conditionRuleValue += "|";
            }
        }
        conditionRuleValue = conditionRuleValue.substring(0, conditionRuleValue.length - 1);
    } else if (conditionRuleKey == "seg") {
        seg = frontendRightHandPanelData.seg.column1;
        if (seg) {
            conditionRuleValue += "|";
            conditionRuleValue += seg;
        }
    } else if (conditionRuleKey == "contains") {
        containsArrayList = frontendRightHandPanelData.containsArray;
        conditionRuleValue += "|";
        for (var i = 0; i < containsArrayList.length; i++) {
            if (containsArrayList[i].column2) {
                conditionRuleValue += containsArrayList[i].column2;
                conditionRuleValue += "|";
            }
        }
        conditionRuleValue = conditionRuleValue.substring(0, conditionRuleValue.length - 1);
    }

    return conditionRuleValue;
}

function actionRuleEncoder(frontendRightHandPanelData){
    var actionRuleKey = frontendRightHandPanelData.actionRule;
    var actionRuleValue = actionRuleKey;

    var setRuleArrayDTOList = [];
    if (actionRuleKey == "substr") {
        var position = frontendRightHandPanelData.substr.column1;
        var substrDirection = frontendRightHandPanelData.substr.column2;
        var substrStartIndex = frontendRightHandPanelData.substr.column3;
        var substrLength = frontendRightHandPanelData.substr.column4;

        if (position) {
            actionRuleValue += "|";
            actionRuleValue += position.toLowerCase();
        }

        if (substrDirection) {
            actionRuleValue += ":";
            actionRuleValue += substrDirection.toUpperCase();
        }

        if (substrStartIndex) {
            actionRuleValue += ",";
            actionRuleValue += substrStartIndex;
        }

        if (substrLength) {
            actionRuleValue += ",";
            actionRuleValue += substrLength;
        }
    } else if (actionRuleKey == "set") {
        setRuleArrayDTOList = frontendRightHandPanelData.setRuleArray;
        actionRuleValue += "|";
        for (var i = 0; i < setRuleArrayDTOList.length; i++) {
            if (setRuleArrayDTOList[i].column2) {
                actionRuleValue += setRuleArrayDTOList[i].column2;
                actionRuleValue += "|";
            }
        }
        actionRuleValue = actionRuleValue.substring(0, actionRuleValue.length - 1);
    } else if (actionRuleKey == "dec") {
        var position = frontendRightHandPanelData.dec.column1;
        var dec = frontendRightHandPanelData.dec.column2;

        if (position) {
            actionRuleValue += "|";
            actionRuleValue += position;
        }

        if (dec) {
            actionRuleValue += ":";
            actionRuleValue += dec;
        }
    } else if (actionRuleKey == "map") {
        var table = frontendRightHandPanelData.map.column1;
        var key = frontendRightHandPanelData.map.column2;
        var position = frontendRightHandPanelData.map.column3;

        if (table) {
            actionRuleValue += "|";
            actionRuleValue += table;
        }

        if (key) {
            actionRuleValue += "|";
            actionRuleValue += key;
        }

        if (position) {
            actionRuleValue += ":";
            actionRuleValue += position;
        }
    }
    return actionRuleValue;
}