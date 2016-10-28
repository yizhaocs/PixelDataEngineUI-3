package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.AdobeDpkeyMappingDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface AdobeDpkeyMappingDAO {
    GenericDTOList<AdobeDpkeyMappingDTO> getMappings() throws Exception;

    AdobeDpkeyMappingDTO getMapping(String id) throws Exception;

    ResponseDTO insertMapping(AdobeDpkeyMappingDTO request) throws Exception;

    ResponseDTO updateMapping(AdobeDpkeyMappingDTO request) throws Exception;

    ResponseDTO deleteMapping(String id) throws Exception;
}
