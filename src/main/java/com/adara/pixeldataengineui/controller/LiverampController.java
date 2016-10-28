package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.LiverampDpMappingsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.LiverampDpkeyMappingsDTO;
import com.adara.pixeldataengineui.service.pixelmapping.LiverampService;
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
public class LiverampController {
    private static final Log LOG = LogFactory.getLog(LiverampController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    LiverampService mLiverampService;

    @RequestMapping(value = "/mappings/liveramp-dp-mappings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericDTOList<LiverampDpMappingsDTO>> getLiverampDpMappings() {
        ResponseEntity<GenericDTOList<LiverampDpMappingsDTO>> response = null;
        GenericDTOList<LiverampDpMappingsDTO> retval = null;

        try {
            retval = mLiverampService.getLiverampDpMappings();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<LiverampDpMappingsDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<LiverampDpMappingsDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[LiverampController.getLiverampDpMappings] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<LiverampDpMappingsDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/mappings/liveramp-dpkey-mappings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericDTOList<LiverampDpkeyMappingsDTO>> getLiverampKeyMappings() {
        ResponseEntity<GenericDTOList<LiverampDpkeyMappingsDTO>> response = null;
        GenericDTOList<LiverampDpkeyMappingsDTO> retval = null;

        try {
            retval = mLiverampService.getLiverampKeyMappings();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<LiverampDpkeyMappingsDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<LiverampDpkeyMappingsDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[LiverampController.getLiverampKeyMappings] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<LiverampDpkeyMappingsDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/mapping/liveramp-dp-mappings", method = RequestMethod.GET)
    public ResponseEntity<LiverampDpMappingsDTO> getLiverampDpMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<LiverampDpMappingsDTO> response = null;
        LiverampDpMappingsDTO retval = null;

        if (id.equals("0")) {
            response = new ResponseEntity<LiverampDpMappingsDTO>(retval, HttpStatus.NO_CONTENT);
            return response;
        }
        try {
            retval = mLiverampService.getLiverampDpMapping(id);;
            response = new ResponseEntity<LiverampDpMappingsDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[LiverampController.getLiverampDpMapping] Service error: " + e, e);
            response = new ResponseEntity<LiverampDpMappingsDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/mapping/liveramp-dpkey-mappings", method = RequestMethod.GET)
    public ResponseEntity<LiverampDpkeyMappingsDTO> getLiverampKeyMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<LiverampDpkeyMappingsDTO> response = null;
        LiverampDpkeyMappingsDTO retval = null;

        if (id.equals("0")) {
            response = new ResponseEntity<LiverampDpkeyMappingsDTO>(retval, HttpStatus.NO_CONTENT);
            return response;
        }
        try {
            retval = mLiverampService.getLiverampKeyMapping(id);
            response = new ResponseEntity<LiverampDpkeyMappingsDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[LiverampController.getLiverampKeyMapping] Service error: " + e, e);
            response = new ResponseEntity<LiverampDpkeyMappingsDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }

    @RequestMapping(value = "/insertMapping/liveramp-dp-mappings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> insertLiverampDpMapping(@RequestBody LiverampDpMappingsDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval =  mLiverampService.insertLiverampDpMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[LiverampController.insertLiverampDpMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }

    @RequestMapping(value = "/insertMapping/liveramp-dpkey-mappings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> insertLiverampKeyMapping(@RequestBody LiverampDpkeyMappingsDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval =  mLiverampService.insertLiverampKeyMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[LiverampController.insertLiverampKeyMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }


    @RequestMapping(value = "/updateMapping/liveramp-dp-mappings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateLiverampDpMapping(@RequestBody LiverampDpMappingsDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval =  mLiverampService.updateLiverampDpMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[LiverampController.updateLiverampDpMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }

    @RequestMapping(value = "/updateMapping/liveramp-dpkey-mappings", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateLiverampKeyMapping(@RequestBody LiverampDpkeyMappingsDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval =  mLiverampService.updateLiverampKeyMapping(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[LiverampController.updateLiverampKeyMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/deleteMapping/liveramp-dp-mappings", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> deleteLiverampDpMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval =  mLiverampService.deleteLiverampDpMapping(id);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[LiverampController.deleteLiverampDpMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }

    @RequestMapping(value = "/deleteMapping/liveramp-dpkey-mappings", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> deleteLiverampKeyMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval =  mLiverampService.deleteLiverampKeyMapping(id);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[LiverampController.deleteLiverampKeyMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }
}
