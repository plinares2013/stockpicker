/**
 * 
 */
package com.baysphere.stockpicker.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.baysphere.stockpicker.client.presenters.impl.MainPanelPresenterImpl;
import com.baysphere.stockpicker.client.presenters.impl.ShowIndexPresenterImpl;
import com.baysphere.stockpicker.client.presenters.impl.ShowStocksPresenterImpl;
import com.baysphere.stockpicker.client.views.MainPanelView;
import com.baysphere.stockpicker.client.views.ShowIndexView;
import com.baysphere.stockpicker.client.views.ShowStocksView;
import com.baysphere.stockpicker.client.views.uibinder.MainPanelViewImpl;
import com.baysphere.stockpicker.client.views.uibinder.ShowIndexViewImpl;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;


/**
 * @author Paul
 *
 */
public class StockPicker extends Composite  implements EntryPoint {

	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	
	private static Logger logger = Logger.getLogger(StockPicker.class.getName());
	
	ClientFactory clientFactory = GWT.create(ClientFactory.class);
	SimplePanel container = new SimplePanel();
	
	@Override
	public void onModuleLoad() {
		
		logger.log(Level.INFO,"Building StockPicker");
		//launchStockPicker();
		//RootPanel.get().add(mainPanelView);
		//RootPanel.get().add(new LaunchStockPicker());
		// Create clientFactory to get app context ready
		EventBus eventBus = clientFactory.getEventBus();
		//initWidget(container);
		container.setSize("100%", "100%");
		
		//ShowIndexView showIndexView = clientFactory.getShowIndexView();
		//new ShowIndexPresenterImpl(showIndexView).go(container);
		
		//ShowStocksView showStocksView = clientFactory.getShowStocksView();
		//new ShowStocksPresenterImpl(showStocksView).go(container);
		
		StockPickerApp stockPickerApp = new StockPickerApp(eventBus);
		stockPickerApp.go(RootPanel.get());

	}

	/* 
	 * private void launchStockPicker() {
	 
		// Create clientFactory to get app context ready
		EventBus eventBus = clientFactory.getEventBus();
		initWidget(container);
		container.setSize("100%", "100%");
		StockPickerApp stockPickerApp = new StockPickerApp(eventBus);
		stockPickerApp.go(container);
		
		//mainPanelView = clientFactory.getMainPanelView();
		//new MainPanelPresenterImpl(mainPanelView).go(container);
		
		//showStocksView = clientFactory.getShowStocksView();
		//new ShowStocksPresenterImpl(showStocksView).go(container);
		
	}
	*
	*/

	
}
