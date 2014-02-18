package com.baysphere.stockpicker.server;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable (identityType = IdentityType.APPLICATION)
public class Index {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	String name;
	@Persistent
	Date createDate;
	@Persistent
	private CriteriaTable criteriaTable;
	@Persistent
	private WeightTable weightTable;
	@Persistent
	private String calculation;
	@Persistent
	double accuracy;
	
	public CriteriaTable getCriteriaTable() {
		return criteriaTable;
	}
	public void setCriteriaTable(CriteriaTable criteriaTable) {
		this.criteriaTable = criteriaTable;
	}
	public WeightTable getWeightTable() {
		return weightTable;
	}
	public void setWeightTable(WeightTable weightTabe) {
		this.weightTable = weightTabe;
	}
	public String getCalculation() {
		return calculation;
	}
	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public double getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

}
