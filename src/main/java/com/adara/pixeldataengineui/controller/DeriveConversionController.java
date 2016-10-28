package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DeriveComboPixelDTO;
import com.adara.pixeldataengineui.service.pixelmapping.DeriveComboPixelService;
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
public class DeriveConversionController {
    private static final Log LOG = LogFactory.getLog(DeriveConversionController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    DeriveComboPixelService mDeriveComboPixelService;

    @RequestMapping(value = "/mappings/deriveconversion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericDTOList<DeriveComboPixelDTO>> getMappings() {
        ResponseEntity<GenericDTOList<DeriveComboPixelDTO>> response = null;
        GenericDTOList<DeriveComboPixelDTO> retval = null;

        try {
            retval = mDeriveComboPixelService.getMappings();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<DeriveComboPixelDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<DeriveComboPixelDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[DeriveConversionController.getMappings] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<DeriveComboPixelDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/mapping/deriveconversion", method = RequestMethod.GET)
    public ResponseEntity<DeriveComboPixelDTO> getMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<DeriveComboPixelDTO> response = null;
        DeriveComboPixelDTO retval = null;

        if (id.equals("0")) {
            response = new ResponseEntity<DeriveComboPixelDTO>(retval, HttpStatus.NO_CONTENT);
            return response;
        }
        try {
            retval = mDeriveComboPixelService.getMapping(id);
            response = new ResponseEntity<DeriveComboPixelDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[DeriveConversionController.getMapping] Service error: " + e, e);
            response = new ResponseEntity<DeriveComboPixelDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/insertMapping/deriveconversion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> insertMapping(@RequestBody DeriveComboPixelDTO request) {

        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mDeriveComboPixelService.insertMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[DeriveConversionController.insertMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }


    @RequestMapping(value = "/updateMapping/deriveconversion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateMapping(@RequestBody DeriveComboPixelDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mDeriveComboPixelService.updateMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[DeriveConversionController.updateMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/deleteMapping/deriveconversion", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> deleteMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mDeriveComboPixelService.deleteMapping(id);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[DeriveConversionController.deleteMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
