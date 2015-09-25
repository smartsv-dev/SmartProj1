package jp.co.smartservice.app.ba01s01.form;

import jp.terasoluna.fw.web.struts.form.ValidatorActionFormEx;

public class BA01S01From extends ValidatorActionFormEx {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1302952352371388731L;
	
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
