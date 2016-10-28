package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.KruxDpkeyDTO;

/**
 * Created by yzhao on 4/18/16.
 */
public interface KruxDpkeyDAO {
    GenericDTOList<KruxDpkeyDTO> getMappings() throws Exception;

    KruxDpkeyDTO getMapping(String kruxSegmentId) throws Exception;

    ResponseDTO insertMapping(KruxDpkeyDTO request) throws Exception;

    ResponseDTO updateMapping(KruxDpkeyDTO request) throws Exception;

    ResponseDTO deleteMapping(String kruxSegmentId) throws Exception;
}
