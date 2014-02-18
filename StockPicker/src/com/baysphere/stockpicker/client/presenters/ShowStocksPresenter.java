package com.baysphere.stockpicker.client.presenters;

public interface ShowStocksPresenter extends Presenter {
	
	public void fetchStock(String symbol);
	public void removeSymbol(String symbol);
	public void clickedMainPageButton();

}
