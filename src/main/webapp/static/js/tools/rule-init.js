function initRuleData() {
    var frontendRightHandPanelData = {
        "parseRule": null,
        "conditionRule": null,
        "actionRule": null,
        "gid": null,
        "keyId": null,
        "priority": null,
        "type": null,
        "split1": {
            "column1": null
        },
        "split2": {
            "column1": null,
            "column2": null
        },
        "lenArray": [],
        "seg": {
            "column1": null
        },
        "containsArray": [],
        "rangeArray": [],
        "substr": {
            "column1": null,
            "column2": null,
            "column3": null,
            "column4": null
        },
        "dec": {
            "column1": null,
            "column2": null
        },
        "map": {
            "column1": null,
            "column2": null,
            "column3": null
        },
        "inArray": [],
        "setRuleArray": []
    };

    return frontendRightHandPanelData;

}


function initRuleArray(frontendRightHandPanelData, defaultValueOfRule) {
    // if no inArray || setRuleArray data from backend, then init them

    if (frontendRightHandPanelData.lenArray.length == 0) {
        frontendRightHandPanelData.lenArray = [{
            column1: '0',
            column2: '' + defaultValueOfRule
        }];
    }

    if (frontendRightHandPanelData.rangeArray.length == 0) {
        frontendRightHandPanelData.rangeArray = [{
            column1: '0',
            column2: '' + defaultValueOfRule
        }];
    }

    if (frontendRightHandPanelData.containsArray.length == 0) {
        frontendRightHandPanelData.containsArray = [{
            column1: '0',
            column2: '' + defaultValueOfRule
        }];
    }

    if (frontendRightHandPanelData.inArray.length == 0) {
        frontendRightHandPanelData.inArray = [{
            column1: '0',
            column2: '' + defaultValueOfRule
        }];
    }

    if (frontendRightHandPanelData.setRuleArray.length == 0) {
        frontendRightHandPanelData.setRuleArray = [{
            column1: '0',
            column2: ''
        }];
    }
}