package com.baysphere.stockpicker.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;

public class LaunchStockPicker extends Composite {
	
	ClientFactory clientFactory = GWT.create(ClientFactory.class);
	SimplePanel container = new SimplePanel();
	
	public void LaunchStockPicker() {
		// Create clientFactory to get app context ready
		EventBus eventBus = clientFactory.getEventBus();
		initWidget(container);
		container.setSize("100%", "100%");
		StockPickerApp stockPickerApp = new StockPickerApp(eventBus);
		stockPickerApp.go(container);
	}

}
