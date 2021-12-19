package com.colco.demo.domain.dto;

import java.util.Set;

import com.colco.demo.domain.Drugs;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MRepDetailsDTO {

	@JsonProperty("rep_id")
	private String id;
	@JsonProperty("rep_name")
	private String name;
	@JsonProperty("assigned_drugs")
	private Set<Drugs> drugIds;
}
