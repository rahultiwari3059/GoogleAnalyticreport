package com.bridgelabz.dao;

import java.util.ArrayList;

import com.bridgelabz.model.AllElementModels;
import com.bridgelabz.model.AppOpenModel;
import com.bridgelabz.model.AppReOpenModel;
import com.bridgelabz.model.SummaryReportModel;

public interface UserDao {
	public void Save(ArrayList<AllElementModels> responseElementModelArrayList);
	public void appOpenModelSave(ArrayList<AppOpenModel> appOpenModelArrayList) ;
	public void appReOpenModelSave(ArrayList<AppReOpenModel> appReOpenModelArrayList) ;
	public void summaryreportSave(ArrayList<SummaryReportModel> summaryReportModelArrayList);
}
