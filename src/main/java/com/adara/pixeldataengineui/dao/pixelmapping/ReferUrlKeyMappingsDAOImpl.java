package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.constants.Constants;
import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.ReferUrlKeyMappingsDTO;
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
 * Created by yzhao on 10/12/16.
 */
public class ReferUrlKeyMappingsDAOImpl implements ReferUrlKeyMappingsDAO {
    private static final Log LOG = LogFactory.getLog(ReferUrlKeyMappingsDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public GenericDTOList<ReferUrlKeyMappingsDTO> getMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMappings" + "]";
        String query = "SELECT d.dp_id, d.refer_url_key_id, d.enabled FROM marketplace.refer_url_key_mappings d order by d.dp_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);


        GenericDTOList<ReferUrlKeyMappingsDTO> result = new GenericDTOList<ReferUrlKeyMappingsDTO>();
        for (Map<String, Object> m : listMap) {
            ReferUrlKeyMappingsDTO mReferUrlKeyMappingsDTO = new ReferUrlKeyMappingsDTO();
            mReferUrlKeyMappingsDTO.setDp_id(Integer.valueOf(String.valueOf(m.get("dp_id"))));
            mReferUrlKeyMappingsDTO.setRefer_url_key_id(Integer.valueOf(String.valueOf(m.get("refer_url_key_id"))));
            mReferUrlKeyMappingsDTO.setEnabled(Integer.valueOf(String.valueOf(m.get("enabled"))));
            result.add(mReferUrlKeyMappingsDTO);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public ReferUrlKeyMappingsDTO getMapping(String dp_id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMapping" + "]";
        String query = "SELECT d.dp_id, d.refer_url_key_id, d.enabled FROM marketplace.refer_url_key_mappings d where d.dp_id= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        ReferUrlKeyMappingsDTO result = jdbcTemplate.queryForObject(query, new Object[]{dp_id}, new RowMapper<ReferUrlKeyMappingsDTO>() {

            @Override
            public ReferUrlKeyMappingsDTO mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                ReferUrlKeyMappingsDTO mReferUrlKeyMappingsDTO = new ReferUrlKeyMappingsDTO();
                mReferUrlKeyMappingsDTO.setDp_id(rs.getInt("dp_id"));
                mReferUrlKeyMappingsDTO.setRefer_url_key_id(rs.getInt("refer_url_key_id"));
                mReferUrlKeyMappingsDTO.setEnabled(rs.getInt("enabled"));
                return mReferUrlKeyMappingsDTO;
            }
        });


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public ResponseDTO insertMapping(ReferUrlKeyMappingsDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertMapping" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "insert into refer_url_key_mappings(dp_id, refer_url_key_id, enabled) values(?, ?, ?)";
        Object[] args = new Object[]{request.getDp_id(), request.getRefer_url_key_id(), request.getEnabled()};

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

    public ResponseDTO updateMapping(ReferUrlKeyMappingsDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateMapping" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "UPDATE marketplace.refer_url_key_mappings SET " + "dp_id" + "=?" + "," + "refer_url_key_id" + "=?" + "," + "enabled" + "=?" + " WHERE dp_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{request.getDp_id(), request.getRefer_url_key_id(), request.getEnabled(), request.getDp_id()};
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

    public ResponseDTO deleteMapping(String conversionPixelId) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteMapping" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "DELETE FROM marketplace.refer_url_key_mappings WHERE dp_id =?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Integer retval = 0;
        retval = jdbcTemplate.update(query, conversionPixelId);

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
