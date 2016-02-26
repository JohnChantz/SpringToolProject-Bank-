package controllers;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.Customer;
import model.CustomerDAO;
import model.Request;
import model.RequestDAO;

@Controller
public class EmployeeController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("myBeans.xml");
	Customer customer = (Customer)context.getBean("customer");
	CustomerDAO customerDAO = (CustomerDAO)context.getBean("customerDAO");
	Request req = (Request)context.getBean("request");
	RequestDAO reqDAO = (RequestDAO)context.getBean("requestDAO");
	
	@RequestMapping(value="/employeePage",method=RequestMethod.GET)		//if /employeePage is asked redirect to /login from login and validation 
	public ModelAndView employeePage(){
		ModelAndView mav = new ModelAndView();
		ArrayList<Request> reqList = reqDAO.getCustomerCreatedRequests();
		mav.addObject("reqList", reqList);
		mav.setViewName("employeePage");
		return mav;
	}
	
	@RequestMapping(value="/saveNewRequest",method=RequestMethod.POST)		//this path is used for posting form data from displayCustomerPage view
	public ModelAndView saveNewRequest(@ModelAttribute("req") Request req,ModelMap model){
		int result=0;
		ModelAndView mav = new ModelAndView();		//initialize ModelAndView
		Customer customer = customerDAO.getCustomerById(req.getCustomerId());		//get customers data from the view
		mav.setViewName("newRequestForm");
		mav.addObject("customer", customer);
		if(req.getSalary() >= 600){
			result=reqDAO.addNewRequest(req);				//if request doesnt exist add new request
			if(result != 0){		//if new request was added successfully inform user
				mav.addObject("message", "<div class=\"alert alert-success\"><strong>Success!</strong>New request succeffully saved to database!</div>");		
			}else{					//if new request wasnt adding successfully inform user
				mav.addObject("message", "<div class=\"alert alert-danger\"><strong>Warning!</strong>Failed to save new request to the database!</div>");
			}
		}else{
			mav.addObject("message", "<div class=\"alert alert-danger\"><strong>Warning!</strong>Customer's salary must be greater than 600 euros</div>");
		}
		return mav;
	}
	
	@RequestMapping(value="/checkCustomer",method=RequestMethod.POST)		//this path is used for posting form's data from employeePage view
	public ModelAndView checkCustomer(@RequestParam("adt") String adt,@RequestParam("id") int id,@RequestParam("afm") int afm,Model model){
		Customer customer = null;
		if(!adt.isEmpty()){
			System.err.println(adt.isEmpty());
			 customer = customerDAO.getCustomerByAdt(adt);		//get customer with adt from db
		}else if(afm != 0){
			System.err.println(afm);
			 customer = customerDAO.getCustomerByAfm(afm);		//get customer with adt from db
		}else if(id != 0){
			System.err.println(id);
			 customer = customerDAO.getCustomerById(id);		//get customer with adt from db
		}
		ModelAndView mav = new ModelAndView();
		if(customer == null){		//if customer wasnt found inform user
			mav.addObject("message1","<div class=\"alert alert-danger\"><strong>Warning!</strong>No customer found with the data you inserted. Please try again...</div>");
			ArrayList<Request> reqList = reqDAO.getCustomerCreatedRequests();
			mav.addObject("reqList", reqList);
			mav.setViewName("employeePage");
//			mav.setViewName("redirect:/employeePage");
			return mav;
		}else{		//if customer found	
			if(!reqDAO.requestExistByCId(customer.getId())){		//if request submitted already exist inform user
				Request req = new Request();		//add new request object for handling data inserted in the form from page customerFoundPage
				mav.addObject("customer", customer);
				model.addAttribute("req", req);
				mav.setViewName("newRequestForm");
			}else{
				mav.addObject("message1","<div class=\"alert alert-danger\"><strong>Warning!</strong>This customer has already submitted a request... Check the table bellow.</div>");
				ArrayList<Request> reqList = reqDAO.getCustomerCreatedRequests();
				mav.addObject("reqList", reqList);
				mav.setViewName("employeePage");
			}
		}
		return mav;
	}
	
	@RequestMapping(value="/displayRequest",method=RequestMethod.POST)	//when a request is choosed from pending requests list then its data are being displayed in a new page for employee to inspect them
	public ModelAndView displayRequest(@RequestParam("id") int id){
		ModelAndView mav = new ModelAndView();
		Request req = reqDAO.getRequestById(id);		//get request with the given ID
		if(req != null){
			mav.addObject("req",req);
			mav.addObject("customer",customerDAO.getCustomerById(req.getCustomerId()));		//get customer's data using request's customer_id field
			mav.setViewName("reviewRequest");
		}else{
			mav.addObject("message2","<div class=\"alert alert-danger\"><strong>Warning!</strong>No request found with ID=" + id + ", please try again with a valid ID.</div>");
			ArrayList<Request> reqList = reqDAO.getPendingRequests();		//get all pending request and display them in employees page
			mav.addObject("reqList", reqList);
			mav.setViewName("employeePage");
		}
		return mav;
	}
	
	@RequestMapping(value="/updateRequest",method=RequestMethod.POST)		//when an employee makes changes to the request this method is called for storing the changes made
	public ModelAndView checkOnlineRequest(@ModelAttribute("req") Request req){
		ModelAndView mav = new ModelAndView();
		int result = reqDAO.updateRequest(req);
		mav.addObject("customer",customerDAO.getCustomerById(req.getCustomerId()));
		if(result != 0){
			mav.addObject("message","<div class=\"alert alert-success\"><strong>Success!</strong>Request updated successfully...</div>");
			mav.setViewName("reviewRequest");
		}else{
			mav.addObject("message","<div class=\"alert alert-danger\"><strong>Warning!</strong>Request could not be updated due to an internal error, please try again later...</div>");
			mav.setViewName("reviewRequest");
		}
		return mav;
	}

}
