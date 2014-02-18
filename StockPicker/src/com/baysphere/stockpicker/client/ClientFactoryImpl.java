/**
 * 
 */
package com.baysphere.stockpicker.client;

import java.util.ArrayList;

import com.baysphere.stockpicker.client.services.IndexListService;
import com.baysphere.stockpicker.client.services.IndexListServiceAsync;
import com.baysphere.stockpicker.client.services.StockService;
import com.baysphere.stockpicker.client.services.StockServiceAsync;
import com.baysphere.stockpicker.client.views.MainPanelView;
import com.baysphere.stockpicker.client.views.ShowIndexView;
import com.baysphere.stockpicker.client.views.ShowStocksView;
import com.baysphere.stockpicker.client.views.ShowWeightsView;
import com.baysphere.stockpicker.client.views.uibinder.MainPanelViewImpl;
import com.baysphere.stockpicker.client.views.uibinder.ShowIndexViewImpl;
import com.baysphere.stockpicker.client.views.uibinder.ShowStocksViewImpl;
import com.baysphere.stockpicker.client.views.uibinder.ShowWeightsViewImpl;
import com.baysphere.stockpicker.shared.CriteriaTableSubset;
import com.baysphere.stockpicker.shared.WeightTableSubset;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;


/**
 * @author Paul
 *
 */
public class ClientFactoryImpl implements ClientFactory {

	/* (non-Javadoc)
	 * @see com.baysphere.stockpicker.client.ClientFactory#getEventBus()
	 */
	
	private static EventBus eventBus;
	private static MainPanelView mainPanelView;
	private static ShowStocksView showStocksView;
	private static ShowIndexView showIndexView;
	private static ShowWeightsView showWeightsView;
	private static StockServiceAsync stockService;
	private static IndexListServiceAsync indexListService;
	
	private static ArrayList<String> indexes;
	private ArrayList<CriteriaTableSubset> criterias;
	private ArrayList<WeightTableSubset> weights;
	private ArrayList<String> calculations;
	
	@Override
	public EventBus getEventBus() {
		if (eventBus == null) eventBus = new SimpleEventBus();
		return eventBus;
	}
	
	@Override
	public StockServiceAsync getStockService() {
		if (stockService == null)  stockService = GWT.create(StockService.class);
		return stockService;
	}
	
	@Override
	public IndexListServiceAsync getIndexListService() {
		if (indexListService == null) indexListService = GWT.create(IndexListService.class);
		return indexListService;
	}

	/* (non-Javadoc)
	 * @see com.baysphere.stockpicker.client.ClientFactory#getMainPanelView()
	 */
	@Override
	public MainPanelView getMainPanelView() {
		if (mainPanelView == null) mainPanelView = new MainPanelViewImpl();
		return mainPanelView;
	}

	@Override
	public ShowStocksView getShowStocksView() {
		if (showStocksView == null) showStocksView = new ShowStocksViewImpl();
		return showStocksView;
	}
	
	@Override
	public ShowIndexView getShowIndexView() {
		if (showIndexView == null) showIndexView = new ShowIndexViewImpl();
		return showIndexView;
	}
	
	@Override
	public ShowWeightsView getShowWeightsView() {
		if (showWeightsView == null) showWeightsView = new ShowWeightsViewImpl();
		return showWeightsView;
	}
	
	@Override
	public ArrayList<String> getIndexes() {
		if (indexes==null) indexes = new ArrayList<String> ();
		return indexes;
	}
	
	@Override
	public ArrayList<CriteriaTableSubset> getCriterias() {
		if (criterias == null) criterias = new ArrayList<CriteriaTableSubset> ();
		return criterias;
	}
	
	@Override
	public ArrayList<WeightTableSubset> getWeights() {
		if (weights == null) weights = new ArrayList<WeightTableSubset> () ;
		return weights;
	}
	
	@Override
	public ArrayList<String> getCalculations() {
		if (calculations == null) calculations = new ArrayList<String> ();
		return calculations;
	}
	
	@Override
	public void setIndexes (ArrayList<String> indexes) {
		this.indexes = indexes;
	}
	
	@Override
	public void setCriterias (ArrayList<CriteriaTableSubset> ctSub) {
		this.criterias = ctSub;
	}
	
	@Override
	public void setWeights (ArrayList<WeightTableSubset> wtSub) {
		this.weights = wtSub;
	}
	
	@Override
	public void setCalculations (ArrayList<String> calculations) {
		this.calculations = calculations;
	}
}
