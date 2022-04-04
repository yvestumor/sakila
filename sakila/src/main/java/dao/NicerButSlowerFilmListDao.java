package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import util.DBUtil;
import vo.NicerButSlowerFilmList;

public class NicerButSlowerFilmListDao {
	public List<NicerButSlowerFilmList> selectNicerButSlowerFilmListByPage (int beginRow, int rowPerPage) {
		List<NicerButSlowerFilmList> list = new ArrayList<NicerButSlowerFilmList>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT fid, title, description, category, price, length, rating, actors FROM nicer_but_slower_film_list ORDER BY fid LIMIT ?,?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			 rs = stmt.executeQuery();
			 while(rs.next()) {
			NicerButSlowerFilmList n = new NicerButSlowerFilmList();
			 n.setFid(rs.getInt("fid"));
			 n.setTitle(rs.getString("title"));
			 n.setDescription(rs.getString("description"));
			 n.setCategory(rs.getString("category"));
			 n.setPrice(rs.getDouble("price"));
			 n.setLength(rs.getInt("length"));
			 n.setRating(rs.getString("rating"));
			 n.setActors(rs.getString("actors"));
			 list.add(n);
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
	public int selectFilmListTotalRow() {
		int totalCount = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
			
		conn = DBUtil.getConnection();
		String sql ="select count(*) cnt FROM nicer_but_slower_film_list";
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
}
