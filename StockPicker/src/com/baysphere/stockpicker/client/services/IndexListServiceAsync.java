package com.baysphere.stockpicker.client.services;

import com.baysphere.stockpicker.shared.IndexInformation;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IndexListServiceAsync {
	public void getIndexList(String[] strings, AsyncCallback<IndexInformation[]> callback);
	public void getIndex (String indexName, AsyncCallback<IndexInformation> formula);

}
