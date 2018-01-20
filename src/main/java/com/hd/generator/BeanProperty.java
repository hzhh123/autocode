package com.hd.generator;

public class BeanProperty {
	private String fieldName;//字段名
	private String methodFieldName;//get、set方法时字段名首字母大写
	private String  fieldType;//字段类型
	private boolean  key;//是否是主键
	private String  strategy;//生成策略
	private boolean hasDate;//是否在日期上加注解
	private boolean hasDateTime;
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		if(fieldType.equals("int")){
			this.fieldType ="Integer";
		}
		if(fieldType.equals("bigint")){
			this.fieldType ="Long";
		}
		if(fieldType.equals("varchar")||fieldType.equals("text")){
			this.fieldType ="String";
		}
		if(fieldType.equals("date")||fieldType.equals("datetime")){
			this.fieldType ="Date";
		}
	}
	public boolean isKey() {
		return key;
	}
	public void setKey(boolean key) {
		this.key = key;
	}
	public String getStrategy() {
		return strategy;
	}
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	public boolean isHasDate() {
		return hasDate;
	}
	public void setHasDate(boolean hasDate) {
		this.hasDate = hasDate;
	}
	
	public boolean isHasDateTime() {
		return hasDateTime;
	}
	public void setHasDateTime(boolean hasDateTime) {
		this.hasDateTime = hasDateTime;
	}
	
	public String getMethodFieldName() {
		return methodFieldName;
	}
	public void setMethodFieldName(String methodFieldName) {
		this.methodFieldName = methodFieldName.substring(0,1).toUpperCase()+methodFieldName.substring(1,methodFieldName.length());
	}
	
	
	
	
	
	
	
	
}
