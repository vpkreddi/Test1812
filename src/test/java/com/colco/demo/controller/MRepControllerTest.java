package com.colco.demo.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.colco.demo.domain.Drugs;
import com.colco.demo.domain.MRepEntity;
import com.colco.demo.domain.dto.MRepCreateDTO;
import com.colco.demo.domain.dto.MRepDeleteDTO;
import com.colco.demo.domain.dto.MRepDetailsDTO;
import com.colco.demo.domain.exceptions.InvalidDrugIdException;
import com.colco.demo.domain.exceptions.InvalidMrepIdException;
import com.colco.demo.global.response.ApiResponse;
import com.colco.demo.services.MRepService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(MRepController.class)
@DisplayName("in MRepController Test")
public class MRepControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private MRepService service;
	
	ApiResponse res;
	MRepEntity entity;
	MRepDetailsDTO detailsDto;
    Set<Drugs> drugSet;
    String jsonBody;
    MRepDeleteDTO repDeleteDto;
    MRepCreateDTO repCreateDto;
	
	@BeforeEach
	void setUp() {
		 res = new ApiResponse();
		 entity = new MRepEntity();
		 detailsDto = new MRepDetailsDTO();
		 drugSet = new HashSet<>();
		 jsonBody="{}";
		 repDeleteDto = new MRepDeleteDTO();
	}
	
	@Test
	@DisplayName("get Rep details by id test with valid id")
	void getRepDetailsByValidId() throws Exception {
		Drugs drug = new Drugs("1","drug-one");
		drugSet.add(drug);
		detailsDto.setDrugIds(drugSet);detailsDto.setId("1");detailsDto.setName("dkdk");
		when(service.getMRepDetails(any())).thenReturn(detailsDto);
		mvc.perform(get("/MRep/1")).andExpect(status().isOk()).andExpect(jsonPath("$.data.rep_id", is("1")));
	}
	
	@Test
	@DisplayName("get Rep detials by id test with in-valid id")
	void getRepDetailsByInValidId() throws Exception {
		when(service.getMRepDetails(any())).thenThrow(InvalidMrepIdException.class);
		mvc.perform(get("/MRep/1")).andExpect(status().isOk()).andExpect(jsonPath("$.error", is(true)));
	}
	
	@Test
	@DisplayName("delete  Rep  by id test with valid id")
	void deleteMrepByValidId() throws Exception {
		jsonBody = "{\n"
				+ "    \"id\":\"1\"\n"
				+ "}";
		repDeleteDto.setId("1");
		mvc.perform(delete("/MRep").contentType(MediaType.APPLICATION_JSON).content(jsonBody)).andExpect(status().isOk()).andExpect(jsonPath("$.error", is(false)));
	}
	
	@Test
	@DisplayName("delete  Rep  by id test with valid id")
	void deleteMrepByInValidId() throws Exception {
		jsonBody = "{\n"
				+ "    \"id\":\"1\"\n"
				+ "}";
		repDeleteDto.setId("1");
		doThrow(InvalidMrepIdException.class).when(service).deleteMRep(any());
		mvc.perform(delete("/MRep").contentType(MediaType.APPLICATION_JSON).content(jsonBody)).andExpect(status().isOk()).andExpect(jsonPath("$.error", is(true)));

	}
	
	@Test
	@DisplayName("create  Rep  test with valid data")
	void createMRepWithValidData() throws Exception {
		jsonBody = "{\n"
				+ "        \"name\":\"venkat\",\n"
				+ "       \"drugIds\" : [\n"
				+ "        \"61bdcdbc4f238027dbd3b194\"\n"
				+ "    ]\n"
				+ "}";
		mvc.perform(post("/MRep").contentType(MediaType.APPLICATION_JSON).content(jsonBody)).andExpect(status().isOk()).andExpect(jsonPath("$.error", is(false)));
	
	}
	
	@Test
	@DisplayName("create  Rep  test with in-valid data")
	void createMRepWithInValidData() throws Exception {
		jsonBody = "{\n"
				+ "        \"name\":\"venkat\",\n"
				+ "       \"drugIds\" : [\n"
				+ "        \"invaliddrugid\"\n"
				+ "    ]\n"
				+ "}";
		doThrow(InvalidDrugIdException.class).when(service).createMRep(any());
		mvc.perform(post("/MRep").contentType(MediaType.APPLICATION_JSON).content(jsonBody)).andExpect(status().isOk()).andExpect(jsonPath("$.error", is(true)));
	
	}
}
