package jp.co.smartservice.fw.message;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;

import jp.terasoluna.fw.web.struts.MessageFormatCacheMapFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

/**
 * <p>
 * Springのメッセージソースを利用するMessageResources実装クラス。<br>
 * </p>
 * <p>
 */
public class MesseageResources implements Serializable {

    /** 
     * シリアルバージョンID
     */
    private static final long serialVersionUID = 8870342287587564386L;

    /**
     *  ログインスタンス
     */
    private static Log log = LogFactory.getLog(MesseageResources.class);

    /** 
     * Springのメッセージソース
     */
    private MessageSource messageSource = null;
    
    /**
     * formats
     */
    @SuppressWarnings("rawtypes")
	protected HashMap formats = new HashMap();

    /**
     * The default Locale for our environment.
     */
    protected Locale defaultLocale = Locale.getDefault();

    /**
     * Indicate is a <code>null</code> is returned instead of an error message
     * string when an unknown Locale or key is requested.
     */
    protected boolean returnNull = false;

    /**
     * Indicates whether 'escape processing' should be performed on the error
     * message string.
     */
    private boolean escape = true;

	/**
     * 指定されたパラメータによってMesseageResourcesExを生成する。
     *
     */
    public MesseageResources() {
        replaceMessageFormatCache();
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
     * 指定されたキーとロケールにもとづきメッセージ文言を取得する。
     *
     * @see org.apache.struts.util.MessageResources#getMessage(java.util.Locale, java.lang.String)
     */
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
        return null;
    }

    /**
     * Returns a text message for the specified key, for the default Locale.
     *
     * @param key The message key to look up
     */
    public String getMessage(String key) {
        return this.getMessage((Locale) null, key, null);
    }

    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.
     *
     * @param key  The message key to look up
     * @param args An array of replacement parameters for placeholders
     */
    public String getMessage(String key, Object[] args) {
        return this.getMessage((Locale) null, key, args);
    }

    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.
     *
     * @param key  The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     */
    public String getMessage(String key, Object arg0) {
        return this.getMessage((Locale) null, key, arg0);
    }

    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.
     *
     * @param key  The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     * @param arg1 The replacement for placeholder {1} in the message
     */
    public String getMessage(String key, Object arg0, Object arg1) {
        return this.getMessage((Locale) null, key, arg0, arg1);
    }

    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.
     *
     * @param key  The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     * @param arg1 The replacement for placeholder {1} in the message
     * @param arg2 The replacement for placeholder {2} in the message
     */
    public String getMessage(String key, Object arg0, Object arg1, Object arg2) {
        return this.getMessage((Locale) null, key, arg0, arg1, arg2);
    }

    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.
     *
     * @param key  The message key to look up
     * @param arg0 The replacement for placeholder {0} in the message
     * @param arg1 The replacement for placeholder {1} in the message
     * @param arg2 The replacement for placeholder {2} in the message
     * @param arg3 The replacement for placeholder {3} in the message
     */
    public String getMessage(String key, Object arg0, Object arg1, Object arg2,
        Object arg3) {
        return this.getMessage((Locale) null, key, arg0, arg1, arg2, arg3);
    }

    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will be returned by this
     * method if no resource bundle has been configured.
     *
     * @param locale The requested message Locale, or <code>null</code> for
     *               the system default Locale
     * @param key    The message key to look up
     * @param args   An array of replacement parameters for placeholders
     */
    @SuppressWarnings({ "unchecked"})
	public String getMessage(Locale locale, String key, Object[] args) {
        // Cache MessageFormat instances as they are accessed
        if (locale == null) {
            locale = defaultLocale;
        }

        MessageFormat format = null;
        String formatKey = messageKey(locale, key);

        synchronized (formats) {
            format = (MessageFormat) formats.get(formatKey);

            if (format == null) {
                String formatString = getMessage(locale, key);

                if (formatString == null) {
                    return returnNull ? null : ("???" + formatKey + "???");
                }

                format = new MessageFormat(escape(formatString));
                format.setLocale(locale);
                formats.put(formatKey, format);
            }
        }

        return format.format(args);
    }

    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will never be returned by
     * this method.
     *
     * @param locale The requested message Locale, or <code>null</code> for
     *               the system default Locale
     * @param key    The message key to look up
     * @param arg0   The replacement for placeholder {0} in the message
     */
    public String getMessage(Locale locale, String key, Object arg0) {
        return this.getMessage(locale, key, new Object[] { arg0 });
    }

    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will never be returned by
     * this method.
     *
     * @param locale The requested message Locale, or <code>null</code> for
     *               the system default Locale
     * @param key    The message key to look up
     * @param arg0   The replacement for placeholder {0} in the message
     * @param arg1   The replacement for placeholder {1} in the message
     */
    public String getMessage(Locale locale, String key, Object arg0, Object arg1) {
        return this.getMessage(locale, key, new Object[] { arg0, arg1 });
    }

    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will never be returned by
     * this method.
     *
     * @param locale The requested message Locale, or <code>null</code> for
     *               the system default Locale
     * @param key    The message key to look up
     * @param arg0   The replacement for placeholder {0} in the message
     * @param arg1   The replacement for placeholder {1} in the message
     * @param arg2   The replacement for placeholder {2} in the message
     */
    public String getMessage(Locale locale, String key, Object arg0,
        Object arg1, Object arg2) {
        return this.getMessage(locale, key, new Object[] { arg0, arg1, arg2 });
    }

    /**
     * Returns a text message after parametric replacement of the specified
     * parameter placeholders.  A null string result will never be returned by
     * this method.
     *
     * @param locale The requested message Locale, or <code>null</code> for
     *               the system default Locale
     * @param key    The message key to look up
     * @param arg0   The replacement for placeholder {0} in the message
     * @param arg1   The replacement for placeholder {1} in the message
     * @param arg2   The replacement for placeholder {2} in the message
     * @param arg3   The replacement for placeholder {3} in the message
     */
    public String getMessage(Locale locale, String key, Object arg0,
        Object arg1, Object arg2, Object arg3) {
        return this.getMessage(locale, key,
            new Object[] { arg0, arg1, arg2, arg3 });
    }

    /**
     * Return <code>true</code> if there is a defined message for the
     * specified key in the system default locale.
     *
     * @param key The message key to look up
     */
    public boolean isPresent(String key) {
        return this.isPresent(null, key);
    }

    /**
     * Return <code>true</code> if there is a defined message for the
     * specified key in the specified Locale.
     *
     * @param locale The requested message Locale, or <code>null</code> for
     *               the system default Locale
     * @param key    The message key to look up
     */
    public boolean isPresent(Locale locale, String key) {
        String message = getMessage(locale, key);

        if (message == null) {
            return false;
        } else if (message.startsWith("???") && message.endsWith("???")) {
            return false;
        } else {
            return true;
        }
    }

    // ------------------------------------------------------ Protected Methods
    /**
     * Indicates whether 'escape processing' should be performed on the error
     * message string.
     *
     * @since Struts 1.2.8
     */
    public boolean isEscape() {
        return escape;
    }

    /**
     * Set whether 'escape processing' should be performed on the error
     * message string.
     *
     * @since Struts 1.2.8
     */
    public void setEscape(boolean escape) {
        this.escape = escape;
    }
    
    /**
     * Escape any single quote characters that are included in the specified
     * message string.
     *
     * @param string The string to be escaped
     */
    protected String escape(String string) {
        if (!isEscape()) {
            return string;
        }

        if ((string == null) || (string.indexOf('\'') < 0)) {
            return string;
        }

        int n = string.length();
        StringBuffer sb = new StringBuffer(n);

        for (int i = 0; i < n; i++) {
            char ch = string.charAt(i);

            if (ch == '\'') {
                sb.append('\'');
            }

            sb.append(ch);
        }

        return sb.toString();
    }

    /**
     * Compute and return a key to be used in caching information by a Locale.
     * <strong>NOTE</strong> - The locale key for the default Locale in our
     * environment is a zero length String.
     *
     * @param locale The locale for which a key is desired
     */
    protected String localeKey(Locale locale) {
        return (locale == null) ? "" : locale.toString();
    }

    /**
     * Compute and return a key to be used in caching information by Locale
     * and message key.
     *
     * @param locale The Locale for which this format key is calculated
     * @param key    The message key for which this format key is calculated
     */
    protected String messageKey(Locale locale, String key) {
        return (localeKey(locale) + "." + key);
    }

    /**
     * Compute and return a key to be used in caching information by locale
     * key and message key.
     *
     * @param localeKey The locale key for which this cache key is calculated
     * @param key       The message key for which this cache key is
     *                  calculated
     */
    protected String messageKey(String localeKey, String key) {
        return (localeKey + "." + key);
    }

    /**
	 * @return messageSource
	 */
	public MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * @param messageSource セットする messageSource
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

}
