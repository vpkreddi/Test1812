package com.colco.demo.model.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.colco.demo.domain.MRepEntity;
import com.colco.demo.domain.dto.MRepDTO;
import com.colco.demo.domain.dto.MRepDetailsDTO;

@Component
public class MRepModelMapper {

	@Autowired
	ModelMapper modelMapper;
	
	public List<MRepDTO> toDTOList(List<MRepEntity> poiList){
		return poiList.stream().map(this::toDTO).collect(Collectors.toList());
	}
	
	public MRepDetailsDTO toDetailsDTO(MRepEntity mRep) {
		return modelMapper.map(mRep, MRepDetailsDTO.class, "MRepEntityToDetailsDTO");
	}
	
	public MRepDTO toDTO(MRepEntity mRep) {
		return modelMapper.map(mRep, MRepDTO.class, "MRepEntityToDTO");
	}
}
