package jp.co.smartservice.fw.mapper;

import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.smartservice.fw.annotation.FieldScope;
import jp.terasoluna.fw.exception.SystemException;
import jp.terasoluna.fw.service.thin.BLogicIO;
import jp.terasoluna.fw.service.thin.BLogicMapper;
import jp.terasoluna.fw.service.thin.BLogicProperty;
import jp.terasoluna.fw.util.ClassLoadException;
import jp.terasoluna.fw.util.ClassUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BLogicBeanMapper extends BLogicMapper {

    /**
     * ログクラス。
     */
    private static Log log = LogFactory.getLog(BLogicBeanMapper.class);
    
    /**
     * ビジネスロジックに入力するJavaBeanの生成に失敗した場合のエラーコード。
     */
    private static final String ERROR_BEAN_CREATE = "errors.blogic.mapper.create";
    
    private static final String SERIALVERSIONUID = "serialVersionUID";
    
    public static final String FORM = "form";
    
    /**
     * コンストラクタ。
     */
    public BLogicBeanMapper(){
        super();
    }
    
    /**
     * コンストラクタ。
     * @param resources リソースのパス
     */
    public BLogicBeanMapper(String resources) {
        super(resources);
    }
    
    @Override
    protected Object setParams(HttpServletRequest request,
            HttpServletResponse response, BLogicIO io) {
        
        List<BLogicProperty> blogicParams = io.getBLogicParams();
        if (blogicParams.isEmpty()) {
            Object bean = null;
            try {
                bean = ClassUtil.create(io.getInputBeanName());
            } catch (ClassLoadException e) {
                log.error("bean creation failure.");
                throw new SystemException(e, ERROR_BEAN_CREATE);
            }
            Field[] beanFields = bean.getClass().getDeclaredFields();

            for (Field field : beanFields) {
                String fieldName = field.getName();
                if (SERIALVERSIONUID.equals(fieldName)) {
                    continue;
                }
                BLogicProperty blogicParam = new BLogicProperty();
                blogicParam.setProperty(fieldName);

                FieldScope annotation = field.getAnnotation(FieldScope.class);
                if (annotation != null) {
                    blogicParam.setSource(annotation.value());
                } else {
                    blogicParam.setSource(FORM);
                }
                io.setBLogicParam(blogicParam);
            }
        }
        return super.setParams(request, response, io);
    }

    @Override
    protected void getResults(HttpServletRequest request,
            HttpServletResponse response, BLogicIO io, Object bean) {
        
        List<BLogicProperty> bLogicResults = io.getBLogicResults();
        if (bLogicResults.isEmpty()) {

            Field[] beanFields = bean.getClass().getDeclaredFields();

            for (Field field : beanFields) {
                String fieldName = field.getName();
                if (SERIALVERSIONUID.equals(fieldName)) {
                    continue;
                }
                BLogicProperty blogicResult = new BLogicProperty();
                blogicResult.setProperty(fieldName);

                FieldScope annotation = field.getAnnotation(FieldScope.class);
                if (annotation != null) {
                    blogicResult.setDest(annotation.value());
                } else {
                    blogicResult.setDest(FORM);
                }
                io.setBLogicResult(blogicResult);
            }
        }
        super.getResults(request, response, io, bean);
    }
}
