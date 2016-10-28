package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.LiverampDpkeyMappingsDTO;
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
public class LiverampDpkeyMappingsDAOImpl implements LiverampDpkeyMappingsDAO {
    private static final Log LOG = LogFactory.getLog(LiverampDpkeyMappingsDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public GenericDTOList<LiverampDpkeyMappingsDTO> getLiverampKeyMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getLiverampKeyMappings" + "]";
        String query = "SELECT a.liveramp_segment_id, a.dp_key_id, a.value FROM marketplace.liveramp_dpkey_mappings a order by a.dp_key_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

        GenericDTOList<LiverampDpkeyMappingsDTO> result = new GenericDTOList<LiverampDpkeyMappingsDTO>();
        for (Map<String, Object> m : listMap) {
            LiverampDpkeyMappingsDTO mLiverampDpkeyMappingsDTO = new LiverampDpkeyMappingsDTO();
            mLiverampDpkeyMappingsDTO.setLiveramp_segment_id(Long.valueOf(String.valueOf(m.get("liveramp_segment_id"))));
            mLiverampDpkeyMappingsDTO.setDp_key_id(Integer.valueOf(String.valueOf(m.get("dp_key_id"))));
            mLiverampDpkeyMappingsDTO.setValue(String.valueOf(m.get("value")));
            result.add(mLiverampDpkeyMappingsDTO);

        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public LiverampDpkeyMappingsDTO getLiverampKeyMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getLiverampKeyMapping" + "]";
        String query = "SELECT a.liveramp_segment_id, a.dp_key_id, a.value FROM marketplace.liveramp_dpkey_mappings a where a.liveramp_segment_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        LiverampDpkeyMappingsDTO result = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<LiverampDpkeyMappingsDTO>() {

            @Override
            public LiverampDpkeyMappingsDTO mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                LiverampDpkeyMappingsDTO mLiverampDpkeyMappingsDTO = new LiverampDpkeyMappingsDTO();
                mLiverampDpkeyMappingsDTO.setLiveramp_segment_id(rs.getLong("liveramp_segment_id"));
                mLiverampDpkeyMappingsDTO.setDp_key_id(rs.getInt("dp_key_id"));
                mLiverampDpkeyMappingsDTO.setValue(rs.getString("value"));

                return mLiverampDpkeyMappingsDTO;
            }
        });

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public ResponseDTO insertLiverampKeyMapping(LiverampDpkeyMappingsDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertLiverampKeyMapping" + "]";
        ResponseDTO result = new ResponseDTO();
        String query = "insert into liveramp_dpkey_mappings(liveramp_segment_id, dp_key_id, value) values(?, ?, ?)";
        Object[] args = new Object[]{request.getLiveramp_segment_id(), request.getDp_key_id(), request.getValue()};

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

    public ResponseDTO updateLiverampKeyMapping(LiverampDpkeyMappingsDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateLiverampKeyMapping" + "]";
        ResponseDTO result = new ResponseDTO();
        String query = "UPDATE marketplace.liveramp_dpkey_mappings SET " + "liveramp_segment_id" + "=?" + "," + "dp_key_id" + "=?" + "," + "value" + "=?" + " WHERE liveramp_segment_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{request.getLiveramp_segment_id(), request.getDp_key_id(), request.getValue(), request.getLiveramp_segment_id()};
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

    public ResponseDTO deleteLiverampKeyMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteLiverampKeyMapping" + "]";
        ResponseDTO result = new ResponseDTO();
        String query = "DELETE FROM marketplace.liveramp_dpkey_mappings WHERE liveramp_segment_id = ? ";
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
