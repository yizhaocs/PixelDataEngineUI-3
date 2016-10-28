package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DataProviderFacebookPixelsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DataProvidersDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.FacebookDpKeysDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface FacebookService {
    GenericDTOList<DataProviderFacebookPixelsDTO> getFacebookPixelMappings() throws Exception;

    GenericDTOList<DataProvidersDTO> getFacebookDpMappings() throws Exception;

    GenericDTOList<FacebookDpKeysDTO> getFacebookKeyMappings() throws Exception;

    DataProviderFacebookPixelsDTO getFacebookPixelMapping(String id) throws Exception;

    DataProvidersDTO getFacebookDpMapping(String id) throws Exception;

    FacebookDpKeysDTO getFacebookKeyMapping(String id) throws Exception;

    ResponseDTO insertMappingDataProviderFacebookPixels(DataProviderFacebookPixelsDTO request) throws Exception;

    ResponseDTO insertMappingFacebookDpKeys(FacebookDpKeysDTO request) throws Exception;

    ResponseDTO updateMappingDataProviderFacebookPixels(DataProviderFacebookPixelsDTO request) throws Exception;

    ResponseDTO updateMappingDataProviders(DataProvidersDTO request) throws Exception;

    ResponseDTO updateMappingFacebookDpKeys(FacebookDpKeysDTO request) throws Exception;

    ResponseDTO deleteFacebookPixelMapping(String id) throws Exception;

    ResponseDTO deleteFacebookKeyMapping(String id) throws Exception;
}
