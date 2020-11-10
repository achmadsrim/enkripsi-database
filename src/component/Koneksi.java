/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skripa3l;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KAMPRTO
 */
public class Koneksi {
     private Connection con;
    
    public Koneksi(){
            String driver   = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/db_a3l"; 
            String user = "root"; 
            String pass = ""; 
 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pass);
           //System.out.println("Koneksi Berhasil");
        } catch (SQLException e) {
            System.out.println("SQLException: "+e.getMessage());
            System.out.println("SQLState: "+e.getSQLState());
            System.out.println("VendorError: "+e.getErrorCode());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
}

        public Connection getConnection(){
        return con;
           }
        
           public static void main(String[] args) {
        Koneksi a = new Koneksi();
        a.getConnection();
    }

    Connection bukaKoneksi1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Connection bukaKoneksi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}