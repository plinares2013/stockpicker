package com.baysphere.stockpicker.server;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskHandle;
import com.google.appengine.api.taskqueue.TaskOptions;

//TODO: need to restrict this scheduled task to admin only in web.xml
public class ScheduledInit extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(ScheduledInit.class.getName());
	boolean weightTableInitialized = false , criteriaTableInitialized = false, foundDefault = false;
	PersistenceManager pm = PMF.get().getPersistenceManager();
	WeightTable weightTable = new WeightTable();
	CriteriaTable criteriaTable = new CriteriaTable();
	
	public void doGet (HttpServletRequest req, HttpServletResponse resp) {
		logger.log(Level.INFO, "Ready to run scheduled init task");
		
		//Query q = pm.newQuery(WeightTable.class, "user==u");
		//q.declareParameters("com.google.appengine.api.users.User u");

		// Read Index from datastore
		 
		Query q = pm.newQuery(Index.class);
		q.setFilter("name == indexName");
		q.declareParameters("String indexName");
		List<Index> indexList = (List<Index>) q.execute("default");
		for (Index index : indexList) {
			if (index.getName().equals("default") ) {
				foundDefault = true;
				break;
			}
		}
		
		// Initialize weight table if it is not initialized
				if (foundDefault == false) {
					weightTable.setUser (UtilityClass.getUser());
					//Force boolean to false in order to always do the init
					//weightTable.setInitialized(true);
					weightTable.setPriceSalesWeight(8);
					weightTable.setPriceBookWeight(7);
					weightTable.setProfitMarginWeight(10);
					weightTable.setROEWeight(8);
					weightTable.setROAWeight(6);
					weightTable.setDebtEquityWeight(5);
					weightTable.setQuickWeight(7);
					weightTable.setCurrentWeight(5);
					weightTable.setOneYearEPSWeight(9);
					weightTable.setThreeYearEPSWeight(7);
					weightTable.setFiveYearEPSWeight(5);
					weightTable.setFreeCashFlowPerShareWeight(8);
					weightTable.setManagementOwnershipWeight(8);
					weightTable.setQuarterlyEPSWeight(10);
					weightTable.setPEGRatioWeight(5);
					weightTable.setEPSQuarterlyGrowthYoYWeight(10);
					
					// Store in datastore
					//	pm.makePersistent(weightTable);
				}
				

			 
				//Initialize CriteriaTable if not initialized
				if (foundDefault == false) {
					criteriaTable.setUser(UtilityClass.getUser());
					// Always set the boolean as false to force the initialization
					//criteriaTable.setInitialized(true);
					criteriaTable.setPriceSalesLevel1(1);
					criteriaTable.setPriceSalesLevel2(2);
					criteriaTable.setPriceSalesLevel3(5);
					criteriaTable.setPriceSalesLevel4(1000);
					criteriaTable.setPriceSalesLevel1Points(10);
					criteriaTable.setPriceSalesLevel2Points(5);
					criteriaTable.setPriceSalesLevel3Points(1);
					criteriaTable.setPriceSalesLevel4Points(0);
					
					criteriaTable.setPriceBookLevel1(1);
					criteriaTable.setPriceBookLevel2(2.5);
					criteriaTable.setPriceBookLevel3(5);
					criteriaTable.setPriceBookLevel4(1000);
					criteriaTable.setPriceBookLevel1Points(10);
					criteriaTable.setPriceBookLevel2Points(5);
					criteriaTable.setPriceBookLevel3Points(1);
					criteriaTable.setPriceBookLevel4Points(0);
					
					criteriaTable.setProfitMarginLevel1(10);
					criteriaTable.setProfitMarginLevel2(15);
					criteriaTable.setProfitMarginLevel3(25);
					criteriaTable.setProfitMarginLevel4(100);
					criteriaTable.setProfitMarginLevel1Points(1);
					criteriaTable.setProfitMarginLevel2Points(5);
					criteriaTable.setProfitMarginLevel3Points(8);
					criteriaTable.setProfitMarginLevel4Points(10);
					
					criteriaTable.setROELevel1(10);
					criteriaTable.setROELevel2(20);
					criteriaTable.setROELevel3(30);
					criteriaTable.setROELevel4(100);
					criteriaTable.setROELevel1Points(1);
					criteriaTable.setROELevel2Points(5);
					criteriaTable.setROELevel3Points(8);
					criteriaTable.setROELevel4Points(10);
					
					criteriaTable.setROALevel1(5);
					criteriaTable.setROALevel2(10);
					criteriaTable.setROALevel3(20);
					criteriaTable.setROALevel4(100);
					criteriaTable.setROALevel1Points(1);
					criteriaTable.setROALevel2Points(3);
					criteriaTable.setROALevel3Points(5);
					criteriaTable.setROALevel4Points(10);
					
					criteriaTable.setDebtEquityLevel1(10);
					criteriaTable.setDebtEquityLevel2(25);
					criteriaTable.setDebtEquityLevel3(50);
					criteriaTable.setDebtEquityLevel4(100);
					criteriaTable.setDebtEquityLevel1Points(10);
					criteriaTable.setDebtEquityLevel2Points(8);
					criteriaTable.setDebtEquityLevel3Points(5);
					criteriaTable.setDebtEquityLevel4Points(1);
					
					criteriaTable.setCurrentLevel1(1);
					criteriaTable.setCurrentLevel2(2);
					criteriaTable.setCurrentLevel3(3);
					criteriaTable.setCurrentLevel4(100);
					criteriaTable.setCurrentLevel1Points(1);
					criteriaTable.setCurrentLevel2Points(5);
					criteriaTable.setCurrentLevel3Points(8);
					criteriaTable.setCurrentLevel4Points(10);
					
					criteriaTable.setOneYearEPSLevel1(10);
					criteriaTable.setOneYearEPSLevel2(20);
					criteriaTable.setOneYearEPSLevel3(30);
					criteriaTable.setOneYearEPSLevel4(100);
					criteriaTable.setOneYearEPSLevel1Points(1);
					criteriaTable.setOneYearEPSLevel2Points(3);
					criteriaTable.setOneYearEPSLevel3Points(5);
					criteriaTable.setOneYearEPSLevel4Points(10);
					
					criteriaTable.setManagementOwnershipLevel1(5);
					criteriaTable.setManagementOwnershipLevel2(10);
					criteriaTable.setManagementOwnershipLevel3(30);
					criteriaTable.setManagementOwnershipLevel4(100);
					criteriaTable.setManagementOwnershipLevel1Points(1);
					criteriaTable.setManagementOwnershipLevel2Points(3);
					criteriaTable.setManagementOwnershipLevel3Points(5);
					criteriaTable.setManagementOwnershipLevel4Points(10);
					
					criteriaTable.setQuarterlyEPSLevel1(10);
					criteriaTable.setQuarterlyEPSLevel2(20);
					criteriaTable.setQuarterlyEPSLevel3(30);
					criteriaTable.setQuarterlyEPSLevel4(100);
					criteriaTable.setQuarterlyEPSLevel1Points(1);
					criteriaTable.setQuarterlyEPSLevel2Points(3);
					criteriaTable.setQuarterlyEPSLevel3Points(5);
					criteriaTable.setQuarterlyEPSLevel4Points(10);
					
					criteriaTable.setQuickRatioLevel1(1);
					criteriaTable.setQuickRatioLevel2(2);
					criteriaTable.setQuickRatioLevel3(3);
					criteriaTable.setQuickRatioLevel4(5);
					criteriaTable.setQuickRatioLevel1Points(1);
					criteriaTable.setQuickRatioLevel2Points(5);
					criteriaTable.setQuickRatioLevel3Points(8);
					criteriaTable.setQuickRatioLevel4Points(10);
					
					criteriaTable.setPEGRatioLevel1(1);
					criteriaTable.setPEGRatioLevel2(2);
					criteriaTable.setPEGRatioLevel3(3);
					criteriaTable.setPEGRatioLevel4(5);
					criteriaTable.setPEGRatioLevel1Points(10);
					criteriaTable.setPEGRatioLevel2Points(5);
					criteriaTable.setPEGRatioLevel3Points(3);
					criteriaTable.setPEGRatioLevel4Points(0);
					
					criteriaTable.setEPSQuarterlyGrowthYoYLevel1(5);
					criteriaTable.setEPSQuarterlyGrowthYoYLevel2(30);
					criteriaTable.setEPSQuarterlyGrowthYoYLevel3(50);
					criteriaTable.setEPSQuarterlyGrowthYoYLevel4(90);
					criteriaTable.setEPSQuarterlyGrowthYoYLevel1Points(0);
					criteriaTable.setEPSQuarterlyGrowthYoYLevel2Points(3);
					criteriaTable.setEPSQuarterlyGrowthYoYLevel3Points(5);
					criteriaTable.setEPSQuarterlyGrowthYoYLevel4Points(10);
					
					//Persist in datastore
					try {
						//pm.makePersistent(criteriaTable);
					} finally {
						//pm.close();
					}
				
				//Initialize the Index which is composed of 3 elements:
				// 1 criteriaTable + 1 WeightTable + 1 calculation
					if (foundDefault == false) {
						Index index = new Index();
						index.setName("default");
						index.setCreateDate(new Date());
						index.setCriteriaTable(criteriaTable);
						index.setWeightTable(weightTable);
						index.setCalculation(StockCalculateAllIndexes.class.getName());	
						//Store Index in datastore and the owned relationship should be stored as well,
						pm.makePersistent(index);
					}
					
					//Launch the process of getting list of stock symbols (tickers)
					Queue defaultQueue = QueueFactory.getDefaultQueue();
					TaskHandle handle = defaultQueue.add(TaskOptions.Builder.withUrl("/stockpicker/tickerlist").method(TaskOptions.Method.valueOf("GET")));

	}

  }
}
