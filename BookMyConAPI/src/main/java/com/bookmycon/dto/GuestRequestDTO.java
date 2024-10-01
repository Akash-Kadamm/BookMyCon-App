package com.bookmycon.dto;

import com.bookmycon.model.Guest;
import com.bookmycon.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

public class GuestRequestDTO {

	private int guestId;
	private String guestName;
	private String guestCompany;
	private String guestEmail;
	private String guestMobileNo;
	private MultipartFile thumbnail;

	private User user;

	public GuestRequestDTO() {

	}

	public GuestRequestDTO(int guestId, String guestName, String guestCompany, String guestEmail, String guestMobileNo, MultipartFile thumbnail, User user) {
		this.guestId = guestId;
		this.guestName = guestName;
		this.guestCompany = guestCompany;
		this.guestEmail = guestEmail;
		this.guestMobileNo = guestMobileNo;
		this.thumbnail = thumbnail;
		this.user = user;
	}

	public GuestRequestDTO(int i, String akashKadam, String s, String s1, String user, String s2, MultipartFile thumbnail, User user1) {
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
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

	public MultipartFile getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(MultipartFile thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "GuestRequestDTO{" +
				"guestId=" + guestId +
				", guestName='" + guestName + '\'' +
				", guestCompany='" + guestCompany + '\'' +
				", guestEmail='" + guestEmail + '\'' +
				", guestMobileNo='" + guestMobileNo + '\'' +
				", thumbnail=" + thumbnail +
				", user=" + user +
				'}';
	}

	public static Guest toEntity(GuestRequestDTO dto)
	{
		Guest entity = new Guest();
		BeanUtils.copyProperties(dto,entity,"thumbnail");
		return entity;
	}
}
