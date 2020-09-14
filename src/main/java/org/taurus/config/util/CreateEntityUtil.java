package org.taurus.config.util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自动生成MyBatis的实体类、实体映射XML文件、Mapper
 *
 * @author lifeifei
 * @create 2018-06-19 16:23
 **/
public class CreateEntityUtil {
    /**
     * *********************************使用前必读*******************
     * *
     * * 使用前请将moduleName更改为自己模块的名称即可（一般情况下与数据库名一致），其他无须改动。
     * *
     * **********************************************************
     */

    private final String type_char = "char";

    private final String type_date = "date";

    private final String type_timestamp = "timestamp";

    private final String type_datetime = "datetime";

    private final String type_int = "int";

    private final String type_bigint = "bigint";

    private final String type_text = "text";

    private final String type_bit = "bit";

    private final String type_decimal = "decimal";

    private final String type_blob = "blob";


//    private final String moduleName = "zhy"; // 对应模块名称（根据自己模块做相应调整!!!务必修改^_^）

    private final String bean_path = "d:/code/entity";

    private final String bean_package = "org.taurus" + ".entity.sys";

    private final String driverName = "com.mysql.cj.jdbc.Driver";

    private final String dbName = "taurus";

    private final String user = "root";

    private final String password = "root";

    private final String url = "jdbc:mysql://127.0.0.1:3306/" + dbName + "?serverTimezone=UTC&useSSL=false";

    private String entityName = null;
    
    private String tableName = null;

    private String beanName = null;

    private Connection conn = null;


    private void init() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, user, password);
    }


    /**
     * 获取所有的表
     *
     * @return
     * @throws SQLException
     */
    private List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<String>();
        PreparedStatement pstate = conn.prepareStatement("show tables");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tableName = results.getString(1);
            //          if ( tableName.toLowerCase().startsWith("yy_") ) {
            tables.add(tableName);
            //          }
        }
        return tables;
    }


    private void processTable(String table) {
        StringBuffer sb = new StringBuffer(table.length());
        String tableNew = table.toLowerCase();
        String[] tables = tableNew.split("_");
        String temp = null;
//        sb.append(tables[0]);
        for (int i = 0; i < tables.length; i++) {
            temp = tables[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
        }
        tableName = table;
        beanName = sb.toString();
        entityName = beanName + "Entity";
    }


    private String processType(String type) {
        if (type.indexOf(type_char) > -1) {
            return "String";
        } else if (type.indexOf(type_bigint) > -1) {
            return "Long";
        } else if (type.indexOf(type_int) > -1) {
            return "Integer";
        } else if (type.indexOf(type_date) > -1) {
            return "Date";
        } else if (type.indexOf(type_text) > -1) {
            return "String";
        } else if (type.indexOf(type_timestamp) > -1) {
            return "Date";
        } else if (type.indexOf(type_datetime) > -1) {
            return "java.util.Date";
        } else if (type.indexOf(type_bit) > -1) {
            return "Boolean";
        } else if (type.indexOf(type_decimal) > -1) {
            return "java.math.BigDecimal";
        } else if (type.indexOf(type_blob) > -1) {
            return "byte[]";
        }
        return null;
    }


    private String processField(String field) {
        StringBuffer sb = new StringBuffer(field.length());
        field = field.toLowerCase();
        String[] fields = field.split("_");
        String temp = null;
        sb.append(fields[0]);
        for (int i = 1; i < fields.length; i++) {
            temp = fields[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
        }
    	
    	
    	
    	
        return sb.toString();
    }

    /**
     * 构建类上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws IOException
     */
    private BufferedWriter buildClassComment(BufferedWriter bw, String text) throws IOException {
        bw.newLine();
        bw.newLine();
        bw.write("/**");
        bw.newLine();
        bw.write(" * ");
        bw.newLine();
        bw.write(" * " + text);
        bw.newLine();
        bw.write(" * ");
        bw.newLine();
        bw.write(" **/");
        return bw;
    }

    /**
     * 生成实体类
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    private void buildEntityBean(List<String> columns, List<String> types, List<String> comments, String tableComment)
            throws IOException {
        File folder = new File(bean_path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File beanFile = new File(bean_path, entityName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile),"utf-8"));
        bw.write("package " + bean_package + ";");
        bw.newLine();
        bw.write("import java.io.Serializable;");
        bw.newLine();
        bw.write("import java.util.Date;");
        bw.newLine();
        bw.write("import com.baomidou.mybatisplus.annotation.TableName;");
        bw.newLine();
        bw = buildClassComment(bw, tableComment);
        bw.newLine();
        bw.newLine();
        bw.write("@TableName(\""+tableName+"\")");
        bw.newLine();
        bw.write("public class " + entityName + " implements Serializable {");
        bw.newLine();
        bw.newLine();
        bw.write("\tprivate static final long serialVersionUID = 1L;");
        bw.newLine();
        bw.newLine();
        int size = columns.size();
        //生成私有字段
        for (int i = 0; i < size; i++) {
        	//注释
            bw.write("\t/**");
            bw.newLine();
            bw.write("\t * " + comments.get(i));
            bw.newLine();
            bw.write("\t */");
            
            bw.newLine();
            bw.write("\tprivate " + processType(types.get(i)) + " " + processField(columns.get(i)) + ";");
            bw.newLine();
            bw.newLine();
        }
        bw.newLine();
        
        // 生成get 和 set方法
        String tempField = null;
        String _tempField = null;
        String tempType = null;
        for (int i = 0; i < size; i++) {
            tempType = processType(types.get(i));
            _tempField = processField(columns.get(i));
            tempField = _tempField.substring(0, 1).toUpperCase() + _tempField.substring(1);

        	//set注释
            bw.write("\t/**");
            bw.newLine();
            bw.write("\t * 设置 " + comments.get(i));
            bw.newLine();
            bw.write("\t * @param " + tempField + " " + comments.get(i));
            bw.newLine();
            bw.write("\t */");
            bw.newLine();
            
            bw.write("\tpublic void set" + tempField + "(" + tempType + " " + _tempField + "){");
            bw.newLine();
            bw.write("\t\tthis." + _tempField + " = " + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();
            
        	//get注释
            bw.write("\t/**");
            bw.newLine();
            bw.write("\t * 获取 " + comments.get(i));
            bw.newLine();
            bw.write("\t * @return " + comments.get(i));
            bw.newLine();
            bw.write("\t */");
            bw.newLine();
            
            bw.write("\tpublic " + tempType + " get" + tempField + "(){");
            bw.newLine();
            bw.write("\t\treturn this." + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();
        }
        bw.newLine();
        bw.write("}");
        bw.newLine();
        bw.flush();
        bw.close();
    }

    /**
     * 获取所有的数据库表注释
     *
     * @return
     * @throws SQLException
     */
    private Map<String, String> getTableComment() throws SQLException {
        Map<String, String> maps = new HashMap<String, String>();
        PreparedStatement pstate = conn.prepareStatement("show table status");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tableName = results.getString("NAME");
            String comment = results.getString("COMMENT");
            maps.put(tableName, comment);
        }
        return maps;
    }


    public void generate() throws ClassNotFoundException, SQLException, IOException {
        init();
        String prefix = "show full fields from ";
        List<String> columns = null;
        List<String> types = null;
        List<String> comments = null;
        PreparedStatement pstate = null;
        List<String> tables = getTables();
        Map<String, String> tableComments = getTableComment();
        for (String table : tables) {
            columns = new ArrayList<String>();
            types = new ArrayList<String>();
            comments = new ArrayList<String>();
            pstate = conn.prepareStatement(prefix + table);
            ResultSet results = pstate.executeQuery();
            while (results.next()) {
                columns.add(results.getString("FIELD"));
                types.add(results.getString("TYPE"));
                comments.add(results.getString("COMMENT"));
            }
            entityName = table;
            processTable(table);
            //          this.outputBaseBean();
            String tableComment = tableComments.get(tableName);
            buildEntityBean(columns, types, comments, tableComment);
        }
        conn.close();
    }


    public static void main(String[] args) {
        try {
            new CreateEntityUtil().generate();
            // 自动打开生成文件的目录
            Runtime.getRuntime().exec("cmd /c start explorer D:\\code");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
