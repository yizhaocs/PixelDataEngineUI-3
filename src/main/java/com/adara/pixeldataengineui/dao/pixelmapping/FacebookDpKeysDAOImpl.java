package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.FacebookDpKeysDTO;
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
public class FacebookDpKeysDAOImpl implements FacebookDpKeysDAO {
    private static final Log LOG = LogFactory.getLog(FacebookDpKeysDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public GenericDTOList<FacebookDpKeysDTO> getFacebookKeyMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getFacebookKeyMappings" + "]";
        String query = "SELECT a.key_id, a.enabled, a.update_interval, a.use_image_pixel FROM marketplace.facebook_dp_keys a order by a.key_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

        GenericDTOList<FacebookDpKeysDTO> result = new GenericDTOList<FacebookDpKeysDTO>();
        for (Map<String, Object> m : listMap) {
            FacebookDpKeysDTO mFacebookDpKeysDTO = new FacebookDpKeysDTO();
            mFacebookDpKeysDTO.setKey_id(Integer.valueOf(String.valueOf(m.get("key_id"))));
            mFacebookDpKeysDTO.setEnabled(Byte.valueOf(String.valueOf(m.get("enabled"))));
            mFacebookDpKeysDTO.setUpdate_interval(m.get("update_interval") == null ? null : Byte.valueOf(String.valueOf(m.get("update_interval"))));
            mFacebookDpKeysDTO.setUse_image_pixel(Boolean.valueOf(String.valueOf(m.get("use_image_pixel"))));
            result.add(mFacebookDpKeysDTO);
        }
        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public FacebookDpKeysDTO getFacebookKeyMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getFacebookKeyMapping" + "]";
        String query = "SELECT a.key_id, a.enabled, a.update_interval, a.use_image_pixel FROM marketplace.facebook_dp_keys a where a.key_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        FacebookDpKeysDTO result = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<FacebookDpKeysDTO>() {
            @Override
            public FacebookDpKeysDTO mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                FacebookDpKeysDTO mFacebookDpKeysDTO = new FacebookDpKeysDTO();
                mFacebookDpKeysDTO.setKey_id(rs.getInt("key_id"));
                mFacebookDpKeysDTO.setEnabled(rs.getByte("enabled"));
                String updateInterval = rs.getString("update_interval");
                // Update Interval is allow null
                if (updateInterval != null) {
                    mFacebookDpKeysDTO.setUpdate_interval(rs.getByte("update_interval"));
                } else {
                    mFacebookDpKeysDTO.setUpdate_interval(null);
                }

                mFacebookDpKeysDTO.setUse_image_pixel(rs.getBoolean("use_image_pixel"));
                return mFacebookDpKeysDTO;
            }
        });

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public ResponseDTO insertMappingFacebookDpKeys(FacebookDpKeysDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertMappingFacebookDpKeys" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "insert into facebook_dp_keys(key_id, enabled, update_interval, use_image_pixel) values(?, ?, ?, ?)";

        Object[] args = new Object[]{request.getKey_id(), request.getEnabled(), request.getUpdate_interval(), request.getUse_image_pixel()};

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

    public ResponseDTO updateMappingFacebookDpKeys(FacebookDpKeysDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateMappingFacebookDpKeys" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "UPDATE marketplace.facebook_dp_keys SET " + "key_id" + "=?" + "," + "enabled" + "=?" + "," + "update_interval" + "=?" + "," + "use_image_pixel" + "=?" + " WHERE key_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());


        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{request.getKey_id(), request.getEnabled(), request.getUpdate_interval(), request.getUse_image_pixel(), request.getKey_id()};
        // Integer result = query.executeUpdate();
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

    public ResponseDTO deleteFacebookKeyMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteFacebookKeyMapping" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "DELETE FROM marketplace.facebook_dp_keys WHERE key_id =?";
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
