package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DBUtil;

public class StatsDataDao {
	public List<Map<String, Object>> amountByCoustomer() {
	      List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      conn = DBUtil.getConnection();
	      String sql = "SELECT p.customer_id customerId,"
	            + "      concat(c.first_name,' ', c.last_name) name,"
	            + "      sum(p.amount) total"
	            + "      FROM payment p INNER JOIN customer c"
	            + "      ON p.customer_id = c.customer_id"
	            + "      GROUP BY p.customer_id"
	            + "	     HAVING SUM(p.amount) >= 180"
	            + "      ORDER BY SUM(p.amount) DESC";
	      try {
	         stmt = conn.prepareStatement(sql);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            Map<String, Object> m = new HashMap<>();
	            m.put("customerId",rs.getInt("customerId"));
	            m.put("name",rs.getString("name"));
	            m.put("total",rs.getInt("total"));
	            list.add(m);
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
	public List<Map<String, Object>> filmCountByRentalRate() {
		 List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		 
		  Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      
	      conn = DBUtil.getConnection();
	      String sql = "SELECT rental_rate rentalRate, COUNT(*) cnt"
	      		+ " FROM film"
	      		+ " GROUP BY rental_rate"
	      		+ " ORDER BY COUNT(*) DESC";
	      try {
	    	  stmt = conn.prepareStatement(sql);
		       rs = stmt.executeQuery();
		       while(rs.next()) {
		    	   Map<String , Object> m = new HashMap<>();
		    	   m.put("rentalRate", rs.getDouble("rentalRate"));
		    	   m.put("cnt", rs.getInt("cnt"));
		    	   list.add(m);
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
	public List<Map<String, Object>> filmCountByRating() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		  Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      conn = DBUtil.getConnection();
	      String sql = "SELECT rating, COUNT(*) cnt"
	      		+ " FROM film"
	      		+ " GROUP BY rating"
	      		+ " ORDER BY COUNT(*) DESC";
	      try {
	    	  stmt = conn.prepareStatement(sql);
		       rs = stmt.executeQuery();
		       while(rs.next()) {
		    	   Map<String , Object> m = new HashMap<>();
		    	   m.put("rating",rs.getString("rating"));
		    	   m.put("cnt", rs.getInt("cnt"));
		    	   list.add(m);
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
	public List<Map<String, Object>> languageFilmCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		  Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      conn = DBUtil.getConnection();
	      String sql ="SELECT l.name name, COUNT(*) cnt"
	      		+ " FROM film f INNER JOIN language l"
	      		+ " ON f.language_id =l.language_id"
	      		+ " GROUP BY l.name";
	      try {
	    	  stmt = conn.prepareStatement(sql);
		       rs = stmt.executeQuery();
		       while(rs.next()) {
		    	   Map<String , Object> m = new HashMap<>();
		    	   m.put("name",rs.getString("name"));
		    	   m.put("cnt", rs.getInt("cnt"));
		    	   list.add(m);
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
	      return list;
	}
	public List<Map<String, Object>> lengthFilmCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		  Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      conn = DBUtil.getConnection();
	    String sql ="SELECT t.length2 runningTime, COUNT(*) cnt"
	    		+ "  FROM (SELECT title, LENGTH,"
	    		+ "		CASE WHEN LENGTH<=60 THEN 'less60'"
	    		+ "			  WHEN LENGTH BETWEEN 61 AND 120 THEN 'between61and120'"
	    		+ "			  WHEN LENGTH BETWEEN 121 AND 180 THEN 'between121and180'"
	    		+ "			  ELSE 'more180'"
	    		+ "			END LENGTH2"
	    		+ "		FROM film) t"
	    		+ " GROUP BY t.length2";
	    try {
	    	stmt = conn.prepareStatement(sql);
	    	rs = stmt.executeQuery();
	    	while (rs.next()) {
	    		 Map<String , Object> m = new HashMap<>();
	    		m.put("runningTime",rs.getString("runningTime"));
	    		m.put("cnt", rs.getInt("cnt"));
	    		list.add(m);
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
	public List<Map<String, Object>> storeInventoryCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		 Connection conn = null;
	     PreparedStatement stmt = null;
	     ResultSet rs = null;
	     conn = DBUtil.getConnection();
	     String sql = "SELECT s.store_id storeId, COUNT(*) cnt"
	     		+ " FROM store s INNER JOIN inventory i"
	     		+ " ON s.store_id = i.store_id"
	     		+ " GROUP BY s.store_id;";
	     try {
		    	stmt = conn.prepareStatement(sql);
		    	rs = stmt.executeQuery();
		    	while (rs.next()) {
		    	 Map<String , Object> m = new HashMap<>();
		    	 m.put("storeId",rs.getInt("storeId"));
		    	 m.put("cnt",rs.getInt("cnt"));
		    	 list.add(m);
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
	public List<Map<String , Object>> customerRentalCount () {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    conn = DBUtil.getConnection();
	    String sql = "SELECT CONCAT (c.first_name,' ',c.last_name) NAME,"
	    		+ " COUNT(*) rentalCount"
	    		+ " FROM customer c"
	    		+ " INNER JOIN rental r ON c.customer_id = r.customer_id"
	    		+ " GROUP BY NAME"
	    		+ " ORDER BY rentalCount desc LIMIT 1,10";
	    try {
	    	stmt = conn.prepareStatement(sql);
	    	rs = stmt.executeQuery();
	    	while (rs.next()) {
	    		Map<String , Object> m = new HashMap<>();
	    		m.put("NAME",rs.getString("NAME"));
	    		m.put("rentalCount",rs.getInt("rentalCount"));
	    		list.add(m);
	    	}
	    } catch (SQLException e){
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
	public List<Map<String , Object>> actorFilmCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    conn = DBUtil.getConnection();
	    String sql = "SELECT CONCAT(a.first_name,' ',a.last_name) NAME,"
	    		+ " COUNT(*) cnt"
	    		+ " FROM actor a"
	    		+ " INNER JOIN film_actor fc ON a.actor_id = fc.actor_id"
	    		+ " GROUP BY NAME"
	    		+ " ORDER BY cnt desc LIMIT 1,10" ;
	    try {
	    	stmt = conn.prepareStatement(sql);
	    	rs = stmt.executeQuery();
	    	while (rs.next()) {
	    		Map <String, Object> m = new HashMap<>();
	    		m.put("NAME", rs.getString("NAME"));
	    		m.put("cnt",rs.getInt("cnt"));
	    		list.add(m);
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
	public List<Map<String ,Object>> customerLikeActor() {
		List<Map<String ,Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = "SELECT CONCAT(a.first_name,' ',a.last_name) NAME,"
				+ " COUNT(*) cnt"
				+ " FROM actor a"
				+ " INNER JOIN film_actor fc ON a.actor_id = fc.actor_id"
				+ " INNER JOIN inventory i ON fc.film_id = i.film_id"
				+ " INNER JOIN rental r ON r.inventory_id = i.inventory_id"
				+ " GROUP BY NAME"
				+ " ORDER BY COUNT(*) DESC LIMIT 1,10";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Map <String, Object> m = new HashMap<>();
				m.put("NAME", rs.getString("NAME"));
				m.put("cnt", rs.getInt("cnt"));
				list.add(m);
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
	public List<Map<String, Object>> actorIncome() { //배우별 총 수입
		List<Map<String ,Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql ="SELECT CONCAT(a.first_name,' ',a.last_name) NAME,"
				+ " SUM(f.rental_rate) inCome"
				+ " FROM actor a"
				+ " INNER JOIN film_actor fa ON fa.actor_id = a.actor_id"
				+ " INNER JOIN film f ON fa.film_id = f.film_id"
				+ " GROUP BY NAME"
				+ " ORDER BY inCome DESC LIMIT 1,10";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map <String, Object> m = new HashMap<>();
				m.put("NAME", rs.getString("NAME"));
				m.put("inCome", rs.getDouble("inCome"));
				list.add(m);
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
	public List<Map<String,Object>> storeUseCustomerCount() {
		List <Map<String , Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql ="SELECT s.store_id storeId, COUNT(*) cnt"
				+ "	FROM store s"
				+ "	INNER JOIN customer c ON s.store_id = c.store_id"
				+ "	GROUP BY storeId";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map <String, Object> m = new HashMap<>();
				m.put("storeId", rs.getInt("storeId"));
				m.put("cnt", rs.getInt("cnt"));
				list.add(m);
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
	public List<Map<String,Object>> countryCityCount() {
		List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql="SELECT c.country country, COUNT(*) cityCount"
				+ " FROM country c"
				+ " INNER JOIN city ct ON c.country_id = ct.country_id"
				+ " GROUP BY country";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Map <String, Object> m = new HashMap<>();
				m.put("country", rs.getString("country"));
				m.put("cityCount", rs.getInt("cityCount"));
				list.add(m);
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
	public List<Map<String ,Object>> customerNc17FilmCount (){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql = " SELECT CONCAT(c.first_name,' ',c.last_name) NAME,"
				+ " COUNT(*) cnt"
				+ " FROM customer c"
				+ " INNER JOIN rental r ON c.customer_id = r.customer_id"
				+ " INNER JOIN inventory i ON r.inventory_id = i.inventory_id"
				+ " INNER JOIN film f  ON i.film_id = f.film_id"
				+ " WHERE rating ='NC-17'"
				+ " GROUP BY NAME"
				+ " ORDER BY COUNT(*) DESC LIMIT 1,10";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Map <String, Object> m = new HashMap<>();
				m.put("NAME",rs.getString("NAME"));
				m.put("cnt", rs.getInt("cnt"));
				list.add(m);
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
	public List<Map<String,Object>> customerRentalCategoryHorrorCount() {
		List<Map<String, Object>> list = new ArrayList<Map<String ,Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		conn = DBUtil.getConnection();
		String sql ="SELECT CONCAT(c.first_name,' ',c.last_name) NAME,"
				+ " COUNT(*) cnt"
				+ " FROM customer c"
				+ " INNER JOIN rental r ON c.customer_id = r.customer_id"
				+ " INNER JOIN inventory i ON r.inventory_id = i.inventory_id"
				+ " INNER JOIN film f  ON i.film_id = f.film_id"
				+ " INNER JOIN film_category fc ON f.film_id = fc.film_id"
				+ " WHERE fc.category_id=11"
				+ " GROUP BY NAME"
				+  " ORDER BY COUNT(*) DESC LIMIT 1,10";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Map <String, Object> m = new HashMap<>();
				m.put("NAME",rs.getString("NAME"));
				m.put("cnt", rs.getInt("cnt"));
				list.add(m);
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
		return list;
	}
}
