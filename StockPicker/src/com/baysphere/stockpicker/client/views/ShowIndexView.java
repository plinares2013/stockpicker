package com.baysphere.stockpicker.client.views;

import com.baysphere.stockpicker.client.presenters.ShowIndexPresenter;
import com.baysphere.stockpicker.shared.IndexInformation;
import com.baysphere.stockpicker.shared.WeightTableSubset;
import com.google.gwt.user.client.ui.IsWidget;

public interface ShowIndexView extends IsWidget {
	void setPresenter (ShowIndexPresenter presenter);
	void addIndexTextBoxSelect();
	void clearNewIndexTextBox();
	void updateTable(IndexInformation index, int row);
	void displayErrorMsg (String error);
}
