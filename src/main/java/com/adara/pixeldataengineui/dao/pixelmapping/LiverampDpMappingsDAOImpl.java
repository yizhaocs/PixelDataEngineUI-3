package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.LiverampDpMappingsDTO;
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
public class LiverampDpMappingsDAOImpl implements LiverampDpMappingsDAO {
    private static final Log LOG = LogFactory.getLog(LiverampDpMappingsDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public GenericDTOList<LiverampDpMappingsDTO> getLiverampDpMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getLiverampDpMappings" + "]";
        String query = "SELECT a.dp_name, a.dp_id, a.threshold_mb FROM marketplace.liveramp_dp_mappings a order by a.dp_name";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

        GenericDTOList<LiverampDpMappingsDTO> result = new GenericDTOList<LiverampDpMappingsDTO>();
        for (Map<String, Object> m : listMap) {
            LiverampDpMappingsDTO mLiverampDpMappingsDTO = new LiverampDpMappingsDTO();
            mLiverampDpMappingsDTO.setDp_name(String.valueOf(m.get("dp_name")));
            mLiverampDpMappingsDTO.setDp_id(Integer.valueOf(String.valueOf(m.get("dp_id"))));
            mLiverampDpMappingsDTO.setThreshold_mb(Long.valueOf(String.valueOf(m.get("threshold_mb"))));
            result.add(mLiverampDpMappingsDTO);
        }
        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public LiverampDpMappingsDTO getLiverampDpMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getLiverampDpMapping" + "]";
        String query = "SELECT a.dp_name, a.dp_id, a.threshold_mb FROM marketplace.liveramp_dp_mappings a where a.dp_name=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        LiverampDpMappingsDTO result = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<LiverampDpMappingsDTO>() {

            @Override
            public LiverampDpMappingsDTO mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                LiverampDpMappingsDTO mLiverampDpMappingsDTO = new LiverampDpMappingsDTO();
                mLiverampDpMappingsDTO.setDp_name(rs.getString("dp_name"));
                mLiverampDpMappingsDTO.setDp_id(rs.getInt("dp_id"));
                mLiverampDpMappingsDTO.setThreshold_mb(rs.getLong("threshold_mb"));

                return mLiverampDpMappingsDTO;
            }
        });

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result);

        return result;
    }

    public ResponseDTO insertLiverampDpMapping(LiverampDpMappingsDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertLiverampDpMapping" + "]";
        ResponseDTO result = new ResponseDTO();
        String query = "insert into liveramp_dp_mappings(dp_name, dp_id, threshold_mb) values(?, ?, ?)";
        Object[] args = new Object[]{request.getDp_name(), request.getDp_id(), request.getThreshold_mb()};

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

    public ResponseDTO updateLiverampDpMapping(LiverampDpMappingsDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateLiverampDpMapping" + "]";
        ResponseDTO result = new ResponseDTO();
        String query = "UPDATE marketplace.liveramp_dp_mappings SET " + "dp_name" + "=?" + "," + "dp_id" + "=?" + "," + "threshold_mb" + "=?" + " WHERE dp_name=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{request.getDp_name(), request.getDp_id(), request.getThreshold_mb(), request.getDp_name()};
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


    public ResponseDTO deleteLiverampDpMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteLiverampDpMapping" + "]";
        ResponseDTO result = new ResponseDTO();
        String query = "DELETE FROM marketplace.liveramp_dp_mappings WHERE dp_name= ?";
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
