/**
 * 
 */
package com.baysphere.stockpicker.client;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.baysphere.stockpicker.client.presenters.impl.MainPanelPresenterImpl;
import com.baysphere.stockpicker.client.presenters.impl.ShowStocksPresenterImpl;
import com.baysphere.stockpicker.client.views.MainPanelView;
import com.baysphere.stockpicker.client.views.ShowStocksView;
import com.baysphere.stockpicker.client.views.uibinder.MainPanelViewImpl;
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
	
	
	private static MainPanelView mainPanelView;
	private static ShowStocksView showStocksView;
	private static Logger logger = Logger.getLogger("Client Logger");
	
	ClientFactory clientFactory = GWT.create(ClientFactory.class);
	SimplePanel container = new SimplePanel();
	
	@Override
	public void onModuleLoad() {
		
		logger.log(Level.INFO,"Building StockPicker");
		launchStockPicker();
		
		//mainPanelView = new MainPanelViewImpl();
		//mainPanelView = clientFactory.getMainPanelView();
		
		
		//RootPanel.get().add(mainPanelView);
		RootPanel.get().add(showStocksView);

	}

	private void launchStockPicker() {
		// Create clientFactory to get app context ready
		EventBus eventBus = clientFactory.getEventBus();
		initWidget(container);
		container.setSize("100%", "100%");
		//StockPickerApp stockPickerApp = new StockPickerApp(eventBus);
		//stockPickerApp.go(container);
		
		//mainPanelView = clientFactory.getMainPanelView();
		//new MainPanelPresenterImpl(mainPanelView).go(container);
		
		showStocksView = clientFactory.getShowStocksView();
		new ShowStocksPresenterImpl(showStocksView).go(container);
		
	}

	
}
