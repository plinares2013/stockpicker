package com.baysphere.stockpicker.client.services;

import com.baysphere.stockpicker.client.LoginInfo;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
	public void login (String requestUri, AsyncCallback<LoginInfo> callback);
}
