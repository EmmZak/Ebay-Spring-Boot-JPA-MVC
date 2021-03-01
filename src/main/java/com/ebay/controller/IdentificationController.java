package com.ebay.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ebay.model.User;
import com.ebay.serviceImpl.UserServiceImpl;

@Controller
public class IdentificationController {
	
	@Autowired
	UserServiceImpl userService;

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView loginPage(Model model) {
    	System.out.println("IdentificationController.loginPage()");
    	//model.addAttribute("user", new User());
    	
		var params = new HashMap<String, Object>();
	    params.put("user", new User());
    	
    	return new ModelAndView("login", params);
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.GET)
    public ModelAndView registrationPageGET(Model model) {
    	System.out.println("IdentificationController.registrnPage GET()");
    	
		var params = new HashMap<String, Object>();
	    params.put("user", new User());
    	
    	return new ModelAndView("registration", params);
    }
    
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public ModelAndView registrationPagePOST(User user) {
    	System.out.println("IdentificationController.registrnPage POST() user " + user);
    	/*
    	if (user.getUsername() == null || user.getPassword() == null
    	    || user.getUsername() == "" || user.getPassword() == "") {
    		
    		System.out.println("All null");
    		
    		var params = new HashMap<String, Object>();
    	    params.put("user", new User());
    	    params.put("registrationError", true);
    	    
    		return new ModelAndView("registration", params);
    	}
    	*/
    	userService.save(user);
    	System.out.println("registrationPOST user saved " + user);
    		
    	return new ModelAndView("redirect:login");
    }
    
    @RequestMapping("/login-error")
    public ModelAndView loginError(Model model) {
    	
	  var params = new HashMap<String, Object>();
	  params.put("user", new User());
	  params.put("loginError", true);
    
      return new ModelAndView("login", params);
    }
    
    @RequestMapping("/logout-success")
    public String logoutPage() {
    	
    	return "redirect:login";
    }
}
