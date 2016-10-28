package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DataProviderFacebookPixelsDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DataProvidersDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.FacebookDpKeysDTO;
import com.adara.pixeldataengineui.service.pixelmapping.FacebookService;
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
public class FacebookController {
    private static final Log LOG = LogFactory.getLog(FacebookController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();

    @Autowired
    FacebookService mFacebookService;

    @RequestMapping(value = "/mappings/data-provider-facebook-pixels", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericDTOList<DataProviderFacebookPixelsDTO>> getFacebookPixelMappings() {
        ResponseEntity<GenericDTOList<DataProviderFacebookPixelsDTO>> response = null;
        GenericDTOList<DataProviderFacebookPixelsDTO> retval = null;

        try {
            retval = mFacebookService.getFacebookPixelMappings();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<DataProviderFacebookPixelsDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<DataProviderFacebookPixelsDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[FacebookController.getFacebookPixelMappings] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<DataProviderFacebookPixelsDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/mappings/data-providers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericDTOList<DataProvidersDTO>> getFacebookDpMappings() {
        ResponseEntity<GenericDTOList<DataProvidersDTO>> response = null;
        GenericDTOList<DataProvidersDTO> retval = null;

        try {
            retval = mFacebookService.getFacebookDpMappings();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<DataProvidersDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<DataProvidersDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[FacebookController.getFacebookDpMappings] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<DataProvidersDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/mappings/facebook-dp-keys", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericDTOList<FacebookDpKeysDTO>> getFacebookKeyMappings() {
        ResponseEntity<GenericDTOList<FacebookDpKeysDTO>> response = null;
        GenericDTOList<FacebookDpKeysDTO> retval = null;

        try {
            retval = mFacebookService.getFacebookKeyMappings();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<FacebookDpKeysDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<FacebookDpKeysDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[FacebookController.getFacebookKeyMappings] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<FacebookDpKeysDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/mapping/data-provider-facebook-pixels", method = RequestMethod.GET)
    public ResponseEntity<DataProviderFacebookPixelsDTO> getFacebookPixelMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<DataProviderFacebookPixelsDTO> response = null;
        DataProviderFacebookPixelsDTO retval = null;

        if (id.equals("0")) {
            response = new ResponseEntity<DataProviderFacebookPixelsDTO>(retval, HttpStatus.NO_CONTENT);
            return response;
        }
        try {
            retval = mFacebookService.getFacebookPixelMapping(id);
            response = new ResponseEntity<DataProviderFacebookPixelsDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[FacebookController.getFacebookPixelMapping] Service error: " + e, e);
            response = new ResponseEntity<DataProviderFacebookPixelsDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/mapping/data-providers", method = RequestMethod.GET)
    public ResponseEntity<DataProvidersDTO> getFacebookDpMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<DataProvidersDTO> response = null;
        DataProvidersDTO retval = null;

        if (id.equals("0")) {
            response = new ResponseEntity<DataProvidersDTO>(retval, HttpStatus.NO_CONTENT);
            return response;
        }
        try {
            retval = mFacebookService.getFacebookDpMapping(id);
            response = new ResponseEntity<DataProvidersDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[FacebookController.getFacebookDpMapping] Service error: " + e, e);
            response = new ResponseEntity<DataProvidersDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/mapping/facebook-dp-keys", method = RequestMethod.GET)
    public ResponseEntity<FacebookDpKeysDTO> getFacebookKeyMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<FacebookDpKeysDTO> response = null;
        FacebookDpKeysDTO retval = null;

        if (id.equals("0")) {
            response = new ResponseEntity<FacebookDpKeysDTO>(retval, HttpStatus.NO_CONTENT);
            return response;
        }
        try {
            retval = mFacebookService.getFacebookKeyMapping(id);
            response = new ResponseEntity<FacebookDpKeysDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[FacebookController.getFacebookKeyMapping] Service error: " + e, e);
            response = new ResponseEntity<FacebookDpKeysDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/insertMapping/data-provider-facebook-pixels", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> insertMapping(@RequestBody DataProviderFacebookPixelsDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mFacebookService.insertMappingDataProviderFacebookPixels(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[FacebookController.insertMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/insertMapping/facebook-dp-keys", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> insertMapping(@RequestBody FacebookDpKeysDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {

            retval = mFacebookService.insertMappingFacebookDpKeys(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[FacebookController.insertMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/updateMapping/data-provider-facebook-pixels", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateMappingDataProviderFacebookPixels(@RequestBody DataProviderFacebookPixelsDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mFacebookService.updateMappingDataProviderFacebookPixels(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[FacebookController.updateMappingDataProviderFacebookPixels] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }

    @RequestMapping(value = "/updateMapping/data-providers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateMappingDataProviders(@RequestBody DataProvidersDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {

            retval = mFacebookService.updateMappingDataProviders(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[FacebookController.updateMappingDataProviders] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }

    @RequestMapping(value = "/updateMapping/facebook-dp-keys", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateMappingFacebookDpKeys(@RequestBody FacebookDpKeysDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {

            retval = mFacebookService.updateMappingFacebookDpKeys(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[FacebookController.updateMappingFacebookDpKeys] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }

    @RequestMapping(value = "/deleteMapping/data-provider-facebook-pixels", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> deleteFacebookPixelMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval =  mFacebookService.deleteFacebookPixelMapping(id);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[FacebookController.deleteFacebookPixelMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }

    @RequestMapping(value = "/deleteMapping/facebook-dp-keys", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseDTO> deleteFacebookKeyMapping(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval =  mFacebookService.deleteFacebookKeyMapping(id);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[FacebookController.deleteFacebookKeyMapping] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;

    }
}
