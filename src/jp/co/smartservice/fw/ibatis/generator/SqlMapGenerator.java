package jp.co.smartservice.fw.ibatis.generator;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.ibator.api.Ibator;
import org.apache.ibatis.ibator.config.IbatorConfiguration;
import org.apache.ibatis.ibator.config.xml.IbatorConfigurationParser;
import org.apache.ibatis.ibator.exception.InvalidConfigurationException;
import org.apache.ibatis.ibator.exception.XMLParserException;
import org.apache.ibatis.ibator.internal.DefaultShellCallback;

public class SqlMapGenerator {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {

        try {
            List warnings = new ArrayList();
            boolean overwrite = true;
            File configFile = new File("C:\\OCCTO\\workspace_oa\\SmartProj1\\Resources\\ibatorConfig.xml");
            IbatorConfigurationParser cp = new IbatorConfigurationParser(warnings);
            IbatorConfiguration config = cp.parseIbatorConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            Ibator ibator = new Ibator(config, callback, warnings);
            ibator.generate(null);
            System.out.println("Generate successfully!");
        } catch (IOException e) {
        } catch (XMLParserException e) {
        } catch (InvalidConfigurationException e) {
        } catch (SQLException e) {
        } catch (InterruptedException e) {
        }
    }
}
