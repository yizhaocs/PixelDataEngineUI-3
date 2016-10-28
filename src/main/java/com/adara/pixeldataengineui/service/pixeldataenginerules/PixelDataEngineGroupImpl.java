package com.adara.pixeldataengineui.service.pixeldataenginerules;

import com.adara.pixeldataengineui.dao.pixeldataenginerules.PixelDataEngineGroupDAO;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineConfigsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixeldataenginerules.PixelDataEngineGroupsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("pixelDataEngineGroupService")
@Transactional
public class PixelDataEngineGroupImpl implements PixelDataEngineGroupService {
    @Autowired
    private PixelDataEngineGroupDAO mPixelDataEngineGroupDAO;

    public ResponseDTO insertGroup(PixelDataEngineGroupsDTO request, Boolean isUITest) throws Exception {
        return mPixelDataEngineGroupDAO.insertGroup(request, isUITest);
    }

    public GenericDTOList<PixelDataEngineGroupsDTO> getGroups() throws Exception {
        return mPixelDataEngineGroupDAO.getGroups();
    }

    public PixelDataEngineGroupsDTO getGroup(String triggerKeyId) throws Exception {
        return mPixelDataEngineGroupDAO.getGroup(triggerKeyId);
    }

    public GenericDTOList<PixelDataEngineConfigsDTO> getSameGroup(Integer gid) throws Exception {
        return mPixelDataEngineGroupDAO.getSameGroup(gid);
    }

    public ResponseDTO updateGroup(PixelDataEngineGroupsDTO request) throws Exception {
        return mPixelDataEngineGroupDAO.updateGroup(request);
    }

    public ResponseDTO deleteGroup(String triggerKeyId, String gid, Boolean isUITest) throws Exception {
        return mPixelDataEngineGroupDAO.deleteGroup(triggerKeyId, gid, isUITest);
    }

    public void truncatePixelDataEngineGroupsTable(Boolean isUITest) throws Exception {
        mPixelDataEngineGroupDAO.truncatePixelDataEngineGroupsTable(isUITest);
    }

}
