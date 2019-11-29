package com.matheuscordeiro.conceptualmodels.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matheuscordeiro.conceptualmodels.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
