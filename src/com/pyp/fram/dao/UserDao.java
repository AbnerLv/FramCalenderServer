package com.pyp.fram.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pyp.fram.entity.UserEntity;
import com.pyp.fram.utils.JsonUtil;

public class UserDao {

	private DBMan manager;
	private static UserDao dao = null;

	public static UserDao getInstance(){
		if (dao == null) {
			try {
				dao = new UserDao();
			} catch (Exception e) {
		  }	
		}
		return dao;
	}

	public UserDao() throws ClassNotFoundException, IOException {
		manager = DBMan.getInstance();

	}

	public String checkLogin(String username, String password) {

		String sql = "select * from t_user where password='" + password
				+ "' and username='" + username + "'";
		String json = null;
		try {
			manager.connDB();
			ResultSet rs = manager.executeQuery(sql);
			System.out.println("sql = " + sql);

			while (rs.next()) {
				Integer id = rs.getInt("id");
				String t_username = rs.getString("username");
				String t_eamil = rs.getString("email");
				String t_password = rs.getString("password");
				String profile = rs.getString("profile");
				String phone = rs.getString("phone");
				String city = rs.getString("city");
				Integer age = rs.getInt("age");
				Integer sex = rs.getInt("sex");
				String address = rs.getString("address");
				UserEntity entity = new UserEntity(id,t_username, t_eamil,
						t_password,profile,phone,city,age,sex,address);
				json = JsonUtil.createJsonString(entity);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("checkLogin json = " + json);
		return json;
	}

	public String forgetPassword(String username, String email,String phone) {
		String sql = "select password from t_user where username = '"
				+ username + "' OR email = '" + email+ "' AND phone = '"+phone+"'" ;
		String password = null;
		try {
			manager.connDB();
			ResultSet rs = manager.executeQuery(sql);
			while (rs.next()) {
				password = rs.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("password = " + password);
		return password;
	}

	/**
	 * 修改密码
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public int modifyPassword(String username, String oldPassword,
			String newPassword) {
		String sql = "update t_user set password = '" + newPassword
				+ "' where password='" + oldPassword
				+ "' and username='" + username + "'";
		System.out.println(sql);
		int flag = 0;
		try {
			manager.connDB();
			flag = manager.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 修改个人信息
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public int modifyPerInfo(UserEntity entity) {
		String sql = "update t_user set sex = '" + entity.getSex()
				+"',age = '" + entity.getAge()+"'"
				+",phone = '" + entity.getPhone()+"'"
				+",email = '" + entity.getEmail()+"'"
				+",city = '" + entity.getCity()+"'"
				+",address = '" + entity.getAddress()+"'"
				+",profile = '" + entity.getProfile()+"'"
				+",username = '" + entity.getUsername()+"'"
				+ " where id='" + entity.getId() + "'";
		System.out.println(sql);
		int flag = 0;
		try {
			manager.connDB();
			flag = manager.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 注册用户信息接口
	 * 
	 * @param username
	 * @param password
	 * @param email
	 * @return
	 */
	public int register(String username, String password, String email) {
		int flag = 0;
		String sql = "INSERT INTO t_user(username, password, email) VALUES ('" + username
				+ "','" + password + "', '" + email + "')";
		System.out.println(sql);
		try {
			manager.connDB();
			flag = manager.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
