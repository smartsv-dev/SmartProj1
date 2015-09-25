package jp.co.smartservice.app.ba01s01.dto;

import java.io.Serializable;

public class BA01S0101Input implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -732302052171306692L;

	private String username;
	
	private String password;

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username セットする username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
