/**
 * 
 */
package com.baysphere.stockpicker.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.baysphere.stockpicker.client.presenters.impl.MainPanelPresenterImpl;
import com.baysphere.stockpicker.client.presenters.impl.ShowIndexPresenterImpl;
import com.baysphere.stockpicker.client.presenters.impl.ShowStocksPresenterImpl;
import com.baysphere.stockpicker.client.presenters.impl.ShowWeightsPresenterImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;


/**
 * @author Paul
 *
 */
public class StockPickerApp  implements ValueChangeHandler<String> {
	
	private static Logger logger = Logger.getLogger(StockPickerApp.class.getName());
	ClientFactory clientFactory = GWT.create(ClientFactory.class);
	
	EventBus eventBus;
	

	
	public StockPickerApp (EventBus eventBus) {
		//logger.log(Level.INFO,"StockPickerApp constructor");
		this.eventBus = eventBus;
		bind();
	}
	
	private void bind() {
		History.addValueChangeHandler(this);
	}
	
	public void onValueChange (ValueChangeEvent<String> event) {
		String token = event.getValue();
		logger.log(Level.INFO,"onValueChange received event " + event.toString());
		
		if (token.equals(Tokens.HOME)) {
			displayMainPanel();
		} else if (token.equals(Tokens.SHOWSTOCKS)){
			displayShowStocks();
		} else if (token.equals(Tokens.SHOWINDEXES)){
			displayShowIndexes();
		} else if (token.contains(Tokens.SHOWWEIGHTS)) {
			String[] bits = token.split("&");
			displayShowWeights(bits[1]);
		} else {
			displayMainPanel();
			logger.log(Level.INFO,"History token leads nowhere. Displayed the main page");
		}
	}
	
	private void displayShowWeights(String string) {
		new ShowWeightsPresenterImpl (clientFactory.getShowWeightsView(), string).go(container);
		
	}

	private void displayShowIndexes() {
		new ShowIndexPresenterImpl (clientFactory.getShowIndexView()).go(container);
		
	}

	private void displayShowStocks() {
		new ShowStocksPresenterImpl (clientFactory.getShowStocksView()).go(container);
		
	}

	private void displayMainPanel() {
		new MainPanelPresenterImpl (clientFactory.getMainPanelView()).go(container);
		
	}

	HasWidgets container ;
	
	// Set container needed for building views and initiate History
	public void go(HasWidgets container) {
		this.container = container;
		logger.log(Level.INFO,"History stack is at  " + History.getToken());
		/*
		 * if (History.getToken()!= null & History.getToken() != Tokens.HOME) {
		 
			if (History.getToken() ==  Tokens.SHOWSTOCKS){
				this.displayShowStocks();
			} else if (History.getToken() == Tokens.SHOWINDEXES) {
				this.displayShowIndexes();
			} else {
				this.displayMainPanel();
			}
		} else {
			this.displayMainPanel();
		}
		*/	
		History.fireCurrentHistoryState();
	}

}
