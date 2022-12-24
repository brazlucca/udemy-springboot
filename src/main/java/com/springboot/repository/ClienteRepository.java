package com.springboot.repository;

import com.springboot.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteRepository {

    @Autowired
    public ClienteRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private EntityManager entityManager;

    @Transactional
    public Cliente save(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente update(Cliente cliente) {
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public Cliente delete(Cliente cliente) {
        var clienteManaged = entityManager.find(Cliente.class, cliente.getId());
        if(!entityManager.contains(cliente)){
            clienteManaged = entityManager.merge(clienteManaged);
        }
        entityManager.remove(clienteManaged);
        return clienteManaged;
    }

    @Transactional(readOnly = true)
    public List<Cliente> findByName(Cliente cliente) {
        String jpql = "select c from Cliente c where c.nome like :nome";
        TypedQuery<Cliente> typedQuery = entityManager.createQuery(jpql, Cliente.class);
        typedQuery.setParameter("nome", "%"+ cliente.getNome() + "%");

        return typedQuery.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Cliente> recuperar() {
        return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
    }

}
