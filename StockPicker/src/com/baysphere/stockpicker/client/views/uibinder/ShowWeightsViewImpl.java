package com.baysphere.stockpicker.client.views.uibinder;

import com.baysphere.stockpicker.client.presenters.ShowWeightsPresenter;
import com.baysphere.stockpicker.client.views.ShowWeightsView;
import com.baysphere.stockpicker.shared.WeightTableSubset;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class ShowWeightsViewImpl extends Composite implements ShowWeightsView {

	private static ShowWeightsViewImplUiBinder uiBinder = GWT
			.create(ShowWeightsViewImplUiBinder.class);
	
	ShowWeightsPresenter presenter;
	
	@UiField ScrollPanel centerMainPanel;
	@UiField FlexTable weightsFlexTable;
	@UiField Button backButton;
	@UiField Label backLabel;
	@UiField Label titleLabel;

	interface ShowWeightsViewImplUiBinder extends
			UiBinder<Widget, ShowWeightsViewImpl> {
	}

	public ShowWeightsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		setWidgetToMaxWidthAndHeight();
		
		//Display Weights Table
		weightsFlexTable.setText(0, 0, "Price to Sales");
		weightsFlexTable.setText(1, 0, "Price to Book");
		weightsFlexTable.setText(2, 0, "Profit Margin");
		weightsFlexTable.setText(3, 0, "Return On Equity");
		weightsFlexTable.setText(4, 0, "Return On Assets");
		weightsFlexTable.setText(5, 0, "Debt/Equity");
		weightsFlexTable.setText(6, 0, "Quick Ratio");
		weightsFlexTable.setText(7, 0, "Current Ratio");
		weightsFlexTable.setText(8, 0, "1 Year EPS");
		weightsFlexTable.setText(9, 0, "3 year EPS");
		weightsFlexTable.setText(10, 0, "5 Year EPS");
		weightsFlexTable.setText(11, 0, "Quarter EPS");
		weightsFlexTable.setText(12, 0, "EPS Quarterly growth YoY");
		weightsFlexTable.setText(13, 0, "Free Cash Flow per Share");
		weightsFlexTable.setText(14, 0, "Management Ownership");
		weightsFlexTable.setText(15, 0, "PEG Ratio");
		weightsFlexTable.setText(16, 0, "PE Ratio");
	}
	
	public void update (WeightTableSubset wtSub) {
		weightsFlexTable.setText(0, 1, Double.toString(wtSub.getPriceSalesWeight()));
		weightsFlexTable.setText(1, 1, Double.toString(wtSub.getPriceBookWeight()));
		weightsFlexTable.setText(2, 1, Double.toString(wtSub.getProfitMarginWeight()));
		weightsFlexTable.setText(3, 1, Double.toString(wtSub.getROEWeight()));
		weightsFlexTable.setText(4, 1, Double.toString(wtSub.getROAWeight()));
		weightsFlexTable.setText(5, 1, Double.toString(wtSub.getDebtEquityWeight()));
		weightsFlexTable.setText(6, 1, Double.toString(wtSub.getQuickWeight()));
		weightsFlexTable.setText(7, 1, Double.toString(wtSub.getCurrentWeight()));
		weightsFlexTable.setText(8, 1, Double.toString(wtSub.getOneYearEPSWeight()));
		weightsFlexTable.setText(9, 1, Double.toString(wtSub.getThreeYearEPSWeight()));
		weightsFlexTable.setText(10, 1, Double.toString(wtSub.getFiveYearEPSWeight()));
		weightsFlexTable.setText(11, 1, Double.toString(wtSub.getQuarterlyEPSWeight()));
		weightsFlexTable.setText(12, 1, Double.toString(wtSub.getEPSQuarterlyGrowthYoYWeight()));
		weightsFlexTable.setText(13, 1, Double.toString(wtSub.getFreeCashFlowPerShareWeight()));
		weightsFlexTable.setText(14, 1, Double.toString(wtSub.getManagementOwnershipWeight()));
		weightsFlexTable.setText(15, 1, Double.toString(wtSub.getPEGRatioWeight()));
		weightsFlexTable.setText(16, 1, Double.toString(wtSub.getPERatioWeight()));
		
	}
	
	public void setPresenter(ShowWeightsPresenter presenter) {
		this.presenter = presenter;
	}
	
	  /*   Widget code centering    */
	 private void setWidgetToMaxWidthAndHeight ()
	    {
	        setWidth("100%");
	        setHeight(Window.getClientHeight() + "px");
	    }

}
