package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderDAO {
    public String generateNewOrderId() throws SQLException, ClassNotFoundException;

    public boolean orderIdExists(String orderId) throws SQLException, ClassNotFoundException;


    public int save(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException;
}
