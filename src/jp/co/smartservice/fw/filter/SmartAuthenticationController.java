package jp.co.smartservice.fw.filter;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jp.co.smartservice.fw.uvo.SmartUVO;
import jp.terasoluna.fw.web.RequestUtil;
import jp.terasoluna.fw.web.UserValueObject;
import jp.terasoluna.fw.web.thin.AuthenticationController;

/**
 * AuthenticationControllerを実装したログオン認証済みチェックを行うクラス。<br>
 * <br>
 * ・共通処理CP0002：ログイン済み検証
 * 
 * <h5>概要</h5>
 * <p>
 * AuthenticationControllerについては、 各プロジェクトごとに実装したクラスを作成する。 <br>
 * この時、ログオン認証済みチェック方法等はプロジェクトごとに異なる為、 任意の実装を行うこと。
 * </p>
 * <p>
 * サンプルアプリケーションでは、Bean定義により パス情報を取得し制御するよう実装している。 <br>
 * </p>
 * <p>
 * ログオン認証済みチェックの処理対象としないパスについては、
 * あらかじめBean定義ファイルによりauthenticatedNoCheckPathsに正規表現で設定しておき、
 * リクエストのパスがauthenticatedNoCheckPathsの正規表現に一致しなかった場合に、 セッションに
 * {@link jp.terasoluna.toursample.common.TourUVO} が格納されているかどうかで、チェックを行う。
 * </p>
 * 
 * @see jp.terasoluna.fw.web.thin.AuthenticationController
 * @see jp.terasoluna.fw.web.thin.AuthenticationControlFilter
 * @see jp.terasoluna.fw.web.UserValueObject
 * @see jp.terasoluna.toursample.common.TourUVO
 * 
 * 
 * 
 */
public class SmartAuthenticationController implements AuthenticationController {

    /**
     * 認証チェックを行わないパス情報リスト。
     */
    private List<String> authenticatedNoCheckPaths = null;

    /**
     * 認証チェックを行わないパス情報リストを設定する。
     * 
     * @param authenticatedNoCheckPaths
     *            パス情報リスト
     */
    public void setAuthenticatedNoCheckPaths(
            List<String> authenticatedNoCheckPaths) {

        this.authenticatedNoCheckPaths = authenticatedNoCheckPaths;
    }

    /**
     * リクエストのパス情報に対して、ログオン認証済みかどうかを判定する。
     * <p>
     * 認証済みかどうかはセッションに {@link jp.terasoluna.toursample.common.TourUVO}
     * が格納されているかどうかで判定する。
     * </p>
     * 
     * @param pathInfo
     *            パス情報
     * @param req
     *            リクエスト
     * @return 認証済みであればtrue。
     */
    public boolean isAuthenticated(String pathInfo, ServletRequest req) {

        HttpSession session = ((HttpServletRequest) req).getSession();

        // セッションからオブジェクトを取得
        Object getObject = session
            .getAttribute(UserValueObject.USER_VALUE_OBJECT_KEY);

        // チェック
        if (getObject == null) {
            return false;
        }

        // チェック
        if (getObject instanceof SmartUVO) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * リクエストのパス情報に対し、 認証チェックを行うべきパスかどうかを判定する。
     * <p>
     * アクセスしようとしているパスが認証チェックを行なわないパスの正規表現 リストに当たらない場合trueを返す。
     * </p>
     * 
     * @param req
     *            判定対象となるServletRequestクラスインスタンス
     * @return 認証チェックを要する場合はtrue。
     */
    public boolean isCheckRequired(ServletRequest req) {

        // pathInfoの取得
        String pathInfo = RequestUtil.getPathInfo(req);

        // 認証チェックを行わないパスのリストになければ、チェックする
        for (int i = 0; i < authenticatedNoCheckPaths.size(); i++) {
            String path = authenticatedNoCheckPaths.get(i);

            // チェック
            if (pathInfo.matches(path)) {
                return false;
            }
        }
        return true;
    }

}
