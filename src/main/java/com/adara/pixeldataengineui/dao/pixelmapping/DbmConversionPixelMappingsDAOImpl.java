package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DbmConversionPixelMappingsDTO;
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
public class DbmConversionPixelMappingsDAOImpl implements DbmConversionPixelMappingsDAO {
    private static final Log LOG = LogFactory.getLog(DbmConversionPixelMappingsDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public GenericDTOList<DbmConversionPixelMappingsDTO> getMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMappings" + "]";
        String query = "SELECT d.conversion_pixel_id, d.dbm_url FROM marketplace.dbm_conversion_pixel_mappings d order by d.conversion_pixel_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);


        GenericDTOList<DbmConversionPixelMappingsDTO> result = new GenericDTOList<DbmConversionPixelMappingsDTO>();
        for (Map<String, Object> m : listMap) {
            DbmConversionPixelMappingsDTO mDbmConversionPixelMappingsDTO = new DbmConversionPixelMappingsDTO();
            mDbmConversionPixelMappingsDTO.setConversion_pixel_id(Integer.valueOf(String.valueOf(m.get("conversion_pixel_id"))));
            mDbmConversionPixelMappingsDTO.setDbm_url(String.valueOf(m.get("dbm_url")));

            result.add(mDbmConversionPixelMappingsDTO);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public DbmConversionPixelMappingsDTO getMapping(String conversionPixelId) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getMapping" + "]";
        String query = "SELECT d.conversion_pixel_id, d.dbm_url FROM marketplace.dbm_conversion_pixel_mappings d where d.conversion_pixel_id= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        DbmConversionPixelMappingsDTO  result = jdbcTemplate.queryForObject(query, new Object[]{conversionPixelId}, new RowMapper<DbmConversionPixelMappingsDTO>() {

            @Override
            public DbmConversionPixelMappingsDTO mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                DbmConversionPixelMappingsDTO mDbmConversionPixelMappingsDTO = new DbmConversionPixelMappingsDTO();
                mDbmConversionPixelMappingsDTO.setConversion_pixel_id(rs.getInt("conversion_pixel_id"));
                mDbmConversionPixelMappingsDTO.setDbm_url(rs.getString("dbm_url"));
                return mDbmConversionPixelMappingsDTO;
            }
        });


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public ResponseDTO insertMapping(DbmConversionPixelMappingsDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertMapping" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "insert into dbm_conversion_pixel_mappings(conversion_pixel_id, dbm_url) values(?, ?)";
        Object[] args = new Object[]{request.getConversion_pixel_id(), request.getDbm_url()};

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

    public ResponseDTO updateMapping(DbmConversionPixelMappingsDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateMapping" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "UPDATE marketplace.dbm_conversion_pixel_mappings SET " + "conversion_pixel_id" + "=?" + "," + "dbm_url" + "=?" + " WHERE conversion_pixel_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{request.getConversion_pixel_id(), request.getDbm_url(), request.getConversion_pixel_id()};
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

        String query = "DELETE FROM marketplace.dbm_conversion_pixel_mappings WHERE conversion_pixel_id =?";
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
