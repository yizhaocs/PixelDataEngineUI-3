package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * Created by yzhao on 10/12/16.
 */
public class ReferUrlKeyMappingsDTO extends BaseDTO implements Serializable {
    private Integer dp_id;
    private Integer refer_url_key_id;
    private Integer enabled;

    public ReferUrlKeyMappingsDTO() {

    }

    public ReferUrlKeyMappingsDTO(Integer dp_id, Integer refer_url_key_id, Integer enabled) {
        this.dp_id = dp_id;
        this.refer_url_key_id = refer_url_key_id;
        this.enabled = enabled;
    }

    public Integer getDp_id() {
        return dp_id;
    }

    public void setDp_id(Integer dp_id) {
        this.dp_id = dp_id;
    }

    public Integer getRefer_url_key_id() {
        return refer_url_key_id;
    }

    public void setRefer_url_key_id(Integer refer_url_key_id) {
        this.refer_url_key_id = refer_url_key_id;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "ReferUrlKeyMappingsDTO [dp_id=" + dp_id + ", refer_url_key_id=" + refer_url_key_id
                + ", enabled=" + enabled + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((dp_id == null) ? 0 : dp_id.hashCode());
        result = prime * result + ((refer_url_key_id == null) ? 0 : refer_url_key_id.hashCode());
        result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ReferUrlKeyMappingsDTO other = (ReferUrlKeyMappingsDTO) obj;
        if (dp_id == null) {
            if (other.dp_id != null) {
                return false;
            }
        } else if (!dp_id.equals(other.dp_id)) {
            return false;
        }
        if (refer_url_key_id == null) {
            if (other.refer_url_key_id != null) {
                return false;
            }
        } else if (!refer_url_key_id.equals(other.refer_url_key_id)) {
            return false;
        }
        if (enabled == null) {
            if (other.enabled != null) {
                return false;
            }
        } else if (!enabled.equals(other.enabled)) {
            return false;
        }
        return true;
    }
}
