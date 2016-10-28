package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.DataProviderFacebookPixelsDAO;
import com.adara.pixeldataengineui.dao.pixelmapping.DataProvidersDAO;
import com.adara.pixeldataengineui.dao.pixelmapping.FacebookDpKeysDAO;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DataProviderFacebookPixelsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DataProvidersDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.FacebookDpKeysDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("facebookService")
@Transactional
public class FacebookServiceImpl implements FacebookService {
    @Autowired
    private DataProvidersDAO mDataProvidersDAO;
    @Autowired
    private FacebookDpKeysDAO mFacebookDpKeysDAO;
    @Autowired
    private DataProviderFacebookPixelsDAO mDataProviderFacebookPixelsDAO;

    public GenericDTOList<DataProviderFacebookPixelsDTO> getFacebookPixelMappings() throws Exception {
        return mDataProviderFacebookPixelsDAO.getFacebookPixelMappings();
    }

    public GenericDTOList<DataProvidersDTO> getFacebookDpMappings() throws Exception {
        return mDataProvidersDAO.getFacebookDpMappings();
    }

    public GenericDTOList<FacebookDpKeysDTO> getFacebookKeyMappings() throws Exception {
        return mFacebookDpKeysDAO.getFacebookKeyMappings();
    }

    public DataProviderFacebookPixelsDTO getFacebookPixelMapping(String id) throws Exception {
        return mDataProviderFacebookPixelsDAO.getFacebookPixelMapping(id);
    }

    public DataProvidersDTO getFacebookDpMapping(String id) throws Exception {
        return mDataProvidersDAO.getFacebookDpMapping(id);
    }

    public FacebookDpKeysDTO getFacebookKeyMapping(String id) throws Exception {
        return mFacebookDpKeysDAO.getFacebookKeyMapping(id);
    }

    public ResponseDTO insertMappingDataProviderFacebookPixels(DataProviderFacebookPixelsDTO request) throws Exception {
        return mDataProviderFacebookPixelsDAO.insertMappingDataProviderFacebookPixels(request);
    }

    public ResponseDTO insertMappingFacebookDpKeys(FacebookDpKeysDTO request) throws Exception {
        return mFacebookDpKeysDAO.insertMappingFacebookDpKeys(request);
    }

    public ResponseDTO updateMappingDataProviderFacebookPixels(DataProviderFacebookPixelsDTO request) throws Exception {
        return mDataProviderFacebookPixelsDAO.updateMappingDataProviderFacebookPixels(request);
    }

    public ResponseDTO updateMappingDataProviders(DataProvidersDTO request) throws Exception {
        return mDataProvidersDAO.updateMappingDataProviders(request);
    }

    public ResponseDTO updateMappingFacebookDpKeys(FacebookDpKeysDTO request) throws Exception {
        return mFacebookDpKeysDAO.updateMappingFacebookDpKeys(request);
    }

    public ResponseDTO deleteFacebookPixelMapping(String id) throws Exception {
        return mDataProviderFacebookPixelsDAO.deleteFacebookPixelMapping(id);
    }

    public ResponseDTO deleteFacebookKeyMapping(String id) throws Exception {
        return mFacebookDpKeysDAO.deleteFacebookKeyMapping(id);
    }

}
