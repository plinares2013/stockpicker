package com.baysphere.stockpicker.client.views;

import com.baysphere.stockpicker.client.presenters.ShowWeightsPresenter;
import com.baysphere.stockpicker.shared.WeightTableSubset;
import com.google.gwt.user.client.ui.IsWidget;

public interface ShowWeightsView extends IsWidget {
	void setPresenter (ShowWeightsPresenter presenter);
	void update (WeightTableSubset wtSub);
}
