package com.matheuscordeiro.conceptualmodels.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.matheuscordeiro.conceptualmodels.domain.Category;
import com.matheuscordeiro.conceptualmodels.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	

	@Transactional(readOnly=true)
	Page<Product> findDistinctByNomeContainingAndCategoriasIn(String name, List<Category> categories, Pageable pageRequest);
	
	/*
	 * Alternative Solution
	 * @Query("SELECT DISTINCT obj FROM Product obj INNER JOIN obj.categories cat WHERE obj.name LIKE %:name% AND cat IN :categories")
		Page<Product> search(@Param ("name") String name, @Param ("category") List<Category> categorias, Pageable pageRequest);
	*/
}
