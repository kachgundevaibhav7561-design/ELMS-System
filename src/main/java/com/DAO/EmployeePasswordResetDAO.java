package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.connection.DBConnection;

public class EmployeePasswordResetDAO {

    // ==========================================
    // SAVE RESET TOKEN
    // ==========================================

    public boolean saveResetToken(int empId, String token,
                                  java.sql.Timestamp expiryTime) {

        String sql = "INSERT INTO employee_password_reset "
                   + "(emp_id, reset_token, expiry_time, used) "
                   + "VALUES (?, ?, ?, FALSE)";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, empId);
            ps.setString(2, token);
            ps.setTimestamp(3, expiryTime);

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // ==========================================
    // GET EMPLOYEE ID BY VALID TOKEN
    // ==========================================

    public int getEmployeeIdByValidToken(String token) {

        int empId = -1;

        String sql = "SELECT emp_id FROM employee_password_reset "
                   + "WHERE reset_token = ? "
                   + "AND expiry_time > NOW() "
                   + "AND used = FALSE";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, token);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                empId = rs.getInt("emp_id");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return empId;
    }


    // ==========================================
    // MARK TOKEN AS USED
    // ==========================================

    public boolean markTokenAsUsed(String token) {

        String sql = "UPDATE employee_password_reset "
                   + "SET used = TRUE "
                   + "WHERE reset_token = ?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, token);

            int result = ps.executeUpdate();

            ps.close();
            con.close();

            return result > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
}