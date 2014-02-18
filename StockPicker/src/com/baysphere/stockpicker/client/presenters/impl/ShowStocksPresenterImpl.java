package com.baysphere.stockpicker.client.presenters.impl;

import java.util.ArrayList;

import com.baysphere.stockpicker.client.ClientFactory;
import com.baysphere.stockpicker.client.Tokens;
import com.baysphere.stockpicker.client.presenters.ShowStocksPresenter;
import com.baysphere.stockpicker.client.services.StockServiceAsync;
import com.baysphere.stockpicker.client.views.ShowStocksView;
import com.baysphere.stockpicker.shared.StockInformation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;



public class ShowStocksPresenterImpl implements ShowStocksPresenter {
	
	
	private ClientFactory clientFactory = GWT.create(ClientFactory.class);
	private StockServiceAsync stockService;
	private EventBus eventBus;
	private final ShowStocksView showStocksView;
	private ArrayList<String> stocks = new ArrayList<String>();
	
	public ShowStocksPresenterImpl (ShowStocksView showStocksView){
		this.stockService = clientFactory.getStockService();
		this.eventBus = clientFactory.getEventBus();
		this.showStocksView = showStocksView;
		bind();
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(showStocksView.asWidget());

	}

	@Override
	public void bind() {
		showStocksView.setPresenter(this);

	}
	
	public void fetchStock(String symb) {
		String symbol;
		symbol = symb.toUpperCase().trim();
		
	    // Stock code must be between 1 and 10 chars that are numbers, letters, or dots.
	    if (!symbol.matches("^[0-9A-Z\\.]{1,10}$")) {
	      Window.alert("'" + symbol + "' is not a valid symbol.");
	      showStocksView.addStockTextBoxSelect();
	      return;
	    }
	    
	    //Don't add the stock if it's already in the table.
	    if (stocks.contains(symbol)) {
	    	showStocksView.clearNewSymbolTextBox();
	    	Window.alert("Stock " + symbol + " is already in the table");
	        return;
	    }

	    
	    //Otherwise first display the stock in the table
	    showStocksView.displayStock(symbol);
	    stocks.add(symbol);
	    
	    //Then, get Stocks info for entire list and update entire table on the screen
		  AsyncCallback<StockInformation[]> yCallback = new AsyncCallback<StockInformation[]> () {
			  public void onFailure (Throwable error) {
			        showStocksView.displayErrorMsg("Couldn't retrieve stock info from stockService");
			  }
			  
			  public void onSuccess (StockInformation stockInfo[]) {
				  StockInformation[] datas = stockInfo;
					//logger.log(Level.WARNING, "infos length = " + infos.length);
					for ( int i=0; i < datas.length ; i++) {
						//If stock is not in table any more, skip
						 if (!stocks.contains(datas[i].getSymbol())) {
							   continue;
							 }
						int row = stocks.indexOf(datas[i].getSymbol()) + 1;
						showStocksView.updateTable(datas[i],row);
					}
			  }
		  };
		  stockService.getStockInformation(stocks.toArray(new String[stocks.size()]), yCallback);
	}

	public void removeSymbol (String symbol) {
        int removedIndex = stocks.indexOf(symbol);
        stocks.remove(removedIndex);
        showStocksView.removeStockRow(removedIndex+1);
	}
	
	public void clickedMainPageButton() {
		History.newItem(Tokens.HOME);
	}
}
