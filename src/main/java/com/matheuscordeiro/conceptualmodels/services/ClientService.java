package com.matheuscordeiro.conceptualmodels.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.matheuscordeiro.conceptualmodels.domain.Client;
import com.matheuscordeiro.conceptualmodels.dto.ClientDTO;
import com.matheuscordeiro.conceptualmodels.repositories.ClientRepository;
import com.matheuscordeiro.conceptualmodels.services.exceptions.DataIntegrityException;
import com.matheuscordeiro.conceptualmodels.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public Client find(Integer id) {

		Optional<Client> object = repository.findById(id);
		if(object == null) {
			throw new ObjectNotFoundException("Object not found! id:" + id
					+ ", Type:" + Client.class.getName());
		}
		return object.orElse(null);
	}

	public Client update(Client object) {
		Client newObject = find(object.getId());
		updateData(newObject, object);
		return repository.save(newObject);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Unable to delete because there are related entities");
		}
	}
	
	public List<Client>  findAll(){
		return repository.findAll();
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Client fromDTO(ClientDTO objectDto) {
		return new Client(objectDto.getId(), objectDto.getName(), objectDto.getEmail(), null , null);
	}
	
	public void updateData(Client newObject, Client object) {
		newObject.setName(object.getName());
		newObject.setEmail(object.getEmail());
	}
}