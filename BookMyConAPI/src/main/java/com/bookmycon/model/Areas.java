package com.bookmycon.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.NoArgsConstructor;

@Entity
@Table(name = "Areas")
//@Getter
//@Setter
//@AllArgsConstructor
@NoArgsConstructor
//@ToString
public class Areas {

	@Id
	@GeneratedValue
	private int areaId ;
	
	public int getAreaId() {
		return areaId;
	}


	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getShape() {
		return shape;
	}


	public void setShape(String shape) {
		this.shape = shape;
	}


	public int[] getCoords() {
		return coords;
	}


	public void setCoords(int[] coords) {
		this.coords = coords;
	}


	public String getPreFillColor() {
		return preFillColor;
	}


	public void setPreFillColor(String preFillColor) {
		this.preFillColor = preFillColor;
	}


	public String getFillColor() {
		return fillColor;
	}


	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}


	public UserLayout getUserLayout() {
		return userLayout;
	}


	public void setUserLayout(UserLayout userLayout) {
		this.userLayout = userLayout;
	}


	private String name ;
	
	private String shape ;
	
	private int[] coords ;
	
	private String preFillColor ;
	
	private String fillColor ;
	
	
    @ManyToOne
    @JoinColumn(name="userLayout_id", nullable=false)
    private UserLayout userLayout;

	public Areas(int areaId, String name, String shape, int[] coords, String preFillColor, String fillColor,
			UserLayout userLayout) {
		super();
		this.areaId = areaId;
		this.name = name;
		this.shape = shape;
		this.coords = coords;
		this.preFillColor = preFillColor;
		this.fillColor = fillColor;
		this.userLayout = userLayout;
	}

}
