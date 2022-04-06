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
	public Map<String, Object> filmInStockCall(int filmId, int storeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		// PreparedStatement : 쿼리를 실행
		// CallableStatement : 프로시저를 실행 
		CallableStatement stmt = null;
		ResultSet rs = null;
		// select inventory_id .... 
		List<Integer> list = new ArrayList<>();
		// select count(inventroy_id) ....
		Integer count = 0;
		conn = DBUtil.getConnection();
		try {
			stmt = conn.prepareCall("{call film_in_stock(?, ?, ?)}");
			stmt.setInt(1, filmId);
			stmt.setInt(2, storeId);
			stmt.registerOutParameter(3, Types.INTEGER);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1)); // rs.getInt("inventory_id")
			}
			count = stmt.getInt(3); // 프로시저 3번째 out변수 값
		} catch (SQLException e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	public static void main(String[] args) {
		FilmListDao fd = new FilmListDao();
		int filmId = 1;
		int storeId = 1;
		Map<String, Object> map = fd.filmInStockCall(filmId, storeId);
		List<Integer> list = (List<Integer>)map.get("list");
		int count = (Integer)map.get("count"); 
		
		System.out.println(filmId +"번 영화가" +storeId+"번 가게에" +count+"개 남음");
		for(int id : list) {
			System.out.println(id);
		} 
		
		}
	public Map<String, Object> filmNotInStockCall(int filmId, int storeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		List<Integer> list = new ArrayList<>();
		Integer count = 0;
		conn = DBUtil.getConnection();
		try {
			stmt = conn.prepareCall("{call film_not_in_stock(?, ?, ?)}");
			stmt.setInt(1, filmId);
			stmt.setInt(2, storeId);
			stmt.registerOutParameter(3, Types.INTEGER);
			rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt(1)); // rs.getInt("inventory_id")
			}
			count = stmt.getInt(3);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		map.put("list", list);
		map.put("count", count);
		return map;
		}
	}

