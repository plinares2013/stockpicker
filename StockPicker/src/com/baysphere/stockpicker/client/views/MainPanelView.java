package com.baysphere.stockpicker.client.views;

import com.baysphere.stockpicker.client.presenters.MainPanelPresenter;
import com.google.gwt.user.client.ui.IsWidget;

public interface MainPanelView extends IsWidget {

	void setPresenter (MainPanelPresenter presenter);
}
