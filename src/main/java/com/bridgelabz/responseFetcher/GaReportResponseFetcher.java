package com.bridgelabz.responseFetcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import com.bridgelabz.model.GaReportInputModel;
import com.bridgelabz.model.ResponseModel;
import com.bridgelabz.model.SecretFileModel;
import com.bridgelabz.responseReader.ResponseReader;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;

public class GaReportResponseFetcher {

	// to store cSV file location
	static String csvFilePath;

	// creating object of InitializeAnalyticsReporting
	InitializeAnalyticsReporting initializeAnalyticsReportingObject = new InitializeAnalyticsReporting();

	// creating object of ResponseReader
	ResponseReader responseReaderObject = new ResponseReader();

	/*-------------------------method to get the response model ArrayList------------------------------------*/
	// to get CSv file path
	public GaReportResponseFetcher(SecretFileModel secretFileModelObject) {
		csvFilePath = secretFileModelObject.getCsvFilePath();
	}

	// default constructor
	public GaReportResponseFetcher() {

	}

	public ResponseModel getResponse(GaReportInputModel gaReportInputModel) {

		// creating object of ResponseModel
		ResponseModel responseModelObject = new ResponseModel();

		// calling initializeAnalyticsReporting method of
		// InitializeAnalyticsReporting class to initialize all credential
		try {
			AnalyticsReporting service = initializeAnalyticsReportingObject.initializeAnalyticsReporting();

			// calling getReport method to get response
			GetReportsResponse response = initializeAnalyticsReportingObject.getReport(service, gaReportInputModel);

			/*-----------------method to write the response into the text file-------------------------*/
		/*	File file = new File(csvFilePath + gaReportInputModel.getmGaDiscription() + ".txt");
			if (file.exists())
				file.delete();

			if (!file.exists())
				file.createNewFile();

			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
*/
			// printing the response
			System.out.println(response);

			// assigning response into variable response JSON of
			// GetReportsResponse type
			GetReportsResponse responsejson = response;
		/*	bw.write(responsejson.toString());
			bw.close();*/

			// reading response and placing it to responseModelArrayList
			responseModelObject = responseReaderObject.responseReader(responsejson.toString());

		} catch (Exception e) {
			e.printStackTrace();

		}

		return responseModelObject;
	}
}
