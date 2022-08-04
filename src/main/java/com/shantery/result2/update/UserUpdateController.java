package com.shantery.result2.update;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserUpdateController {
	@Autowired
	UserUpdateService uuService;
	
	//ユーザー情報画面一覧表示
	@RequestMapping(value="/index")
	public String displayList(Model model) {
		List<UserInfo> user = uuService.searchAll();
		model.addAttribute("users", user);
		return "index";
	}
	
	//編集へ画面遷移
	@RequestMapping(value="/index/{id}")
	public ModelAndView edit(@PathVariable Long id, ModelAndView mav) {
		UserInfo user = uuService.findById(id);
		String loginId = user.getLoginId();
		String loginID = user.getLoginId();
		String userName = user.getUserName();
		String icon = user.getIcon();
		String profile = user.getProfile();
		mav.addObject("loginId", loginId);
		mav.addObject("loginID", loginID);
		mav.addObject("userName", userName);
		mav.addObject("icon", icon);
		mav.addObject("profile", profile);
		mav.setViewName("UserUpdateInput");
		return mav;
	}
	
	//編集確認画面へ遷移
	@RequestMapping(value="/UserUpdateConfirm", method=RequestMethod.POST)
	public ModelAndView editCheck(@RequestParam(name="loginID") String loginID,	//初期のログインID	
								  @RequestParam(name="loginId") String loginId,	//変更後のログインID
								  @RequestParam(name="userName") String userName,
								  @RequestParam(name="icon") String icon,
								  @RequestParam(name="profile") String profile,
								  ModelAndView mav) {
		
		boolean check = false;
		
		//変更後のログインID検索
		UserInfo user = uuService.findByLoginId(loginId);
		
		//変更前と変更後が同じ場合
		if(loginId.equals(loginID)) {
			mav.addObject("loginId", loginId);
			mav.addObject("loginID", loginID);
			mav.addObject("userName", userName);
			mav.addObject("icon", icon);
			mav.addObject("profile", profile);
			mav.setViewName("UserUpdateConfirm");
		}else {
			mav.addObject("loginId", loginId);
			mav.addObject("loginID", loginID);
			mav.addObject("userName", userName);
			mav.addObject("icon", icon);
			mav.addObject("profile", profile);
			//変更が生じ、かつ既存のログインIDでないか確認
			if(user == null) {
				mav.setViewName("UserUpdateConfirm");
			}else {
				check = true;
				mav.addObject("error", check);
				mav.setViewName("UserUpdateInput");
			}
		}
		return mav;
	}
}
