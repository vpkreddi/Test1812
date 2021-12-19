package com.colco.demo.services;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.colco.demo.domain.Drugs;
import com.colco.demo.domain.MRepEntity;
import com.colco.demo.domain.dto.MRepCreateDTO;
import com.colco.demo.domain.dto.MRepDTO;
import com.colco.demo.domain.dto.MRepDeleteDTO;
import com.colco.demo.domain.dto.MRepDetailsDTO;
import com.colco.demo.domain.exceptions.InvalidDrugIdException;
import com.colco.demo.domain.exceptions.InvalidMrepIdException;
import com.colco.demo.domain.repo.DrugsRepository;
import com.colco.demo.domain.repo.MRepRepository;
import com.colco.demo.model.mapper.MRepModelMapper;

@ExtendWith(SpringExtension.class)
@DisplayName("In MRep Service Test class")
public class MRepServicesTest {

	@Autowired
	private MRepService service;

	@MockBean
	private MRepRepository mReprepo;

	@MockBean
	private DrugsRepository drugRepo;

	MRepEntity entity;
	MRepDetailsDTO detailsDto;
	Set<Drugs> drugSet;
	Set<String> drugIds;
	MRepDeleteDTO repDeleteDto;
	MRepCreateDTO repCreateDto;

	@TestConfiguration
	static class POIServiceTestMockitoSetup {

		@Bean
		public MRepService POIService() {
			return new MRepService();
		}

		@Bean
		public ModelMapper modelMapper() {
			return new ModelMapper();
		}

		@Bean
		public MRepModelMapper poiModelMapper() {
			return new MRepModelMapper();
		}

		@Bean
		public TypeMap<MRepEntity, MRepDetailsDTO> mRepEntityToDetailsDto(ModelMapper modelMapper) {
			TypeMap<MRepEntity, MRepDetailsDTO> tm = modelMapper.createTypeMap(MRepEntity.class, MRepDetailsDTO.class,
					"MRepEntityToDetailsDTO");
			return tm;
		}

		@Bean
		public TypeMap<MRepEntity, MRepDTO> mRepEntityToDto(ModelMapper modelMapper) {
			TypeMap<MRepEntity, MRepDTO> tm = modelMapper.createTypeMap(MRepEntity.class, MRepDTO.class,
					"MRepEntityToDTO");
			return tm;
		}

	}

	@BeforeEach
	void setUp() {
		entity = new MRepEntity();
		detailsDto = new MRepDetailsDTO();
		drugSet = new HashSet<>();
		drugIds = new HashSet<>();
		repDeleteDto = new MRepDeleteDTO();
		repCreateDto = new MRepCreateDTO();
	}

	@Test
	@DisplayName("get Rep details by id test with valid id")
	void getRepDetailsByValidId() throws Exception {
		Drugs drug = new Drugs("1", "drug-one");
		drugSet.add(drug);
		entity.setDrugIds(drugSet);
		entity.setId("1");
		entity.setName("dkdk");
		when(mReprepo.findById(any())).thenReturn(Optional.of(entity));
		assertEquals(true, service.getMRepDetails("1").getId().equals("1"));
	}

	@Test
	@DisplayName("get Rep details by id test with in-valid id")
	void getRepDetailsByInValidId() throws Exception {
		when(mReprepo.findById(any())).thenReturn(Optional.empty());
		assertThrows(InvalidMrepIdException.class, () -> {
			service.getMRepDetails("kdkdk");
		});
	}

	@Test
	@DisplayName("delete  Rep  by id test with valid id")
	void deleteMrepByValidId() throws Exception {
		repDeleteDto.setId("1");
		Drugs drug = new Drugs("1", "drug-one");
		drugSet.add(drug);
		entity.setDrugIds(drugSet);
		entity.setId("1");
		entity.setName("dkdk");
		when(mReprepo.findById(any())).thenReturn(Optional.of(entity));
		assertDoesNotThrow(()->{service.deleteMRep(repDeleteDto);});
	}

	@Test
	@DisplayName("delete  Rep  by id test with valid id")
	void deleteMrepByInValidId() throws Exception {
		repDeleteDto.setId("dkdk");
		when(mReprepo.findById(any())).thenReturn(Optional.empty());
		assertThrows(InvalidMrepIdException.class, () -> {
			service.deleteMRep(repDeleteDto);
		});
	}
	
	@Test
	@DisplayName("create  Rep  test with valid data")
	void createMRepWithValidData() throws Exception {
		repCreateDto.setName("venkat");
		Drugs drug = new Drugs("1", "drug-one");
		drugIds.add("1234");
		repCreateDto.setDrugIds(drugIds);
		when(drugRepo.findById(any())).thenReturn(Optional.of(drug));
		assertDoesNotThrow(()->{service.createMRep(repCreateDto);});
	}
	
	@Test
	@DisplayName("create  Rep  test with in-valid data")
	void createMRepWithInValidData() throws Exception {
		repCreateDto.setName("venkat");
		Drugs drug = new Drugs("1", "drug-one");
		drugIds.add("1234");
		repCreateDto.setDrugIds(drugIds);
		when(drugRepo.findById(any())).thenReturn(Optional.empty());
		assertThrows(InvalidDrugIdException.class, () -> {
			service.createMRep(repCreateDto);
		});
	}
}
