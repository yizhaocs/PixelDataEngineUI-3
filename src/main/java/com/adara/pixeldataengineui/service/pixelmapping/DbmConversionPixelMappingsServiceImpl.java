package com.adara.pixeldataengineui.service.pixelmapping;

import com.adara.pixeldataengineui.dao.pixelmapping.DbmConversionPixelMappingsDAO;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DbmConversionPixelMappingsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@Service("dbmConversionPixelMappingsService")
@Transactional
public class DbmConversionPixelMappingsServiceImpl implements DbmConversionPixelMappingsService {
    @Autowired
    private DbmConversionPixelMappingsDAO mDbmConversionPixelMappingsDAO;

    public GenericDTOList<DbmConversionPixelMappingsDTO> getMappings() throws Exception {
        return mDbmConversionPixelMappingsDAO.getMappings();
    }

    public DbmConversionPixelMappingsDTO getMapping(String conversionPixelId) throws Exception {
        return mDbmConversionPixelMappingsDAO.getMapping(conversionPixelId);
    }

    public ResponseDTO insertMapping(DbmConversionPixelMappingsDTO request) throws Exception {
        return mDbmConversionPixelMappingsDAO.insertMapping(request);
    }

    public ResponseDTO updateMapping(DbmConversionPixelMappingsDTO request) throws Exception {
        return mDbmConversionPixelMappingsDAO.updateMapping(request);
    }

    public ResponseDTO deleteMapping(String conversionPixelId) throws Exception {
        return mDbmConversionPixelMappingsDAO.deleteMapping(conversionPixelId);
    }
}
