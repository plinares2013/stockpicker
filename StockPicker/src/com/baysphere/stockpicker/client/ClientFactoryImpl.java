/**
 * 
 */
package com.baysphere.stockpicker.client;

import com.baysphere.stockpicker.client.services.StockService;
import com.baysphere.stockpicker.client.services.StockServiceAsync;
import com.baysphere.stockpicker.client.views.MainPanelView;
import com.baysphere.stockpicker.client.views.ShowStocksView;
import com.baysphere.stockpicker.client.views.uibinder.MainPanelViewImpl;
import com.baysphere.stockpicker.client.views.uibinder.ShowStocksViewImpl;
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
	private static StockServiceAsync stockService;
	
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
}
