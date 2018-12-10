/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chien.model;

import com.chien.entity.Phone;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chien
 */
public class DB {
    Connection cnn;
    private Connection getConnect(){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            cnn = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/PhoneDB", "sa", "sa"
            );
            return cnn;
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int addPhone(Phone p){
        PreparedStatement ps;
        String sql = "INSERT INTO PhoneDB (id, name, brand, price, description) VALUES(?,?,?,?,?)";
        try {

            ps = getConnect().prepareStatement(sql);
            ps.setLong(1, System.currentTimeMillis());
            ps.setString(2, p.getName());
            ps.setString(3, p.getBrand());
            ps.setInt(4, p.getPrice());
            ps.setString(5, p.getDesc());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public List<Phone> getAll() {
        PreparedStatement ps;
        List<Phone> list = new LinkedList();
        try {
            String sql = "SELECT * FROM PhoneDB";
            ps = getConnect().prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Phone p = new Phone();
                p.setId(rs.getLong("id"));
                p.setName(rs.getString("name"));
                p.setBrand(rs.getString("brand"));
                p.setPrice(rs.getInt("price"));
                p.setDesc(rs.getString("description"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return list;
    }
    
}
