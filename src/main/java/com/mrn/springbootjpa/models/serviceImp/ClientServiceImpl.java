package com.mrn.springbootjpa.models.serviceImp;

import com.mrn.springbootjpa.models.dao.IClientDAO;
import com.mrn.springbootjpa.models.dao.IProductDao;
import com.mrn.springbootjpa.models.entity.Client;
import com.mrn.springbootjpa.models.entity.Product;
import com.mrn.springbootjpa.models.service.IClientService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    private final IClientDAO clientDAO;
    private final IProductDao productDao;

    public ClientServiceImpl(IClientDAO clientDAO, IProductDao productDao) {
        this.clientDAO = clientDAO;
        this.productDao = productDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAll() {
        return (List<Client>) clientDAO.findAll();
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return clientDAO.findAll(pageable);
    }


    @Override
    @Transactional
    public void save(Client client) {
        clientDAO.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Client findById(Long id) {
        return clientDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        clientDAO.deleteById(id);
    }

    @Override
    public List<Product> findByProductName(String key) {
        return productDao.findByProductNameLikeIgnoreCase("%" + key + "%");
    }
}
