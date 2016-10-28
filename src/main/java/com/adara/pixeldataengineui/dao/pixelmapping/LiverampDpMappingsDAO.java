package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.LiverampDpMappingsDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface LiverampDpMappingsDAO {
    GenericDTOList<LiverampDpMappingsDTO> getLiverampDpMappings() throws Exception;

    LiverampDpMappingsDTO getLiverampDpMapping(String id) throws Exception;

    ResponseDTO insertLiverampDpMapping(LiverampDpMappingsDTO request) throws Exception;

    ResponseDTO updateLiverampDpMapping(LiverampDpMappingsDTO request) throws Exception;

    ResponseDTO deleteLiverampDpMapping(String id) throws Exception;
}
