package com.colco.demo.domain.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MRepUpdateDTO {
	@NotEmpty(message = "id cannot be null or empty")
	private String id;
	@NotEmpty(message = "name cannot be null or empty")
	private String name;
	private Set<String> drugIds;
}
