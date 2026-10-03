package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.connection.DBConnection;


public class LoginDao {


	public boolean checkLogin(int empId, String password) {


		boolean status = false;


		try {


			Connection con = DBConnection.getConnection();


			String query = 
			"select * from employee where emp_id=? and password=?";


			PreparedStatement ps = 
			con.prepareStatement(query);


			ps.setInt(1, empId);

			ps.setString(2, password);



			ResultSet rs = ps.executeQuery();



			if(rs.next()) {

				status = true;

			}


		}
		catch(Exception e) {

			e.printStackTrace();

		}


		return status;


	}


}