package com.baysphere.stockpicker.client.presenters.impl;

import java.util.ArrayList;

import com.baysphere.stockpicker.client.ClientFactory;
import com.baysphere.stockpicker.client.Tokens;
import com.baysphere.stockpicker.client.presenters.ShowIndexPresenter;
import com.baysphere.stockpicker.client.services.IndexListServiceAsync;

import com.baysphere.stockpicker.client.views.ShowIndexView;
import com.baysphere.stockpicker.shared.CriteriaTableSubset;
import com.baysphere.stockpicker.shared.IndexInformation;
import com.baysphere.stockpicker.shared.WeightTableSubset;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class ShowIndexPresenterImpl implements ShowIndexPresenter {

	private ClientFactory clientFactory = GWT.create(ClientFactory.class);
	private IndexListServiceAsync indexListService;
	private EventBus eventBus;
	private final ShowIndexView showIndexView;
	
	private ArrayList<String> indexes;
	private ArrayList<CriteriaTableSubset> criterias ;
	private ArrayList<WeightTableSubset> weights;
	private ArrayList<String> calculations;
	
	public ShowIndexPresenterImpl (ShowIndexView showIndexView) {
		this.indexes = clientFactory.getIndexes();
		this.criterias = clientFactory.getCriterias();
		this.weights = clientFactory.getWeights();
		this.calculations = clientFactory.getCalculations();
		this.indexListService = clientFactory.getIndexListService();
		this.eventBus = clientFactory.getEventBus();
		this.showIndexView = showIndexView;
		bind();
	}
	
	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(showIndexView.asWidget());

	}

	@Override
	public void bind() {
		showIndexView.setPresenter(this);

	}
	
	@Override
	public void fetchIndex(String indexName) {
		 String name = indexName.trim();
		
	    // Index name must be between 1 and 10 chars that are numbers, letters, or dots.
	    if (!name.matches("^[0-9A-Za-z\\.]{1,10}$")) {
	      Window.alert("'" + name + "' is not a valid symbol. Index name is composed of letters and numbers.");
	      showIndexView.addIndexTextBoxSelect();
	      return;
	    }
	    
	    //Don't add the stock if it's already in the table.
	    indexes = clientFactory.getIndexes();
	    if (indexes.contains(name)) {
	    	showIndexView.clearNewIndexTextBox();
	    	Window.alert("Stock " + name + " is already in the table");
	        return;
	    } else {
	    	indexes.add(name);
	    	clientFactory.setIndexes(indexes);
	    }
	    
	    // Retrieve Index information from server: a WeightTable, a PointsTable and a formula (the task name to compute) 
	    // An Index is entirely defined by the 3 components: Weight, Points, Formula
	    
		  AsyncCallback<IndexInformation[]> iCallback = new AsyncCallback<IndexInformation[]> () {
			  public void onFailure (Throwable error) {
			        showIndexView.displayErrorMsg("Couldn't retrieve stock info from stockService");
			  }
			  
			  public void onSuccess (IndexInformation indexInfos[]) {

					//logger.log(Level.WARNING, "infos length = " + infos.length);
					for ( int i=0; i < indexInfos.length ; i++) {
						//If index is not in table any more, skip
						 if (!indexes.contains(indexInfos[i].getName())) {
							   continue;
							 }
						//Store the 3 elements characterizing each Index locally:
						// the Criteria, the Weights and the calculation
						//Then display the Index in the UI table
						 for (IndexInformation index : indexInfos) {
							 int  row = indexes.indexOf(index.getName());
							 if (row != -1) {
								 criterias.add(row, index.getCriteriaTableSubset()) ;
								 weights.add(row, index.getWeightTableSubset());
								 calculations.add(row, index.getCalculationMode());
							 }
						 showIndexView.updateTable(index, row+1);
						 }
					}
					//Update all changes in the central location (the clientFactory)
					clientFactory.setCriterias(criterias);
					clientFactory.setWeights(weights);
					clientFactory.setCalculations(calculations);
			  }
		  };
		  indexListService.getIndexList(indexes.toArray(new String[indexes.size()]), iCallback);
	}
	
	@Override
	public void displayIndexWeights (String name){
		//Launch a new view to diplay the weigths of the selected "index"
		int row = indexes.indexOf(name);
		History.newItem(Tokens.SHOWWEIGHTS + "&" + row);
	}
	
	@Override
	public void displayIndexPoints (String name) {
		
	}
	
	@Override
	public void removeIndex (String name) {
		
	}
}
