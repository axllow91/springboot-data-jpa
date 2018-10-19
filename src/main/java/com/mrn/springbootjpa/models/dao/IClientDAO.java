package com.mrn.springbootjpa.models.dao;

import com.mrn.springbootjpa.models.entity.Client;
import org.springframework.data.repository.CrudRepository;


// CrudRepository interface has all method we need to handle an object
// save/findOne/delete/deleteAll/count/exists
public interface IClientDAO extends CrudRepository<Client, Long> {

}
