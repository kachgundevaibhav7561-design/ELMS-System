package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.connection.DBConnection;
import com.pojo.LeaveRequest;

public class LeaveRequestDAO {

	// ==========================================
	// GET ALL LEAVE REQUESTS
	// ==========================================

	public List<LeaveRequest> getAllLeaveRequests() {

		List<LeaveRequest> leaveRequests = new ArrayList<>();

		String sql = "SELECT l.leave_id, l.emp_id, e.name, " + "l.leave_type, l.from_date, l.to_date, "
				+ "l.reason, l.status " + "FROM leave_request l " + "JOIN employee e " + "ON l.emp_id = e.emp_id";
		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				LeaveRequest leave = new LeaveRequest();

				leave.setLeaveId(rs.getInt("leave_id"));
				leave.setEmpId(rs.getInt("emp_id"));
				leave.setEmployeeName(rs.getString("name"));
				leave.setLeaveType(rs.getString("leave_type"));
				leave.setFromDate(rs.getDate("from_date"));
				leave.setToDate(rs.getDate("to_date"));
				leave.setReason(rs.getString("reason"));
				leave.setStatus(rs.getString("status"));

				leaveRequests.add(leave);
			}

			System.out.println("Total Leave Requests Found: " + leaveRequests.size());

			rs.close();
			ps.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveRequests;
	}

	// ==========================================
	// UPDATE LEAVE STATUS
	// ==========================================

	public boolean updateLeaveStatus(int leaveId, String status) {

		boolean result = false;

		String sql = "UPDATE leave_request SET status=? WHERE leave_id=?";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, status);
			ps.setInt(2, leaveId);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				result = true;
			}

			ps.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;
	}

//==========================================
//GET LEAVE COUNT BY STATUS
//==========================================

	public int getLeaveCountByStatus(String status) {

		int count = 0;

		String sql = "SELECT COUNT(*) FROM leave_request WHERE status = ?";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, status);

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
	// GET LEAVE REQUESTS BY STATUS
	// ==========================================

	public List<LeaveRequest> getLeaveRequestsByStatus(String status) {

		List<LeaveRequest> leaveRequests = new ArrayList<>();

		String sql = "SELECT l.leave_id, l.emp_id, e.name, " + "l.leave_type, l.from_date, l.to_date, "
				+ "l.reason, l.status " + "FROM leave_request l " + "JOIN employee e ON l.emp_id = e.emp_id "
				+ "WHERE l.status = ?";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, status);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				LeaveRequest leave = new LeaveRequest();

				leave.setLeaveId(rs.getInt("leave_id"));
				leave.setEmpId(rs.getInt("emp_id"));
				leave.setEmployeeName(rs.getString("name"));
				leave.setLeaveType(rs.getString("leave_type"));
				leave.setFromDate(rs.getDate("from_date"));
				leave.setToDate(rs.getDate("to_date"));
				leave.setReason(rs.getString("reason"));
				leave.setStatus(rs.getString("status"));

				leaveRequests.add(leave);
			}

			rs.close();
			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return leaveRequests;
	}

	// ==========================================
	// GET LEAVE HISTORY BY EMPLOYEE ID
	// ==========================================

	public List<LeaveRequest> getLeaveHistoryByEmployeeId(int empId) {

		List<LeaveRequest> leaveRequests = new ArrayList<>();

		String sql = "SELECT * FROM leave_request WHERE emp_id = ?";

		try {

			Connection con = DBConnection.getConnection();

			PreparedStatement ps = con.prepareStatement(sql);

			ps.setInt(1, empId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				LeaveRequest leave = new LeaveRequest();

				leave.setLeaveId(rs.getInt("leave_id"));
				leave.setEmpId(rs.getInt("emp_id"));
				leave.setLeaveType(rs.getString("leave_type"));
				leave.setFromDate(rs.getDate("from_date"));
				leave.setToDate(rs.getDate("to_date"));
				leave.setReason(rs.getString("reason"));
				leave.setStatus(rs.getString("status"));

				leaveRequests.add(leave);
			}

			rs.close();
			ps.close();
			con.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return leaveRequests;
	}
	
	// ==========================================
	// APPLY LEAVE
	// ==========================================

	public boolean applyLeave(LeaveRequest leave) {

	    boolean result = false;

	    String sql = "INSERT INTO leave_request "
	               + "(emp_id, leave_type, from_date, to_date, reason, status) "
	               + "VALUES (?, ?, ?, ?, ?, ?)";

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, leave.getEmpId());
	        ps.setString(2, leave.getLeaveType());
	        ps.setDate(3, leave.getFromDate());
	        ps.setDate(4, leave.getToDate());
	        ps.setString(5, leave.getReason());
	        ps.setString(6, leave.getStatus());

	        int rows = ps.executeUpdate();

	        if (rows > 0) {

	            result = true;

	            System.out.println("LEAVE APPLIED SUCCESSFULLY");

	        }

	        ps.close();
	        con.close();

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return result;
	}	
	// ==========================================
	// GET TOTAL LEAVE COUNT BY EMPLOYEE
	// ==========================================

	public int getTotalLeaveCountByEmployee(int empId) {

	    int count = 0;

	    String sql = "SELECT COUNT(*) FROM leave_request WHERE emp_id = ?";

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, empId);

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
	// GET APPROVED LEAVE COUNT BY EMPLOYEE
	// ==========================================

	public int getApprovedLeaveCountByEmployee(int empId) {

	    int count = 0;

	    String sql = "SELECT COUNT(*) FROM leave_request "
	               + "WHERE emp_id = ? AND status = 'Approved'";

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, empId);

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
	// GET PENDING LEAVE COUNT BY EMPLOYEE
	// ==========================================

	public int getPendingLeaveCountByEmployee(int empId) {

	    int count = 0;

	    String sql = "SELECT COUNT(*) FROM leave_request "
	               + "WHERE emp_id = ? AND status = 'Pending'";

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, empId);

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
	// GET RECENT LEAVE REQUESTS
	// ==========================================

	public List<LeaveRequest> getRecentLeaveRequests() {

	    List<LeaveRequest> leaveRequests = new ArrayList<>();

	    String sql = "SELECT l.leave_id, l.emp_id, e.name, "
	            + "l.leave_type, l.from_date, l.to_date, "
	            + "l.reason, l.status "
	            + "FROM leave_request l "
	            + "JOIN employee e ON l.emp_id = e.emp_id "
	            + "ORDER BY l.leave_id DESC "
	            + "LIMIT 5";
	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(sql);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            LeaveRequest leave = new LeaveRequest();

	            leave.setLeaveId(rs.getInt("leave_id"));
	            leave.setEmpId(rs.getInt("emp_id"));
	            leave.setEmployeeName(rs.getString("name"));
	            leave.setLeaveType(rs.getString("leave_type"));
	            leave.setFromDate(rs.getDate("from_date"));
	            leave.setToDate(rs.getDate("to_date"));
	            leave.setReason(rs.getString("reason"));
	            leave.setStatus(rs.getString("status"));

	            leaveRequests.add(leave);
	        }

	        System.out.println(
	            "Recent Leave Requests Found: " + leaveRequests.size()
	        );

	        rs.close();
	        ps.close();
	        con.close();

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return leaveRequests;
	}
	
	// ==========================================
	// GET LEAVE REQUESTS WITH PAGINATION
	// ==========================================

	public List<LeaveRequest> getLeaveRequestsByPage(int page, int recordsPerPage) {

	    List<LeaveRequest> leaveRequests = new ArrayList<>();

	    int offset = (page - 1) * recordsPerPage;

	    String sql = "SELECT l.leave_id, l.emp_id, e.name, "
	               + "l.leave_type, l.from_date, l.to_date, "
	               + "l.reason, l.status "
	               + "FROM leave_request l "
	               + "JOIN employee e ON l.emp_id = e.emp_id "
	               + "ORDER BY l.leave_id DESC "
	               + "LIMIT ? OFFSET ?";

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setInt(1, recordsPerPage);
	        ps.setInt(2, offset);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            LeaveRequest leave = new LeaveRequest();

	            leave.setLeaveId(rs.getInt("leave_id"));
	            leave.setEmpId(rs.getInt("emp_id"));
	            leave.setEmployeeName(rs.getString("name"));
	            leave.setLeaveType(rs.getString("leave_type"));
	            leave.setFromDate(rs.getDate("from_date"));
	            leave.setToDate(rs.getDate("to_date"));
	            leave.setReason(rs.getString("reason"));
	            leave.setStatus(rs.getString("status"));

	            leaveRequests.add(leave);
	        }

	        System.out.println(
	            "Leave Requests Found For Page "
	            + page + ": "
	            + leaveRequests.size()
	        );

	        rs.close();
	        ps.close();
	        con.close();

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return leaveRequests;
	}


	// ==========================================
	// GET LEAVE REQUESTS BY STATUS WITH PAGINATION
	// ==========================================

	public List<LeaveRequest> getLeaveRequestsByStatusAndPage(
	        String status,
	        int page,
	        int recordsPerPage) {

	    List<LeaveRequest> leaveRequests = new ArrayList<>();

	    int offset = (page - 1) * recordsPerPage;

	    String sql = "SELECT l.leave_id, l.emp_id, e.name, "
	               + "l.leave_type, l.from_date, l.to_date, "
	               + "l.reason, l.status "
	               + "FROM leave_request l "
	               + "JOIN employee e ON l.emp_id = e.emp_id "
	               + "WHERE l.status = ? "
	               + "ORDER BY l.leave_id DESC "
	               + "LIMIT ? OFFSET ?";

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setString(1, status);
	        ps.setInt(2, recordsPerPage);
	        ps.setInt(3, offset);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            LeaveRequest leave = new LeaveRequest();

	            leave.setLeaveId(rs.getInt("leave_id"));
	            leave.setEmpId(rs.getInt("emp_id"));
	            leave.setEmployeeName(rs.getString("name"));
	            leave.setLeaveType(rs.getString("leave_type"));
	            leave.setFromDate(rs.getDate("from_date"));
	            leave.setToDate(rs.getDate("to_date"));
	            leave.setReason(rs.getString("reason"));
	            leave.setStatus(rs.getString("status"));

	            leaveRequests.add(leave);
	        }

	        System.out.println(
	            "Leave Requests Found For Status "
	            + status
	            + " Page "
	            + page
	            + ": "
	            + leaveRequests.size()
	        );

	        rs.close();
	        ps.close();
	        con.close();

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    return leaveRequests;
	}


	// ==========================================
	// GET TOTAL LEAVE REQUEST PAGES
	// ==========================================

	public int getTotalLeaveRequestPages(int recordsPerPage) {

	    int totalRequests = 0;

	    String sql = "SELECT COUNT(*) FROM leave_request";

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(sql);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            totalRequests = rs.getInt(1);
	        }

	        rs.close();
	        ps.close();
	        con.close();

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    int totalPages =
	            (int) Math.ceil(
	                    (double) totalRequests / recordsPerPage
	            );

	    return totalPages;
	}


	// ==========================================
	// GET TOTAL LEAVE REQUEST PAGES BY STATUS
	// ==========================================

	public int getTotalLeaveRequestPagesByStatus(
	        String status,
	        int recordsPerPage) {

	    int totalRequests = 0;

	    String sql =
	            "SELECT COUNT(*) "
	          + "FROM leave_request "
	          + "WHERE status = ?";

	    try {

	        Connection con = DBConnection.getConnection();

	        PreparedStatement ps = con.prepareStatement(sql);

	        ps.setString(1, status);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {

	            totalRequests = rs.getInt(1);
	        }

	        rs.close();
	        ps.close();
	        con.close();

	    } catch (Exception e) {

	        e.printStackTrace();
	    }

	    int totalPages =
	            (int) Math.ceil(
	                    (double) totalRequests / recordsPerPage
	            );

	    return totalPages;
	}

}