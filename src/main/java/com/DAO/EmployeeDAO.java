package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.connection.DBConnection;
import com.pojo.Employee;

public class EmployeeDAO {

	// ==========================================
	// GET ALL EMPLOYEES
	// ==========================================

	public List<Employee> getAllEmployees() {

		List<Employee> employees = new ArrayList<>();

		String sql = "SELECT * FROM employee";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Employee employee = new Employee();

				employee.setEmpId(rs.getInt("emp_id"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("password"));
				employee.setDepartment(rs.getString("department"));

				employees.add(employee);
			}

			System.out.println("Total Employees Found: " + employees.size());

			rs.close();
			ps.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return employees;
	}

	// ==========================================
	// ADD EMPLOYEE
	// ==========================================

	public boolean addEmployee(Employee employee) {

		String sql = "INSERT INTO employee " + "(emp_id, name, email, password, department) "
				+ "VALUES (?, ?, ?, ?, ?)";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, employee.getEmpId());
			ps.setString(2, employee.getName());
			ps.setString(3, employee.getEmail());
			ps.setString(4, employee.getPassword());
			ps.setString(5, employee.getDepartment());

			int result = ps.executeUpdate();

			ps.close();
			con.close();

			if (result > 0) {

				System.out.println("EMPLOYEE ADDED SUCCESSFULLY");

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	// ==========================================
	// GET EMPLOYEE BY ID
	// ==========================================

	public Employee getEmployeeById(int empId) {

		Employee employee = null;

		String sql = "SELECT * FROM employee WHERE emp_id = ?";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, empId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				employee = new Employee();

				employee.setEmpId(rs.getInt("emp_id"));

				employee.setName(rs.getString("name"));

				employee.setEmail(rs.getString("email"));

				employee.setPassword(rs.getString("password"));

				employee.setDepartment(rs.getString("department"));
			}

			rs.close();
			ps.close();
			con.close();

			if (employee != null) {

				System.out.println("Employee Found: " + employee.getName());

			} else {

				System.out.println("Employee Not Found: " + empId);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return employee;
	}
	
	// ==========================================
	// GET EMPLOYEE BY EMAIL
	// ==========================================

	public Employee getEmployeeByEmail(String email) {

	    Employee employee = null;

	    String sql = "SELECT * FROM employee WHERE email = ?";

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setString(1, email);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            employee = new Employee();

	            employee.setEmpId(rs.getInt("emp_id"));
	            employee.setName(rs.getString("name"));
	            employee.setEmail(rs.getString("email"));
	            employee.setPassword(rs.getString("password"));
	            employee.setDepartment(rs.getString("department"));
	        }

	        rs.close();
	        ps.close();
	        con.close();

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return employee;
	}

	// ==========================================
	// UPDATE EMPLOYEE
	// ==========================================

	public boolean updateEmployee(Employee employee) {

		String sql = "UPDATE employee SET " + "name = ?, " + "email = ?, " + "password = ?, " + "department = ? "
				+ "WHERE emp_id = ?";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, employee.getName());
			ps.setString(2, employee.getEmail());
			ps.setString(3, employee.getPassword());
			ps.setString(4, employee.getDepartment());
			ps.setInt(5, employee.getEmpId());

			int result = ps.executeUpdate();

			ps.close();
			con.close();

			if (result > 0) {

				System.out.println("EMPLOYEE UPDATED SUCCESSFULLY");

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

//==========================================
//DELETE EMPLOYEE
//==========================================

	public boolean deleteEmployee(int empId) {

		String sql = "DELETE FROM employee WHERE emp_id = ?";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, empId);

			int result = ps.executeUpdate();

			ps.close();
			con.close();

			if (result > 0) {

				System.out.println("EMPLOYEE DELETED SUCCESSFULLY");

				return true;
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

//==========================================
//GET TOTAL EMPLOYEES
//==========================================

	public int getTotalEmployees() {

		int count = 0;

		String sql = "SELECT COUNT(*) FROM employee";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				count = rs.getInt(1);
			}

			rs.close();
			ps.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return count;
	}
	
	// ==========================================
	// GET EMPLOYEES WITH PAGINATION
	// ==========================================

	public List<Employee> getEmployeesByPage(int page, int recordsPerPage) {

		List<Employee> employees = new ArrayList<>();

		int offset = (page - 1) * recordsPerPage;

		String sql = "SELECT * FROM employee "
				   + "ORDER BY emp_id "
				   + "LIMIT ? OFFSET ?";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, recordsPerPage);
			ps.setInt(2, offset);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Employee employee = new Employee();

				employee.setEmpId(rs.getInt("emp_id"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("password"));
				employee.setDepartment(rs.getString("department"));

				employees.add(employee);
			}

			System.out.println(
				"Employees Found For Page " + page + ": "
				+ employees.size()
			);

			rs.close();
			ps.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return employees;
	}


	// ==========================================
	// GET TOTAL NUMBER OF PAGES
	// ==========================================

	public int getTotalEmployeePages(int recordsPerPage) {

		int totalEmployees = getTotalEmployees();

		int totalPages =
				(int) Math.ceil(
					(double) totalEmployees / recordsPerPage
				);

		return totalPages;
	}	
}
