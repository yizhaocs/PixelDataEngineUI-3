package com.adara.pixeldataengineui.controller;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.geofilemanager.PixelDataEngineMapsDTO;
import com.adara.pixeldataengineui.service.geofilemanager.GeoFileManagerService;
import com.adara.pixeldataengineui.constants.Constants;
import com.adara.pixeldataengineui.util.ExecCommandUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by yzhao on 7/21/16.
 */
@RestController
public class GeoFileManagerController {
    public static final String ROOT = "upload-dir";
    private static final Log LOG = LogFactory.getLog(GeoFileManagerController.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    @Autowired
    private GeoFileManagerService mGeoFileManagerService;

    @Autowired(required = true)
    private String mysqlHost;

    @RequestMapping(method = RequestMethod.POST, value = "/createPixelDataEngineMap", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> createPixelDataEngineMap(@RequestBody PixelDataEngineMapsDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mGeoFileManagerService.createPixelDataEngineMap(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.createPixelDataEngineMap] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updatePixelDataEngineMap", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updatePixelDataEngineMap(@RequestBody PixelDataEngineMapsDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mGeoFileManagerService.updatePixelDataEngineMap(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.updatePixelDataEngineMap] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateLoadingInProgress", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateLoadingInProgress(@RequestBody PixelDataEngineMapsDTO request) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mGeoFileManagerService.updateLoadingInProgress(request);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.updateLoadingInProgress] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deletePixelDataEngineMap")
    public ResponseEntity<ResponseDTO> deletePixelDataEngineMap(@RequestParam("mapname") String mapName) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        try {
            retval = mGeoFileManagerService.deletePixelDataEngineMap(mapName);
            if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
            } else {
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
            }

        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.deletePixelDataEngineMap] Service error: " + e, e);
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/getPixelDataEngineMaps", method = RequestMethod.GET)
    public ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>> getPixelDataEngineMaps() {
        ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>> response = null;
        GenericDTOList<PixelDataEngineMapsDTO> retval = null;

        try {
            retval = mGeoFileManagerService.getPixelDataEngineMaps();
            if (retval.getList().size() == 0) {
                response = new ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>>(retval, HttpStatus.NO_CONTENT);
            } else {
                response = new ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>>(retval, HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.getPixelDataEngineMaps] Service error: " + e, e);
            response = new ResponseEntity<GenericDTOList<PixelDataEngineMapsDTO>>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @RequestMapping(value = "/getPixelDataEngineMap", method = RequestMethod.GET)
    public ResponseEntity<PixelDataEngineMapsDTO> getPixelDataEngineMap(@RequestParam(value = "mapname", required = false) String mapName) {
        ResponseEntity<PixelDataEngineMapsDTO> response = null;
        PixelDataEngineMapsDTO retval = null;

        if (mapName.equals("0") || mapName.equals("undefined")) {
            response = new ResponseEntity<PixelDataEngineMapsDTO>(retval, HttpStatus.NO_CONTENT);
            return response;
        }
        try {
            retval = mGeoFileManagerService.getPixelDataEngineMap(mapName);

            response = new ResponseEntity<PixelDataEngineMapsDTO>(retval, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.getPixelDataEngineMap] Service error: " + e, e);
            response = new ResponseEntity<PixelDataEngineMapsDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }


    /*
    * @appendWhenCreatingTable -> appendWhenCreatingTable == yes means append then time when use use creating button, then we don't update the version from 0 to 1.
    *
    * */
    @RequestMapping(method = RequestMethod.POST, value = "/appendTable")
    public ResponseEntity<ResponseDTO> appendTable(@RequestParam("file") MultipartFile file, @RequestParam(value = "table", required = false) String table, @RequestParam(value = "appendWhenCreatingTable", required = false) String appendWhenCreatingTable
    ) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        if (!file.isEmpty()) {
            try {
                retval = mGeoFileManagerService.append(file, table, appendWhenCreatingTable);
                if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                    response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
                } else {
                    response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
                }
            } catch (Exception e) {
                LOG.error("[GeoFileManagerController.appendTable] Service error: " + e, e);
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NO_CONTENT);
        }

        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/overrideTable")
    public ResponseEntity<ResponseDTO> overrideTable(@RequestParam("file") MultipartFile file, @RequestParam(value = "table", required = false) String table
    ) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;

        if (!file.isEmpty()) {
            try {
                retval = mGeoFileManagerService.override(file, table);
                if (retval.getMessage().equals(Constants.Response.FAILURE)) {
                    response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NOT_FOUND);
                } else {
                    response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);
                }
            } catch (Exception e) {
                LOG.error("[GeoFileManagerController.overrideTable] Service error: " + e, e);
                response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.NO_CONTENT);
        }

        return response;
    }

    @Deprecated
    @RequestMapping(value = "/createCSVFromTable", method = RequestMethod.GET)
    public ResponseEntity<ResponseDTO> createCSVFromTable(
            @RequestParam(value = "mapname", required = false) String mapName) {
        ResponseEntity<ResponseDTO> response = null;
        ResponseDTO retval = null;
        response = new ResponseEntity<ResponseDTO>(retval, HttpStatus.OK);

        /*
        * Delete the download preparation file from both mysql database server
        * */
        String commandDeleteFileInMysqlServer = "/usr/bin/ssh " + mysqlHost + " rm -f " + Constants.PdeuiGeoFile.FILE_DOWNLOADING_PATH;
        LOG.info("[GeoFileManagerController.downloadTheMap] Service execute command: " + commandDeleteFileInMysqlServer);
        String commandOutput = ExecCommandUtil.execWithOutput(commandDeleteFileInMysqlServer, null);
        LOG.info("[GeoFileManagerController.downloadTheMap] Service execute command: " + commandOutput);

        /*
        * Create a new csv file in mysql database
        * */
        try {
            mGeoFileManagerService.createCSVFromTable(mapName);
        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.createCSVFromTable] Service error: " + e, e);
        }

        return response;
    }


    @RequestMapping(value = "/downloadTheMap", method = RequestMethod.GET)
    public void downloadTheMap(HttpServletResponse response, @RequestParam(value = "mapname", required = false) String mapName) {
        String tableName = "pde_map_" + mapName;
        String credential = " -i  /home/om/.credential/sshkey_om ";
        /*
        * Create /opt/opinmind/
        * */
        String commandCreateDirForDownloadingFileLevel0 = "/usr/bin/ssh " + credential + mysqlHost + " mkdir " + Constants.PdeuiGeoFile.GEO_MANAGER_DIRECTORY_LEVEL0;
        LOG.info("[GeoFileManagerController.downloadTheMap] Service execute command: " + commandCreateDirForDownloadingFileLevel0);
        String commandOutputOfCreateDirForDownloadingFileLevel0 = ExecCommandUtil.execWithOutput(commandCreateDirForDownloadingFileLevel0, null);
        LOG.info("[GeoFileManagerController.downloadTheMap] Service execute command: " + commandOutputOfCreateDirForDownloadingFileLevel0);
        /*
        * Create /opt/opinmind/var/
        * */
        String commandCreateDirForDownloadingFileLevel1 = "/usr/bin/ssh " + credential + mysqlHost + " mkdir " + Constants.PdeuiGeoFile.GEO_MANAGER_DIRECTORY_LEVEL1;
        LOG.info("[GeoFileManagerController.downloadTheMap] Service execute command: " + commandCreateDirForDownloadingFileLevel1);
        String commandOutputOfCreateDirForDownloadingFileLevel1 = ExecCommandUtil.execWithOutput(commandCreateDirForDownloadingFileLevel1, null);
        LOG.info("[GeoFileManagerController.downloadTheMap] Service execute command: " + commandOutputOfCreateDirForDownloadingFileLevel1);

        /*
        * Create /opt/opinmind/var/pdeui/
        * */
        String commandCreateDirForDownloadingFileLevel2 = "/usr/bin/ssh " + credential + mysqlHost + " mkdir " + Constants.PdeuiGeoFile.GEO_MANAGER_DIRECTORY_LEVEL2;
        LOG.info("[GeoFileManagerController.downloadTheMap] Service execute command: " + commandCreateDirForDownloadingFileLevel2);
        String commandOutputOfCreateDirForDownloadingFileLevel2 = ExecCommandUtil.execWithOutput(commandCreateDirForDownloadingFileLevel2, null);
        LOG.info("[GeoFileManagerController.downloadTheMap] Service execute command: " + commandOutputOfCreateDirForDownloadingFileLevel2);

        /*
        * Delete the download preparation file from both mysql database server
        * */
        String commandDeleteFileInMysqlServer = "/usr/bin/ssh " + credential + mysqlHost + " rm -f " + Constants.PdeuiGeoFile.FILE_DOWNLOADING_PATH;
        LOG.info("[GeoFileManagerController.downloadTheMap] Service execute command: " + commandDeleteFileInMysqlServer);
        String commandOutputOfCreatingFile = ExecCommandUtil.execWithOutput(commandDeleteFileInMysqlServer, null);
        LOG.info("[GeoFileManagerController.downloadTheMap] Service execute command: " + commandOutputOfCreatingFile);

        /*
        * Create a new csv file in mysql database
        * */
        try {
            mGeoFileManagerService.createCSVFromTable(tableName);
            Thread.sleep(3000);
        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.createCSVFromTable] Service error: " + e, e);
        }


        // get your file as InputStream
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=" + tableName + ".csv");

        try {
            /*
            * Download the csv file mysql database to pdeui server by using scp command
            * */
            String commandDownloadTheFileFromMysqlServerToPDEUIServer = "scp " + credential + mysqlHost + ":" + Constants.PdeuiGeoFile.FILE_DOWNLOADING_PATH + " " + Constants.PdeuiGeoFile.GEO_MANAGER_DIRECTORY_LEVEL2;
            LOG.info("[GeoFileManagerController.downloadTheMap] Service execute command: " + commandDownloadTheFileFromMysqlServerToPDEUIServer);
            String commandOutputOfDownloading = ExecCommandUtil.execWithOutput(commandDownloadTheFileFromMysqlServerToPDEUIServer, null);
            LOG.info("[GeoFileManagerController.downloadTheMap] Service execute command: " + commandOutputOfDownloading);
            /*
            * sleep for 5 seconds wait for the download complete
            * */
            // Thread.sleep(5000);
            /*
            * Start with downloading
            * */
            File file = new File(Constants.PdeuiGeoFile.FILE_DOWNLOADING_PATH);
            response.setContentLength((int) file.length());
            InputStream is = new FileInputStream(file);
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            LOG.error("[GeoFileManagerController.downloadTheMap] Service error: " + e, e);
        } finally {

            /*
            * Delete the download preparation file from pdeui server
            * */
            File file = new File(Constants.PdeuiGeoFile.FILE_DOWNLOADING_PATH);
            try {
                // need to delete the file after downloading since the file from "SELECT INTO OUTFILE" is forbidden from replacing so that I have to delete the file after user download it
                if (file.exists()) {
                    file.delete();
                }
            } catch (Exception e) {
                LOG.error("[GeoFileManagerController.downloadTheMap] Service error: " + e, e);
            }
        }
    }

    public String getMysqlHost() {
        return mysqlHost;
    }

    public void setMysqlHost(String mysqlHost) {
        this.mysqlHost = mysqlHost;
    }
}
