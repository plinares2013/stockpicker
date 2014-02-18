package com.baysphere.stockpicker.client.services;

import com.baysphere.stockpicker.client.NotLoggedInException;
import com.baysphere.stockpicker.shared.IndexInformation;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("index")
public interface IndexListService extends RemoteService {
	public  IndexInformation[] getIndexList (String[] strings) throws NotLoggedInException;
	public IndexInformation getIndex (String indexName) throws NotLoggedInException;

}
