package com.colco.demo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.colco.demo.domain.MRepEntity;
import com.colco.demo.domain.dto.MRepDTO;
import com.colco.demo.domain.dto.MRepDetailsDTO;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public TypeMap<MRepEntity, MRepDetailsDTO> mRepEntityToDetailsDto(ModelMapper modelMapper) {
		TypeMap<MRepEntity, MRepDetailsDTO> tm = modelMapper.createTypeMap(MRepEntity.class, MRepDetailsDTO.class, "MRepEntityToDetailsDTO");
		return tm;
	}
	
	@Bean
	public TypeMap<MRepEntity, MRepDTO> mRepEntityToDto(ModelMapper modelMapper) {
		TypeMap<MRepEntity, MRepDTO> tm = modelMapper.createTypeMap(MRepEntity.class, MRepDTO.class, "MRepEntityToDTO");
		return tm;
	}
	
}
