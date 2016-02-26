package controllers;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import model.Employee;
import model.EmployeeDAO;
import model.Request;
import model.RequestDAO;


/*Main Controller that manages the login page and uses login methods for communicating with the database */
@Controller
public class MainController{
	
	ApplicationContext context = new ClassPathXmlApplicationContext("myBeans.xml");
	EmployeeDAO empDAO = (EmployeeDAO)context.getBean("employeeDAO");
	Employee emp = (Employee)context.getBean("employee");
	Request req = (Request)context.getBean("request");
	RequestDAO reqDAO = (RequestDAO)context.getBean("requestDAO");
	
	@RequestMapping(value="/",method=RequestMethod.GET)	//if the root path is asked sent to login page
	public String index(){
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)	//if login page is asked return loginHomePage view
	public String loginHomePage(Model model){
		model.addAttribute("loggedEmp", emp);		//a variable for storing employees data from the views form
		return "loginHomePage";
	}
	
	@RequestMapping(value="/checkUser",method=RequestMethod.POST)	//loginHomePage posts form data to this path in order to validate login
	public ModelAndView checkLogin(@ModelAttribute("loggedEmp")Employee emp,HttpServletRequest httpRequest){
		String username = emp.getAcc_username();	//getting form's data 
		String password = emp.getAcc_password();
		Employee newEmp = empDAO.getEmployeeByUsername(emp.getAcc_username());	//get employee with this username from the database
		ModelAndView mav = new ModelAndView();	
		if(newEmp == null){		//if no employee found return error message to user
			mav.setViewName("loginHomePage");
			mav.addObject("message", "<div class=\"alert alert-danger\"><strong>Wrong Credentials!</strong> Please try again with a valid username/password.</div>");
			return mav;
		}
		if(newEmp.getAcc_username().equals(username) && newEmp.getAcc_password().equals(password)){		//if username and password match database data
			httpRequest.getSession().setAttribute("username", newEmp.getAcc_username());	//add to session variable the username to be accessible from all views and controllersin this session
			if(newEmp.getAcc_role().equals("employee")){	//if role==employee return employee view
				ArrayList<Request> reqList = reqDAO.getCustomerCreatedRequests();
				mav.addObject("reqList", reqList);
				mav.setViewName("employeePage");
				return mav;
			}else if(newEmp.getAcc_role().equals("manager")){	//if role==manager return manager view
				ArrayList<Request> reqList = reqDAO.getPendingRequests();		//get all available requests and sent them to manager's view
				mav.addObject("reqList", reqList);
				mav.addObject("request", new Request());
				mav.setViewName("managerPage");
				return mav;
			}
		}else{
			mav.setViewName("loginHomePage");
			mav.addObject("message", "<div class=\"alert alert-danger\"><strong>Wrong Password!</strong> Please try again...</div>");
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value="/logOut",method=RequestMethod.GET)
	public ModelAndView logOut(HttpServletRequest httpRequest){
		ModelAndView mav = new ModelAndView();
		httpRequest.getSession().removeAttribute("username");
		mav.setViewName("redirect:/");
		return mav;
		
	}
}
