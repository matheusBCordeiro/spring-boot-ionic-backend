package com.matheuscordeiro.conceptualmodels.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.matheuscordeiro.conceptualmodels.domain.Client;
import com.matheuscordeiro.conceptualmodels.domain.enums.ClientType;
import com.matheuscordeiro.conceptualmodels.dto.ClientNewDTO;
import com.matheuscordeiro.conceptualmodels.repositories.ClientRepository;
import com.matheuscordeiro.conceptualmodels.resources.exception.FieldMessage;
import com.matheuscordeiro.conceptualmodels.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert ,ClientNewDTO> {
	
	@Autowired
	private ClientRepository repository;
	
	@Override
	public void initialize(ClientInsert ann){
	}
		
	@Override
	public boolean isValid(ClientNewDTO objectDto, ConstraintValidatorContext context){
		
		List<FieldMessage> list= new ArrayList<>();
		
		if (objectDto.getType().equals(ClientType.NATURALPERSON.getCode()) && !BR.isValidCPF(objectDto.getCpfORCnpj())) {
			list.add(new FieldMessage("cpfORCnpj", "CPF invalid"));
		}

		if (objectDto.getType().equals(ClientType.JURIDICALPERSON.getCode()) && !BR.isValidCNPJ(objectDto.getCpfORCnpj())) {
			list.add(new FieldMessage("cpfORCnpj", "CNPJ invalid"));
		}
		
		Client client = repository.findByEmail(objectDto.getEmail());
		if (client != null) {
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