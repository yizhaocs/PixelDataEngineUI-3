package com.adara.pixeldataengineui.dao.pixeldataenginerules;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineConfigsDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface PixelDataEngineRuleDAO {
    ResponseDTO insertRule(PixelDataEngineConfigsDTO request, Boolean isUITest) throws Exception;

    GenericDTOList<PixelDataEngineConfigsDTO> getRules() throws Exception;

    PixelDataEngineConfigsDTO getRule(Integer gid, String keyId, Integer priority) throws Exception;

    ResponseDTO updateRule(PixelDataEngineConfigsDTO request, Integer newPriority) throws Exception;

    ResponseDTO deleteRule(Integer gid, String keyId, Integer priority, Boolean isUITest) throws Exception;

    void truncatePixelDataEngineConfigsTable(Boolean isUITest) throws Exception;
}
