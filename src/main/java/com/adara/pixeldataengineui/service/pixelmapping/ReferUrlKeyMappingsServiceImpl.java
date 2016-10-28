package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.ReferUrlKeyMappingsDAO;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.ReferUrlKeyMappingsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("referUrlKeyMappingsService")
@Transactional
public class ReferUrlKeyMappingsServiceImpl implements ReferUrlKeyMappingsService {
    @Autowired
    private ReferUrlKeyMappingsDAO mReferUrlKeyMappingsDAO;

    public GenericDTOList<ReferUrlKeyMappingsDTO> getMappings() throws Exception {
        return mReferUrlKeyMappingsDAO.getMappings();
    }

    public ReferUrlKeyMappingsDTO getMapping(String dpId) throws Exception {
        return mReferUrlKeyMappingsDAO.getMapping(dpId);

    }

    public ResponseDTO insertMapping(ReferUrlKeyMappingsDTO request) throws Exception {
        return mReferUrlKeyMappingsDAO.insertMapping(request);
    }

    public ResponseDTO updateMapping(ReferUrlKeyMappingsDTO request) throws Exception {
        return mReferUrlKeyMappingsDAO.updateMapping(request);
    }

    public ResponseDTO deleteMapping(String dpId) throws Exception {
        return mReferUrlKeyMappingsDAO.deleteMapping(dpId);
    }
}
