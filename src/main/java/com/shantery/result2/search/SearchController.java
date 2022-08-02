package com.shantery.result2.search;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
	
	//index.htmlから送信されてくるのでpost
	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView searchInput(ModelAndView mav) {
		mav.setViewName("UserSearchInput");
		return mav;
	}
	
	//検索条件が入力されたらpostで受け取る
	//追加すること(仮)　・バリデーションチェック、検索メソッド
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public ModelAndView search(@RequestParam(value="loginId",required=false)String loginId,
			@RequestParam(value="userName",required=false)String userName,
			@RequestParam(value="icon1",required=false)boolean icon1,
			@RequestParam(value="icon2",required=false)boolean icon2,
			@RequestParam(value="profile",required=false)String profile,
			@ModelAttribute("formModel")@Validated UserData userdata, BindingResult result,
			ModelAndView mav) {
		ModelAndView res = null;
		if(!result.hasErrors()) {
			//ここで検索メソッド呼び出し
			//ここでは検索結果
			mav.addObject("loginId",loginId);
			mav.addObject("userName", userName);
			String iconCheck = null;
			if(icon1==false && icon2==false) {
				iconCheck = "noCheck";
			}else {
				if(icon1==false) {
					iconCheck = "icon-female";
				}else if(icon2==false) {
					iconCheck = "icon-male";
				}else {
					iconCheck = "check";
				}
			}
			mav.addObject("check", iconCheck);
			mav.addObject("profile", profile);
			res = new ModelAndView("redirect:/");
		}else {
			mav.setViewName("UserSearchInput");
			mav.addObject("error", result.hasErrors());
			mav.addObject("check", "noCheck");
			res = mav;
		}
		return res;
	}
}
