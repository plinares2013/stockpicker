package com.baysphere.stockpicker.shared;

import com.google.gwt.user.client.rpc.IsSerializable;


public class WeightTableSubset implements IsSerializable {
	private double PriceSalesWeight;	
	private double PriceBookWeight;	
	private double ProfitMarginWeight;	
	private double ROEWeight;	
	private double ROAWeight;	
	private double DebtEquityWeight;	
	private double QuickWeight;	
	private double CurrentWeight;	
	private double OneYearEPSWeight;	
	private double ThreeYearEPSWeight;	
	private double FiveYearEPSWeight;	
	private double FreeCashFlowPerShareWeight;	
	private double ManagementOwnershipWeight;		
	private double QuarterlyEPSWeight;		
	private double PEGRatioWeight;	
	private double EPSQuarterlyGrowthYoYWeight;	
	private double PERatioWeight;
	
	public double getPriceSalesWeight() {
		return PriceSalesWeight;
	}
	public void setPriceSalesWeight(double priceSalesWeight) {
		PriceSalesWeight = priceSalesWeight;
	}
	public double getPriceBookWeight() {
		return PriceBookWeight;
	}
	public void setPriceBookWeight(double priceBookWeight) {
		PriceBookWeight = priceBookWeight;
	}
	public double getProfitMarginWeight() {
		return ProfitMarginWeight;
	}
	public void setProfitMarginWeight(double profitMarginWeight) {
		ProfitMarginWeight = profitMarginWeight;
	}
	public double getROEWeight() {
		return ROEWeight;
	}
	public void setROEWeight(double rOEWeight) {
		ROEWeight = rOEWeight;
	}
	public double getROAWeight() {
		return ROAWeight;
	}
	public void setROAWeight(double rOAWeight) {
		ROAWeight = rOAWeight;
	}
	public double getDebtEquityWeight() {
		return DebtEquityWeight;
	}
	public void setDebtEquityWeight(double debtEquityWeight) {
		DebtEquityWeight = debtEquityWeight;
	}
	public double getQuickWeight() {
		return QuickWeight;
	}
	public void setQuickWeight(double quickWeight) {
		QuickWeight = quickWeight;
	}
	public double getCurrentWeight() {
		return CurrentWeight;
	}
	public void setCurrentWeight(double currentWeight) {
		CurrentWeight = currentWeight;
	}
	public double getOneYearEPSWeight() {
		return OneYearEPSWeight;
	}
	public void setOneYearEPSWeight(double oneYearEPSWeight) {
		OneYearEPSWeight = oneYearEPSWeight;
	}
	public double getThreeYearEPSWeight() {
		return ThreeYearEPSWeight;
	}
	public void setThreeYearEPSWeight(double threeYearEPSWeight) {
		ThreeYearEPSWeight = threeYearEPSWeight;
	}
	public double getFiveYearEPSWeight() {
		return FiveYearEPSWeight;
	}
	public void setFiveYearEPSWeight(double fiveYearEPSWeight) {
		FiveYearEPSWeight = fiveYearEPSWeight;
	}
	public double getFreeCashFlowPerShareWeight() {
		return FreeCashFlowPerShareWeight;
	}
	public void setFreeCashFlowPerShareWeight(double freeCashFlowPerShareWeight) {
		FreeCashFlowPerShareWeight = freeCashFlowPerShareWeight;
	}
	public double getManagementOwnershipWeight() {
		return ManagementOwnershipWeight;
	}
	public void setManagementOwnershipWeight(double managementOwnershipWeight) {
		ManagementOwnershipWeight = managementOwnershipWeight;
	}
	public double getQuarterlyEPSWeight() {
		return QuarterlyEPSWeight;
	}
	public void setQuarterlyEPSWeight(double quarterlyEPSWeight) {
		QuarterlyEPSWeight = quarterlyEPSWeight;
	}
	public double getPEGRatioWeight() {
		return PEGRatioWeight;
	}
	public void setPEGRatioWeight(double pEGRatioWeight) {
		PEGRatioWeight = pEGRatioWeight;
	}
	public double getEPSQuarterlyGrowthYoYWeight() {
		return EPSQuarterlyGrowthYoYWeight;
	}
	public void setEPSQuarterlyGrowthYoYWeight(double ePSQuarterlyGrowthYoYWeight) {
		EPSQuarterlyGrowthYoYWeight = ePSQuarterlyGrowthYoYWeight;
	}
	public double getPERatioWeight() {
		return PERatioWeight;
	}
	public void setPERatioWeight(double pERatioWeight) {
		PERatioWeight = pERatioWeight;
	}
}
