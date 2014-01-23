/**
 * 
 */
package com.baysphere.stockpicker.client.views.uibinder;

import java.util.Date;

import com.baysphere.stockpicker.client.presenters.ShowStocksPresenter;
import com.baysphere.stockpicker.client.views.ShowStocksView;
import com.baysphere.stockpicker.shared.StockInformation;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Paul
 *
 */
public class ShowStocksViewImpl extends Composite implements ShowStocksView {

	private static ShowStocksViewImplUiBinder uiBinder = GWT
			.create(ShowStocksViewImplUiBinder.class);
	
	ShowStocksPresenter presenter;
	
	@UiField DockLayoutPanel mainDockLayoutPanel;
	@UiField Button buttonShowStocks;
	@UiField Button buttonShowIndexes;
	@UiField Button buttonShowWeights;
	@UiField Button buttonShowPoints;
	@UiField Button configure;
	
	@UiField FlexTable stocksFlexTable;
	@UiField TextBox newSymbolTextBox;
	@UiField Label errorMsgLabel;
	@UiField Button addStockButton;
	@UiField Label lastUpdatedLabel;

	interface ShowStocksViewImplUiBinder extends
			UiBinder<Widget, ShowStocksViewImpl> {
	}

	public ShowStocksViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		setWidgetToMaxWidthAndHeight();
		
		// Create table for stock data.  
		stocksFlexTable.setText(0, 0, "Symbol");  
		stocksFlexTable.setText(0, 1, "Price");  
		stocksFlexTable.setText(0, 2, "Change");  
		stocksFlexTable.setText(0, 3, "Remove");
		stocksFlexTable.setText(0, 4, "Index");
		
	    // Add styles to elements in the stock list table.
	    stocksFlexTable.getRowFormatter().addStyleName(0, "watchListHeader");
	    stocksFlexTable.addStyleName("watchList");
	    stocksFlexTable.getCellFormatter().addStyleName(0, 1, "watchListNumericColumn");
	    stocksFlexTable.getCellFormatter().addStyleName(0, 2, "watchListNumericColumn");
	    stocksFlexTable.getCellFormatter().addStyleName(0, 3, "watchListRemoveColumn");
	    stocksFlexTable.getCellFormatter().addStyleName(0, 4, "watchlistIndexColumn");

	    // Move cursor focus to the TextBox box.
	    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
	        public void execute () {
	        	newSymbolTextBox.setFocus(true);
	        }
	   });
	    
	    // Hide error message
	    errorMsgLabel.setVisible(false);
	    
	}

	public void setPresenter (ShowStocksPresenter presenter) {
		this.presenter = presenter;
	}
	
	  /* *************  WIDGET CENTERING CODE *************** */
	 private void setWidgetToMaxWidthAndHeight ()
	    {
	        setWidth("100%");
	        setHeight(Window.getClientHeight() + "px");
	    }
	 
	 /* handle the "Add" click buttom when adding a stock */
	 @UiHandler("addStockButton") 
	 void addStock (ClickEvent event) {
		 if (presenter != null) {
			 presenter.fetchStock (newSymbolTextBox.getText());
		 }
	 }

	 @UiHandler("newSymbolTextBox")
	 public void addStockFromNewSymbolTextBox (KeyDownEvent event) {
	        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
		   		 if (presenter != null) {
					 presenter.fetchStock (newSymbolTextBox.getText());
				 }
	          }
	 }
	 
	 public void addStockTextBoxSelect() {
	      newSymbolTextBox.selectAll();
	 }
	 
	 public void displayErrorMsg(String error) {
		 errorMsgLabel.setText("Error: " + error);  
		 errorMsgLabel.setVisible(true); 
	 }
	 
	 /* Add a row to the FlexTable for a new Stock */
	 public void displayStock (final String symbol) {
			// Add the stock as a row of the FlexTable.
		    int row = stocksFlexTable.getRowCount();
		    stocksFlexTable.setText(row, 0, symbol);
		    stocksFlexTable.setWidget(row, 2, new Label());

		    // Add a button to remove this stock from the table.
		    Button removeStockButton = new Button("x");
		    removeStockButton.addClickHandler(new ClickHandler() {
		    	public void onClick(ClickEvent event) {
		    		presenter.removeSymbol(symbol);
		      	}
		    });
		    stocksFlexTable.setWidget(row, 3, removeStockButton);
		    
		    stocksFlexTable.getCellFormatter().addStyleName(row, 1, "watchListNumericColumn");
		    stocksFlexTable.getCellFormatter().addStyleName(row, 2, "watchListNumericColumn");
		    stocksFlexTable.getCellFormatter().addStyleName(row, 3, "watchListRemoveColumn");
		    removeStockButton.addStyleDependentName("remove");
		    stocksFlexTable.getCellFormatter().addStyleName(0, 4, "watchlistIndexColumn");
	 }
	 
	 /* Remove a row when a Stock is deleted from the FlexTable */
	 public void removeStockRow (int index) {
	        stocksFlexTable.removeRow(index); 
	 }
	 
	 /* Update 1 row of the FlexTable with data, the information relative to a Stock */
	 public void updateTable (StockInformation data, int row) {
		 // Format the data in the Price and Change fields.
		 String priceText = NumberFormat.getFormat("#,##0.00").format(
		     data.getPrice());
		 NumberFormat changeFormat = NumberFormat.getFormat("+#,##0.00;-#,##0.00");
		 String changeText = changeFormat.format(data.getChange());
		 String changePercentText = changeFormat.format(data.getChangePercent());

		 // Populate the Price and Change fields with new data.
		 stocksFlexTable.setText(row, 1, priceText);
		 Label changeWidget = (Label)stocksFlexTable.getWidget(row, 2);
		 changeWidget.setText(changeText + " (" + changePercentText + "%)");
		 
		 // Change the color of text in the Change field based on its value.
		 String changeStyleName = "noChange";
		 if (data.getChangePercent() < -0.1f) {
		   changeStyleName = "negativeChange";
		 }
		 else if (data.getChangePercent() > 0.1f) {
		   changeStyleName = "positiveChange";
		 }

		 changeWidget.setStyleName(changeStyleName);

		 // Populate the index
		 String index = NumberFormat.getFormat("#,##0.00").format(data.getIndex());
		 stocksFlexTable.setText(row,  4,  index);
		 
		 //Reset the symbol TextBox entry
		 newSymbolTextBox.setText("");
		 
		 // Display timestamp showing last refresh.  Note: did decide to not add a new method to the ShowStockView interface
		 lastUpdatedLabel.setText("Last update : "  + DateTimeFormat.getMediumDateTimeFormat().format(new Date()));
		  
		 // Clear any errors. Note: did decide to not add a new method to the ShowStockView interface
		 errorMsgLabel.setVisible(false);
	 }
	 
	 

}
