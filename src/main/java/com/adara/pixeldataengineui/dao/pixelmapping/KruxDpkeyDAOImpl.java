package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.KruxDpkeyDTO;
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
 * Created by yzhao on 4/18/16.
 */
public class KruxDpkeyDAOImpl implements KruxDpkeyDAO {
    private static final Log LOG = LogFactory.getLog(KruxDpkeyDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public GenericDTOList<KruxDpkeyDTO> getMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMappings" + "]";
        String query = "SELECT k.krux_segment_id, k.dp_key_id FROM marketplace.krux_dpkey_mappings k order by k.krux_segment_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

        GenericDTOList<KruxDpkeyDTO> result = new GenericDTOList<KruxDpkeyDTO>();
        for (Map<String, Object> m : listMap) {
            KruxDpkeyDTO kruxDpkeyDTO = new KruxDpkeyDTO();
            kruxDpkeyDTO.setKrux_segment_id(String.valueOf(m.get("krux_segment_id")));
            kruxDpkeyDTO.setDp_key_id(Integer.valueOf(String.valueOf(m.get("dp_key_id"))));
            result.add(kruxDpkeyDTO);

        }
        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public KruxDpkeyDTO getMapping(final String kruxSegmentId) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMapping" + "]";
        String query = "SELECT k.krux_segment_id, k.dp_key_id FROM marketplace.krux_dpkey_mappings k where k.krux_segment_id= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        KruxDpkeyDTO result = jdbcTemplate.queryForObject(query, new Object[]{kruxSegmentId}, new RowMapper<KruxDpkeyDTO>() {
            @Override
            public KruxDpkeyDTO mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                KruxDpkeyDTO kruxDpkeyDTO = new KruxDpkeyDTO();
                kruxDpkeyDTO.setKrux_segment_id(rs.getString("krux_segment_id"));
                kruxDpkeyDTO.setDp_key_id(rs.getInt("dp_key_id"));
                return kruxDpkeyDTO;
            }
        });

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public ResponseDTO insertMapping(KruxDpkeyDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertMapping" + "]";
        ResponseDTO result = new ResponseDTO();
        String query = "insert into marketplace.krux_dpkey_mappings(krux_segment_id, dp_key_id) values(?, ?)";
        Object[] args = new Object[]{request.getKrux_segment_id(), request.getDp_key_id()};

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

    public ResponseDTO updateMapping(KruxDpkeyDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateMapping" + "]";
        ResponseDTO result = new ResponseDTO();
        String query = "UPDATE marketplace.krux_dpkey_mappings SET " + "krux_segment_id" + "=?" + "," + "dp_key_id" + "=?" + " WHERE krux_segment_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{request.getKrux_segment_id(), request.getDp_key_id(), request.getKrux_segment_id()};
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

    public ResponseDTO deleteMapping(String kruxSegmentId) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteMapping" + "]";
        ResponseDTO result = new ResponseDTO();
        String query = "DELETE FROM marketplace.krux_dpkey_mappings WHERE krux_segment_id =?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Integer retval = 0;
        retval = jdbcTemplate.update(query, kruxSegmentId);

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
