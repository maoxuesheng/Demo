package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import util.DBUtils;

public class UserDAO {
	
	/**
	 * ɾ��ָ��id���û���Ϣ
	 * @param user
	 * @throws SQLException 
	 */
	public void delete(int id) throws SQLException{
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement("delete from t_user weher id = ?");
			stat.setInt(1, id);
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(conn, stat, null);
		}
	}
	/**
	 * ���û���Ϣ���뵽���ݿ�
	 * @param user
	 * @throws SQLException 
	 */
	public void save(User user) throws SQLException{
		Connection conn = null;
		PreparedStatement stat = null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement("insert into t_user values(null,?,?,?)");
			stat.setString(1, user.getUsername());
			stat.setString(2, user.getPassword());
			stat.setString(3, user.getEmail());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(conn, stat, null);
		}
	}
	/**
	 * ��ѯt_user���������û���Ϣ��ѯ����
	 * @return
	 * @throws SQLException 
	 */
	public List<User> findAll() throws SQLException{
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement  stat = null;
		ResultSet rs= null;
		try {
			conn = DBUtils.getConn();
			stat = conn.prepareStatement("select * from t_user");
			rs = stat.executeQuery();
			while(rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				User user = new User();
				user.setId(id);
				user.setUsername(username);
				user.setPassword(password);
				user.setEmail(email);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			DBUtils.close(conn, stat, rs);
		}
		return users;
	}
}
