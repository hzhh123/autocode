/**
 * 
 */
package com.hd.generator;

/**
 * 在数据库中，我们可以用sql语句："desc table_name"，查询名称为："table_name"的表的结构情况<br>
 * 这里就是
 */
public class DescTableBean {

	/**
	 * id编号
	 */
	private Integer id;
	/**
	 * 属性,eg:<code>name</code>
	 */
	private String field;
	/**
	 * 类型,eg:<code>varchar</code>,<code>datetime</code>,<code>double</code>,<code>longtext</code>
	 */
	private String type;
	/**
	 * 长度，如果没有规定长度的，则默认为255
	 */
	private Integer length = 255;
	/**
	 * 十进位，默认为0，针对<code>decimal</code>
	 */
	private Integer decase;
	/**
	 * 是否为空，只有两个选择:YES,NO,eg:<code>YES</code>
	 */
	private String isNull;
	/**
	 * 是否为主键，如果为主键则， key = <code>PRI</code>，如果唯一，则 key = <code>UNI</code>
	 * ，如果为外键，则 key = <code>MUL</code>
	 */
	private String key;
	/**
	 * 属性的默认值
	 */
	private String defaultValue;
	/**
	 * 其他选项，如一个表的id的增长方式为：auto_increment
	 */
	private String extra;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public Integer getDecase() {
		return decase;
	}

	public void setDecase(Integer decase) {
		this.decase = decase;
	}

	public DescTableBean(Integer id, String field, String type, Integer length, Integer decase, String isNull,
			String key, String defaultValue, String extra) {
		super();
		this.id = id;
		this.field = field;
		this.type = type;
		this.length = length;
		this.decase = decase;
		this.isNull = isNull;
		this.key = key;
		this.defaultValue = defaultValue;
		this.extra = extra;
	}

	public DescTableBean() {
		super();
	}
	
}
