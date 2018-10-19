package com.mrn.springbootjpa.models.dao;

import com.mrn.springbootjpa.models.entity.Client;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ClientDAOImpl implements IClientDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
//    // we cannot change the results of this method
//    @Transactional(readOnly = true) -> when implemented the server
    // we can add the annotation to the service impl
    public List<Client> findAll() {
        return em.createQuery("from Client").getResultList();
    }

    @Override
    public Client findById(Long id) {

        // return the id from Client class (entity)
        // find() -> Find by primary key. Search for an entity of the specified class and primary key.
        // If the entity instance is contained in the persistence context, it is returned from there.
        return em.find(Client.class, id);
    }

    @Override
    public void save(Client client) {
        // check if id exists and merge it if true
        if (client.getId() != null && client.getId() > 0) {
            // Merge the state of the given entity into the current persistence context.
            // merge attached the new content to the existent obj
            em.merge(client);
        } else {
            // create new client obj and attached it
            em.persist(client);
        }
    }

    @Override
    public void deleteById(Long id) {
//        // return finding client by id
//        Client client = findById(id);
//        em.remove(client);

        // shorter implementation
        // remove client obj from
        em.remove(findById(id));
    }
}
