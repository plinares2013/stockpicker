package com.baysphere.stockpicker.shared;

import com.baysphere.stockpicker.server.CriteriaTable;
import com.baysphere.stockpicker.server.WeightTable;
import com.google.gwt.user.client.rpc.IsSerializable;

public class IndexInformation implements IsSerializable {
	private String name;
	private CriteriaTableSubset criteriaTableSubset;
	private WeightTableSubset weightTableSubset;
	private String calculationMode;
	private double accuracy;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCalculationMode() {
		return calculationMode;
	}
	public void setCalculationMode(String calculationMode) {
		this.calculationMode = calculationMode;
	}
	public double getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
	public CriteriaTableSubset getCriteriaTableSubset() {
		return criteriaTableSubset;
	}
	public void setCriteriaTableSubset(CriteriaTableSubset criteriaTableSubset) {
		this.criteriaTableSubset = criteriaTableSubset;
	}
	public WeightTableSubset getWeightTableSubset() {
		return weightTableSubset;
	}
	public void setWeightTableSubset(WeightTableSubset weightTableSubset) {
		this.weightTableSubset = weightTableSubset;
	}
}
