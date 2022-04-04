package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.CustomerList;

public class CustomerListDao {
	public List<CustomerList> selectCustomerListByPage(int beginRow, int rowPerPage) {
		List<CustomerList> list = new ArrayList<CustomerList>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT id,name, address,'zip code', phone, city, country, notes, sid FROM customer_list ORDER BY id LIMIT ?,? ";
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
				c.setAddress(rs.getString("'zip code'"));
				c.setPhone(rs.getInt("phone"));
				c.setCity(rs.getString("city"));
				c.setCountry(rs.getString("country"));
				c.setNotes(rs.getString("notes"));
				c.setSid(rs.getInt("sid"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(1);
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(2);
			}
		}
		return list;
	}
}
