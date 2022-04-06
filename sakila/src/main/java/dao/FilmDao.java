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

public class FilmDao {
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
		FilmDao fd = new FilmDao();
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
	public List<Double> selectFilmPriceList() {
		List<Double> list = new ArrayList<Double>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql ="SELECT DISTINCT price FROM film_list ORDER BY price";
		try {
			 stmt = conn.prepareStatement(sql);
			 rs =stmt.executeQuery();
			 while(rs.next()) {
				 list.add(rs.getDouble("price"));
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
	//검색 구현
	public List<FilmList> selectFilmSearchList(int beginRow, int rowPerPage,String category, String rating, double price, int length, String title, String actor) {
		
		List<FilmList> list = new ArrayList<FilmList>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			//동적쿼리 
			String sql = "SELECT fid, title, description, category, price, length, rating, actors FROM film_list where title LIKE ? AND actors LIKE ?";
			if (category.equals("") && rating.equals("") && price==-1 && length ==-1) {
				sql +=" ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			}else if (category.equals("") && rating.equals("") && price==-1 && length !=-1) { // length만 입력
				if(length == 0) {
					sql += " AND length<60 ORDER BY fid LIMIT ?,?";
				} else if (length == 1){
					sql += " AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setInt(3, beginRow);
				stmt.setInt(4, rowPerPage);
			} else if (category.equals("") && rating.equals("") && price!=-1 && length ==-1) { //price
				sql += " AND price=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setDouble(3, price);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (category.equals("") && rating.equals("") && price != -1 && length !=-1) { // price값,length,title값,actor값 입력
				if(length ==0 ) {
				sql += " AND length<60 AND price=? ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
				sql += " AND length>=60 AND price=? ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setDouble(3, price);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (category.equals("") && !rating.equals("") && price==-1 && length ==-1) { //rating 값만 넣었을때
				sql += " AND rating=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3,rating);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (category.equals("") && !rating.equals("") && price==-1 && length!=-1){ //rating과 length값만 입력
				if (length ==0) {
				sql += " AND rating=? AND length<60 ORDER BY fid LIMIT ?,?";
				} else if(length == 1) {
				sql += " AND rating=? AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3,rating);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (category.equals("") && !rating.equals("") && price !=-1 && length !=-1) {
				if (length ==0) {
					sql +=" AND rating=? AND price=? AND length<60 ORDER BY fid LIMIT ?,?";
				} else if (length == 1) {
					sql += " AND rating=? AND price=? AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3,rating);
				stmt.setDouble(4, price);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else if (category.equals("") && !rating.equals("") && price !=-1 && length ==-1) {
				sql += " AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3,rating);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (!category.equals("") && rating.equals("") && price ==-1 && length ==-1) { //카테고리만 입력
				sql += " AND category=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (!category.equals("") && rating.equals("") && price ==-1 && length !=-1) { // category,length만 입력
				if(length == 0) {
					sql += " AND category=? AND length<60 ORDER BY fid LIMIT ?,?";
				} else if (length == 1) {
					sql += " AND category=? AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setInt(4, beginRow);
				stmt.setInt(5, rowPerPage);
			} else if (!category.equals("") && rating.equals("") && price !=-1 && length ==-1) { // category랑 price입력
				sql += " AND category=? AND price=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setDouble(4, price);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else if (!category.equals("") && rating.equals("") && price !=-1 && length !=-1) { //rating 뺴고 다입력
				if (length == 0) {
					sql += " AND category=? AND price=? AND length<60 ORDER BY fid LIMIT ?,?";
				} else if  (length ==1) {
					sql += " AND category=? AND price=? AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setDouble(4, price);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else if (!category.equals("") && !rating.equals("") && price ==-1 && length ==-1) { //category,rating 입력
				sql += " AND category=? AND rating=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setString(4, rating);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else if (!category.equals("") && !rating.equals("") && price ==-1 && length !=-1) { //price 뺴고 다입력
				if (length == 0) {
					sql += " AND category=? AND rating=? length<60 ORDER BY fid LIMIT ?,?";
				} else if (length == 1) {
					sql += " AND category=? AND rating=? length>=60 ORDER By fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setString(4, rating);
				stmt.setInt(5, beginRow);
				stmt.setInt(6, rowPerPage);
			} else if (!category.equals("") && !rating.equals("") && price !=-1 && length ==-1) { //length 뺴고 다입력
				sql += " AND category=? AND rating=? AND price=? ORDER BY fid LIMIT ?,?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setString(4, rating);
				stmt.setDouble(5, price);
				stmt.setInt(6, beginRow);
				stmt.setInt(7, rowPerPage);
			} else if (!category.equals("") && !rating.equals("") && price !=-1 && length !=-1) { //다입력했을떄
				if (length == 0) {
					sql += " AND category=? AND rating=? AND price=? AND length<60 ORDER BY fid LIMIT ?,?";
				} else if (length == 1) {
					sql += " AND category=? AND rating=? AND price=? AND length>=60 ORDER BY fid LIMIT ?,?";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setString(4, rating);
				stmt.setDouble(5, price);
				stmt.setInt(6, beginRow);
				stmt.setInt(7, rowPerPage);
			}
			rs =stmt.executeQuery();
			 while (rs.next()) {
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
		public int selectFilmSearchTotalRow (String category, String rating, double price, int length, String title, String actor) {
		int	totalCount = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		try {
			String sql ="SELECT count(*) cnt FROM film_list where title LIKE ? AND actors LIKE ?";
			if (category.equals("") && rating.equals("") && price==-1 && length ==-1) {
			 stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
			} else if (category.equals("") && rating.equals("") && price==-1 && length !=-1) {
				if (length == 0) {
					sql +=" AND length<60";
				} else if (length == 1) {
					sql +=" AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
			} else if (category.equals("") && rating.equals("") && price !=-1 && length ==-1) {
				sql += " AND price =?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setDouble(3, price);
			} else if (category.equals("") && rating.equals("") && price !=-1 && length !=-1) {
				if (length == 0) {
					sql += " AND price =? AND length<60";
				} else if (length == 1) {
					sql += " AND price=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setDouble(3, price);
			} else if (category.equals("") && !rating.equals("") && price ==-1 && length ==-1) {
				sql += " AND rating=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, rating);
			} else if (category.equals("") && !rating.equals("") && price ==-1 && length !=-1) {
				if (length == 0) {
					sql += " AND rating=? AND length<60";
				} else if (length == 1) {
					sql += " AND rating=? AND length<60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, rating);
			} else if (category.equals("") && !rating.equals("") && price !=-1 && length ==-1) {
				sql += " AND rating=? AND price=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, rating);
				stmt.setDouble(4, price);
			} else if (category.equals("") && !rating.equals("") && price !=-1 && length !=-1) {
				if (length == 0) {
					sql += " AND rating=? AND price=? AND length<60";
				} else if (length == 1) {
					sql += " AND rating=? AND price=? AND length>=60"; 
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, rating);
				stmt.setDouble(4, price);
			} else if (!category.equals("") && rating.equals("") && price ==-1 && length ==-1) {
				sql += " AND category=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
			} else if (!category.equals("") && rating.equals("") && price ==-1 && length !=-1) {
				if (length == 0) {
					sql += " AND category=? AND length<60";
				} else if (length == 1) {
					sql += " AND category=? AND length>=60";
				} 
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
			} else if (!category.equals("") && rating.equals("") && price !=-1 && length ==-1) {
				sql += " AND category=? AND price=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setDouble(4,price);
			} else if (!category.equals("") && rating.equals("") && price !=-1 && length !=-1) {
				if (length == 0) {
					sql += " AND category=? AND price=? AND length<60";
				} else if (length == 1) {
					sql += " AND category=? AND price=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setDouble(4,price);
			} else if (!category.equals("") && !rating.equals("") && price ==-1 && length ==-1) {
				sql += " AND category=? AND rating=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setString(4,rating);
			}  else if (!category.equals("") && !rating.equals("") && price ==-1 && length !=-1) {
				if (length ==0) {
					sql += " AND category=? AND rating=? AND length<60";
				} else if (length == 1) {
					sql += " AND category=? AND rating=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setString(4,rating);
			} else if (!category.equals("") && !rating.equals("") && price !=-1 && length ==-1) {
				sql += " AND category=? AND rating=? AND price=?";
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setString(4,rating);
				stmt.setDouble(5, price);
			} else if (!category.equals("") && !rating.equals("") && price !=-1 && length !=-1) {
				if (length == 0) {
					sql += " AND category=? AND rating=? AND price=? AND length<60";
				} else if (length == 1) {
					sql += " AND category=? AND rating=? AND price=? AND length>=60";
				}
				stmt =conn.prepareStatement(sql);
				stmt.setString(1, "%" +title+"%");
				stmt.setString(2,"%" + actor + "%");
				stmt.setString(3, category);
				stmt.setString(4,rating);
				stmt.setDouble(5, price);
			}
			
			rs =stmt.executeQuery();
			 if(rs.next()) {
				 totalCount = rs.getInt("cnt");
			 } 
		}catch (SQLException e) {
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
		return totalCount;
		}
	}

