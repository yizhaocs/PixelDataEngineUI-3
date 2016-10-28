package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DeriveComboPixelDTO;
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
public class DeriveComboPixelDaoImpl implements DeriveComboPixelDao {
    private static final Log LOG = LogFactory.getLog(DeriveComboPixelDaoImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public GenericDTOList<DeriveComboPixelDTO> getMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMappings" + "]";
        String query = "SELECT d.dp_key_id, d.advertiser_id, d.cp_id FROM marketplace.derive_combo_pixel_mappings d order by d.dp_key_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);

        GenericDTOList<DeriveComboPixelDTO> result = new GenericDTOList<DeriveComboPixelDTO>();
        for (Map<String, Object> m : listMap) {
            DeriveComboPixelDTO deriveComboPixelDTO = new DeriveComboPixelDTO();
            deriveComboPixelDTO.setDp_key_id(Integer.valueOf(String.valueOf(m.get("dp_key_id"))));
            deriveComboPixelDTO.setAdvertiser_id(Integer.valueOf(String.valueOf(m.get("advertiser_id"))));
            deriveComboPixelDTO.setCp_id(Integer.valueOf(String.valueOf(m.get("cp_id"))));

            result.add(deriveComboPixelDTO);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public DeriveComboPixelDTO getMapping(String dpKeyId) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMapping" + "]";


        String query = "SELECT d.dp_key_id, d.advertiser_id, d.cp_id FROM marketplace.derive_combo_pixel_mappings d where d.dp_key_id= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        DeriveComboPixelDTO result = jdbcTemplate.queryForObject(query, new Object[]{dpKeyId}, new RowMapper<DeriveComboPixelDTO>() {
            @Override
            public DeriveComboPixelDTO mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                DeriveComboPixelDTO deriveComboPixelDTO = new DeriveComboPixelDTO();
                deriveComboPixelDTO.setDp_key_id(rs.getInt("dp_key_id"));
                deriveComboPixelDTO.setAdvertiser_id(rs.getInt("advertiser_id"));
                deriveComboPixelDTO.setCp_id(rs.getInt("cp_id"));
                return deriveComboPixelDTO;
            }
        });


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public ResponseDTO insertMapping(DeriveComboPixelDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertMapping" + "]";

        ResponseDTO result = new ResponseDTO();
        String query = "insert into marketplace.derive_combo_pixel_mappings(dp_key_id, advertiser_id, cp_id) values(?, ?, ?)";
        Object[] args = new Object[]{request.getDp_key_id(), request.getAdvertiser_id(), request.getCp_id()};

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

    public ResponseDTO updateMapping(DeriveComboPixelDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateMapping" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "UPDATE marketplace.derive_combo_pixel_mappings SET " + "dp_key_id" + "=?" + "," + "advertiser_id" + "=?" + "," + "cp_id" + "=?" + " WHERE dp_key_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{request.getDp_key_id(), request.getAdvertiser_id(), request.getCp_id(), request.getDp_key_id()};

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

    public ResponseDTO deleteMapping(String dpKeyId) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteMapping" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "DELETE FROM marketplace.derive_combo_pixel_mappings WHERE dp_key_id =?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Integer retval = 0;
        retval = jdbcTemplate.update(query, dpKeyId);

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
