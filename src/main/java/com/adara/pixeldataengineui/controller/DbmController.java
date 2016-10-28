package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DbmConversionPixelMappingsDTO;
import com.adara.pixeldataengineui.service.pixelmapping.DbmConversionPixelMappingsService;
import com.adara.pixeldataengineui.constants.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yzhao on 9/21/16.
 */
@RestController
public class DbmController {
    private static final Log LOG = LogFactory.getLog(DbmController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    DbmConversionPixelMappingsService mDbmConversionPixelMappingsService;

    @RequestMapping(value = "/mappings/dbm", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericDTOList<DbmConversionPixelMappingsDTO>> getMappings() {
        ResponseEntity<GenericDTOList<DbmConversionPixelMappingsDTO>> response = null;
        GenericDTOList<DbmConversionPixelMappingsDTO> retval = null;

        try {
            retval = mDbmConversionPixelMappingsService.getMappings();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<DbmConversionPixelMappingsDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<DbmConversionPixelMappingsDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[DbmController.getMappings] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<DbmConversionPixelMappingsDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/mapping/dbm", method = RequestMethod.GET)
    public ResponseEntity<DbmConversionPixelMappingsDTO> getMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<DbmConversionPixelMappingsDTO> response = null;
        DbmConversionPixelMappingsDTO retval = null;

        if (id.equals("0")) {
            response = new ResponseEntity<DbmConversionPixelMappingsDTO>(retval, HttpStatus.NO_CONTENT);
            return response;
        }
        try {
            retval = mDbmConversionPixelMappingsService.getMapping(id);
            response = new ResponseEntity<DbmConversionPixelMappingsDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[DbmController.getMapping] Service error: " + e, e);
            response = new ResponseEntity<DbmConversionPixelMappingsDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/insertMapping/dbm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> insertMapping(@RequestBody DbmConversionPixelMappingsDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mDbmConversionPixelMappingsService.insertMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[DbmController.insertMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


    @RequestMapping(value = "/updateMapping/dbm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateMapping(@RequestBody DbmConversionPixelMappingsDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mDbmConversionPixelMappingsService.updateMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[DbmController.updateMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/deleteMapping/dbm", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> deleteMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mDbmConversionPixelMappingsService.deleteMapping(id);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[DbmController.deleteMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }
}
