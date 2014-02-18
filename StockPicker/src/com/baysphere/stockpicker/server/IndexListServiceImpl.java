package com.baysphere.stockpicker.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.baysphere.stockpicker.client.NotLoggedInException;
import com.baysphere.stockpicker.client.services.IndexListService;
import com.baysphere.stockpicker.shared.CriteriaTableSubset;
import com.baysphere.stockpicker.shared.IndexInformation;
import com.baysphere.stockpicker.shared.WeightTableSubset;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class IndexListServiceImpl extends RemoteServiceServlet implements
		IndexListService {
	
	private static final Logger logger = Logger.getLogger(IndexListServiceImpl.class.getName());
	


	@Override
	public IndexInformation[] getIndexList(String[] strings) throws NotLoggedInException {
		//TODO: put in place the login requirement
		//UtilityClass.checkLoggedIn();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Index> indexList = new ArrayList<Index>();
		// For each index, read information from datastore
		for (String name : strings) {
			// Prepare request
			try {
				//Query q = pm.newQuery(Index.class, "user==u");
				//q.declareParameters("com.google.appengine.api.users.User u");
				Query q = pm.newQuery(Index.class);
				q.setFilter("name == indexName");
				q.declareParameters("String indexName");
				q.setOrdering("createDate");
				List<Index> indexResults = (List<Index>) q.execute(name);
				for (Index one : indexResults) {
					indexList.add(one);
				}
			} catch (Exception e) {
				logger.log(Level.WARNING,"Error reading Index list from data store");
			}
		}

		// Create IndexInformation from indexList
		List<IndexInformation> indexInformation = new ArrayList<IndexInformation>();
		
		for (Index one : indexList) {
			IndexInformation infoOne = new IndexInformation();
			CriteriaTableSubset ctSub = new CriteriaTableSubset();
			WeightTableSubset wtSub = new WeightTableSubset();
			//Retrieve CriteriaTableinfo
			ctSub.setCurrentLevel1(one.getCriteriaTable().getCurrentLevel1());
			ctSub.setCurrentLevel2(one.getCriteriaTable().getCurrentLevel2());
			ctSub.setCurrentLevel3(one.getCriteriaTable().getCurrentLevel3());
			ctSub.setCurrentLevel4(one.getCriteriaTable().getCurrentLevel4());
			ctSub.setDebtEquityLevel1(one.getCriteriaTable().getDebtEquityLevel1());
			ctSub.setDebtEquityLevel2(one.getCriteriaTable().getDebtEquityLevel2());
			ctSub.setDebtEquityLevel3(one.getCriteriaTable().getDebtEquityLevel3());
			ctSub.setDebtEquityLevel4(one.getCriteriaTable().getDebtEquityLevel4());
			ctSub.setEPSQuarterlyGrowthYoYLevel1(one.getCriteriaTable().getEPSQuarterlyGrowthYoYLevel1());
			ctSub.setEPSQuarterlyGrowthYoYLevel2(one.getCriteriaTable().getEPSQuarterlyGrowthYoYLevel2());
			ctSub.setEPSQuarterlyGrowthYoYLevel3(one.getCriteriaTable().getEPSQuarterlyGrowthYoYLevel3());
			ctSub.setEPSQuarterlyGrowthYoYLevel4(one.getCriteriaTable().getEPSQuarterlyGrowthYoYLevel4());
			//Retrieve WeightTable info
			wtSub.setCurrentWeight(one.getWeightTable().getCurrentWeight());
			wtSub.setDebtEquityWeight(one.getWeightTable().getDebtEquityWeight());
			wtSub.setEPSQuarterlyGrowthYoYWeight(one.getWeightTable().getEPSQuarterlyGrowthYoYWeight());
			wtSub.setFiveYearEPSWeight(one.getWeightTable().getFiveYearEPSWeight());
			wtSub.setFreeCashFlowPerShareWeight(one.getWeightTable().getFreeCashFlowPerShareWeight());
			wtSub.setManagementOwnershipWeight(one.getWeightTable().getManagementOwnershipWeight());
			wtSub.setOneYearEPSWeight(one.getWeightTable().getOneYearEPSWeight());
			wtSub.setPEGRatioWeight(one.getWeightTable().getPEGRatioWeight());
			wtSub.setPERatioWeight(one.getWeightTable().getPERatioWeight());
			wtSub.setPriceBookWeight(one.getWeightTable().getPriceBookWeight());
			wtSub.setPriceSalesWeight(one.getWeightTable().getPriceSalesWeight());
			wtSub.setProfitMarginWeight(one.getWeightTable().getProfitMarginWeight());
			wtSub.setQuarterlyEPSWeight(one.getWeightTable().getQuarterlyEPSWeight());
			wtSub.setQuickWeight(one.getWeightTable().getQuickWeight());
			wtSub.setROAWeight(one.getWeightTable().getROAWeight());
			wtSub.setROEWeight(one.getWeightTable().getROEWeight());
			wtSub.setThreeYearEPSWeight(one.getWeightTable().getThreeYearEPSWeight());
			//Fill out the IndexInformation
			infoOne.setName(one.getName());
			infoOne.setCriteriaTableSubset(ctSub);
			infoOne.setWeightTableSubset(wtSub);
			infoOne.setAccuracy(one.getAccuracy());
			infoOne.setCalculationMode("StockCalculateAllIndexes");
			
			//Add to the ArrayList of IndexInfo to send back to GUI
			indexInformation.add(infoOne);
		}
		
		return (IndexInformation []) indexInformation.toArray(new IndexInformation[0]);
	}

	@Override
	public IndexInformation getIndex(String indexName) throws NotLoggedInException{
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		// TODO Auto-generated method stub
		return null;
	}

}
