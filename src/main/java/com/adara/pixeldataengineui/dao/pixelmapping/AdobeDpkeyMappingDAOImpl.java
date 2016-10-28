package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.AdobeDpkeyMappingDTO;
import com.adara.pixeldataengineui.constants.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class AdobeDpkeyMappingDAOImpl implements AdobeDpkeyMappingDAO {
    private static final Log LOG = LogFactory.getLog(AdobeDpkeyMappingDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public GenericDTOList<AdobeDpkeyMappingDTO> getMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMappings" + "]";
        String query = "SELECT a.adobe_segment_id, a.dp_key_id FROM marketplace.adobe_dpkey_mapping a order by a.adobe_segment_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);


        GenericDTOList<AdobeDpkeyMappingDTO> result = new GenericDTOList<AdobeDpkeyMappingDTO>();
        for (Map<String, Object> m : listMap) {
            AdobeDpkeyMappingDTO adobeDpkeyMappingDTO = new AdobeDpkeyMappingDTO();
            adobeDpkeyMappingDTO.setAdobe_segment_id(Integer.valueOf(String.valueOf(m.get("adobe_segment_id"))));
            adobeDpkeyMappingDTO.setDp_key_id(Integer.valueOf(String.valueOf(m.get("dp_key_id"))));

            result.add(adobeDpkeyMappingDTO);
        }


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public AdobeDpkeyMappingDTO getMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMapping" + "]";
        String query = "SELECT a.adobe_segment_id, a.dp_key_id FROM marketplace.adobe_dpkey_mapping a where a.adobe_segment_id= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
         AdobeDpkeyMappingDTO adobeDpkeyMappingDTO = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<AdobeDpkeyMappingDTO>() {

            @Override
            public AdobeDpkeyMappingDTO mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                AdobeDpkeyMappingDTO adobeDpkeyMappingDTO = new AdobeDpkeyMappingDTO();
                adobeDpkeyMappingDTO.setAdobe_segment_id(rs.getInt("adobe_segment_id"));
                adobeDpkeyMappingDTO.setDp_key_id(rs.getInt("dp_key_id"));

                return adobeDpkeyMappingDTO;
            }
        });

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + adobeDpkeyMappingDTO.toString());

        return adobeDpkeyMappingDTO;
    }

    public ResponseDTO insertMapping(AdobeDpkeyMappingDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertMapping" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "insert into marketplace.adobe_dpkey_mapping(adobe_segment_id, dp_key_id) values(?, ?)";
        Object[] args = new Object[]{request.getAdobe_segment_id(), request.getDp_key_id()};

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        Integer retval = 0;
        retval = jdbcTemplate.update(query, args);

        if (retval > 0) {
            result.setMessage(Constants.Response.SUCCESS);
        } else {
            result.setMessage(Constants.Response.FAILURE);
        }


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public ResponseDTO updateMapping(AdobeDpkeyMappingDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateMapping" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "UPDATE marketplace.adobe_dpkey_mapping SET " + "adobe_segment_id" + "=?" + "," + "dp_key_id" + "=?" + " WHERE adobe_segment_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{request.getAdobe_segment_id(), request.getDp_key_id(), request.getAdobe_segment_id()};
        Integer retval = 0;
        retval = jdbcTemplate.update(query, args);

        if (retval > 0) {
            result.setMessage(Constants.Response.SUCCESS);
        } else {
            result.setMessage(Constants.Response.FAILURE);
        }


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public ResponseDTO deleteMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteMapping" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "DELETE FROM marketplace.adobe_dpkey_mapping WHERE adobe_segment_id =?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Integer retval = 0;
        retval = jdbcTemplate.update(query, id);

        if (retval > 0) {
            result.setMessage(Constants.Response.SUCCESS);
        } else {
            result.setMessage(Constants.Response.FAILURE);
        }


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }
}
