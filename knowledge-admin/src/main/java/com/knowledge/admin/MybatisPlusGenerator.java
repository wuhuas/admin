package com.knowledge.admin;

import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MybatisPlusGenerator {

	public static void main(String[] args) {
		AutoGenerator mpg = new AutoGenerator();
		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir("d://SHOP//");
		gc.setFileOverride(true);
		gc.setActiveRecord(true);
		gc.setEnableCache(true);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(true);// XML columList
		gc.setAuthor("jide");

		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		gc.setMapperName("%sDao");
		gc.setXmlName("%sMapper");
		gc.setServiceName("I%sService");
		gc.setServiceImplName("%sServiceImpl");
		gc.setControllerName("%sController");
		mpg.setGlobalConfig(gc);

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL);
		/*
		 * dsc.setTypeConvert(new MySqlTypeConvert(){ // 自定义数据库表字段类型转换【可选】
		 * 
		 * @Override public DbColumnType processTypeConvert(String fieldType) {
		 * System.out.println("转换类型：" + fieldType); return
		 * super.processTypeConvert(fieldType); } });
		 */

		dsc.setDriverName("com.mysql.jdbc.Driver");
		dsc.setUsername("root");
		dsc.setPassword("Loogk12346");
		dsc.setUrl("jdbc:mysql://119.23.201.55:3307/knowledge");
		mpg.setDataSource(dsc);

		// 策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
		//strategy.setTablePrefix(new String[] { "TB_" });// 此处可以修改为您的表前缀
		strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		strategy.setInclude(new String[] { "tb_notice"}); // 需要生成的表
		String moduleName="notice";
		mpg.setStrategy(strategy);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent("com.knowledge.common.business");
		pc.setModuleName(moduleName);
		mpg.setPackageInfo(pc);
		
		
		// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				this.setMap(map);
			}
		};
		mpg.setCfg(cfg);
		
		//设置生成代码模板
		TemplateConfig tc = new TemplateConfig();
		tc.setController("/vm/controller.vm");
		tc.setXml("/vm/mapper.vm");
		tc.setMapper("vm/dao.vm");
		tc.setEntity("vm/entity.vm");
		mpg.setTemplate(tc);

		// 执行生成
		mpg.execute();
	}
}
