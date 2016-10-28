package com.adara.pixeldataengineui.model.frontend.requestbody.pixeldataenginerules;

import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineConfigsDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class PixelDataEngineConfigsTestRequest {

    private PixelDataEngineConfigsDTO pixelDataEngineConfigsDTO;
    private String triggerKeyId;
    private String newPriority; // for update rule only

    private String testValue;
    private String testOption;
    public PixelDataEngineConfigsTestRequest() {

    }

    public PixelDataEngineConfigsTestRequest(PixelDataEngineConfigsDTO pixelDataEngineConfigsDTO, String triggerKeyId, String newPriority, String testValue, String testOption) {
        this.pixelDataEngineConfigsDTO = pixelDataEngineConfigsDTO;
        this.triggerKeyId = triggerKeyId;
        this.newPriority = newPriority;
        this.testValue = testValue;
        this.testOption = testOption;
    }

    public PixelDataEngineConfigsDTO getPixelDataEngineConfigsDTO() {
        return pixelDataEngineConfigsDTO;
    }

    public void setPixelDataEngineConfigsDTO(PixelDataEngineConfigsDTO pixelDataEngineConfigsDTO) {
        this.pixelDataEngineConfigsDTO = pixelDataEngineConfigsDTO;
    }

    public String getTriggerKeyId() {
        return triggerKeyId;
    }

    public void setTriggerKeyId(String triggerKeyId) {
        this.triggerKeyId = triggerKeyId;
    }

    public String getNewPriority() {
        return newPriority;
    }

    public void setNewPriority(String newPriority) {
        this.newPriority = newPriority;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

    public String getTestOption() {
        return testOption;
    }

    public void setTestOption(String testOption) {
        this.testOption = testOption;
    }
}
