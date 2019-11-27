package com.matheuscordeiro.conceptualmodels;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.matheuscordeiro.conceptualmodels.domain.Category;
import com.matheuscordeiro.conceptualmodels.repositories.CategoryRepository;

@SpringBootApplication
public class ConceptualmodelsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ConceptualmodelsApplication.class, args);
	}
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
	}

}
