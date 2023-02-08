package com.ioffice.demo.model;


import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Account {

	@Id
	@Column(name="customer_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="customer_name")
	private String customerName;
	
    @Column(columnDefinition = "boolean default false",name = "is_migrate")
	private Boolean isMigrate; 
}
