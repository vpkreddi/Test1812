package com.colco.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.colco.demo.domain.MRepEntity;
import com.colco.demo.domain.dto.MRepCreateDTO;
import com.colco.demo.domain.dto.MRepDTO;
import com.colco.demo.domain.dto.MRepDetailsDTO;
import com.colco.demo.domain.dto.MRepDeleteDTO;
import com.colco.demo.domain.dto.MRepUpdateDTO;
import com.colco.demo.global.response.ApiResponse;
import com.colco.demo.services.MRepService;

@RestController
@RequestMapping("/MRep")
public class MRepController {
	
	@Autowired
	private MRepService mRepService;
	
	@GetMapping("/all")
	public ApiResponse getAllMRep() {
		ApiResponse res = new ApiResponse();
		List<MRepDTO> data = mRepService.getAllMrep();
		if(data.isEmpty()) {
			res.setData(null);
			res.setError(true);
			res.setErrorMsg("data not found");
			res.setStatus("failure");
		} else {
			res.setData(data);
			res.setStatus("success");
		}
		return res;
	}
	
	@GetMapping("/{id}")
	public ApiResponse getMRepDetails(@PathVariable("id") String id) {
		ApiResponse res = new ApiResponse();
		try {
			MRepDetailsDTO data = mRepService.getMRepDetails(id);
			res.setData(data);
			res.setStatus("success");
		} catch (Exception e) {
			res.setData(null);
			res.setError(true);
			res.setErrorMsg("exception occurred while getting mrep details :"+e.getMessage());
			res.setStatus("failure");
		} 
		return res;
	}
	
	@PostMapping
	public ApiResponse createMRep(@RequestBody @Valid MRepCreateDTO dto) {
		ApiResponse res = new ApiResponse();
		try {
			mRepService.createMRep(dto);
			res.setData(null);
			res.setStatus("success");
		} catch (Exception e) {
			e.printStackTrace();
			res.setData(null);
			res.setError(true);
			res.setErrorMsg("exception occurred while creating mrep :"+e.getMessage());
			res.setStatus("failure");
		}
		return res;
	}
	
	@PutMapping
	public ApiResponse updateMRep(@RequestBody @Valid MRepUpdateDTO dto) {
		ApiResponse res = new ApiResponse();
		try {
			mRepService.updateMRep(dto);
			res.setData(null);
			res.setStatus("success");
		} catch (Exception e) {
			e.printStackTrace();
			res.setData(null);
			res.setError(true);
			res.setErrorMsg("exception occurred while updating mrep:"+e.getMessage());
			res.setStatus("failure");
		}
		return res;
	}
	
	
	@DeleteMapping("")
	public ApiResponse deleteMRep(@RequestBody @Valid MRepDeleteDTO dto) {
		ApiResponse res = new ApiResponse();
		try {
			mRepService.deleteMRep(dto);
			res.setData(null);
			res.setStatus("success");
		} catch (Exception e) {
			e.printStackTrace();
			res.setData(null);
			res.setError(true);
			res.setErrorMsg("exception occurred while deleting mrep :"+e.getMessage());
			res.setStatus("failure");
		}
		return res;
	}

}
