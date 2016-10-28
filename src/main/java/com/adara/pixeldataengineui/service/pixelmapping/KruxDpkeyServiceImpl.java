package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.KruxDpkeyDAO;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.KruxDpkeyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("kruxDpkeyService")
@Transactional
public class KruxDpkeyServiceImpl implements KruxDpkeyService {
    @Autowired
    private KruxDpkeyDAO mKruxDpkeyDAO;

    public GenericDTOList<KruxDpkeyDTO> getMappings() throws Exception {
        return mKruxDpkeyDAO.getMappings();
    }

    public KruxDpkeyDTO getMapping(String kruxSegmentId) throws Exception {
        return mKruxDpkeyDAO.getMapping(kruxSegmentId);
    }

    public ResponseDTO insertMapping(KruxDpkeyDTO request) throws Exception {
        return mKruxDpkeyDAO.insertMapping(request);
    }

    public ResponseDTO updateMapping(KruxDpkeyDTO request) throws Exception {
        return mKruxDpkeyDAO.updateMapping(request);
    }

    public ResponseDTO deleteMapping(String kruxSegmentId) throws Exception {
        return mKruxDpkeyDAO.deleteMapping(kruxSegmentId);
    }
}
