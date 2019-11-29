package com.matheuscordeiro.conceptualmodels.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.matheuscordeiro.conceptualmodels.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
