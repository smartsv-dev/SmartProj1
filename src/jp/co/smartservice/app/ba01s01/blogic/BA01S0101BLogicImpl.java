package jp.co.smartservice.app.ba01s01.blogic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import jp.co.smartservice.app.ba01s01.dto.BA01S0101Input;
import jp.co.smartservice.app.ba01s01.dto.BA01S0101Output;
import jp.co.smartservice.app.common.Constants;
import jp.co.smartservice.app.common.entity.T001User;
import jp.co.smartservice.fw.exception.BusinessException;
import jp.co.smartservice.fw.message.MesseageResources;
import jp.co.smartservice.fw.uvo.SmartUVO;
import jp.terasoluna.fw.dao.QueryDAO;
import jp.terasoluna.fw.service.thin.BLogic;
import jp.terasoluna.fw.service.thin.BLogicMessages;
import jp.terasoluna.fw.service.thin.BLogicResult;
import jp.terasoluna.fw.web.UserValueObject;

public class BA01S0101BLogicImpl implements BLogic<BA01S0101Input> {

    /**
     * ログクラス。
     */
    private static final Log log = LogFactory.getLog(BA01S0101BLogicImpl.class);

    /**
     * DAOクラス。 Springによりインスタンス生成され設定される。
     */
    protected QueryDAO queryDAO = null;

    /**
     * パスワードエンコードクラス
     */
    PasswordEncoder passwordEncoder;

    /**
     * メッセージソース
     */
    protected MesseageResources messeageResources = null;

	/**
     * ビジネスロジックを実行する。
     *
     * @param params ビジネスロジック入力情報
     * @return ビジネスロジック処理結果
     */
	public BLogicResult execute(BA01S0101Input params) {

        if (log.isDebugEnabled()) {
            log.debug("execute Start.");
        }

        // 実行結果を用意する。
        BLogicResult result = new BLogicResult();
        BA01S0101Output output = new BA01S0101Output();
        // メッセージを設定する。
        BLogicMessages messages = new BLogicMessages();

        T001User t001UserIn = new T001User();
        t001UserIn.setUserId(params.getUsername());

        T001User userInfo = queryDAO.executeForObject("t001_user.ibatorgenerated_selectByPrimaryKey", t001UserIn, T001User.class);

        if (userInfo == null) {
            throw new BusinessException("The user is not existed!");
        }

        if (userInfo.getPassword() == null || params.getPassword() == null
                || !passwordEncoder.matches(params.getPassword(), userInfo.getPassword())) {
            throw new BusinessException("The password is wrong!");
        }

        SmartUVO uvo = (SmartUVO) UserValueObject.createUserValueObject();
        uvo.setUsername(userInfo.getUserId());
        uvo.setPassword(userInfo.getPassword());

        output.setUSER_VALUE_OBJECT(uvo);
        // 結果をセットする。
        result.setResultObject(output);
        result.setMessages(messages);
        result.setResultString(Constants.FROWARD_SUCCESS);

        if (log.isDebugEnabled()) {
            log.debug("execute End.");
        }
		return result;
	}

    /**
     * QueryDAOを設定する。
     *
     * @param queryDAO 設定するQueryDAO
     */
    public void setQueryDAO(QueryDAO queryDAO) {
        this.queryDAO = queryDAO;
    }

    /**
	 * @param messeageResources セットする messeageResources
	 */
	public void setMesseageResources(MesseageResources messeageResources) {
		this.messeageResources = messeageResources;
	}

    /**
	 * @param passwordEncoder セットする passwordEncoder
	 */
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

}
