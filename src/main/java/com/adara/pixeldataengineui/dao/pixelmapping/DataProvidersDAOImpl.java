package com.adara.pixeldataengineui.dao.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.GenericDTOList;
import com.adara.pixeldataengineui.model.backend.dto.generic.ResponseDTO;
import com.adara.pixeldataengineui.model.backend.dto.pixelmapping.DataProvidersDTO;
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
public class DataProvidersDAOImpl implements DataProvidersDAO {
    private static final Log LOG = LogFactory.getLog(DataProvidersDAOImpl.class);
    private final String CLASS_NAME = this.getClass().getSimpleName();
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public GenericDTOList<DataProvidersDTO> getFacebookDpMappings() throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getFacebookDpMappings" + "]";
        String query = "SELECT a.id, a.name, a.sync_facebook FROM marketplace.data_providers a order by a.id";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Map<String, Object>> listMap = null;
        listMap = jdbcTemplate.queryForList(query);


        GenericDTOList<DataProvidersDTO> result = new GenericDTOList<DataProvidersDTO>();
        for (Map<String, Object> m : listMap) {
            DataProvidersDTO mDataProvidersDTO = new DataProvidersDTO();
            mDataProvidersDTO.setDp_id(Integer.valueOf(String.valueOf(m.get("id"))));
            mDataProvidersDTO.setName(String.valueOf(m.get("name")));
            mDataProvidersDTO.setSync_facebook(Boolean.valueOf(String.valueOf(m.get("sync_facebook"))));
            result.add(mDataProvidersDTO);
        }

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public DataProvidersDTO getFacebookDpMapping(String id) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "getFacebookDpMapping" + "]";
        String query = "SELECT a.id, a.name, a.sync_facebook FROM marketplace.data_providers a where a.id= ?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        DataProvidersDTO result = jdbcTemplate.queryForObject(query, new Object[]{id}, new RowMapper<DataProvidersDTO>() {

            @Override
            public DataProvidersDTO mapRow(ResultSet rs, int rowNum)
                    throws SQLException {
                DataProvidersDTO mDataProvidersDTO = new DataProvidersDTO();
                mDataProvidersDTO.setDp_id(rs.getInt("id"));
                mDataProvidersDTO.setName(rs.getString("name"));
                mDataProvidersDTO.setSync_facebook(rs.getBoolean("sync_facebook"));

               return mDataProvidersDTO;
            }
        });

        if (LOG.isDebugEnabled())
            LOG.debug(LOG_HEADER + "  ,method return -> " + result.toString());

        return result;
    }

    public ResponseDTO updateMappingDataProviders(DataProvidersDTO request) throws Exception {
        final String LOG_HEADER = "[" + CLASS_NAME + "." + "updateMappingDataProviders" + "]";
        ResponseDTO result = new ResponseDTO();

        String query = "UPDATE marketplace.data_providers SET " + "id" + "=?" + "," + "name" + "=?" + "," + "sync_facebook" + "=?" + " WHERE id=?";
        LOG.info(LOG_HEADER + ", " + "Executing query -> " + query.toString());
        Byte syncFacebook = request.getSync_facebook() == false ? (byte) 0 : 1;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = new Object[]{request.getDp_id(), request.getName(), syncFacebook, request.getDp_id()};
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
}
