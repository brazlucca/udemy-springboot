package com.springboot.repository;

import com.springboot.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteRepository {

    @Autowired
    public ClienteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String SELECT_ALL = "select * from cliente";
    private static String UPDATE = "update cliente set nome = ? where id = ?";
    private static String DELETE = "delete from cliente where id = ?";

    private JdbcTemplate jdbcTemplate;

    public Cliente save(Cliente cliente) {
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public List<Cliente> recuperar() {
        return jdbcTemplate.query(SELECT_ALL, clientMapper());
    }

    public Cliente update(Cliente cliente) {
        jdbcTemplate.update(UPDATE, new Object[]{cliente.getNome(), cliente.getId()});
        return cliente;
    }

    public Cliente delete(Cliente cliente) {
        jdbcTemplate.update(DELETE, new Object[]{cliente.getId()});
        return cliente;
    }

    public List<Cliente> findByName(Cliente cliente) {
        return jdbcTemplate.query(SELECT_ALL.concat(" where nome like ?"),clientMapper(), new Object[]{"%" + cliente.getNome() + "%"});
    }

    private RowMapper<Cliente> clientMapper() {
        return new RowMapper<Cliente>() {

            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Cliente(resultSet.getString("nome"), resultSet.getInt("id"));
            }

        };
    }

}
