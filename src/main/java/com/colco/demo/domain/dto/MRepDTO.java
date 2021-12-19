package com.colco.demo.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MRepDTO {
	@JsonProperty("rep_id")
	private String id;
	@JsonProperty("rep_name")
	private String name;
}
