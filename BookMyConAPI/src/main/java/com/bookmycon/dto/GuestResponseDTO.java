package com.bookmycon.dto;

import com.bookmycon.model.Guest;
import org.springframework.beans.BeanUtils;


public class GuestResponseDTO {

    private int guestId;
    private String guestName;
    private String guestCompany;
    private String guestEmail;
    private String guestMobileNo;
    private String thumbnail;

    public GuestResponseDTO() {

    }

    public GuestResponseDTO(int guestId, String guestName, String guestCompany, String guestEmail,
                            String guestMobileNo, String thumbnail) {
        super();
        this.guestId = guestId;
        this.guestName = guestName;
        this.guestCompany = guestCompany;
        this.guestEmail = guestEmail;
        this.guestMobileNo = guestMobileNo;
        this.thumbnail = thumbnail;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestCompany() {
        return guestCompany;
    }

    public void setGuestCompany(String guestCompany) {
        this.guestCompany = guestCompany;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getGuestMobileNo() {
        return guestMobileNo;
    }

    public void setGuestMobileNo(String guestMobileNo) {
        this.guestMobileNo = guestMobileNo;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "GuestResponseDTO [guestId=" + guestId + ", guestName=" + guestName + ", guestCompany=" + guestCompany
                + ", guestEmail=" + guestEmail + ", guestMobileNo=" + guestMobileNo + ", thumbnail=" + thumbnail + "]";
    }

    public static GuestResponseDTO fromEntity(Guest entity) {
        GuestResponseDTO guestResponseDTO = new GuestResponseDTO();
        BeanUtils.copyProperties(entity, guestResponseDTO);
        return guestResponseDTO;
    }
}
