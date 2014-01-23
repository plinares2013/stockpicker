/**
 * 
 */
package com.baysphere.stockpicker.client;

import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	private static Logger logger = Logger.getLogger("StockPickerApp");
	
	EventBus eventBus;
	

	
	public StockPickerApp (EventBus eventBus) {
		this.eventBus = eventBus;
		bind();
	}
	
	private void bind() {
		History.addValueChangeHandler(this);
	}
	
	public void onValueChange (ValueChangeEvent<String> event) {
		String token = event.getValue();
		logger.log(Level.INFO,"onValueChange received event " + event.toString());
	}
	
	HasWidgets container ;
	
	public void go(HasWidgets container) {
		this.container = container;
	}

}
