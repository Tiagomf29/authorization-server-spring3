package com.authorizationserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.authorizationserver.model.Cliente;

@RepositoryRestResource
public interface ClienteRespository extends JpaRepository<Cliente, Integer> {

}
