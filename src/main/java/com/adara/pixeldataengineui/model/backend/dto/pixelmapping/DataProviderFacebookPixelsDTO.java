package com.adara.pixeldataengineui.model.backend.dto.pixelmapping;

import com.adara.pixeldataengineui.model.backend.dto.generic.BaseDTO;

import java.io.Serializable;

/**
 * @author YI ZHAO[yi.zhao@adara.com]
 */
public class DataProviderFacebookPixelsDTO extends BaseDTO implements Serializable {
    private Integer dp_id; // int(11)
    private Long facebook_pixel_id; // bigint(20)
    private Integer enable_dat;

    public DataProviderFacebookPixelsDTO() {

    }

    public DataProviderFacebookPixelsDTO(Integer dp_id, Long facebook_pixel_id, Integer enable_dat) {
        this.dp_id = dp_id;
        this.facebook_pixel_id = facebook_pixel_id;
        this.enable_dat = enable_dat;
    }

    public Integer getDp_id() {
        return dp_id;
    }

    public void setDp_id(Integer dp_id) {
        this.dp_id = dp_id;
    }

    public Long getFacebook_pixel_id() {
        return facebook_pixel_id;
    }

    public void setFacebook_pixel_id(Long facebook_pixel_id) {
        this.facebook_pixel_id = facebook_pixel_id;
    }

    public Integer getEnable_dat() {
        return enable_dat;
    }

    public void setEnable_dat(Integer enable_dat) {
        this.enable_dat = enable_dat;
    }

    @Override
    public String toString() {
        return "DataProviderFacebookPixelsDTO [dp_id=" + dp_id + ", facebook_pixel_id=" + facebook_pixel_id  + ", enable_dat=" + enable_dat + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((dp_id == null) ? 0 : dp_id.hashCode());
        result = prime * result + ((facebook_pixel_id == null) ? 0 : facebook_pixel_id.hashCode());
        result = prime * result + ((enable_dat == null) ? 0 : enable_dat.hashCode());
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
        DataProviderFacebookPixelsDTO other = (DataProviderFacebookPixelsDTO) obj;
        if (dp_id == null) {
            if (other.dp_id != null) {
                return false;
            }
        } else if (!dp_id.equals(other.dp_id)) {
            return false;
        }
        if (facebook_pixel_id == null) {
            if (other.facebook_pixel_id != null) {
                return false;
            }
        } else if (!facebook_pixel_id.equals(other.facebook_pixel_id)) {
            return false;
        }

        if (enable_dat == null) {
            if (other.enable_dat != null) {
                return false;
            }
        } else if (!enable_dat.equals(other.enable_dat)) {
            return false;
        }



        return true;
    }
}
