package com.baysphere.stockpicker.client.presenters.impl;

import java.util.ArrayList;

import com.baysphere.stockpicker.client.ClientFactory;
import com.baysphere.stockpicker.client.presenters.ShowWeightsPresenter;
import com.baysphere.stockpicker.client.views.ShowWeightsView;
import com.baysphere.stockpicker.shared.WeightTableSubset;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasWidgets;

public class ShowWeightsPresenterImpl implements ShowWeightsPresenter {
	
	private ClientFactory clientFactory = GWT.create(ClientFactory.class);
	
	private final ShowWeightsView showWeightsView;
	private String row;
	
	public ShowWeightsPresenterImpl (ShowWeightsView weightsView, String row) {
		this.row = row;
		this.showWeightsView = weightsView;
		ArrayList<WeightTableSubset> weightTableSub = clientFactory.getWeights();
		WeightTableSubset wtSub = weightTableSub.get(Integer.parseInt(row));
		showWeightsView.update(wtSub);
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(showWeightsView.asWidget());

	}

	@Override
	public void bind() {
		showWeightsView.setPresenter(this);

	}

}
