package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.Category;

public class CategoryDao {
	public List<Category> selectCategoryList () {
		List<Category> list = new ArrayList<Category>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT category_id categoryId,name,last_update lastUpdate FROM category";
		try {		
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Category c = new Category();
				c.setCategoryId(rs.getInt("categoryId"));
				c.setName(rs.getString("name"));
				c.setLastUpdate(rs.getString("lastUpdate"));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
}
