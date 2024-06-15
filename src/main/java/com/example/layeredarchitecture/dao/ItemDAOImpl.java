package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO{
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");

        ArrayList<ItemDTO> allItem = new ArrayList<>();
        while (rst.next()){
            ItemDTO itemDTO = new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")
            );
            allItem.add(itemDTO);
        }

        return  allItem;
    }
    @Override
    public void SaveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code,description,UnitPrice,qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1,itemDTO.getCode());
        pstm.setString(2, itemDTO.getDescription());
        pstm.setBigDecimal(3, itemDTO.getUnitPrice());
        pstm.setInt(4, itemDTO.getQtyOnHand());
        pstm.executeUpdate();

    }
    @Override
    public void UpdateItem (ItemDTO itemDTO) throws SQLException,ClassNotFoundException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1, itemDTO.getCode());
        pstm.setString(2, itemDTO.getDescription());
        pstm.setBigDecimal(3, itemDTO.getUnitPrice());
        pstm.setInt(4, itemDTO.getQtyOnHand());
        pstm.executeUpdate();
    }
    @Override
    public void DeleteItem (String code) throws SQLException,ClassNotFoundException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
        pstm.executeUpdate();
    }
    public boolean exitItem (String code) throws SQLException,ClassNotFoundException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return false;

    }
    @Override
    public ItemDTO findItem (String code) throws SQLException ,ClassNotFoundException{
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, code);
        ResultSet rst = pstm.executeQuery();
        rst.next();
        return new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
    }
    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            return rst.getString(1);

        }return null;
    }
    @Override
    public ItemDTO getAllItems(String newItemCode) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, newItemCode + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();
        ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        return item;
    }
    @Override
    public ArrayList<ItemDTO> getAllItemIds() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");

        ArrayList<ItemDTO> allItems = new ArrayList<>();
        while (rst.next()){
            ItemDTO itemDTO = new ItemDTO(
                    rst.getString("code"),
                    rst.getString("description"),
                    rst.getBigDecimal("unitPrice"),
                    rst.getInt("qtyOnHand")
            );
            allItems.add(itemDTO);
        }
        return allItems;
    }


}
