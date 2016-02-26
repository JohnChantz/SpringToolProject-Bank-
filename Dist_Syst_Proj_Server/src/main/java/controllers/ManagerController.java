package controllers;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import model.Request;
import model.RequestDAO;

@Controller
public class ManagerController {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("myBeans.xml");
	Request req = (Request)context.getBean("request");
	RequestDAO reqDAO = (RequestDAO)context.getBean("requestDAO");
	
	@RequestMapping(value="/managerPage" , method=RequestMethod.GET)	//if managerPage is asked sent to login for login and validation
	public String managerPage(Model model){
		return "redirect:/login";
	}
	@RequestMapping(value="/editRequest",method=RequestMethod.POST)		//this path is used by managerPage's view to post data from each form
	public ModelAndView editRequest(@ModelAttribute("request") Request request){
		ModelAndView mav = new ModelAndView();
		if(reqDAO.requestExistByRId(request.getId())){
			if(request.getCreditLimit() != 0){
				reqDAO.editRequestCreditLimit(request.getId(), request.getCreditLimit());
			}
			if(!request.getRate().isEmpty()){
				reqDAO.editRequestRate(request.getId(), request.getRate());
			}
			if(request.getRequestStatus().equals("Accepted")){		//when request is either approved or rejected inform user 
				reqDAO.editRequestStatus(request.getId(), request.getRequestStatus());
			}else if(request.getRequestStatus().equals("Rejected")){
				reqDAO.editRequestStatus(request.getId(), request.getRequestStatus());
			}
			if(!request.getDescription().isEmpty()){
				reqDAO.editRequestDescription(request.getId(), request.getDescription());
			}
		}else{
			mav.addObject("message","<div class=\"alert alert-danger\"><strong>Warning!</strong> No request exist with this ID...Please try with a valid ID.</div>");
		}
		mav.setViewName("managerPage");
		ArrayList<Request> reqList = reqDAO.getPendingRequests();		//get updated requests
		mav.addObject("reqList", reqList);			//add data to model
		mav.addObject("request", new Request());
		return mav;
	}
}
