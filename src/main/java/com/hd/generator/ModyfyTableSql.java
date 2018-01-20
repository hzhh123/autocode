package com.hd.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 仅仅支持mysql
 * 拼凑建表、修改表字段、修改字段属性、添加字段、删除字段语句
 * @author hzhh123
 *
 */
public class ModyfyTableSql {
	
	//显示所有表
	public static String showTablesSql(){
		String sql="show tables";
		return sql;
	}
	
	//查看表
	public static String showTableSql(String tableName){
		String sql="desc "+tableName;
		return sql;
	}
	//创建表的sql语句
	public static String createTableSql(String tableName,List<DescTableBean> descTableBeans){
		String sql="DROP TABLE IF EXISTS "+tableName+";"
				+ "CREATE TABLE "+tableName+"(";
		String primary_key="";
		for(DescTableBean descTableBean:descTableBeans){
			if(descTableBean.getType().equals("date")||descTableBean.getType().equals("datetime")){
				sql+=descTableBean.getField()+"  "+descTableBean.getType();
			}else{
				sql+=descTableBean.getField()+"  "+descTableBean.getType()+"("+descTableBean.getLength()+")";
			}
			if(descTableBean.getIsNull().equals("NO")){
			sql+=" not null ";
			}
			if(descTableBean.getKey().equals("PRI")){
				primary_key+=descTableBean.getField()+",";
			}
			if(descTableBean.getDefaultValue()!=null&&!descTableBean.getDefaultValue().equals("")){
				sql+=" default '"+descTableBean.getDefaultValue()+"'";
			}
			if(descTableBean.getExtra()!=null&&!descTableBean.getExtra().equals("")){
				sql+=" "+descTableBean.getExtra();
			}
			sql+=",";
		}
		primary_key=primary_key.substring(0, primary_key.lastIndexOf(","));
		if(!primary_key.equals("")){
		sql+=" PRIMARY KEY("+primary_key+")";
		}
		sql+=")ENGINE=InnoDB DEFAULT CHARSET=utf8";
		return sql;
	}
	//修改列
	public static String updateColumnSql(String tableName,List<DescTableBean> oldescTableBeans,List<DescTableBean> newdescTableBeans){
		String result="";
		for(int i=0;i<newdescTableBeans.size();i++){
			String sql="alter table "+tableName+" change";
			sql+=" "+oldescTableBeans.get(i).getField()+" "+newdescTableBeans.get(i).getField();
			if(newdescTableBeans.get(i).getType().equals("date")||newdescTableBeans.get(i).getType().equals("datetime")){
				sql+=" "+newdescTableBeans.get(i).getType();
			}else{
				sql+=" "+newdescTableBeans.get(i).getType()+"("+newdescTableBeans.get(i).getLength()+")";
			}
			if(newdescTableBeans.get(i).getIsNull().equals("NO")){
				sql+=" not null";
			}
			if(newdescTableBeans.get(i).getDefaultValue()!=null&&!newdescTableBeans.get(i).getDefaultValue().equals("")){
					sql+=" default '"+newdescTableBeans.get(i).getDefaultValue()+"'";
			 }
			sql+=";";
			result+=sql;
		}
		return result;
	}
	//添加列
	public static String addColumnSql(String tableName,List<DescTableBean> descTableBeans){
		String result="";
		for(DescTableBean descTableBean:descTableBeans){
			String sql="alter table "+tableName+" add";
			sql+=" "+descTableBean.getField();
			if(descTableBean.getType().equals("date")||descTableBean.getType().equals("datetime")){
				sql+=" "+descTableBean.getType();
			}else{
				sql+=" "+descTableBean.getType()+"("+descTableBean.getLength()+")";
			}
			if(descTableBean.getIsNull().equals("NO")){
				sql+=" not null";
			}
			if(descTableBean.getDefaultValue()!=null&&!descTableBean.getDefaultValue().equals("")){
					sql+=" default '"+descTableBean.getDefaultValue()+"'";
			 }
			sql+=";";
			result+=sql;
		}
		return result;
	}
	//删除列
	public static String deleteColumnSql(String tableName,List<DescTableBean> descTableBeans){
		String result="";
		for(DescTableBean descTableBean:descTableBeans){
			String sql="alter table "+tableName+" drop column "+descTableBean.getField();
			sql+=";";
			result+=sql;
		}
		return result;
	}
	//alter table 表名 add constraint 主键 （形如：PK_表名） primary key 表名(主键字段);
	public static String addPKSql(String tableName,List<DescTableBean> descTableBeans){
		String result="";
		for(DescTableBean descTableBean:descTableBeans){
			String sql="alter table "+tableName+" drop primary key;alter table "+tableName+" add constraint PK_"+new Random().nextInt(1000)+" primary key "+tableName+"("+descTableBean.getField()+")";
			sql+=";";
			result+=sql;
		}
		return result;
	}
	
	public static String updateTableNameSql(String oldTableName,String newTableName){
		String sql="alter table "+oldTableName+" rename to "+newTableName;
		return sql;
	}
	public static void main(String[] args) {
		//testCreateTableSql();
		//testAddColumnSql();
		//testUpdateColumnSql();
		//testDeleteColumn();
		//testAddPK();
		testUpdateTableName();
	}

	private static void testUpdateTableName() {
		System.out.println(updateTableNameSql("resource","rean"));
		
	}
	private static void testAddPK() {
		List<DescTableBean> oldescTableBeans=new ArrayList<DescTableBean>();
		oldescTableBeans.add(new DescTableBean(1, "name", "varchar", 25, 0, "NO", "", "", ""));
		String sql=addPKSql("resource",oldescTableBeans);
		System.out.println(sql);
		
	}
	private static void testDeleteColumn() {
		List<DescTableBean> oldescTableBeans=new ArrayList<DescTableBean>();
		oldescTableBeans.add(new DescTableBean(1, "name", "varchar", 25, 0, "NO", "", "", ""));
		String sql=deleteColumnSql("resource",oldescTableBeans);
		System.out.println(sql);
	}
	private static void testUpdateColumnSql() {
		List<DescTableBean> oldescTableBeans=new ArrayList<DescTableBean>();
		oldescTableBeans.add(new DescTableBean(1, "name", "varchar", 25, 0, "NO", "", "", ""));
		oldescTableBeans.add(new DescTableBean(1, "birth", "date", 25, 0, "NO", "", "", ""));
		
		List<DescTableBean> newdescTableBeans=new ArrayList<DescTableBean>();
		newdescTableBeans.add(new DescTableBean(1, "name2", "varchar", 25, 0, "YES", "", "", ""));
		newdescTableBeans.add(new DescTableBean(1, "birth", "varchar", 25, 0, "NO", "", "1", ""));
		String sql=updateColumnSql("project",oldescTableBeans,newdescTableBeans);
		System.out.println(sql);
		
	}
	private static void testAddColumnSql() {
		List<DescTableBean> descTableBeans=new ArrayList<DescTableBean>();
		descTableBeans.add(new DescTableBean(1, "name", "varchar", 25, 0, "NO", "", "", ""));
		descTableBeans.add(new DescTableBean(1, "birth", "date", 25, 0, "NO", "", "", ""));
		String sql=addColumnSql("project",descTableBeans);
		System.out.println(sql);
		
	}
	private static void testCreateTableSql() {
		List<DescTableBean> descTableBeans=new ArrayList<DescTableBean>();
		descTableBeans.add(new DescTableBean(1, "id", "int", 11, 0, "NO", "PRI", "", "auto_increment"));
		descTableBeans.add(new DescTableBean(2, "name", "varchar", 25, 0, "YES", "", "", ""));
		descTableBeans.add(new DescTableBean(2, "birthday", "date", 0, 0, "YES", "", "", ""));
		descTableBeans.add(new DescTableBean(2, "create_time", "datetime", 0, 0, "YES", "", "", ""));
		String sql=createTableSql("project",descTableBeans);
		System.out.println(sql);
	}
}
