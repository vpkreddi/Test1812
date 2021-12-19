package com.colco.demo.domain.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MRepDeleteDTO {
	@NotEmpty(message = "id cannot be null or empty")
	private String id;
}
