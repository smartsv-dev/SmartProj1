package jp.co.smartservice.fw.ibatis.generator;

//import java.lang.reflect.InvocationTargetException;
//import java.util.Map;

//import jp.co.smartservice.fw.utils.MethodUtilsEx;

//import org.apache.ibatis.abator.api.FullyQualifiedTable;
//import org.apache.ibatis.abator.internal.sqlmap.SqlMapGeneratorLegacyImpl;

public class SqlMapGeneratorImpl /*extends SqlMapGeneratorLegacyImpl*/ {

    /**
     * Constructs an instance of SqlMapGeneratorImpl
     */
    public SqlMapGeneratorImpl() {
        super();
    }

    /**
     * Calculates a file name for the current table. Typically the name is
     * "sqlMap-XXXX.xml" where XXXX is the fully qualified table name (delimited
     * with underscores).
     * 
     * @param table
     *            the current table
     * @return tha name of the SqlMap file
     */

//    @SuppressWarnings({ "rawtypes", "unchecked" })
//	@Override
//    protected String getSqlMapFileName(FullyQualifiedTable table) {
//        String key = "getSqlMapFileName";
//
//        Map map = getTableMap(table);
//        String s = (String) map.get(key);
//        if (s == null) {
//            StringBuffer sb = new StringBuffer();
//            sb.append("sqlMap-");
//            sb.append(table.getDomainObjectName());
//            sb.append(".xml");
//
//            s = sb.toString();
//            map.put(key, s);
//        }
//        return s;
//    }
//    
//    @SuppressWarnings("rawtypes")
//	protected Map getTableMap(FullyQualifiedTable table) {
//        Object[] methodParams = new Object[] { table };
//        Class[] parameterTypes = new Class[] { FullyQualifiedTable.class };
//        Object value = null;
//        try {
//            value = MethodUtilsEx.invokeMethod(this, "getTableStringMap", methodParams, parameterTypes);
//        } catch (NoSuchMethodException e) {
//        } catch (IllegalAccessException e) {
//        } catch (InvocationTargetException e) {
//        }
//        return (Map) value;
//    }
}
