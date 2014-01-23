/**
 * 
 */
package com.baysphere.stockpicker.client.views.uibinder;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Paul
 *
 */
public class IntroPanel extends Composite {

	private static IntroPanelUiBinder uiBinder = GWT
			.create(IntroPanelUiBinder.class);

	interface IntroPanelUiBinder extends UiBinder<Widget, IntroPanel> {
	}
	
	@UiField ScrollPanel scrollPanel; 

	/**
	 * Because this class has a default constructor, it can
	 * be used as a binder template. In other words, it can be used in other
	 * *.ui.xml files as follows:
	 * <ui:UiBinder xmlns:ui="urn:ui:com.google.gwt.uibinder"
	 *   xmlns:g="urn:import:**user's package**">
	 *  <g:**UserClassName**>Hello!</g:**UserClassName>
	 * </ui:UiBinder>
	 * Note that depending on the widget that is used, it may be necessary to
	 * implement HasHTML instead of HasText.
	 */
	public IntroPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	    Window.addResizeHandler(resizeHandler);
	    scrollPanel.setHeight("100%");
	    scrollPanel.setWidth("100%");
	}
	

	 private ResizeHandler resizeHandler = new ResizeHandler() {
		    
		    public void onResize(ResizeEvent event) {
		      scrollPanel.setHeight((event.getHeight() - 20) + "px");
		    }
		  };
}
