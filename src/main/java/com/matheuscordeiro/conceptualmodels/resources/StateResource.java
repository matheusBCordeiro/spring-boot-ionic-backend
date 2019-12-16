package com.matheuscordeiro.conceptualmodels.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matheuscordeiro.conceptualmodels.domain.City;
import com.matheuscordeiro.conceptualmodels.domain.State;
import com.matheuscordeiro.conceptualmodels.dto.CityDTO;
import com.matheuscordeiro.conceptualmodels.dto.StateDTO;
import com.matheuscordeiro.conceptualmodels.services.CityService;
import com.matheuscordeiro.conceptualmodels.services.StateService;

@RestController
@RequestMapping(value="/staties")
public class StateResource {

	@Autowired
	private StateService service;

	@Autowired
	private CityService cityService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll() {
		List<State> list = service.findAll();
		List<StateDTO> listDto = list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value="/{stateId}/cities", method=RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> findCidades(@PathVariable Integer stateId) {
		List<City> list = cityService.findByState(stateId);
		List<CityDTO> listDto = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
}
