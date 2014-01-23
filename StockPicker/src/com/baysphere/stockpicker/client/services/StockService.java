package com.baysphere.stockpicker.client.services;


import com.baysphere.stockpicker.client.NotLoggedInException;
import com.baysphere.stockpicker.shared.StockInformation;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("stock")
public interface StockService extends RemoteService {
	public void addStock (String symbol) throws NotLoggedInException;
	public void removeStock (String symbol) throws NotLoggedInException;
	public String[] getStocks() throws NotLoggedInException;
	public StockInformation[] getStockInformation(String[] symbols) throws NotLoggedInException;
}
