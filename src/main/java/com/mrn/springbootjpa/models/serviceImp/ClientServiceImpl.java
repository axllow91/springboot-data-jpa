package com.mrn.springbootjpa.models.serviceImp;

import com.mrn.springbootjpa.models.dao.IClientDAO;
import com.mrn.springbootjpa.models.entity.Client;
import com.mrn.springbootjpa.models.service.IClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    private final IClientDAO clientDAO;

    public ClientServiceImpl(IClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    @Override
    @Transactional
    public void save(Client client) {
        clientDAO.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clientDAO.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clientDAO.deleteById(id);
    }
}
