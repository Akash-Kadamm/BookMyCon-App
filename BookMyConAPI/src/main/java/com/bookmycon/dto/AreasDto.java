package com.bookmycon.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AreasDto {
    

	
	private String name ;
	
	private String shape ;
	
	private int[] coords ;
	
	private String preFillColor ;
	
	private String fillColor ;
	
	
}
