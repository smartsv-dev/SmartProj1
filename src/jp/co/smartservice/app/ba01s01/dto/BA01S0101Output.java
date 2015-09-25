package jp.co.smartservice.app.ba01s01.dto;

import java.io.Serializable;

import jp.co.smartservice.app.common.Constants;
import jp.co.smartservice.fw.annotation.FieldScope;
import jp.co.smartservice.fw.uvo.SmartUVO;

public class BA01S0101Output implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6082976893804276960L;
	
	@FieldScope(Constants.SCOPE_SESSION)
	private SmartUVO USER_VALUE_OBJECT = null;

	/**
	 * @return USER_VALUE_OBJECT
	 */
	public SmartUVO getUSER_VALUE_OBJECT() {
		return USER_VALUE_OBJECT;
	}

	/**
	 * @param USER_VALUE_OBJECT セットする USER_VALUE_OBJECT
	 */
	public void setUSER_VALUE_OBJECT(SmartUVO USER_VALUE_OBJECT) {
		this.USER_VALUE_OBJECT = USER_VALUE_OBJECT;
	}

}
