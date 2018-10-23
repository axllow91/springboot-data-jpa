package com.mrn.springbootjpa.models.service;

import com.mrn.springbootjpa.models.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientService {
    // return all clients
    List<Client> findAll();

    // return all number pages
    Page<Client> findAll(Pageable pageable);

    // save client
    void save(Client client);

    // find client by id
    Client findById(Long id);

    // delete client by id
    void deleteById(Long id);
}
