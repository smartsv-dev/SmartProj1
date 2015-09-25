package jp.co.smartservice.fw.message;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;

import jp.terasoluna.fw.exception.SystemException;
import jp.terasoluna.fw.web.struts.MessageFormatCacheMapFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * <p>
 * SpringのメッセージソースをStrutsから利用するMessageResources実装クラス。<br>
 * </p>
 */
public class MessageResourcesEx extends MessageResources {

    /** 
     * シリアルバージョンID
     */
	private static final long serialVersionUID = -276392448036717946L;

	/**
     *  ログインスタンス
     */
    private static Log log = LogFactory.getLog(MessageResourcesEx.class);

    /** 
     * エラーメッセージキー
     */
    private static final String ERR_BEAN_EXCEPTION = "errors.message.bean.exception";

    /** 
     * アプリケーションコンテキスト
     */
    private WebApplicationContext context = null;

    /** 
     * Springのメッセージソース
     */
    private MessageSource messageSource = null;

    /**
     * 指定されたパラメータによってSpringMessageResourcesを生成する。
     *
     * @param factory メッセージリソースファクトリ
     * @param config コンテナから取得するMessageSourceのBean名
     *               （省略時はデフォルトの"messageSource"）
     */
    public MessageResourcesEx(MessageResourcesFactory factory, String config) {
        super(factory, config);
        replaceMessageFormatCache();
        this.context = ContextLoader.getCurrentWebApplicationContext();

        initMessageSource();
    }

    /**
     * 指定されたパラメータによってSpringMessageResourcesを生成する。
     *
     * @param factory メッセージリソースファクトリ
     * @param config コンテナから取得するMessageSourceのBean名
     *               （省略時はデフォルトの"messageSource"）
     * @param returnNull <code>org.apache.struts.util.MessageResources</code>
     *                   クラスの <code>returnNull</code>。
     *                   <code>false</code> 指定時、キーに該当するメッセージが
     *                   存在しない場合???Locale.key???という形式でメッセージを
     *                   返却する。
     */
    public MessageResourcesEx(MessageResourcesFactory factory,
            String config, boolean returnNull) {
        super(factory, config, returnNull);
        replaceMessageFormatCache();
        this.context = ContextLoader.getCurrentWebApplicationContext();

        initMessageSource();
    }

    /**
     * MessageFormatキャッシュ(formats)のインスタンス差し替えを行う。
     * <p>
     * Strutsのバグ STR-2172回避用のキャッシュオブジェクトに差し替える。
     * </p>
     * @see MessageFormatCacheMapFactory
     */
    private void replaceMessageFormatCache() {
        HashMap<String, MessageFormat> map = MessageFormatCacheMapFactory
                .getInstance();
        if (map != null) {
            formats = map;
        }
    }

    /**
     * MessageSourceの初期化を行う。
     */
    private void initMessageSource() {
        if (this.context != null) {
            if (config != null && !("".equals(config))) {
                // パラメータに指定されたBeanIDを取得する
                try {
                    this.messageSource = (MessageSource) this.context.getBean(
                            config, MessageSource.class);
                } catch (BeansException e) {
                    if (log.isErrorEnabled()) {
                        StringBuilder mes = new StringBuilder();
                        mes.append(config);
                        mes.append(" is not found");
                        mes.append(" or it is not MessageSource instance.");
                        log.error(mes.toString());
                    }
                    throw new SystemException(e, ERR_BEAN_EXCEPTION);
                }

                if (log.isDebugEnabled()) {
                    StringBuilder mes = new StringBuilder();
                    mes.append(config);
                    mes.append(" MessageSource is used.");
                    log.debug(mes.toString());
                }
            }

            // デフォルトのMessageSourceを採用（ApplicationContext自身）
            if (this.messageSource == null) {
                this.messageSource = this.context;
                if (log.isDebugEnabled()) {
                    log.debug("Default MessageSource is used.");
                }
            }
        } else {
            if (log.isWarnEnabled()) {
                log.warn("ApplicationContext is not found.");
            }
        }
    }

    /**
     * 指定されたキーとロケールにもとづきメッセージ文言を取得する。
     *
     * @see org.apache.struts.util.MessageResources#getMessage(java.util.Locale, java.lang.String)
     */
    @Override
    public String getMessage(Locale locale, String key) {
        if (this.messageSource != null) {
            String retMessage = null;

            try {
                retMessage = this.messageSource.getMessage(key, null, locale);
            } catch (NoSuchMessageException e) {
                // 何もしない
            }
            
            if (retMessage != null) {
                if (log.isDebugEnabled()) {
                    StringBuilder mes = new StringBuilder();
                    mes.append("key:[");
                    mes.append(key);
                    mes.append("] locale:[");
                    mes.append(locale);
                    mes.append("] message:[");
                    mes.append(retMessage);
                    mes.append("]");
                    log.debug(mes.toString());
                }
                return retMessage;
            }
        }

        // メッセージが存在しない場合は、returnNullの設定の有無に従って返却する
        if (!returnNull) {
            StringBuilder mes = new StringBuilder();
            mes.append("???");
            mes.append(messageKey(locale, key));
            mes.append("???");
            return mes.toString();
        }
        return null;
    }

}
