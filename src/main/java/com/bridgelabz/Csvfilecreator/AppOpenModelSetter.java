package com.bridgelabz.Csvfilecreator;

import java.util.ArrayList;

import com.bridgelabz.model.AllElementModels;
import com.bridgelabz.model.AppOpenModel;
import com.bridgelabz.model.GaReportInputModel;

public class AppOpenModelSetter {

	ArrayList<AppOpenModel> appOpenModelArrayList = new ArrayList<AppOpenModel>();

	public ArrayList<AppOpenModel> appOpenCSvCreator(ArrayList<AllElementModels> allElementModelArrayListObject,
			GaReportInputModel gaReportInputModel) {
		try {
			if (gaReportInputModel.mGaID.equals("1")) {

				for (int i = 0; i < allElementModelArrayListObject.size(); i++) {

					// creating object of AppOpenModel
					AppOpenModel appOpenModelObject = new AppOpenModel();
					/*------------now setting values to the appOpenmodel class_-------------------*/
					appOpenModelObject.setmGaId(gaReportInputModel.getmGaID());

					appOpenModelObject.setmGadiscription(gaReportInputModel.getmGaDiscription());

					appOpenModelObject.setmDate(allElementModelArrayListObject.get(i).getmDate());

					appOpenModelObject.setmAndroidId(allElementModelArrayListObject.get(i).getmAndroidId());

					appOpenModelObject.setmEventcategory(allElementModelArrayListObject.get(i).getmEventCategory());

					//appOpenModelObject.setmTotalEvents(allElementModelArrayListObject.get(i).getmTotalEvents());

					appOpenModelArrayList.add(appOpenModelObject);
				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}
		return appOpenModelArrayList;
	}

}
