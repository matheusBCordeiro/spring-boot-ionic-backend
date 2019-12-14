package com.matheuscordeiro.conceptualmodels.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.matheuscordeiro.conceptualmodels.domain.Address;
import com.matheuscordeiro.conceptualmodels.domain.City;
import com.matheuscordeiro.conceptualmodels.domain.Client;
import com.matheuscordeiro.conceptualmodels.domain.enums.ClientType;
import com.matheuscordeiro.conceptualmodels.domain.enums.Profile;
import com.matheuscordeiro.conceptualmodels.dto.ClientDTO;
import com.matheuscordeiro.conceptualmodels.dto.ClientNewDTO;
import com.matheuscordeiro.conceptualmodels.repositories.AddressRepository;
import com.matheuscordeiro.conceptualmodels.repositories.ClientRepository;
import com.matheuscordeiro.conceptualmodels.security.UserSS;
import com.matheuscordeiro.conceptualmodels.services.exceptions.AuthorizationException;
import com.matheuscordeiro.conceptualmodels.services.exceptions.DataIntegrityException;
import com.matheuscordeiro.conceptualmodels.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefix;

	public Client find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acess denied");
		}
		Optional<Client> object = repository.findById(id);
		if (object == null) {
			throw new ObjectNotFoundException("Object not found! id:" + id + ", Type:" + Client.class.getName());
		}
		return object.orElse(null);

	}

	@Transactional
	public Client insert(Client object) {
		object.setId(null);
		object = repository.save(object);
		addressRepository.saveAll(object.getAddresses());
		return object;
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
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Unable to delete because there are related entities");
		}
	}

	public List<Client> findAll() {
		return repository.findAll();
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO objectDto) {
		return new Client(objectDto.getId(), objectDto.getName(), objectDto.getEmail(), null, null, null);
	}

	public Client fromDTO(ClientNewDTO objectDto) {
		Client client = new Client(null, objectDto.getName(), objectDto.getEmail(), objectDto.getCpfORCnpj(),
				ClientType.toEnum(objectDto.getType()), pe.encode(objectDto.getPassword()));
		City city = new City(objectDto.getCityId(), null, null);
		Address address = new Address(null, objectDto.getPlace(), objectDto.getNumber(), objectDto.getComplement(),
				objectDto.getDistrict(), objectDto.getCep(), client, city);
		client.getAddresses().add(address);
		client.getPhones().add(objectDto.getPhone1());
		if (objectDto.getPhone2() != null) {
			client.getPhones().add(objectDto.getPhone2());
		}
		if (objectDto.getPhone3() != null) {
			client.getPhones().add(objectDto.getPhone3());
		}
		return client;
	}

	public void updateData(Client newObject, Client object) {
		newObject.setName(object.getName());
		newObject.setEmail(object.getEmail());
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		String fileName = prefix + user.getId() + ".jpg";

		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}