package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.FacebookDpKeysDTO;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public interface FacebookDpKeysDAO {
    GenericDTOList<FacebookDpKeysDTO> getFacebookKeyMappings() throws Exception;

    FacebookDpKeysDTO getFacebookKeyMapping(String id) throws Exception;

    ResponseDTO insertMappingFacebookDpKeys(FacebookDpKeysDTO request) throws Exception;

    ResponseDTO updateMappingFacebookDpKeys(FacebookDpKeysDTO request) throws Exception;

    ResponseDTO deleteFacebookKeyMapping(String id) throws Exception;
}
