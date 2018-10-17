package com.mrn.springbootjpa.models.dao;

import com.mrn.springbootjpa.models.entity.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClientDAOImpl implements IClientDAO{

    @PersistenceContext
    private EntityManager em;

    @Override
    // we cannot change the results of this method
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return em.createQuery("from Client").getResultList();
    }
}
