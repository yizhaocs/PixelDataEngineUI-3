package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DataProviderFacebookPixelsDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface DataProviderFacebookPixelsDAO {
    GenericDTOList<DataProviderFacebookPixelsDTO> getFacebookPixelMappings() throws Exception;

    DataProviderFacebookPixelsDTO getFacebookPixelMapping(String id) throws Exception;

    ResponseDTO insertMappingDataProviderFacebookPixels(DataProviderFacebookPixelsDTO request) throws Exception;

    ResponseDTO updateMappingDataProviderFacebookPixels(DataProviderFacebookPixelsDTO request) throws Exception;

    ResponseDTO deleteFacebookPixelMapping(String id) throws Exception;
}
