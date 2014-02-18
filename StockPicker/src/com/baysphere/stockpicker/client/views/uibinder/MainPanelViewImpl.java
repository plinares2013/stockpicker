package com.baysphere.stockpicker.client.views.uibinder;

import com.baysphere.stockpicker.client.presenters.MainPanelPresenter;
import com.baysphere.stockpicker.client.views.MainPanelView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MainPanelViewImpl extends Composite implements MainPanelView {

	private static MainPanelViewImplUiBinder uiBinder = GWT
			.create(MainPanelViewImplUiBinder.class);
	
	MainPanelPresenter presenter;

	interface MainPanelViewImplUiBinder extends
			UiBinder<Widget, MainPanelViewImpl> {
	}

	public MainPanelViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		setWidgetToMaxWidthAndHeight();
		centerMainPanel.add(new IntroPanel());
	}

	//@UiField Button buttonShowStocks;
	//@UiField Button buttonShowIndexes;
	//@UiField Button buttonShowWeights;
	//@UiField Button buttonShowPoints;
	//@UiField Button configure;
	@UiField DockLayoutPanel mainDockLayoutPanel;
	@UiField SimplePanel centerMainPanel;


	//@UiHandler("buttonShowStocks")
	//void onClick(ClickEvent e) {
	//	if (presenter != null) {
	//		presenter.clickedButtonShowStocks();
	//	}
	//}

	@Override
	public void setPresenter(MainPanelPresenter presenter) {
		this.presenter = presenter;
	}
	
	  /* *************  WIDGET CENTERING CODE *************** */
	 private void setWidgetToMaxWidthAndHeight ()
	    {
	        setWidth("100%");
	        setHeight(Window.getClientHeight() + "px");
	    }

}
