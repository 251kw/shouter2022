package com.shantery.result2.login;



import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	
	//index.htmlへ接続
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value = "/Info", method = RequestMethod.POST)
	public ModelAndView UserInfoInput(ModelAndView mav) {
		mav.setViewName("UserInfoInput");
		return mav;
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ModelAndView shouter(@RequestParam(value="lodinId",required=true)String loginId,
								@RequestParam(value="password",required=true)String password,
								ModelAndView mav) {
		
		
		return mav;
	}
	
	
	
}
