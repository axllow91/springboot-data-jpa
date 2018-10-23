package com.mrn.springbootjpa.models.dao;

import com.mrn.springbootjpa.models.entity.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

// Extension of CrudRepository to provide additional methods
// to retrieve entities using the pagination and sorting abstraction.
public interface IClientDAO extends PagingAndSortingRepository<Client, Long> {

}
