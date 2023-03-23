package com.bookmycon.model;



import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "UserLayout")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLayout {

	@Id
	private String nameId ;
	
	@JsonIgnore
	  @OneToMany(mappedBy="userLayout")
	    private Set<Areas> areas;
	
}
