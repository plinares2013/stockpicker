package com.baysphere.stockpicker.client.views.uibinder;

import com.baysphere.stockpicker.client.presenters.ShowIndexPresenter;
import com.baysphere.stockpicker.client.views.ShowIndexView;
import com.baysphere.stockpicker.shared.IndexInformation;
import com.baysphere.stockpicker.shared.WeightTableSubset;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ShowIndexViewImpl extends Composite implements ShowIndexView {

	private static ShowIndexViewImplUiBinder uiBinder = GWT
			.create(ShowIndexViewImplUiBinder.class);
	
	ShowIndexPresenter presenter;
	
	@UiField Label errorMsgLabel;
	@UiField FlexTable indexFlexTable;
	@UiField TextBox newIndexTextBox;
	@UiField Button addIndexButton;
	@UiField Label lastUpdatedLabel;

	interface ShowIndexViewImplUiBinder extends
			UiBinder<Widget, ShowIndexViewImpl> {
	}

	public ShowIndexViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		setWidgetToMaxWidthAndHeight();
		// Create table for Index data.  
		indexFlexTable.setText(0, 0, "Index Name");  
		indexFlexTable.setText(0, 1, "WeightTable");  
		indexFlexTable.setText(0, 2, "PointsTable");  
		indexFlexTable.setText(0, 3, "Calculation");
		indexFlexTable.setText(0, 4, "Accuracy");
		indexFlexTable.setText(0, 5, "Remove");
		
	    // Add styles to elements in the index table.
	    indexFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
	    indexFlexTable.addStyleName("watchList");
	    indexFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
	    indexFlexTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
	    indexFlexTable.getCellFormatter().addStyleName(0, 3, "watchListNumericColumn");
	    indexFlexTable.getCellFormatter().addStyleName(0, 4, "watchlistNumericColumn");
	    indexFlexTable.getCellFormatter().addStyleName(0, 5, "watchlistRemoveColumn");

	    // Move cursor focus to the TextBox box.
	    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	        public void execute () {
	        	newIndexTextBox.setFocus(true);
	        }
	   });
	    
	    // Hide error message
	    errorMsgLabel.setVisible(false);
	    
	}

	private void setWidgetToMaxWidthAndHeight() {
		setWidth("100%");
		setHeight(Window.getClientHeight() + "px");
		
	}

	public void setPresenter (ShowIndexPresenter presenter) {
		this.presenter = presenter;
	}
	
	@UiHandler ("addIndexButton")
	public void clickedAddIndexButton (ClickEvent event) {
		if (presenter != null) {
			presenter.fetchIndex(newIndexTextBox.getText());
		}
	}
	
	 // Handle the ENTER in the textbox to add a symbol
	 @UiHandler("newIndexTextBox")
	 public void addStockFromNewSymbolTextBox (KeyDownEvent event) {
	        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		   		 if (presenter != null) {
					 presenter.fetchIndex (newIndexTextBox.getText());
				 }
	          }
	 }
	
	public void addIndexTextBoxSelect() {
		newIndexTextBox.selectAll();
	}
	
	public void clearNewIndexTextBox() {
		newIndexTextBox.setText("");
	}
	
	public void updateTable (final IndexInformation index, int row) {
		// Add the stock as a row of the FlexTable.
	    indexFlexTable.setText(row, 0, index.getName());
	    indexFlexTable.setWidget(row, 2, new Label());
	    
	    //Add a button to display the Weight table associated to the index
	    Button displayIndexWeightsButton = new Button("Display Weights");
	    displayIndexWeightsButton.addClickHandler(new ClickHandler() {
	    	public void onClick (ClickEvent event) {
	    		if (presenter != null) {
	    			presenter.displayIndexWeights (index.getName());
	    		}
	    	}
	    }); 
	    
	    //Add a button to display the Points table associated to the index
	    Button displayIndexPointsButton = new Button("Display Points");
	    displayIndexPointsButton.addClickHandler(new ClickHandler() {
	    	public void onClick (ClickEvent event) {
	    		if (presenter != null) {
	    			presenter.displayIndexPoints(index.getName());
	    		}
	    	}
	    }); 

	    // Add a button to remove this index from the table.
	    Button removeIndexButton = new Button("x");
	    removeIndexButton.addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
	    		presenter.removeIndex(index.getName());
	      	}
	    });

	    
	    indexFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
	    indexFlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
	    indexFlexTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");
	    removeIndexButton.addStyleDependentName("remove");
	    indexFlexTable.getCellFormatter().addStyleName(0, 4, "watchlistIndexColumn");
	    
	    indexFlexTable.setText(row, 0, index.getName());
	    indexFlexTable.setWidget(row, 1, displayIndexWeightsButton);
	    indexFlexTable.setWidget(row, 2, displayIndexPointsButton);
	    indexFlexTable.setText(row, 3, index.getCalculationMode());
	    indexFlexTable.setText(row, 4, Double.toString(100*index.getAccuracy()).concat("%"));
	    indexFlexTable.setWidget(row, 5, removeIndexButton);
	}
	

	public void displayErrorMsg (String error) {
		 errorMsgLabel.setText("Error: " + error);  
		 errorMsgLabel.setVisible(true); 
	}
}
