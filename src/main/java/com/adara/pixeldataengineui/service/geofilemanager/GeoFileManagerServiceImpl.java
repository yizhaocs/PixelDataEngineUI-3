package com.adara.pixeldataengineui.service.geofilemanager;

import com.adara.pixeldataengineui.dao.geofilemanager.GeoFileManagerDAOImpl;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.geofilemanager.PixelDataEngineMapsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yzhao on 7/21/16.
 */
@Service("geoFileManagerService")
@Transactional
public class GeoFileManagerServiceImpl implements GeoFileManagerService {
    @Autowired
    private GeoFileManagerDAOImpl mGeoFileManagerDAOImpl;

    public ResponseDTO createPixelDataEngineMap(PixelDataEngineMapsDTO request) throws Exception {
        return mGeoFileManagerDAOImpl.createPixelDataEngineMap(request);
    }

    public ResponseDTO updatePixelDataEngineMap(PixelDataEngineMapsDTO request) throws Exception {
        return mGeoFileManagerDAOImpl.updatePixelDataEngineMap(request);
    }

    public ResponseDTO updateLoadingInProgress(PixelDataEngineMapsDTO request) throws Exception {
        return mGeoFileManagerDAOImpl.updateLoadingInProgress(request);
    }

    public ResponseDTO deletePixelDataEngineMap(String mapName) throws Exception {
        return mGeoFileManagerDAOImpl.deletePixelDataEngineMap(mapName);
    }

    public GenericDTOList<PixelDataEngineMapsDTO> getPixelDataEngineMaps() throws Exception {
        return mGeoFileManagerDAOImpl.getPixelDataEngineMaps();
    }

    public ResponseDTO append(MultipartFile file, String table, String appendWhenCreatingTable) throws Exception {
        return mGeoFileManagerDAOImpl.append(file, table, appendWhenCreatingTable);
    }

    public ResponseDTO override(MultipartFile file, String table) throws Exception {
        return mGeoFileManagerDAOImpl.override(file, table);
    }

    public void createCSVFromTable(String tableName) throws Exception {
        mGeoFileManagerDAOImpl.createCSVFromTable(tableName);
    }

    public PixelDataEngineMapsDTO getPixelDataEngineMap(String tableName) throws Exception {
        return mGeoFileManagerDAOImpl.getPixelDataEngineMap(tableName);
    }
}
