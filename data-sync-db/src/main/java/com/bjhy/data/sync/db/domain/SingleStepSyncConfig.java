package com.bjhy.data.sync.db.domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bjhy.data.sync.db.inter.face.OwnInterface.SingleStepListener;

/**
 * 单个步骤同步配置参数
 * @author wubo
 */
public class SingleStepSyncConfig {
	
	/**
	 * 系统检测同步
	 */
	public static final String START_STEP_SYSTEM_CHECK_SYNC = "systemCheckSync";
	
	/**
	 * 用户同步
	 */
	public static final String START_STEP_USER_SYNC = "userSync";
	
	/**
	 * 当个数据源运行的Entity
	 */
	private SingleRunEntity singleRunEntity;
	
	/**
	 * 启动同步步骤方式
	 */
	private String startStepSyncType = START_STEP_USER_SYNC;
	
	/**
	 * 步骤唯一标示
	 */
	private String stepUniquelyIdentifies;

	/**
	 * 来源sql的from到最后
	 */
	private String fromFromPart;
	
	/**
	 * 来源Sql的select到from之间的部分
	 */
	private String fromSelectPart;
	
	/**
	 * 目标表表名
	 */
	private String toTableName;
	
	/**
	 * 添加(来源)静态列-值,意思不是要在来源(fromSql)的添加,而是把这种配置的静态列也当成来源数据
	 */
	private Map<String,Object> addStaticFromColumns = new LinkedHashMap<String,Object>();
	
	
	/**
	 * 剔除(来源)不需要的字段
	 */
	private List<String> removeFromColumns = new ArrayList<String>();
	
	/**
	 * 是否多线程分页
	 */
	private Boolean isMultiThreadPage = true;
	
	/**
	 * 高性能的分页列
	 */
	private String highPerformancePageColumn;
	
	/**
	 * 是否只同步一次
	 * 该参数为false则表示无限次同步,true表示仅同步一次,该参数的使用必须配合配置文件中的 sync.is.this.only.one的属性
	 */
	private Boolean isThisOnlyOneSync = false;
	
	/**
	 * 是否同步null值,true表示同步空值,否则反之
	 */
	private Boolean isSyncNullValue = true;
	
	/**
	 * 是否添加版本检测过滤数据
	 */
	private Boolean isAddVersionCheckFilter = true;
	
	/**
	 * 更新字段,通过该字段去更新已经存在的数据
	 */
	private String updateColumn;
	
	/**
	 * 该字段配置的update的Where后面的语句,表示要更新的条件
	 */
	private String updateWhere;
	
	/**
	 * 校验目标表是用到的where语句 : 该语句主要是用于系统校验是用的
	 */
	private String toValidationWhere;
	
	/**
	 * 同步分行实体: 将同步字段量非常多的实体进行拆分,先插入主字段后采用多线程的方式进行进行更新后面的字段值
	 * 例如:user(column1,column2,...,column300) 假设 column1,column2是主字段,
	 * 而数据库中又不存在改行的值,就先进行查询,然后采用多线程的方式更新其他值
	 */
	private SyncPageRowEntity syncPageRowEntity;
	
	/**
	 * 单个步骤的同步监听器
	 */
	private SingleStepListener singleStepListener;
	
	/**
	 * 采用反射生成监听器的 监听器名称
	 */
	private String SingleStepListenerName;
	
	/**
	 * 得到fromSql语句
	 * @return
	 */
	public String getFromSql(){
		StringBuffer fromSql = new StringBuffer();
		if(StringUtils.isEmpty(fromSelectPart)){
			fromSql.append("SELECT *");
		}else{
			fromSql.append(fromSelectPart);
		}
		fromSql.append(" "+fromFromPart);
		return fromSql.toString();
	}
	
	/**
	 * 得到fromCount语句
	 * @return
	 */
	public String getFromCountSql(){
		return "SELECT COUNT(1) NUM_ "+fromFromPart;
	}
	
	/**
	 * 设置单个步骤的监听
	 * @param singleStepListener
	 */
	public void setSingleStepListener(SingleStepListener singleStepListener) {
		this.singleStepListener = singleStepListener;
	}
	
	public SingleStepListener getSingleStepListener() {
		return singleStepListener;
	}

	public SingleRunEntity getSingleRunEntity() {
		return singleRunEntity;
	}

	public void setSingleRunEntity(SingleRunEntity singleRunEntity) {
		this.singleRunEntity = singleRunEntity;
	}
	
	public String getFromFromPart() {
		return fromFromPart;
	}

	public void setFromFromPart(String fromFromPart) {
		this.fromFromPart = fromFromPart;
	}

	public String getFromSelectPart() {
		return fromSelectPart;
	}

	public void setFromSelectPart(String fromSelectPart) {
		this.fromSelectPart = fromSelectPart;
	}

	public String getToTableName() {
		return toTableName;
	}

	public void setToTableName(String toTableName) {
		this.toTableName = toTableName;
	}

	public Map<String, Object> getAddStaticFromColumns() {
		return addStaticFromColumns;
	}

	public void setAddStaticFromColumns(Map<String, Object> addStaticFromColumns) {
		this.addStaticFromColumns = addStaticFromColumns;
	}

	public Boolean getIsMultiThreadPage() {
		return isMultiThreadPage;
	}

	public void setIsMultiThreadPage(Boolean isMultiThreadPage) {
		this.isMultiThreadPage = isMultiThreadPage;
	}

	public Boolean getIsThisOnlyOneSync() {
		return isThisOnlyOneSync;
	}

	public void setIsThisOnlyOneSync(Boolean isThisOnlyOneSync) {
		this.isThisOnlyOneSync = isThisOnlyOneSync;
	}

	public String getUpdateColumn() {
		return updateColumn;
	}

	public void setUpdateColumn(String updateColumn) {
		this.updateColumn = updateColumn;
	}

	public String getUpdateWhere() {
		return updateWhere;
	}

	public void setUpdateWhere(String updateWhere) {
		this.updateWhere = updateWhere;
	}

	public Boolean getIsSyncNullValue() {
		return isSyncNullValue;
	}

	public void setIsSyncNullValue(Boolean isSyncNullValue) {
		this.isSyncNullValue = isSyncNullValue;
	}

	public List<String> getRemoveFromColumns() {
		return removeFromColumns;
	}

	public void setRemoveFromColumns(List<String> removeFromColumns) {
		this.removeFromColumns = removeFromColumns;
	}

	public String getHighPerformancePageColumn() {
		return highPerformancePageColumn;
	}

	public void setHighPerformancePageColumn(String highPerformancePageColumn) {
		this.highPerformancePageColumn = highPerformancePageColumn;
	}

	public Boolean getIsAddVersionCheckFilter() {
		return isAddVersionCheckFilter;
	}

	public void setIsAddVersionCheckFilter(Boolean isAddVersionCheckFilter) {
		this.isAddVersionCheckFilter = isAddVersionCheckFilter;
	}

	public String getStepUniquelyIdentifies() {
		return stepUniquelyIdentifies;
	}

	public void setStepUniquelyIdentifies(String stepUniquelyIdentifies) {
		this.stepUniquelyIdentifies = stepUniquelyIdentifies;
	}

	public String getStartStepSyncType() {
		return startStepSyncType;
	}

	public void setStartStepSyncType(String startStepSyncType) {
		this.startStepSyncType = startStepSyncType;
	}

	public String getSingleStepListenerName() {
		return SingleStepListenerName;
	}

	public void setSingleStepListenerName(String singleStepListenerName) {
		SingleStepListenerName = singleStepListenerName;
	}

	public String getToValidationWhere() {
		return toValidationWhere;
	}

	public void setToValidationWhere(String toValidationWhere) {
		this.toValidationWhere = toValidationWhere;
	}

	public SyncPageRowEntity getSyncPageRowEntity() {
		return syncPageRowEntity;
	}

	public void setSyncPageRowEntity(SyncPageRowEntity syncPageRowEntity) {
		this.syncPageRowEntity = syncPageRowEntity;
	}
}
