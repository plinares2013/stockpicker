package com.baysphere.stockpicker.client.views.uibinder;

import com.baysphere.stockpicker.client.Tokens;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LeftMenu extends Composite {

	private static LeftMenuUiBinder uiBinder = GWT
			.create(LeftMenuUiBinder.class);

	interface LeftMenuUiBinder extends UiBinder<Widget, LeftMenu> {
	}

	public LeftMenu() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField Button buttonShowStocks;
	@UiField Button buttonMainPage;
	@UiField Button buttonShowIndexes;
	@UiField Button buttonShowWeights;
	@UiField Button buttonShowPoints;
	@UiField Button configure;
	
	@UiHandler ("buttonMainPage")
	void clickedMainPage (ClickEvent event) {
		History.newItem(Tokens.HOME);
	}
	
	@UiHandler ("buttonShowStocks")
	void clickedShowStocks (ClickEvent event) {
		History.newItem(Tokens.SHOWSTOCKS);
	}
	
	@UiHandler ("buttonShowIndexes")
	void clickedShowIndexes (ClickEvent event) {
		History.newItem(Tokens.SHOWINDEXES);
	}
}
