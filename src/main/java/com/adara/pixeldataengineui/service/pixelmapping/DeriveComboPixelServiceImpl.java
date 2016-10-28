package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.DeriveComboPixelDao;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DeriveComboPixelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("deriveComboPixelService")
@Transactional
public class DeriveComboPixelServiceImpl implements DeriveComboPixelService {
    @Autowired
    private DeriveComboPixelDao mDeriveComboPixelDao;

    public GenericDTOList<DeriveComboPixelDTO> getMappings() throws Exception {
        return mDeriveComboPixelDao.getMappings();
    }

    public DeriveComboPixelDTO getMapping(String dpKeyId) throws Exception {
        return mDeriveComboPixelDao.getMapping(dpKeyId);
    }

    public ResponseDTO insertMapping(DeriveComboPixelDTO request) throws Exception {
        return mDeriveComboPixelDao.insertMapping(request);
    }

    public ResponseDTO updateMapping(DeriveComboPixelDTO request) throws Exception {
        return mDeriveComboPixelDao.updateMapping(request);
    }

    public ResponseDTO deleteMapping(String dpKeyId) throws Exception {
        return mDeriveComboPixelDao.deleteMapping(dpKeyId);
    }
}
