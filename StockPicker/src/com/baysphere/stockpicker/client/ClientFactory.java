package com.baysphere.stockpicker.client;

import com.baysphere.stockpicker.client.services.StockServiceAsync;
import com.baysphere.stockpicker.client.views.MainPanelView;
import com.baysphere.stockpicker.client.views.ShowStocksView;
import com.google.gwt.event.shared.EventBus;

public interface ClientFactory {
	public EventBus getEventBus();
	public MainPanelView getMainPanelView();
	public ShowStocksView getShowStocksView();
	public StockServiceAsync getStockService();
}
