/*
 *  Copyright 2008 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package jp.co.smartservice.fw.ibatis.plugins;

import java.util.List;

import org.apache.ibatis.ibator.api.FullyQualifiedTable;
import org.apache.ibatis.ibator.api.IbatorPluginAdapter;
import org.apache.ibatis.ibator.api.IntrospectedTable;

/**
 * This plugin is used to change the sqlMap filename.
 * 
 * @author bakuskjp
 *
 */
public class SqlMapFileNamePlugin extends IbatorPluginAdapter {
    
    public SqlMapFileNamePlugin() {
        super();
    }

    public boolean validate(List<String> warnings) {
        // this plugin is always valid
        return true;
    }

    public void initialized(IntrospectedTable introspectedTable) {
        String sqlMapFileName = calculateSqlMapFileName(introspectedTable.getFullyQualifiedTable());
        introspectedTable.setAttribute(IntrospectedTable.ATTR_SQL_MAP_FILE_NAME, sqlMapFileName);
    }
    
    private String calculateSqlMapFileName(FullyQualifiedTable fullyQualifiedTable) {
        StringBuilder sb = new StringBuilder();
        sb.append("sqlMap-");
        sb.append(fullyQualifiedTable.getDomainObjectName());
        sb.append(".xml");
        
        return sb.toString();
    }
}
