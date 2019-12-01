package com.matheuscordeiro.conceptualmodels.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.matheuscordeiro.conceptualmodels.domain.Client;
import com.matheuscordeiro.conceptualmodels.dto.ClientDTO;
import com.matheuscordeiro.conceptualmodels.repositories.ClientRepository;
import com.matheuscordeiro.conceptualmodels.resources.exception.FieldMessage;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate ,ClientDTO> {
	

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientRepository repository;
	
	@Override
	public void initialize(ClientUpdate ann){
	}
		
	@Override
	public boolean isValid(ClientDTO objectDto, ConstraintValidatorContext context){
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list= new ArrayList<>();
		
		Client client = repository.findByEmail(objectDto.getEmail());
		if (client != null && !client.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Email already existing"));
		}
		
		for(FieldMessage e : list){
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}