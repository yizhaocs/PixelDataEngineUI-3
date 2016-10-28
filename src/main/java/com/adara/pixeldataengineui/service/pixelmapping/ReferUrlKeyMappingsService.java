package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.ReferUrlKeyMappingsDTO;

/**
 * Created by yzhao on 10/12/16.
 */
public interface ReferUrlKeyMappingsService {
    GenericDTOList<ReferUrlKeyMappingsDTO> getMappings() throws Exception;

    ReferUrlKeyMappingsDTO getMapping(String dpId) throws Exception;

    ResponseDTO insertMapping(ReferUrlKeyMappingsDTO request) throws Exception;

    ResponseDTO updateMapping(ReferUrlKeyMappingsDTO request) throws Exception;

    ResponseDTO deleteMapping(String dpId) throws Exception;
}
