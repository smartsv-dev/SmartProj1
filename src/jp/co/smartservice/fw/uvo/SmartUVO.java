package jp.co.smartservice.fw.uvo;

import jp.terasoluna.fw.web.UserValueObject;

public class SmartUVO extends UserValueObject {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8375959126090579221L;

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
