package jp.co.smartservice.fw.utils;

import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;

public class MessageUtils {

    public static final String factoryClass = "jp.co.smartservice.fw.message.MessageResourcesExFactory";
    public static final String config = "messageSource";
    public static MessageResources messageResources = null;
    
    public static MessageResources getResources() {
        if (messageResources == null) {
            MessageResourcesFactory.setFactoryClass(factoryClass);
            MessageResourcesFactory factoryObject = MessageResourcesFactory.createFactory();
            messageResources = factoryObject.createResources(config);
        }
        return messageResources;
    }
}
