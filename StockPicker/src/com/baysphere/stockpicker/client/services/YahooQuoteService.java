package com.baysphere.stockpicker.client.services;


import com.baysphere.stockpicker.shared.StockInformation;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("yahooQuote")
public interface YahooQuoteService extends RemoteService {
	public StockInformation[] getStockInformation (String[] symbols);

}
