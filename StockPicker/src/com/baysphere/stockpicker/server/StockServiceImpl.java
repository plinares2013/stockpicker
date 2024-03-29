package com.baysphere.stockpicker.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.baysphere.stockpicker.client.NotLoggedInException;
import com.baysphere.stockpicker.client.services.StockService;
import com.baysphere.stockpicker.shared.StockInformation;




import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class StockServiceImpl extends RemoteServiceServlet implements
		StockService {

	private static final Logger logger = Logger.getLogger(StockServiceImpl.class.getName());
	
	@Override
	public void addStock(String symbol) throws NotLoggedInException {
		UtilityClass.checkLoggedIn();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(new Stock (UtilityClass.getUser(), symbol));
		} finally {
			pm.close();
		}

	}

	@Override
	public void removeStock(String symbol) throws NotLoggedInException {
		UtilityClass.checkLoggedIn();
		PersistenceManager pm = PMF.get().getPersistenceManager();	
		
		try {
			long deleteCount =0;
			Query q = pm.newQuery(Stock.class, "user==u");
			q.declareParameters("com.google.appengine.api.users.User u");
			q.setOrdering("createDate");
			List<Stock> stocks = (List<Stock>) q.execute(UtilityClass.getUser());
			
			for (Stock stock : stocks) {
				if (stock.getSymbol().equals(symbol)) {
					deleteCount++;
					pm.deletePersistent(stock);
				}
			}
			
			if (deleteCount != 1) {
				logger.log(Level.INFO, "removeStock deleted " + deleteCount + " Stocks" );
			}
		} finally {
			pm.close();
		}

	}

	@Override
	public String[] getStocks() throws NotLoggedInException {
		List<String> symbolList = new ArrayList<String>();
		UtilityClass.checkLoggedIn();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
		Query q = pm.newQuery(Stock.class, "user==u");
		//Query q = pm.newQuery(Stock.class);
		//Query q = pm.newQuery("SELECT FROM com.google.gwt.sample.stockpicker.server.Stock WHERE symbol== tickerParam" +
		//						"parameters String tickerParam");
		q.declareParameters("com.google.appengine.api.users.User u");
		q.setOrdering("createDate");
		List<Stock> stocks = (List<Stock>) q.execute(UtilityClass.getUser());
		//List<Stock> stocks = (List<Stock>) q.execute("XOM");
		//List<Stock> stocks = (List<Stock>) q.execute();
		
		for (Stock stock : stocks) {
			symbolList.add(stock.getSymbol());
		}
		} finally {
			pm.close();
		}
		
		return (String[]) symbolList.toArray(new String[0]);
	}

	@Override
	public StockInformation[] getStockInformation(String[] symbols)
			throws NotLoggedInException {
		
		UtilityClass.checkLoggedIn();
		
		StockInformation[] stockinfos = new StockInformation[symbols.length];
		Stock[] stocks = new Stock[symbols.length];
		for (int i=0; i < symbols.length ; i++) {
			stockinfos[i] = new StockInformation();
			stocks[i] = new Stock();
		}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			//Retrieve Stock object from datastore, 1 symbol at a time
			int j =0;
			for (String name : symbols) {
				// Prepare query
				Query q = pm.newQuery(Stock.class);
				q.setFilter("symbol == ticker");
				q.declareParameters("String ticker");
				//Extract the fields from the datastore
				List<Stock> results = (List<Stock>) q.execute(name);
				if (results.size() == 1) {
					for (Stock item : results) {
						stocks[j] = item;
						//logger.log(Level.INFO,"j is equal to: " + j);
							j++;
					}
				} else {
					if (results.isEmpty()) {
						logger.log(Level.WARNING,"Ticker " + name + " is not in the data store");
					}
					if ((results.size()) > 1) {
						logger.log(Level.WARNING,"More than 1 record for symbol " + name + " in the data store");
					}
				}
			}
			//Retrieve the properties to be displayed by front end
			for (int k = 0 ; k < symbols.length ; k++) {
				stockinfos[k].setSymbol(stocks[k].getSymbol());
				stockinfos[k].setPrice(stocks[k].getPrice());
				stockinfos[k].setChange(stocks[k].getChange());
				stockinfos[k].setPercentChange(stocks[k].getPercentChange());
				stockinfos[k].setIndex(stocks[k].getIndex());
			}
		} finally {
			pm.close();
		}

		
		// Send to front end
		return (stockinfos);
	}

}
