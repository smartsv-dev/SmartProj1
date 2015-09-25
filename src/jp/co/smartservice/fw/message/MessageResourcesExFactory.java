package jp.co.smartservice.fw.message;

import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;

/**
 * SpringMessageResourcesFactoryを生成するファクトリクラス
 *
 * <p>
 * Springのメッセージソースを利用するSpringMessageResourcesを
 * 生成するファクトリクラス。
 * </p>
 *
 * @see jp.terasoluna.fw.web.struts.util.SpringMessageResources
 *
 */
public class MessageResourcesExFactory extends MessageResourcesFactory {

    /** 
     * シリアルバージョンID
     */
	private static final long serialVersionUID = -3456979527769981588L;

	/**
     * 新規にMessageResourcesExを生成する。
     *
     * @param config リクエストバンドルに対する設定パラメータ
     * @return MessageResourcesExインスタンス
     */
    @Override
    public MessageResources createResources(String config) {
        return new MessageResourcesEx(this, config, super.returnNull);
    }
}
