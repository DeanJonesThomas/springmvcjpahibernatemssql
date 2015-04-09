package com.java.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.authorize.Environment;
import net.authorize.Merchant;
import net.authorize.aim.Transaction;
import net.authorize.TransactionType;
import net.authorize.aim.Result;
import net.authorize.data.creditcard.CreditCard;
import net.authorize.sim.Fingerprint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.bean.EmployeeBean;
import com.java.model.Employee;
import com.java.model.Payment;
import com.java.service.EmployeeService;

/**
 * @author
 * 
 */
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	private String apiLoginId = "48HL4gsE";
	private String transactionKey = "934d7Sq89GEUafrg";
	//Secret Question Simon
	private String relayResponseUrl = "http://MERCHANT_HOST/relay_response.jsp";
	private String amount = "1.99";

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute EmployeeBean employeeBean,
			BindingResult result) {
		Employee employee = prepareModel(employeeBean);
		Random ran = new Random();
		employeeService.addEmployee(employee);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees",
				prepareListofBean(employeeService.listEmployeess()));
		return new ModelAndView("employeesList", model);
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ModelAndView listEmployees() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees",
				prepareListofBean(employeeService.listEmployeess()));
		return new ModelAndView("employeesList", model);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addEmployee(@ModelAttribute("addEmp") EmployeeBean employeeBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		/*model.put("employees",
				prepareListofBean(employeeService.listEmployeess()));*/
		return new ModelAndView("addEmployee", model);
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	public ModelAndView paymentMode(@ModelAttribute("payment") Payment payment) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("initialForm");
		
		return mv;
	}
	
	@RequestMapping(value = "/initial", method = RequestMethod.POST)
	public ModelAndView welcomeInitial(@ModelAttribute("payment") Payment payment, HttpServletRequest req, HttpServletResponse resp ) {

		

		
//		 Fingerprint fingerprint = Fingerprint.createFingerprint(apiLoginId, transactionKey, 111L, amount);
		 
		/*
		 * Fingerprint fingerprint = Fingerprint.createFingerprint(apiLoginId,
		 * transactionKey, 1234567890, amount);
		 * 
		 * long x_fp_sequence = fingerprint.getSequence(); long x_fp_timestamp =
		 * fingerprint.getTimeStamp(); String x_fp_hash =
		 * fingerprint.getFingerprintHash();
		 */

		ModelAndView mv = new ModelAndView();

		// setting the request params
		mv.addObject("apiLoginId", apiLoginId);
		mv.addObject("transactionKey", transactionKey);
		mv.addObject("relayResponseUrl", relayResponseUrl);
		mv.addObject("amount", amount);

		/*
		 * mv.addObject("x_fp_sequence",x_fp_sequence);
		 * mv.addObject("x_fp_timestamp",x_fp_timestamp);
		 * mv.addObject("x_fp_hash",x_fp_hash);
		 */

		// setting the view
		mv.setViewName("initialForm");
		
		String creditCardNumber =  req.getParameter("creditCardNumber");
		String creditCardExpr =  req.getParameter("exprDate");
		String ccHolder = req.getParameter("ccHolder");
		
		try {
			URL post_url = new URL("https://test.authorize.net/gateway/transact.dll");

			Hashtable post_values = new Hashtable();
			  
			// the API Login ID and Transaction Key must be replaced with valid values
			post_values.put ("x_login", apiLoginId);
			post_values.put ("x_tran_key",transactionKey);
			  
			post_values.put ("x_version", "3.1");
			post_values.put ("x_delim_data", "TRUE");
			post_values.put ("x_delim_char", "|");
			post_values.put ("x_relay_response", "FALSE");

			post_values.put ("x_type", "AUTH_CAPTURE");
			post_values.put ("x_method", "CC");
			post_values.put ("x_card_num", creditCardNumber);
			post_values.put ("x_exp_date", creditCardExpr);

			post_values.put ("x_amount", "19.99");
			post_values.put ("x_description", "Sample Transaction");

			post_values.put ("x_first_name", "John");
			post_values.put ("x_last_name", "Doe");
			post_values.put ("x_address", "1234 Street");
			post_values.put ("x_state", "WA");
			post_values.put ("x_zip", "98004");
			// Additional fields can be added here as outlined in the AIM integration
			// guide at: http://developer.authorize.net

			// This section takes the input fields and converts them to the proper format
			// for an http post.  For example: "x_login=username&x_tran_key=a1B2c3D4"
			StringBuffer post_string = new StringBuffer();
			Enumeration keys = post_values.keys();
			while( keys.hasMoreElements() ) {
			  String key = URLEncoder.encode(keys.nextElement().toString(),"UTF-8");
			  String value = URLEncoder.encode(post_values.get(key).toString(),"UTF-8");
			  post_string.append(key + "=" + value + "&");
			}

			
			// Open a URLConnection to the specified post url
			URLConnection connection = post_url.openConnection();
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			// this line is not necessarily required but fixes a bug with some servers
			connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

			// submit the post_string and close the connection
			DataOutputStream requestObject = new DataOutputStream( connection.getOutputStream() );
			requestObject.write(post_string.toString().getBytes());
			requestObject.flush();
			requestObject.close();

			// process and read the gateway response
			BufferedReader rawResponse = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			String responseData = rawResponse.readLine();
			rawResponse.close();	                     // no more data

			// split the response into an array
			String [] responses = responseData.split("\\|");

			// The results are output to the screen in the form of an html numbered list.
			System.out.println("<OL>");
			for(Iterator iter=Arrays.asList(responses).iterator(); iter.hasNext();) {
				System.out.println("<LI>" + iter.next() + "&nbsp;</LI>");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mv;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView editEmployee(
			@ModelAttribute("command") EmployeeBean employeeBean,
			BindingResult result) {
		
		employeeService.deleteEmployee(prepareModel(employeeBean));
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employees",
				prepareListofBean(employeeService.listEmployeess()));
		return new ModelAndView("employeesList", model);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(
			@ModelAttribute("addEmp") EmployeeBean employeeBean,
			BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("employee", prepareEmployeeBean(employeeService
				.getEmployee(employeeBean.getId())));
		model.put("employees",
				prepareListofBean(employeeService.listEmployeess()));
		return new ModelAndView("addEmployee", model);
	}
	
	@RequestMapping(value="/cc", method= RequestMethod.POST)
	public ModelAndView paymentForCC(){
		
		
		 Merchant merchant = Merchant.createMerchant(Environment.SANDBOX, apiLoginId, transactionKey);
		
		// create credit card
		CreditCard creditCard = CreditCard.createCreditCard();
	    creditCard.setCreditCardNumber("4111 1111 1111 1111");
	    creditCard.setExpirationMonth("12");
	    creditCard.setExpirationYear("2015");
	    
	 // create transaction
	    Transaction authCaptureTransaction = merchant.createAIMTransaction(
	        TransactionType.AUTH_CAPTURE, new BigDecimal(1.99));
	    ((net.authorize.aim.Transaction) authCaptureTransaction).setCreditCard(creditCard);

	    Result<Transaction> result = (Result<Transaction>)merchant.postTransaction(authCaptureTransaction);
	    if(result.isApproved()) {
	        System.out.println("Approved!</br>");
	        System. out.println("Transaction Id: " + ((net.authorize.aim.Transaction) result.getTarget()).getTransactionId());
	      } else if (result.isDeclined()) {
	    	  System.out.println("Declined.</br>");
	    	  System.out.println(result.getReasonResponseCode() + " : " + result.getResponseText());
	      } else {
	    	  System.out.println("Error.</br>");
	    	  System.out.println(result.getReasonResponseCode() + " : " + result.getResponseText());
	      }
		return new ModelAndView("addEmployee");
	}
	
	
	private Employee prepareModel(EmployeeBean employeeBean) {
		Employee employee = new Employee();
		employee.setEmpAddress(employeeBean.getAddress());
		employee.setEmpName(employeeBean.getName());
		employee.setEmpId(employeeBean.getId());
		employeeBean.setId(null);
		return employee;
	}

	private List<EmployeeBean> prepareListofBean(List<Employee> employees) {
		List<EmployeeBean> beans = null;
		if (employees != null && !employees.isEmpty()) {
			beans = new ArrayList<EmployeeBean>();
			EmployeeBean bean = null;
			for (Employee employee : employees) {
				bean = new EmployeeBean();
				bean.setName(employee.getEmpName());
				bean.setId(employee.getEmpId());
				bean.setAddress(employee.getEmpAddress());
				beans.add(bean);
			}
		}
		return beans;
	}

	private EmployeeBean prepareEmployeeBean(Employee employee) {
		EmployeeBean bean = new EmployeeBean();
		bean.setAddress(employee.getEmpAddress());
		bean.setName(employee.getEmpName());
		bean.setId(employee.getEmpId());
		return bean;
	}
}
