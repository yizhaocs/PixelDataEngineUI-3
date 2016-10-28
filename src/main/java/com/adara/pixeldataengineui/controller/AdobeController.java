package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.AdobeDpkeyMappingDTO;
import com.adara.pixeldataengineui.service.pixelmapping.AdobeService;
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
public class AdobeController {
    private static final Log LOG = LogFactory.getLog(AdobeController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    AdobeService mAdobeService;

    @RequestMapping(value = "/mappings/adobe", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericDTOList<AdobeDpkeyMappingDTO>> getMappings() {
        ResponseEntity<GenericDTOList<AdobeDpkeyMappingDTO>> response = null;
        GenericDTOList<AdobeDpkeyMappingDTO> retval = null;

        try {
            retval = mAdobeService.getMappings();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<AdobeDpkeyMappingDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<AdobeDpkeyMappingDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[AdobeController.getMappings] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<AdobeDpkeyMappingDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/mapping/adobe", method = RequestMethod.GET)
    public ResponseEntity<AdobeDpkeyMappingDTO> getMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<AdobeDpkeyMappingDTO> response = null;
        AdobeDpkeyMappingDTO retval = null;

        if (id.equals("0")) {
            response = new ResponseEntity<AdobeDpkeyMappingDTO>(retval, HttpStatus.NO_CONTENT);
            return response;
        }
        try {
            retval = mAdobeService.getMapping(id);
            response = new ResponseEntity<AdobeDpkeyMappingDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[AdobeController.getMapping] Service error: " + e, e);
            response = new ResponseEntity<AdobeDpkeyMappingDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/insertMapping/adobe", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> insertMapping(@RequestBody AdobeDpkeyMappingDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mAdobeService.insertMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[AdobeController.insertMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


    @RequestMapping(value = "/updateMapping/adobe", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateMapping(@RequestBody AdobeDpkeyMappingDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mAdobeService.updateMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[AdobeController.updateMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/deleteMapping/adobe", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> deleteMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mAdobeService.deleteMapping(id);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[AdobeController.deleteMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
