package com.hd.generator;

import java.util.List;

public class GeneratorCode {
	public static void main(String[] args) throws Exception {
		String tableNames[]={"resource","user_role"};
		autoCode(tableNames);
	}

	private static void autoCode(String[] tableNames) throws Exception {
		GeneratorEntity entity = new GeneratorEntity();//生成实体类
		GeneratorService service=new GeneratorService();//生成Service
		GeneratorServiceImpl serviceimpl=new GeneratorServiceImpl();//生成ServiceImpl
		GeneratorController controller=new GeneratorController();//生成Controller
		GeneratorWebAdd add=new GeneratorWebAdd();
		GeneratorWebEdit edit=new GeneratorWebEdit();
		GeneratorWebView view=new GeneratorWebView();
		GeneratorWebList list=new GeneratorWebList();
		DBUtil dbUtil = new DBUtil();
		for(String tableName:tableNames){
			List<BeanProperty>beanProperties=dbUtil.getBeanPropertys(tableName);
			System.out.println(beanProperties);
			entity.generateBean(tableName, beanProperties);
			service.generateBean(tableName, beanProperties);
			serviceimpl.generateBean(tableName, beanProperties);
			controller.generateBean(tableName, beanProperties);
			add.generateBean(tableName, beanProperties);
			edit.generateBean(tableName, beanProperties);
			view.generateBean(tableName, beanProperties);
			list.generateBean(tableName, beanProperties);
		}
	}
}
