package dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;
import vo.CustomerList;

public class CustomerListDao {
	public List<CustomerList> selectCustomerListByPage(int beginRow, int rowPerPage) {
		List<CustomerList> list = new ArrayList<CustomerList>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT id, name,address, `zip code` zipCode, phone, city, country,notes, sid FROM customer_list ORDER BY id LIMIT ?,? ";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,beginRow);
			stmt.setInt(2,rowPerPage);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				CustomerList c = new CustomerList();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setAddress(rs.getString("address"));
				c.setZipCode(rs.getInt("zipCode"));
				c.setPhone(rs.getLong("phone"));
				c.setCity(rs.getString("city"));
				c.setCountry(rs.getString("country"));
				c.setNotes(rs.getString("notes"));
				c.setSid(rs.getInt("sid"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		}
		return list;
	}
	public int selectCustomerListTotalRow() {
		int totalCount = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		conn = DBUtil.getConnection();
		String sql = "SELECT count(*) cnt FROM customer_list";
		try {
			 stmt = conn.prepareStatement(sql);
			 rs =stmt.executeQuery();
			 if(rs.next()) {
				 totalCount = rs.getInt("cnt");
			 } 
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		return totalCount;
	}
	public Map<String, Object> CustomerRewardsReportCall(int minMonthlyPurchases, double minDollarAmountPurchased) {
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		// PreparedStatement : 쿼리를 실행
		// CallableStatement : 프로시저를 실행 
		CallableStatement stmt = null;
		ResultSet rs = null;
		// select inventory_id .... 
		// select count(inventroy_id) ....
		Integer count = 0;
		conn = DBUtil.getConnection();
		try {
			stmt = conn.prepareCall("{call rewards_report(?, ?, ?)}");
			stmt.setInt(1, minMonthlyPurchases);
			stmt.setDouble(2, minDollarAmountPurchased);
			stmt.registerOutParameter(3, Types.INTEGER);
			ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();
			HashMap<String,Object> c = null;
			rs = stmt.executeQuery();
			while(rs.next()) {
				c = new HashMap<String,Object>();
				c.put("customerId",rs.getInt(1));
				c.put("storeId",rs.getInt(2));
				c.put("firstName",rs.getString(3));
				c.put("lastName",rs.getString(4));
				c.put("email",rs.getString(5));
				c.put("addressId",rs.getInt(6));
				c.put("active",rs.getInt(7));
				c.put("createDate",rs.getString(8));
				c.put("updateDate",rs.getString(9));
				list.add(c);
			}
			count = stmt.getInt(3); // 프로시저 3번째 out변수 값
		} catch (SQLException e) {
			e.printStackTrace();
		}
		map.put("count", count);
		return map;
	}
}
