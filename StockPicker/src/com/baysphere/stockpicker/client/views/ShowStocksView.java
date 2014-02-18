package com.baysphere.stockpicker.client.views;

import com.baysphere.stockpicker.client.presenters.ShowStocksPresenter;
import com.baysphere.stockpicker.shared.StockInformation;
import com.google.gwt.user.client.ui.IsWidget;

public interface ShowStocksView extends IsWidget {
	
	void setPresenter (ShowStocksPresenter presenter);
	void addStockTextBoxSelect();
	void displayStock (String symbol);
	void updateTable (StockInformation data, int row);
	void removeStockRow (int index);
	void displayErrorMsg(String error);
	void clearNewSymbolTextBox ();

}
