package gr.hua.client;

import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	public static final String SERVER_URL="http://localhost:8080/dist_syst_proj/";		//location of the server for use of API's internet services
	
	ApplicationContext context = new ClassPathXmlApplicationContext("myBeans.xml");
	Request req = (Request)context.getBean("request");
	Customer cust = (Customer)context.getBean("customer");
	
	@RequestMapping(value = "/", method = RequestMethod.GET)		//returns homePage view when root is called
	public String home(Model model,@ModelAttribute("response")String response) {
		model.addAttribute("acc",new Account());		//model attribute for jsp form usage
		model.addAttribute("response",response);		//message from submitMail method
		return "homePage";
	}
	
	@RequestMapping(value = "/login" , method = RequestMethod.POST)		//check username password while performing a login
	public ModelAndView login(@ModelAttribute("acc")Account acc,HttpServletRequest httpRequest){
		ModelAndView mav = new ModelAndView();
		String uri = SERVER_URL+"checkAccount";
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String,String> param = new LinkedMultiValueMap<String,String>();
		param.add("username", acc.getUsername());
		param.add("password", acc.getPassword());
		Account responseAcc = restTemplate.postForObject(uri, param, Account.class);		//make use of API service "checkAccount"
		if(responseAcc != null){		//if account data is correct 
			httpRequest.getSession().setAttribute("account", responseAcc);	//add account as sessionAttribute to be accessible by all pages
			mav.setViewName("userPage");
		}else{
			mav.addObject("message","<div style=\"visibility:visible\" class=\"alert alert-danger\"><strong>Warning!</strong> <p>No user found! Please try again with a valid account...</p></div>");
			mav.setViewName("homePage");
		}
		return mav;
	}
	
	@RequestMapping(value = "/addRequest", method = RequestMethod.POST)
    //add new request to database using API services
    public ModelAndView addRequest(@ModelAttribute("req") Request req, HttpServletRequest httpRequest) {
        ModelAndView mav = new ModelAndView();
        Account acc = (Account) httpRequest.getSession().getAttribute("account");        //gets account data
        if (acc != null) {        //if user is logged in
            req.setCustomerId(acc.getCustomerId());        //add customer_id field to the request
            req.setCreatedBy("customer");
            if (req.getSalary() >= 600) {
                RestTemplate restTemplate = new RestTemplate();
                Request reqResponse = restTemplate.postForObject(SERVER_URL + "addRequest", req, Request.class);    //use "addRequest" API service
                if (reqResponse != null) {    //if not null, request was successfully added, create user messages
                    mav.addObject("message1", "<div style=\"visibility:visible\" class=\"alert alert-success\"><strong>Success!</strong> <p>Your request has been submitted suuccessfully...</p></div>");
                    mav.setViewName("userPage");
                } else {
                    mav.addObject("message1", "<div style=\"visibility:visible\" class=\"alert alert-danger\"><strong>Warning!</strong> <p>Your request could not be submitted due to an internal error, please try again later...</p></div>");
                    mav.setViewName("userPage");
                }
            } else {
            	mav.addObject("message","<div style=\"visibility:visible\" class=\"alert alert-danger\"><strong>Warning!</strong> <p>Your salary must be greater than 600 euros...</p></div>");
            	mav.addObject("req", new Request());
            	mav.setViewName("newRequestForm");
            }
        } else {        //if no user logged in return to homePage, create user message
            mav.addObject("message", "<div style=\"visibility:visible\" class=\"alert alert-danger\"><strong>Warning!</strong> <p>Connection expired, please log in...</p></div>");
            mav.setViewName("homePage");
        }
        return mav;
    }
	
	@RequestMapping(value="/newRequestForm",method=RequestMethod.GET)		//return request form when submit request service is asked
	public String newRequestPage(Model model,HttpServletRequest httpRequest){
		Account account = (Account)httpRequest.getSession().getAttribute("account");	//get account data
		if(account != null){		//if user logged in
			RestTemplate restTemplate = new RestTemplate();
			boolean response = restTemplate.postForObject(SERVER_URL+"checkRequest", account, Boolean.class);		//use "checkRequest" API service
			if(!response){		//if response true, then request already exists
				model.addAttribute("req",new Request());
				return "newRequestForm";
			}else{
				model.addAttribute("message1","<div style=\"visibility:visible\" class=\"alert alert-danger\"><strong>Warning!</strong> <p>You already have submitted a request...</p><p>Please use \"Inspect Request\" button to inspect your request...</p></div>");
				return "userPage";
			}
		}else{		//if no user logged in return to homePage
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/inspectRequest",method=RequestMethod.GET)		//return the request form of the user when inspect request is asked
	public ModelAndView inspectRequest(HttpServletRequest httpRequest){
		ModelAndView mav = new ModelAndView();
		Account acc = (Account)httpRequest.getSession().getAttribute("account");
		if(acc != null){		//if user logged in
			mav.setViewName("inspectRequest");		//return inspectRequest view
			RestTemplate restTemplate = new RestTemplate();
			Request req = restTemplate.postForObject(SERVER_URL+"getRequest", acc, Request.class);		//get request data using API from server
			Customer cust = restTemplate.postForObject(SERVER_URL+"getCustomer", acc, Customer.class);	//get user/customer data using API from server
			if(req != null){		//req is not null when s request is found for the user
				mav.addObject("req", req);
				mav.addObject("cust", cust);
				if(req.getRequestStatus().equals("Accepted")){		//create user messages
					mav.addObject("message","<div style=\"visibility:visible\" class=\"alert alert-success\"><strong>Success!</strong><p>Your request has been accepted...</p></div>");
				}else if(req.getRequestStatus().equals("Rejected")){
					mav.addObject("message","<div style=\"visibility:visible\" class=\"alert alert-danger\"><strong>Warning!</strong><p>Your request has been rejected...</p></div>");
				}else if(req.getRequestStatus().equals("Pending")){
					mav.addObject("message","<div style=\"visibility:visible\" class=\"alert alert-info\"><strong>Info!</strong><p>Your request is under processing by our employees...</p></div>");
				}
			}else{		//if user has no request submitted create user message
				mav.addObject("message2","<div style=\"visibility:visible\" class=\"alert alert-info\"><strong>Info!</strong><p>Not request was found, please submit a request first using \"Submit Request\" button.</p></div>");
				mav.setViewName("userPage");
			}
		}else{		//if no user logged in return to home page
			mav.setViewName("homePage");
		}
		return mav;
	}
	
	@RequestMapping(value="/userPage",method=RequestMethod.GET)		//retunr userPage view when asked
	public String userPage(){
		return "userPage";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)		//logout handling
	public String logout(HttpServletRequest httpRequest){
		if(httpRequest.getSession().getAttribute("account") != null){		//if user logged in 
			httpRequest.getSession().removeAttribute("account");		//remove user's account from session 
			return "redirect:/";
		}					
		return "redirect:/";		//return to homePage when logout is selected
	}
	
	@RequestMapping(value="/submitMail",method=RequestMethod.POST)		//add new email to newsletter list to database using server API 
	public String submitMail(@RequestParam("email") String email,Model model){
		RestTemplate restTemplate = new RestTemplate();
		int response = restTemplate.postForObject(SERVER_URL+"submitMail", email, Integer.class);		//sent email submitted to server via API service
		if(response != 0){		//if response is not 0 the email successfully submitted
			model.addAttribute("response","<div style=\"visibility:visible\" class=\"alert alert-success\"><strong>Success!</strong><p>Your email has been added to the newsletter list...</p></div>");
		}else{
			model.addAttribute("response","<div style=\"visibility:visible\" class=\"alert alert-danger\"><strong>Warning!</strong> <p>Your email could not be submitted, another email with the same address exists in the newsletter list...Please try with another email address.</p></div>");
		}
		return "redirect:/";		//return to homePage
	}
	
}
