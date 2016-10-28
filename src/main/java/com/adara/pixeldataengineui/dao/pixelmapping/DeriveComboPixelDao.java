package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DeriveComboPixelDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DeriveComboPixelDao {
    GenericDTOList<DeriveComboPixelDTO> getMappings() throws Exception;

    DeriveComboPixelDTO getMapping(String dpKeyId) throws Exception;

    ResponseDTO insertMapping(DeriveComboPixelDTO request) throws Exception;

    ResponseDTO updateMapping(DeriveComboPixelDTO request) throws Exception;

    ResponseDTO deleteMapping(String dpKeyId) throws Exception;
}
