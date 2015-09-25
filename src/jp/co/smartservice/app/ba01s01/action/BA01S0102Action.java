package jp.co.smartservice.app.ba01s01.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import jp.terasoluna.fw.web.struts.actions.LogoffAction;

/**
 * ログアウト処理を行うアクションクラス。<br>
 * <br>
 * Terasolunaの{@link jp.terasoluna.fw.web.struts.actions.LogoffAction LogoffAction}
 * の機能を使用し、セッション情報をクリアする。<br>
 * セッション情報をクリアした後にはログアウト結果として表示されるメッセージを 生成し返す。
 * 
 */
public class BA01S0102Action extends LogoffAction {

    /**
     * ログアウトメッセージコード。
     */
    private static final String LOGOUT_MESSAGE = "info.UC_A99_02.00001";

    /**
     * ログアウト処理を行う。<br>
     * 全てのセッション情報をクリアし、ログアウト成功メッセージを設定する。
     * 
     * @param mapping
     *            アクションマッピング
     * @param form
     *            フォーム
     * @param request
     *            リクエスト
     * @param response
     *            レスポンス
     * @return 遷移先アクションフォワード
     * 
     */
    @Override
    public ActionForward doExecute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        // セッションクリア処理を行う。
        ActionForward forward = super.doExecute(mapping, form, request, response);

        // forwardがnullの場合はnullを返す。(FWの仕様)
        if (forward == null) {
            return null;
        }

        // リダイレクト設定を行なう。
        forward.setRedirect(true);

        // セッションを取得する。
        HttpSession session = request.getSession();

        // メッセージを生成する。
        ActionMessages messages = new ActionMessages();
        messages.add(Globals.MESSAGE_KEY, new ActionMessage(LOGOUT_MESSAGE));

        // メッセージを設定する。
        addMessages(session, messages);

        return forward;
    }

}
