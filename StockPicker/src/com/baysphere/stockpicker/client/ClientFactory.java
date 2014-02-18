package com.baysphere.stockpicker.client;

import java.util.ArrayList;

import com.baysphere.stockpicker.client.services.IndexListServiceAsync;
import com.baysphere.stockpicker.client.services.StockServiceAsync;
import com.baysphere.stockpicker.client.views.MainPanelView;
import com.baysphere.stockpicker.client.views.ShowIndexView;
import com.baysphere.stockpicker.client.views.ShowStocksView;
import com.baysphere.stockpicker.client.views.ShowWeightsView;
import com.baysphere.stockpicker.shared.CriteriaTableSubset;
import com.baysphere.stockpicker.shared.WeightTableSubset;
import com.google.gwt.event.shared.EventBus;

public interface ClientFactory {
	public EventBus getEventBus();
	public MainPanelView getMainPanelView();
	public ShowStocksView getShowStocksView();
	public ShowIndexView getShowIndexView();
	public ShowWeightsView getShowWeightsView();
	public StockServiceAsync getStockService();
	public IndexListServiceAsync getIndexListService();
	public ArrayList<String> getIndexes();
	public ArrayList<CriteriaTableSubset> getCriterias();
	public ArrayList<WeightTableSubset> getWeights();
	public ArrayList<String> getCalculations();
	public void setIndexes (ArrayList<String> strings);
	public void setCriterias (ArrayList<CriteriaTableSubset> ctSub);
	public void setWeights (ArrayList<WeightTableSubset> wtSub);
	public void setCalculations (ArrayList<String> calculations);
}
