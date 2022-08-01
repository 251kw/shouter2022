package com.shantery.result2.search;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
	
	//index.htmlから送信されてくるのでpost
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView serchInput(ModelAndView mav) {
		mav.setViewName("UserSearchInput");
		mav.addObject("check", "noCheck");
		return mav;
	}
	
	//検索条件が入力されたらpostで受け取る
	//追加すること(仮)　・バリデーションチェック、検索メソッド
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ModelAndView search(@RequestParam("loginId")String loginId,
			@RequestParam("userName")String userName,@RequestParam("icon1")String icon1,
			@RequestParam("icon2")String icon2,@RequestParam("profile")String profile, ModelAndView mav) {
		//検索条件の保持
		mav.setViewName("UserSearchInput");
		mav.addObject("loginId",loginId);
		mav.addObject("userName", userName);
		String iconCheck = null;
		if(icon1=="icon-male" && icon2=="icon-female") {
			iconCheck = "checks";
		}else {
			if(icon1=="icon-male") {
				iconCheck = icon1;
			}else if(icon2=="icon-female") {
				iconCheck = icon2;
			}else {
				iconCheck = "noCheck";
			}
		}
		mav.addObject("check", iconCheck);
		mav.addObject("profile", profile);
		return mav;
	}
}
