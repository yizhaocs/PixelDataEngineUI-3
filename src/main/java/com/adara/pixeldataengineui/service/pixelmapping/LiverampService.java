package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.LiverampDpMappingsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.LiverampDpkeyMappingsDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface LiverampService {
    GenericDTOList<LiverampDpMappingsDTO> getLiverampDpMappings() throws Exception;

    GenericDTOList<LiverampDpkeyMappingsDTO> getLiverampKeyMappings() throws Exception;

    LiverampDpMappingsDTO getLiverampDpMapping(String id) throws Exception;

    LiverampDpkeyMappingsDTO getLiverampKeyMapping(String id) throws Exception;

    ResponseDTO insertLiverampDpMapping(LiverampDpMappingsDTO request) throws Exception;

    ResponseDTO insertLiverampKeyMapping(LiverampDpkeyMappingsDTO request) throws Exception;

    ResponseDTO updateLiverampDpMapping(LiverampDpMappingsDTO request) throws Exception;

    ResponseDTO updateLiverampKeyMapping(LiverampDpkeyMappingsDTO request) throws Exception;

    ResponseDTO deleteLiverampDpMapping(String id) throws Exception;

    ResponseDTO deleteLiverampKeyMapping(String id) throws Exception;

}
