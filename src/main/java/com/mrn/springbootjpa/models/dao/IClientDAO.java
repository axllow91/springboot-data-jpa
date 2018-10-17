package com.mrn.springbootjpa.models.dao;

import com.mrn.springbootjpa.models.entity.Client;

import java.util.List;

public interface IClientDAO {
    List<Client> findAll();
}
