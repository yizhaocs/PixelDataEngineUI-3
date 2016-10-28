package com.adara.pixeldataengineui.service.pixeldataenginerules;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineGroupsDTO;

/**
 * Created by yzhao on 4/28/16.
 */
public interface PixelDataEngineGroupService {
    ResponseDTO insertGroup(PixelDataEngineGroupsDTO request, Boolean isUITest) throws Exception;

    GenericDTOList<PixelDataEngineGroupsDTO> getGroups() throws Exception;

    PixelDataEngineGroupsDTO getGroup(String triggerKeyId) throws Exception;

    GenericDTOList<PixelDataEngineConfigsDTO> getSameGroup(Integer gid) throws Exception;

    ResponseDTO updateGroup(PixelDataEngineGroupsDTO request) throws Exception;

    ResponseDTO deleteGroup(String triggerKeyId, String gid, Boolean isUITest) throws Exception;

    void truncatePixelDataEngineGroupsTable(Boolean isUITest) throws Exception;
}
