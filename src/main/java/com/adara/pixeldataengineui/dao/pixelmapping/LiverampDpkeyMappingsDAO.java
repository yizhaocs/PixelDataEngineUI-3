package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.LiverampDpkeyMappingsDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface LiverampDpkeyMappingsDAO {
    GenericDTOList<LiverampDpkeyMappingsDTO> getLiverampKeyMappings() throws Exception;

    LiverampDpkeyMappingsDTO getLiverampKeyMapping(String id) throws Exception;

    ResponseDTO insertLiverampKeyMapping(LiverampDpkeyMappingsDTO request) throws Exception;

    ResponseDTO updateLiverampKeyMapping(LiverampDpkeyMappingsDTO request) throws Exception;

    ResponseDTO deleteLiverampKeyMapping(String id) throws Exception;
}
