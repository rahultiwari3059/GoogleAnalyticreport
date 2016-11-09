package com.bridgelabz.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.Csvfilecreator.AppOpenModelSetter;
import com.bridgelabz.Csvfilecreator.AppReOpenModelSetter;
import com.bridgelabz.dao.HibernateDao;
import com.bridgelabz.inputReader.GaReprtInfoArrayList;
import com.bridgelabz.model.AllElementModels;
import com.bridgelabz.model.AppOpenModel;
import com.bridgelabz.model.AppReOpenModel;
import com.bridgelabz.model.GaReportInputModel;
import com.bridgelabz.model.ResponseModel;
import com.bridgelabz.model.SecretFileModel;
import com.bridgelabz.responseElementReader.ResponseElementReader;
import com.bridgelabz.responseFetcher.GaReportResponseFetcher;

import java.util.List;

@Controller
public class GoogleAnalyticReporting {
	static String csvfilepath;
	private static final String UPLOAD_DIRECTORY = "/home/bridgeit/Desktop/GoogleAnalyticwebproject/GoogleAnalyticReport/src/main/webapp/WEB-INF/FilePath/";
	@Resource(name = "hibernateDao")
	private HibernateDao hibernateDaoObject;
	

	@RequestMapping("/uploadform")
	public ModelAndView uploadForm() {
		System.out.println("File upload get method");
		return new ModelAndView("uploadform");
	}

	@RequestMapping(value = "savefile", method = RequestMethod.POST)

	public ModelAndView saveJsonFile(@RequestParam MultipartFile file, HttpSession session, Model model)
			throws Exception {
		System.out.println("File upload post method");
		FileCopyUtils.copy(file.getBytes(), new FileOutputStream(UPLOAD_DIRECTORY + "/" + file.getOriginalFilename()));
		String msg = "";
		try {

			// taking JSON file path
			String jsonfilepath = UPLOAD_DIRECTORY + "/" + file.getOriginalFilename();
			
			
			// creating object of ResponseModel
			ResponseModel responseModelObject = new ResponseModel();
			
			//creating object of SecretModelObject
			SecretFileModel secretFileModelObject= new SecretFileModel();

			// creating object of ResponseElementReader
			ResponseElementReader ResponseElementReader = new ResponseElementReader();

			// creating object of GaReportResponseFetcher
			GaReportResponseFetcher gaReportResponseFetcherObject = new GaReportResponseFetcher();

			// creating object GaReprtInfoArrayList class
			GaReprtInfoArrayList GaReprtInfoArrayListObject = new GaReprtInfoArrayList();
			// creating object of appReOpenModelArrayList
			ArrayList<AppReOpenModel> appReOpenModelArrayList = new ArrayList<AppReOpenModel>();
			// creating object of appOpenModelArrayList
			ArrayList<AppOpenModel> appOpenModelArrayList = new ArrayList<AppOpenModel>();

			// creating object of AppOpenModelSetter class
			AppOpenModelSetter appOpenModelSetterObject = new AppOpenModelSetter();
			// creating object of AppReOpenModelSetter class
			AppReOpenModelSetter appReOpenModelSetterObject = new AppReOpenModelSetter();

			// passing JSONpath and getting ArrayList of GaInputInfoModel class
			ArrayList<GaReportInputModel> gaReportInputInfoArrayList = GaReprtInfoArrayListObject
					.readInputJsonFile(jsonfilepath);
			
			
			csvfilepath=secretFileModelObject.getCsvFilePath();

		
			for (int i = 0; i < gaReportInputInfoArrayList.size(); i++) {

				// getting responseModel after passing one by one
				// gaReportInputInfoArrayList
				responseModelObject = gaReportResponseFetcherObject.getResponse(gaReportInputInfoArrayList.get(i));

				// calling responseElementReader method of ResponseElementReader
				// class
				ArrayList<AllElementModels> allElementModelArrayListObject = ResponseElementReader
						.responseElementReader(responseModelObject, gaReportInputInfoArrayList.get(i));

				// calling appOpenCSvCreatorObject method of
				// AppOpenModelcsvsetter class
				appOpenModelArrayList = appOpenModelSetterObject.appOpenCSvCreator(allElementModelArrayListObject,
						gaReportInputInfoArrayList.get(i));

				// calling appReopenCSvCreatorObject method of
				// AppReopenCSvCreator

				appReOpenModelArrayList = appReOpenModelSetterObject.appReopenCSvCreator(allElementModelArrayListObject,
						gaReportInputInfoArrayList.get(i));
				// calling save method of HibernateDao class

				hibernateDaoObject.Save(allElementModelArrayListObject);
				// allElementModelArrayListObject.clear();
			}

			// calling appOpenModelSave of hibernateDao class to put appOpen
			// data in database
			hibernateDaoObject.appOpenModelSave(appOpenModelArrayList);
			// calling appREOpenModelSave of hibernateDao class to put appReOpen
			// data in database
			hibernateDaoObject.appReOpenModelSave(appReOpenModelArrayList);

			// printing after completion of process
			System.out.println("all reports are created");

			List<AppReOpenModel> appReopenList = (List<AppReOpenModel>) hibernateDaoObject.getAppReOpen();
			model.addAttribute("appReopenList", appReopenList);
			
			List<AppOpenModel> appOpenList = (List<AppOpenModel>) hibernateDaoObject.getAppOpen();
			model.addAttribute("appOpenList", appOpenList);
			
			msg = "file uploaded successfully";
		}

		catch (Exception e) {
			msg = "Error occured";
			e.printStackTrace();
		}
		model.addAttribute("filesuccess", msg);
		return new ModelAndView("GaReportExplorer");

	}
	/*-------------------------------------------Download file to local storage for App ReOpen ------------------------------------------*/

	@RequestMapping(value = "AppReopenDownload", method = RequestMethod.GET)
	public void getReOpenCSvFile(
			HttpServletResponse response/* ,@PathVariable String value */) throws FileNotFoundException {

		try {
			
			// writing download file in byte
			File file = new File(csvfilepath+"GAReportappReopen.csv");
			byte[] byteArray = new byte[(int) file.length()];
			byteArray = FileUtils.readFileToByteArray(file);
			// end of writing to bytes

			response.setHeader("Content-disposition", "attachment; filename=\"" + "GAReportappReopen.csv" + "\"");
			FileCopyUtils.copy(byteArray, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*-------------------------------------------Download file to local storage for App Open-------------------------------------------*/

	@RequestMapping(value = "AppOpenDownload", method = RequestMethod.GET)
	public void getOpenCSvFile(
			HttpServletResponse response/* ,@PathVariable String value */) throws FileNotFoundException {
		// fileMeta = files.get(Integer.parseInt(value));

		try {
			
			// writing download file in byte
			File file = new File(csvfilepath+"GAReportAppOpen.csv");
			byte[] byteArray = new byte[(int) file.length()];
			byteArray = FileUtils.readFileToByteArray(file);
			// end of writing to bytes

			response.setHeader("Content-disposition", "attachment; filename=\"" + "GAReportappOpen.csv" + "\"");
			FileCopyUtils.copy(byteArray, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*-------------------------------------------Download file to local storage for summary ------------------------------------------*/

	@RequestMapping(value = "SumaaryReportDownload", method = RequestMethod.GET)
	public void getSummaryReportCSvFile(
			HttpServletResponse response/* ,@PathVariable String value */) throws FileNotFoundException {

		try {
			
			// writing download file in byte
			File file = new File(csvfilepath+"summaryreport.csv");
			byte[] byteArray = new byte[(int) file.length()];
			byteArray = FileUtils.readFileToByteArray(file);
			// end of writing to bytes

			response.setHeader("Content-disposition", "attachment; filename=\"" + "summaryreport.csv" + "\"");
			FileCopyUtils.copy(byteArray, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}