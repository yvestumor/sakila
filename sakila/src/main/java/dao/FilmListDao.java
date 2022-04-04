package dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import vo.FilmList;

public class FilmListDao {
	public List<FilmList> selctFilmListByPage(int beginRow, int rowPerPage) {
		List<FilmList> list = new ArrayList<FilmList>();
		Connection conn = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT fid, title, description, category, price, length, rating, actors FROM film_list ORDER BY fid LIMIT ?,?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			 rs = stmt.executeQuery();
			 
			 while(rs.next()) {
				 FilmList f = new FilmList();
				 f.setFid(rs.getInt("fid"));
				 f.setTitle(rs.getString("title"));
				 f.setDescription(rs.getString("description"));
				 f.setCategory(rs.getString("category"));
				 f.setPrice(rs.getDouble("price"));
				 f.setLength(rs.getInt("length"));
				 f.setRating(rs.getString("rating"));
				 f.setActors(rs.getString("actors"));
				 list.add(f);
			 }
			}catch (SQLException e) {
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
		String sql ="select count(*) cnt FROM film_list";
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
