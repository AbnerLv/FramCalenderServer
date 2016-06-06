package com.pyp.fram.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pyp.fram.entity.UserEntity;
import com.pyp.fram.utils.JsonUtil;

public class UserDao {

	private DBMan manager;
	private static UserDao dao = null;

	public static UserDao getInstance() throws ClassNotFoundException,
			IOException {
		if (dao == null) {
			dao = new UserDao();
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
				String t_username = rs.getString("username");
				String t_eamil = rs.getString("email");
				String t_password = rs.getString("password");

				UserEntity entity = new UserEntity(t_username, t_eamil,
						t_password);
				json = JsonUtil.createJsonString(entity);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return json;
	}

	public String BackPassword(String emp_no, String emp_phone_no,
			String emp_identify) {
		String sql = "select emp_password from emp_info where emp_no = '"
				+ emp_no + "' and emp_phone_no = '" + emp_phone_no
				+ "' and emp_identify = '" + emp_identify + "'";
		String password = null;
		try {
			manager.connDB();
			ResultSet rs = manager.executeQuery(sql);
			while (rs.next()) {
				password = rs.getString("emp_password");
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
	public int changePassword(String username, String oldPassword,
			String newPassword) {
		String sql = "update emp_info set emp_password = '" + newPassword
				+ "' where emp_password='" + oldPassword
				+ "' and emp_nickname='" + username + "' or emp_no ='"
				+ username + "' or emp_phone_no='" + username + "'";
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
		String sql = "UPDATE t_user SET username = '" + username
				+ "',password = '" + password + "', email = '" + email + "'";
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
