package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.connection.DBConnection;
import com.pojo.Admin;

public class AdminDAO {

    public Admin loginAdmin(int adminId, String password) {

        Admin admin = null;

        String sql = "SELECT * FROM admin WHERE admin_id = ? AND password = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, adminId);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                admin = new Admin();

                admin.setAdminId(rs.getInt("admin_id"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPassword(rs.getString("password"));
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return admin;
    }
    
 // ==========================================
 // GET ADMIN BY EMAIL
 // ==========================================

 public Admin getAdminByEmail(String email) {

     Admin admin = null;

     String sql = "SELECT * FROM admin WHERE email = ?";

     try {

         Connection con = DBConnection.getConnection();

         PreparedStatement ps = con.prepareStatement(sql);

         ps.setString(1, email);

         ResultSet rs = ps.executeQuery();

         if (rs.next()) {

             admin = new Admin();

             admin.setAdminId(rs.getInt("admin_id"));
             admin.setName(rs.getString("name"));
             admin.setEmail(rs.getString("email"));
             admin.setPassword(rs.getString("password"));
         }

         rs.close();
         ps.close();
         con.close();

     } catch (Exception e) {

         e.printStackTrace();
     }

     return admin;
 }
 
//==========================================
//GET ADMIN BY ID
//==========================================

public Admin getAdminById(int adminId) {

  Admin admin = null;

  String sql = "SELECT * FROM admin WHERE admin_id = ?";

  try {

      Connection con = DBConnection.getConnection();

      PreparedStatement ps = con.prepareStatement(sql);

      ps.setInt(1, adminId);

      ResultSet rs = ps.executeQuery();

      if (rs.next()) {

          admin = new Admin();

          admin.setAdminId(rs.getInt("admin_id"));
          admin.setName(rs.getString("name"));
          admin.setEmail(rs.getString("email"));
          admin.setPassword(rs.getString("password"));
      }

      rs.close();
      ps.close();
      con.close();

  } catch (Exception e) {

      e.printStackTrace();
  }

  return admin;
}


//==========================================
//UPDATE ADMIN PASSWORD
//==========================================

public boolean updateAdminPassword(int adminId, String password) {

  boolean updated = false;

  String sql =
          "UPDATE admin SET password = ? WHERE admin_id = ?";

  try {

      Connection con = DBConnection.getConnection();

      PreparedStatement ps =
              con.prepareStatement(sql);

      ps.setString(1, password);
      ps.setInt(2, adminId);

      int rows = ps.executeUpdate();

      if (rows > 0) {

          updated = true;
      }

      ps.close();
      con.close();

  } catch (Exception e) {

      e.printStackTrace();
  }

  return updated;
}
}