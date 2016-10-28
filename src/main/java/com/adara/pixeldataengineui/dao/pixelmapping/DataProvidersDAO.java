package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DataProvidersDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DataProvidersDAO {
    GenericDTOList<DataProvidersDTO> getFacebookDpMappings() throws Exception;

    DataProvidersDTO getFacebookDpMapping(String id) throws Exception;

    ResponseDTO updateMappingDataProviders(DataProvidersDTO request) throws Exception;
}
