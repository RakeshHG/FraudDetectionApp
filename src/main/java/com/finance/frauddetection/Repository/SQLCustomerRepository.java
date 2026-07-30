package com.finance.frauddetection.Repository;

import com.finance.frauddetection.Model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class SQLCustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Customer> rowMapper = (ResultSet rs, int rowNum) -> new Customer(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("account_number"),
            rs.getString("registered_country")
    );

    public SQLCustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> getCustomers() {
        return jdbcTemplate.query("SELECT * FROM customers", rowMapper);
    }

    public Customer getCustomerById(int id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        List<Customer> customers = jdbcTemplate.query(sql, rowMapper, id);
        return customers.isEmpty() ? null : customers.get(0);
    }

    public Customer save(Customer customer) {
        String sql = "INSERT INTO customers (name, account_number, registered_country) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAccountNumber());
            ps.setString(3, customer.getRegisteredCountry());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            customer.setId(keyHolder.getKey().intValue());
        }
        return customer;
    }

    public boolean update(int id, Customer updatedCustomer) {
        String sql = "UPDATE customers SET name = ?, account_number = ?, registered_country = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                updatedCustomer.getName(),
                updatedCustomer.getAccountNumber(),
                updatedCustomer.getRegisteredCountry(),
                id);
        return rowsAffected > 0;
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }
}