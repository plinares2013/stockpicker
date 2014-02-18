package com.baysphere.stockpicker.client.presenters;

public interface ShowIndexPresenter extends Presenter {
	public void fetchIndex (String name);
	public void displayIndexWeights (String name);
	public void displayIndexPoints (String name);
	public void removeIndex (String name);

}
