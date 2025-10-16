package com.example.dao;

import com.example.model.Customer;
import com.example.util.DbConnection;

import java.sql.*;
import java.util.HashMap;

public class CustomerDAO {
    HashMap<Integer,Customer> customerMap=new HashMap<>();
    // Load all customers from DB into HashMap
    public void loadAll()throws SQLException  {
        try(Connection con= DbConnection.makecon()){
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery("select * from customer");

         while(rs.next()){
             Customer c=new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getString("phone"));
             customerMap.put(c.getId(),c);
         }
        }
    }
    //CREATE
    public void add(Customer c) throws SQLException {
        try (Connection con = DbConnection.makecon()) {
            PreparedStatement ps=con.prepareStatement("insert into customer values(?,?,?,?)");
            ps.setInt(1,c.getId());
            ps.setString(2,c.getName());
            ps.setString(3,c.getEmail());
            ps.setString(4,c.getPhone());
            ps.executeUpdate();
            customerMap.put(c.getId(),c);
            IO.print("Customer added successfully");
        }
    }
    //READ
    public void viewAll(){
        if(customerMap.isEmpty()){
            IO.print("No customer found");
            return;
        }
        customerMap.values().forEach(IO::println);
    }
    //UPDATE
    public void update(Customer c) throws SQLException {
        try (Connection con = DbConnection.makecon()) {
            PreparedStatement ps=con.prepareStatement("update customer set name=?,email=?,phone=? where id=?");
            ps.setString(1,c.getName());
            ps.setString(2,c.getEmail());
            ps.setString(3,c.getPhone());
            ps.setInt(4,c.getId());
            int rows=ps.executeUpdate();
            if(rows>0){
                customerMap.put(c.getId(),c);
                IO.print("Customer updated successfully");
            }
            else{
                IO.print("No customer found");
            }
        }
    }
    //DELETE
    public void delete(int id) throws SQLException {
        try (Connection con = DbConnection.makecon()) {
            PreparedStatement ps=con.prepareStatement("delete from customer where id=?");
            ps.setInt(1,id);
            int rows=ps.executeUpdate();
            if(rows>0){
                customerMap.remove(id);
                IO.print("Customer deleted successfully");
            }
            else{
                IO.print("No customer found");
            }
        }
    }
    // GET customer by ID
    public Customer findById(int id) throws SQLException {
        return customerMap.get(id);
    }
}
