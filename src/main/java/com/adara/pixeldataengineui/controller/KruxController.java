package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.KruxDpkeyDTO;
import com.adara.pixeldataengineui.service.pixelmapping.KruxDpkeyService;
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
public class KruxController {
    private static final Log LOG = LogFactory.getLog(KruxController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    KruxDpkeyService mKruxDpkeyService;

    @RequestMapping(value = "/mappings/krux", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericDTOList<KruxDpkeyDTO>> getMappings() {
        ResponseEntity<GenericDTOList<KruxDpkeyDTO>> response = null;
        GenericDTOList<KruxDpkeyDTO> retval = null;

        try {
            retval = mKruxDpkeyService.getMappings();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<KruxDpkeyDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<KruxDpkeyDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[KruxController.getMappings] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<KruxDpkeyDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/mapping/krux", method = RequestMethod.GET)
    public ResponseEntity<KruxDpkeyDTO> getMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<KruxDpkeyDTO> response = null;
        KruxDpkeyDTO retval = null;

        if (id.equals("0")) {
            response = new ResponseEntity<KruxDpkeyDTO>(retval, HttpStatus.NO_CONTENT);
            return response;
        }
        try {
            retval = mKruxDpkeyService.getMapping(id);
            response = new ResponseEntity<KruxDpkeyDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[KruxController.getMapping] Service error: " + e, e);
            response = new ResponseEntity<KruxDpkeyDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/insertMapping/krux", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> insertMapping(@RequestBody KruxDpkeyDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval =  mKruxDpkeyService.insertMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[KruxController.insertMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


    @RequestMapping(value = "/updateMapping/krux", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateMapping(@RequestBody KruxDpkeyDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval =  mKruxDpkeyService.updateMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[KruxController.updateMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }

    @RequestMapping(value = "/deleteMapping/krux", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> deleteMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval =  mKruxDpkeyService.deleteMapping(id);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[KruxController.deleteMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
