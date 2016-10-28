package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.constants.Constants;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.ReferUrlKeyMappingsDTO;
import com.adara.pixeldataengineui.service.pixelmapping.ReferUrlKeyMappingsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
@RestController
public class ReferUrlKeyMappingsController {
    private static final Log LOG = LogFactory.getLog(ReferUrlKeyMappingsController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    ReferUrlKeyMappingsService mReferUrlKeyMappingsService;

    @RequestMapping(value = "/mappings/rukm", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericDTOList<ReferUrlKeyMappingsDTO>> getMappings() {
        ResponseEntity<GenericDTOList<ReferUrlKeyMappingsDTO>> response = null;
        GenericDTOList<ReferUrlKeyMappingsDTO> retval = null;

        try {
            retval = mReferUrlKeyMappingsService.getMappings();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<ReferUrlKeyMappingsDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<ReferUrlKeyMappingsDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[ReferUrlKeyMappingsController.getMappings] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<ReferUrlKeyMappingsDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/mapping/rukm", method = RequestMethod.GET)
    public ResponseEntity<ReferUrlKeyMappingsDTO> getMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<ReferUrlKeyMappingsDTO> response = null;
        ReferUrlKeyMappingsDTO retval = null;

        if (id.equals("0")) {
            response = new ResponseEntity<ReferUrlKeyMappingsDTO>(retval, HttpStatus.NO_CONTENT);
            return response;
        }
        try {
            retval = mReferUrlKeyMappingsService.getMapping(id);
            response = new ResponseEntity<ReferUrlKeyMappingsDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[ReferUrlKeyMappingsController.getMapping] Service error: " + e, e);
            response = new ResponseEntity<ReferUrlKeyMappingsDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/insertMapping/rukm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> insertMapping(@RequestBody ReferUrlKeyMappingsDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mReferUrlKeyMappingsService.insertMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[ReferUrlKeyMappingsController.insertMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


    @RequestMapping(value = "/updateMapping/rukm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateMapping(@RequestBody ReferUrlKeyMappingsDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mReferUrlKeyMappingsService.updateMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[ReferUrlKeyMappingsController.updateMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/deleteMapping/rukm", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> deleteMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mReferUrlKeyMappingsService.deleteMapping(id);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[ReferUrlKeyMappingsController.deleteMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }
}
