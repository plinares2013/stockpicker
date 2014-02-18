package com.baysphere.stockpicker.client.services;



import com.baysphere.stockpicker.shared.StockInformation;
import com.google.gwt.user.client.rpc.AsyncCallback;


public interface YahooQuoteServiceAsync {
	public void getStockInformation (String[] symbols, AsyncCallback<StockInformation[]> callback);

}
