package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DataProviderFacebookPixelsDTO;
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
public class DataProviderFacebookPixelsDAOImpl implements DataProviderFacebookPixelsDAO {
    private static final Log LOG = LogFactory.getLog(DataProviderFacebookPixelsDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public GenericDTOList<DataProviderFacebookPixelsDTO> getFacebookPixelMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getFacebookPixelMappings" + "]";
        String query = "SELECT a.dp_id, a.facebook_pixel_id, a.enable_dat FROM marketplace.data_provider_facebook_pixels a order by a.dp_id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);


        GenericDTOList<DataProviderFacebookPixelsDTO> result = new GenericDTOList<DataProviderFacebookPixelsDTO>();
        for (Map<String, Object> m : listMap) {
            DataProviderFacebookPixelsDTO mDataProviderFacebookPixelsDTO = new DataProviderFacebookPixelsDTO();
            mDataProviderFacebookPixelsDTO.setDp_id(Integer.valueOf(String.valueOf(m.get("dp_id"))));
            mDataProviderFacebookPixelsDTO.setFacebook_pixel_id(Long.valueOf(String.valueOf(m.get("facebook_pixel_id"))));
            mDataProviderFacebookPixelsDTO.setEnable_dat(Integer.valueOf(String.valueOf(m.get("enable_dat"))));
            result.add(mDataProviderFacebookPixelsDTO);
        }
        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public DataProviderFacebookPixelsDTO getFacebookPixelMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getFacebookPixelMapping" + "]";
        String query = "SELECT a.dp_id, a.facebook_pixel_id, a.enable_dat FROM marketplace.data_provider_facebook_pixels a where a.dp_id= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        DataProviderFacebookPixelsDTO result = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<DataProviderFacebookPixelsDTO>() {

            @Override
            public DataProviderFacebookPixelsDTO mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                DataProviderFacebookPixelsDTO mDataProviderFacebookPixelsDTO = new DataProviderFacebookPixelsDTO();
                mDataProviderFacebookPixelsDTO.setDp_id(rs.getInt("dp_id"));
                mDataProviderFacebookPixelsDTO.setFacebook_pixel_id(rs.getLong("facebook_pixel_id"));
                mDataProviderFacebookPixelsDTO.setEnable_dat(rs.getInt("enable_dat"));

                return mDataProviderFacebookPixelsDTO;
            }
        });


        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public ResponseDTO insertMappingDataProviderFacebookPixels(DataProviderFacebookPixelsDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "insertMappingDataProviderFacebookPixels" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "insert into data_provider_facebook_pixels(dp_id, facebook_pixel_id, enable_dat) values(?, ?, ?)";
        Object[] args = new Object[]{request.getDp_id(), request.getFacebook_pixel_id(), request.getEnable_dat()};

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

    public ResponseDTO updateMappingDataProviderFacebookPixels(DataProviderFacebookPixelsDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateMappingDataProviderFacebookPixels" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "UPDATE marketplace.data_provider_facebook_pixels SET " + "dp_id" + "=?" + "," + "facebook_pixel_id" + "=?" + "," + "enable_dat" + "=?" + " WHERE dp_id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{request.getDp_id(), request.getFacebook_pixel_id(), request.getEnable_dat(), request.getDp_id()};
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

    public ResponseDTO deleteFacebookPixelMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "deleteFacebookPixelMapping" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "DELETE FROM marketplace.data_provider_facebook_pixels WHERE dp_id = ?";
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
