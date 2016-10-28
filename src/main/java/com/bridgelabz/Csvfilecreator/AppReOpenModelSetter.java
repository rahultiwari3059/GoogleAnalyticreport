package com.bridgelabz.Csvfilecreator;

import java.util.ArrayList;

import com.bridgelabz.model.AllElementModels;
import com.bridgelabz.model.AppReOpenModel;
import com.bridgelabz.model.GaReportInputModel;

public class AppReOpenModelSetter {
	ArrayList<AppReOpenModel> appReOpenModelArrayList = new ArrayList<AppReOpenModel>();

	public ArrayList<AppReOpenModel> appReopenCSvCreator(ArrayList<AllElementModels> allElementModelArrayListObject,
			GaReportInputModel gaReportInputModel) {
		try {
			if (gaReportInputModel.mGaID.equals("2")) {

				for (int i = 0; i < allElementModelArrayListObject.size(); i++) {
					// creating object of AppReOpenModel
					AppReOpenModel appReOpenModelObject = new AppReOpenModel();
					/*------------now setting values to the appOpenModel class_-------------------*/
					appReOpenModelObject.setmGaId(gaReportInputModel.getmGaID());

					appReOpenModelObject.setmGadiscription(gaReportInputModel.getmGaDiscription());

					appReOpenModelObject.setmDate(allElementModelArrayListObject.get(i).getmDate());

					appReOpenModelObject.setmAndroidId(allElementModelArrayListObject.get(i).getmAndroidId());

					appReOpenModelObject.setmEventcategory(allElementModelArrayListObject.get(i).getmEventCategory());

					//appReOpenModelObject.setmTotalEvents(responseElementModelArrayList.get(i).getmTotalEvents());

					appReOpenModelArrayList.add(appReOpenModelObject);
				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return appReOpenModelArrayList;
	}
}
