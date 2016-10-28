package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.AdobeDpkeyMappingDAO;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.AdobeDpkeyMappingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("adobeService")
@Transactional
public class AdobeServiceImpl implements AdobeService {
    @Autowired
    private AdobeDpkeyMappingDAO mAdobeDpkeyMappingDAO;

    public GenericDTOList<AdobeDpkeyMappingDTO> getMappings() throws Exception {
        return mAdobeDpkeyMappingDAO.getMappings();
    }

    public AdobeDpkeyMappingDTO getMapping(String id) throws Exception {
        return mAdobeDpkeyMappingDAO.getMapping(id);
    }

    public ResponseDTO insertMapping(AdobeDpkeyMappingDTO request) throws Exception {
        return mAdobeDpkeyMappingDAO.insertMapping(request);
    }

    public ResponseDTO updateMapping(AdobeDpkeyMappingDTO request) throws Exception {
        return mAdobeDpkeyMappingDAO.updateMapping(request);
    }

    public ResponseDTO deleteMapping(String id) throws Exception {
        return mAdobeDpkeyMappingDAO.deleteMapping(id);
    }
}
