package controllers;

import java.util.ArrayList;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import model.Customer;
import model.CustomerDAO;
import model.NewsletterEmailDAO;
import model.OnlineAccount;
import model.OnlineAccountDAO;
import model.Request;
import model.RequestDAO;

@Controller
public class ApiController {
	
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("myBeans.xml");
	OnlineAccountDAO accDAO = context.getBean("OnlineAccountDAO",OnlineAccountDAO.class);
	RequestDAO reqDAO = context.getBean("requestDAO",RequestDAO.class);
	CustomerDAO custDAO = context.getBean("customerDAO",CustomerDAO.class);
	NewsletterEmailDAO mailDAO = context.getBean("newsletterEmailDAO",NewsletterEmailDAO.class);
	
	@RequestMapping(value="/onlineAccounts",method=RequestMethod.GET,produces="application/json")		//returns all online Accounts of users
	public @ResponseBody ArrayList<OnlineAccount> getAccounts(){
		ArrayList<OnlineAccount> accList = accDAO.getAllAccounts();
		return accList;
	}
	
	@RequestMapping(value="/checkAccount",method=RequestMethod.POST,produces="application/json")		//check for an online account with the username and password given by the client
	public @ResponseBody OnlineAccount checkAccount(@RequestParam("username") String username,@RequestParam("password") String password ){
		OnlineAccount account = accDAO.checkAccount(username, password);
		return account;
	}
	
	@RequestMapping(value="/addRequest",method=RequestMethod.POST,produces="application/json")		//takes as input a request coming from the client and returns the request that was added to the database
	public @ResponseBody Request addRequest(@RequestBody Request request  /*needs json incoming*/){
		if(reqDAO.addNewRequest(request) != 0){		//if method does not return 0 then request was added successfully
			return request;
		}else{
			return null;
		}
	}
	
	@RequestMapping(value="/getRequest",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody Request getRequest(@RequestBody OnlineAccount acc){	//takes a customers id as input and return the request of this customeor null if request doesnt exist
		if(reqDAO.requestExistByCId(acc.getCustomerId())){
			return reqDAO.getRequestByCustomerId(acc.getCustomerId());
		}else{
			return null;
		}
	}
	
	@RequestMapping(value="/getCustomer",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody Customer getCustomer(@RequestBody OnlineAccount acc){		//takes a customers id as input and return customer data or null
		Customer cust = custDAO.getCustomerById(acc.getCustomerId());
		if(cust != null){
			return cust;
		}else{
			return null;
		}
	}
	
	@RequestMapping(value="/checkRequest",method=RequestMethod.POST)
	public @ResponseBody boolean checkRequest(@RequestBody OnlineAccount acc){		//takes customer's id as input and return a boolean representing if request exist or not
		if(reqDAO.requestExistByCId(acc.getCustomerId())){
			return true;
		}else{
			return false;
		}
	}
	
	@RequestMapping(value="/submitMail",method=RequestMethod.POST)
	public @ResponseBody int submitMail(@RequestBody String email){		//adds to the database of newslettter email a new email submitted by the client
		return mailDAO.addEmailToList(email);
	}
	
}
