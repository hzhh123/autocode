package com.hd.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库工具，该类功能：<br>
 * <li>showTables()获取数据库的所有表信息</li>
 * <li>descTable()获取表的描述信息</li>
 * <br>********************************************<br>
 * <code>jdbc.properties</code>为数据库的配置信息，默认位置是在项目src目录下面<br>
 */
public class DBUtil {
	Logger log = LoggerFactory.getLogger(DBUtil.class);

	// 载入配置
	// ==================================
	private static final String JDBC__PROPERTIES = "jdbc.properties";
	private static LoadProperties loadProperties = new LoadProperties(JDBC__PROPERTIES);

	// JDBC RESOURCES
	// ==================================
	private static String DB_DRIVER = loadProperties.getValue("driverClass");
	private static String DB_NAME = loadProperties.getValue("db_name");
	private static String DB_PASSWORD = loadProperties.getValue("password");
	private static String DB_USER_NAME = loadProperties.getValue("username");
	private static String DB_URL = loadProperties.getValue("jdbcUrl");
	private static String SHOW_TABLES = "show tables";
	
	/**
	 * 读取配置文件，初始化信息
	 */
	public DBUtil() {
		loadProperties = loadProperties == null ? new LoadProperties(JDBC__PROPERTIES) : loadProperties;
	}

	/**
	 * 获取数据库中所有的表名称
	 * 
	 * @return 数据库中表名称的list
	 * @throws Exception
	 */
	public List<String> showTables() throws Exception {
		List<String> list = new ArrayList<String>();
		Class.forName(DB_DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
		PreparedStatement ps = conn.prepareStatement(SHOW_TABLES);
		ResultSet rs = ps.executeQuery();
		log.info("数据库:[" + DB_NAME + "]中的表如下：");
		while (rs.next()) {
			String tableName = rs.getString(1);
			log.info(tableName);
			list.add(tableName);
		}
		close(rs, ps, conn);
		return list;
	}

	/**
	 * table操作
	 * 建表、修改表字段、修改字段属性、添加字段、删除字段
	 * @param sql
	 * @throws Exception
	 */
	public static void modifyTable(String sql) throws Exception {
		Class.forName(DB_DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.execute();
		close(null, ps, conn);
	}
	
	//判断表是否存在
	public static boolean TableExist(String schema,String tableName) throws Exception {
		boolean flag=false;
		String sql="SELECT COUNT(1) AS COUNT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='"+schema+"' AND TABLE_NAME='"+tableName+"';";
		Class.forName(DB_DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		int count=0;
		while(rs.next()){
			 count=Integer.parseInt(rs.getString("count"));
		}
		if(count==1){
			flag=true;
		}
		close(null, ps, conn);
		return flag;
	}
	/**
	 * 获取表的描述
	 * @param tableName 表名称
	 * @return
	 * @throws Exception
	 */
	public List<DescTableBean> descTable(String tableName) throws Exception {
		List<DescTableBean> list = new ArrayList<DescTableBean>();
		DescTableBean temp = null;
		Class.forName(DB_DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
		PreparedStatement ps = conn.prepareStatement("desc " + tableName);
		ResultSet rs = ps.executeQuery();
		log.info("获取数据库表：[" + tableName + "]的结构:");
		while (rs.next()) {
			String descTable = " " + rs.getString(1) + "   " + rs.getString(2) + "    " + rs.getString(3) + "        " + rs.getString(4) + "        " + rs.getString(5) + "      " + rs.getString(6);
			log.info(descTable);
			temp = new DescTableBean();
			temp.setField(rs.getString(1));
			String type = rs.getString(2);
			temp.setType(getType(type));
			temp.setLength(Integer.valueOf(getValueByType(type)));
			temp.setDecase(Integer.valueOf(getDecase(type)));
			temp.setIsNull(rs.getString(3));
			temp.setKey(rs.getString(4));
			temp.setDefaultValue(rs.getString(5));
			temp.setExtra(rs.getString(6));
			list.add(temp);
		}
		close(rs, ps, conn);
		return list;
	}
	
	/**
	 * 关闭：记录集,声明,链接对象
	 * @param rs 记录集
	 * @param ps 声明
	 * @param conn 链接对象
	 * @throws Exception
	 */
	protected static void close(ResultSet rs,PreparedStatement ps,Connection conn) throws Exception{
		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
	
	/**
	 * 获取类型
	 * @param type 如：<code>varchar(20)</code>,<code>datetime</code>,<code>double</code>,<code>longtext</code>
	 * @return 结果：<br><li>type = "varchar(20)", return type = "varchar";</li>
	 * 		   <li>type = "datetime", return type = "datetime";</li>
	 */
	protected String getType(String type){
		if(type.endsWith(")")){
			type = type.substring(0, type.indexOf("("));
		}
		return type;
	}
	
	/**
	 * 字符串处理
	 * 
	 * @param columnName
	 *            需要被处理的字符串
	 * @return <li>
	 *         <code>columnName = "expert_user_admin"; <br>return "expertUserAdmin";</code>
	 *         </li> <li><code>columnName = "expert"; <br>return "expert";</code>
	 *         </li>
	 */
	protected static String handleColumnName(String columnName) {
		if (columnName.contains("_")) {
			String[] array = columnName.split("_");
			String temp = "";
			for (String str : array) {
				temp = temp + str.substring(0, 1).toUpperCase() + str.substring(1);
			}
			temp=temp.substring(0, 1).toLowerCase()+temp.substring(1,temp.length());
			return temp;
		} else {
			return columnName;
		}
	}
	/**
	 * 获取类型的长度,默认为255
	 * @param type 如：<code>varchar(20)</code>,<code>decimal(19,2)</code>,<code>datetime</code>,<code>double</code>,<code>longtext</code>
	 * @return 结果：<br><li>type = "varchar(20)", return "20";</li>
	 * 		   <li>type = "datetime", return type = "255";</li>
	 * 		   <li>type = "decimal(19,2)", return type = "19";</li>
	 */
	protected String getValueByType(String type) {
		if (type.endsWith(")")) {
			type = type.substring(type.indexOf("(") + 1, type.length() - 1);
			if(type.contains(",")){
				type = type.substring(0,type.indexOf(","));
			}
			return type;
		} else {
			return "255";
		}
	}
	
	/**
	 * 获取十进位,默认为0
	 * @param type 如：<code>varchar(20)</code>,<code>decimal(19,2)</code>,<code>datetime</code>,<code>double</code>,<code>longtext</code>
	 * @return 结果：<br><li>type = "varchar(20)", return "0";</li>
	 * 		   <li>type = "datetime", return type = "255";</li>
	 * 		   <li>type = "decimal(19,2)", return type = "19";</li>
	 */
	protected String getDecase(String type){
		if (type.endsWith(")")) {
			type = type.substring(type.indexOf("(") + 1, type.length() - 1);
			if(type.contains(",")){
				type = type.substring(type.indexOf(",") + 1, type.length());
			}else{
				type = "0";
			}
			return type;
		} else {
			return "0";
		}
	}

	public List<BeanProperty> getBeanPropertys(String tableName) throws Exception{
		List<BeanProperty>beanPropertys=new ArrayList<BeanProperty>();
			List<DescTableBean> listd = descTable(tableName);//查询表结构
			for(DescTableBean d : listd){
				BeanProperty beanProperty=new BeanProperty();
				beanProperty.setFieldName(handleColumnName(d.getField()));
				beanProperty.setFieldType(d.getType());
				beanProperty.setStrategy(d.getExtra());
				beanProperty.setMethodFieldName(handleColumnName(d.getField()));
				if(d.getKey().equals("PRI")){
					beanProperty.setKey(true);
				}
				if(d.getType().equals("date")){
					beanProperty.setHasDate(true);
				}
				if(d.getType().equals("datetime")){
					beanProperty.setHasDateTime(true);
				}
				beanPropertys.add(beanProperty);
			}
		return beanPropertys;
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println(TableExist("ssh","user"));
	}
}
