package com.hd.generator;

import java.util.ArrayList;
import java.util.List;
public class Bean {

	/** bean 名称 */
	private String name;
	/** bean 首字母小写名称 */
	private String lowerName;
	/** bean 路径 */
	private String beanUrl;
	/** dao 路径 */
	private String beanDaoUrl;
	/** dao 实现路径 */
	private String beanDaoImplUrl;
	/** service 路径 */
	private String beanServiceUrl;
	/** service 实现路径 */
	private String beanServiceImplUrl;
	/** web Action 路径 */
	private String webActionUrl;
	/** web Admin Action 路径 */
	private String webAdminActionUrl;

	private List<Class> beanDaoList = new ArrayList<Class>();
	private List<Class> beanDaoImplList = new ArrayList<Class>();
	private List<Class> beanServiceList = new ArrayList<Class>();
	private List<Class> beanServiceImplList = new ArrayList<Class>();

	private static List<String> beanDaoUrlList = new ArrayList<String>();
	private static List<String> beanDaoImplUrlList = new ArrayList<String>();
	private static List<String> beanServiceUrlList = new ArrayList<String>();
	private static List<String> beanServiceImplUrlList = new ArrayList<String>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLowerName() {
		return lowerName;
	}

	public void setLowerName(String lowerName) {
		this.lowerName = lowerName;
	}

	public String getBeanUrl() {
		return beanUrl;
	}

	public void setBeanUrl(String beanUrl) {
		this.beanUrl = beanUrl;
	}

	public String getBeanDaoUrl() {
		return beanDaoUrl;
	}

	public void setBeanDaoUrl(String beanDaoUrl) {
		this.beanDaoUrl = beanDaoUrl;
	}

	public String getBeanDaoImplUrl() {
		return beanDaoImplUrl;
	}

	public void setBeanDaoImplUrl(String beanDaoImplUrl) {
		this.beanDaoImplUrl = beanDaoImplUrl;
	}

	public String getBeanServiceUrl() {
		return beanServiceUrl;
	}

	public void setBeanServiceUrl(String beanServiceUrl) {
		this.beanServiceUrl = beanServiceUrl;
	}

	public String getBeanServiceImplUrl() {
		return beanServiceImplUrl;
	}

	public void setBeanServiceImplUrl(String beanServiceImplUrl) {
		this.beanServiceImplUrl = beanServiceImplUrl;
	}

	public String getWebActionUrl() {
		return webActionUrl;
	}

	public void setWebActionUrl(String webActionUrl) {
		this.webActionUrl = webActionUrl;
	}

	public List<Class> getBeanDaoList() {
		return beanDaoList;
	}

	public void setBeanDaoList(List<Class> beanDaoList) {
		this.beanDaoList = beanDaoList;
	}

	public List<Class> getBeanDaoImplList() {
		return beanDaoImplList;
	}

	public void setBeanDaoImplList(List<Class> beanDaoImplList) {
		this.beanDaoImplList = beanDaoImplList;
	}

	public List<Class> getBeanServiceList() {
		return beanServiceList;
	}

	public void setBeanServiceList(List<Class> beanServiceList) {
		this.beanServiceList = beanServiceList;
	}

	public List<Class> getBeanServiceImplList() {
		return beanServiceImplList;
	}

	public void setBeanServiceImplList(List<Class> beanServiceImplList) {
		this.beanServiceImplList = beanServiceImplList;
	}

	public static List<String> getBeanDaoUrlList() {
		return beanDaoUrlList;
	}

	public static void setBeanDaoUrlList(List<String> beanDaoUrlList) {
		Bean.beanDaoUrlList = beanDaoUrlList;
	}

	public static List<String> getBeanDaoImplUrlList() {
		return beanDaoImplUrlList;
	}

	public static void setBeanDaoImplUrlList(List<String> beanDaoImplUrlList) {
		Bean.beanDaoImplUrlList = beanDaoImplUrlList;
	}

	public static List<String> getBeanServiceUrlList() {
		return beanServiceUrlList;
	}

	public static void setBeanServiceUrlList(List<String> beanServiceUrlList) {
		Bean.beanServiceUrlList = beanServiceUrlList;
	}

	public static List<String> getBeanServiceImplUrlList() {
		return beanServiceImplUrlList;
	}

	public static void setBeanServiceImplUrlList(List<String> beanServiceImplUrlList) {
		Bean.beanServiceImplUrlList = beanServiceImplUrlList;
	}

	public String getWebAdminActionUrl() {
		return webAdminActionUrl;
	}

	public void setWebAdminActionUrl(String webAdminActionUrl) {
		this.webAdminActionUrl = webAdminActionUrl;
	}

}
