package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DbmConversionPixelMappingsDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DbmConversionPixelMappingsDAO {
    GenericDTOList<DbmConversionPixelMappingsDTO> getMappings() throws Exception;

    DbmConversionPixelMappingsDTO getMapping(String conversionPixelId) throws Exception;

    ResponseDTO insertMapping(DbmConversionPixelMappingsDTO request) throws Exception;

    ResponseDTO updateMapping(DbmConversionPixelMappingsDTO request) throws Exception;

    ResponseDTO deleteMapping(String conversionPixelId) throws Exception;
}
