package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.LiverampDpMappingsDAO;
import com.adara.pixeldataengineui.dao.pixelmapping.LiverampDpkeyMappingsDAO;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.LiverampDpMappingsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.LiverampDpkeyMappingsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("liverampService")
@Transactional
public class LiverampImpl implements LiverampService {
    @Autowired
    private LiverampDpMappingsDAO mLiverampDpMappingsDAO;
    @Autowired
    private LiverampDpkeyMappingsDAO mLiverampDpkeyMappingsDAO;

    public GenericDTOList<LiverampDpMappingsDTO> getLiverampDpMappings() throws Exception {
        return mLiverampDpMappingsDAO.getLiverampDpMappings();
    }

    public GenericDTOList<LiverampDpkeyMappingsDTO> getLiverampKeyMappings() throws Exception {
        return mLiverampDpkeyMappingsDAO.getLiverampKeyMappings();
    }

    public LiverampDpMappingsDTO getLiverampDpMapping(String id) throws Exception {
        return mLiverampDpMappingsDAO.getLiverampDpMapping(id);
    }

    public LiverampDpkeyMappingsDTO getLiverampKeyMapping(String id) throws Exception {
        return mLiverampDpkeyMappingsDAO.getLiverampKeyMapping(id);
    }

    public ResponseDTO insertLiverampDpMapping(LiverampDpMappingsDTO request) throws Exception {
        return mLiverampDpMappingsDAO.insertLiverampDpMapping(request);
    }

    public ResponseDTO insertLiverampKeyMapping(LiverampDpkeyMappingsDTO request) throws Exception {
        return mLiverampDpkeyMappingsDAO.insertLiverampKeyMapping(request);
    }

    public ResponseDTO updateLiverampDpMapping(LiverampDpMappingsDTO request) throws Exception {
        return mLiverampDpMappingsDAO.updateLiverampDpMapping(request);
    }

    public ResponseDTO updateLiverampKeyMapping(LiverampDpkeyMappingsDTO request) throws Exception {
        return mLiverampDpkeyMappingsDAO.updateLiverampKeyMapping(request);
    }

    public ResponseDTO deleteLiverampDpMapping(String id) throws Exception {
        return mLiverampDpMappingsDAO.deleteLiverampDpMapping(id);
    }

    public ResponseDTO deleteLiverampKeyMapping(String id) throws Exception {
        return mLiverampDpkeyMappingsDAO.deleteLiverampKeyMapping(id);
    }
}
