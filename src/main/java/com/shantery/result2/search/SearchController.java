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
		mav.addObject("check", "noCheck");
		return mav;
	}
	
	//検索条件が入力されたらpostで受け取る
	//追加すること(仮)　・バリデーションチェック、検索メソッド
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ModelAndView search(@RequestParam(value="loginId",required=false)String loginId,
			@RequestParam(value="userName",required=false)String userName,
			@RequestParam(value="icon1",required=false)boolean icon1,
			@RequestParam(value="icon2",required=false)boolean icon2,
			@RequestParam(value="profile",required=false)String profile,
			@ModelAttribute("formModel")@Validated UserData userdata, BindingResult result,
			ModelAndView mav) {
		ModelAndView res = null;
		if(!result.hasErrors()) {
			//検索条件保持
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
			//検索メソッド呼び出し
			
			//検索結果画面に遷移
			mav.setViewName("UserSearchInput");
		}else {
			//入力エラー表示
			mav.addObject("error", result.hasErrors());
			mav.addObject("check", "noCheck");
			mav.setViewName("UserSearchInput");
		}
		res = mav;
		return res;
	}
}
