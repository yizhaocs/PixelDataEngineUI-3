package com.adara.pixeldataengineui.dao.geofilemanager;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.geofilemanager.PixelDataEngineMapsDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yzhao on 7/21/16.
 */
public interface GeoFileManagerDAO {
    ResponseDTO createPixelDataEngineMap(PixelDataEngineMapsDTO request) throws Exception;

    ResponseDTO updatePixelDataEngineMap(PixelDataEngineMapsDTO request) throws Exception;

    ResponseDTO updateLoadingInProgress(PixelDataEngineMapsDTO request) throws Exception;

    ResponseDTO deletePixelDataEngineMap(String mapName) throws Exception;

    GenericDTOList<PixelDataEngineMapsDTO> getPixelDataEngineMaps() throws Exception;

    ResponseDTO append(MultipartFile file, String table, String appendWhenCreatingTable) throws Exception;

    ResponseDTO override(MultipartFile file, String table) throws Exception;

    void createCSVFromTable(String tableName) throws Exception;

    PixelDataEngineMapsDTO getPixelDataEngineMap(String tableName) throws Exception;
}
