package com.colco.demo.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.colco.demo.domain.Drugs;
import com.colco.demo.domain.MRepEntity;
import com.colco.demo.domain.dto.MRepCreateDTO;
import com.colco.demo.domain.dto.MRepDTO;
import com.colco.demo.domain.dto.MRepDetailsDTO;
import com.colco.demo.domain.dto.MRepDeleteDTO;
import com.colco.demo.domain.dto.MRepUpdateDTO;
import com.colco.demo.domain.exceptions.InvalidDrugIdException;
import com.colco.demo.domain.exceptions.InvalidMrepIdException;
import com.colco.demo.domain.repo.DrugsRepository;
import com.colco.demo.domain.repo.MRepRepository;
import com.colco.demo.model.mapper.MRepModelMapper;

@Service
public class MRepService {
	
	@Autowired
	private MRepRepository mRepRepo;
	
	@Autowired
	private DrugsRepository drugsRepo;
	
	@Autowired
	private MRepModelMapper modelMapper;

	public List<MRepDTO> getAllMrep() {
		return mRepRepo.findAllMRep();
	}

	public void createMRep(MRepCreateDTO dto) throws InvalidDrugIdException {
		MRepEntity entity =  new MRepEntity();
		entity.setName(dto.getName());
		Set<Drugs> drugs = new HashSet<>();
		for(String id : dto.getDrugIds()){
			Optional<Drugs> drugObj = drugsRepo.findById(id);
			if(drugObj.isPresent()) {
				drugs.add(drugObj.get());
			} else {
				throw new InvalidDrugIdException("invalid drug id");
			}
		};
		entity.setDrugIds(drugs);
		mRepRepo.save(entity);
	}

	public void updateMRep(MRepUpdateDTO dto) throws InvalidMrepIdException , InvalidDrugIdException{
		Optional<MRepEntity> entityObj =  mRepRepo.findById(dto.getId());
		if(entityObj.isPresent()) {
			MRepEntity entity = entityObj.get();
			entity.setName(dto.getName());
			Set<Drugs> drugs = new HashSet<>();
			for(String id : dto.getDrugIds()){
				Optional<Drugs> drugObj = drugsRepo.findById(id);
				if(drugObj.isPresent()) {
					drugs.add(drugObj.get());
				} else {
					throw new InvalidDrugIdException("invalid drug id");
				}
			};
			entity.setDrugIds(drugs);
			mRepRepo.save(entity);
		}else {
			throw new InvalidMrepIdException("invalid mrep id");
		}
		
	}

	public void deleteMRep(MRepDeleteDTO dto) throws InvalidMrepIdException {
		Optional<MRepEntity> entityObj =  mRepRepo.findById(dto.getId());
		if(entityObj.isPresent()) {
			MRepEntity entity = entityObj.get();
			mRepRepo.delete(entity);
		}else {
			throw new InvalidMrepIdException("invalid mrep id");
		}
		
		
	}

	public MRepDetailsDTO getMRepDetails(String id) throws InvalidMrepIdException {
		Optional<MRepEntity> entityObj =  mRepRepo.findById(id);
		if(entityObj.isPresent()) {
			MRepEntity entity = entityObj.get();
			return modelMapper.toDetailsDTO(entity);
		}else {
			throw new InvalidMrepIdException("invalid mrep id");
		}
	}

}
