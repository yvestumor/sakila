package dao;
import java.util.*;
import java.sql.*;

public class StaffDao {
	public List<Map<String, Object>> selectStaffList() {
		List<Map<String, Object>> list = new ArrayList<>(); // 다형성
		Connection conn = null;
		PreparedStatement stmt =  null;
		ResultSet rs = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
			String sql = " SELECT s.manager_staff_id staffId,"
					+ " CONCAT(m.first_name,' ',m.last_name) staffName,"
					+ " CONCAT(a.address,IFNULL(a.address2, ' '), district) staffAddress,"
					+ " s.store_id storeId,"
					+ " m.email email,"
					+ " s.last_update lastUpdate"
					+ " FROM staff m"
					+ " INNER JOIN store s"
					+ " INNER JOIN address a"
					+ " ON s.manager_staff_id = m.staff_id"
					+ " AND s.address_id =a.address_id; ";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>(); // 다형성
				map.put("staffId",rs.getInt("staffId"));
				map.put("staffName",rs.getString("staffName"));
				map.put("staffAddress",rs.getString("staffAddress"));
				map.put("storeId",rs.getInt("storeId"));
				map.put("email",rs.getString("email"));
				map.put("lastUpdate",rs.getString("lastUpdate"));
				list.add(map);
				
		    }
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외발생"); //예외발생시 출력
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
	
	//selectStaffList() 테스트코드
	public static void main(String[] args) {
		StaffDao dao = new StaffDao();
		List<Map<String, Object>> list = dao.selectStaffList();
		for(Map m : list) {
			System.out.println(m.get("staffId")+ " ,");
			System.out.println(m.get("staffName")+ " ,");
			System.out.println(m.get("staffAddress")+ " ,");
			System.out.println(m.get("storeId")+ " ,");
			System.out.println(m.get("email")+ " ,");
			System.out.println(m.get("lastUpdate"));
			System.out.println("");
			
		}
	}
}
		
