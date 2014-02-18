/**
 * 
 */
package com.baysphere.stockpicker.client.presenters.impl;

import com.baysphere.stockpicker.client.Tokens;
import com.baysphere.stockpicker.client.presenters.MainPanelPresenter;
import com.baysphere.stockpicker.client.views.MainPanelView;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

/**
 * @author Paul
 *
 */
public class MainPanelPresenterImpl implements MainPanelPresenter {

	/* (non-Javadoc)
	 * @see com.baysphere.stockpicker.client.presenters.Presenter#go(com.google.gwt.user.client.ui.HasWidgets)
	 */
	
	private final MainPanelView mainPanelView;
	
	public MainPanelPresenterImpl (MainPanelView mainPanelView) {
		this.mainPanelView = mainPanelView;
		bind();
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(mainPanelView.asWidget());

	}

	/* (non-Javadoc)
	 * @see com.baysphere.stockpicker.client.presenters.Presenter#bind()
	 */
	@Override
	public void bind() {
		mainPanelView.setPresenter(this);

	}
	
	public void clickedButtonShowStocks() {
		History.newItem(Tokens.SHOWSTOCKS);
	}

}
