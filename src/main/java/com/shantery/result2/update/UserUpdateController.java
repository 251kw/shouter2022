package com.shantery.result2.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shantery.result2.repositories.UserUpdateRepository;

@Controller
public class UserUpdateController {
	@Autowired
	UserUpdateRepository repository;
	
	//編集画面へ遷移
	@RequestMapping(value="/index/{id}", method=RequestMethod.POST)
	public ModelAndView edit(@PathVariable Long id, ModelAndView mav) {
		UserInfo user = repository.findById(id).get();
		String loginId = user.getLoginId();
		String loginID = user.getLoginId();
		String userName = user.getUserName();
		String icon = user.getIcon();
		String profile = user.getProfile();
		mav.addObject("userId", id);
		mav.addObject("loginId", loginId);
		mav.addObject("loginID", loginID);
		mav.addObject("userName", userName);
		mav.addObject("icon", icon);
		mav.addObject("profile", profile);
		mav.setViewName("UserUpdateInput");
		return mav;
	}
	
	//更新確認画面へ遷移
	@RequestMapping(value="/UserUpdateConfirm", method=RequestMethod.POST)
	public ModelAndView editCheck(@RequestParam(name="userId") Long userId,
								  @RequestParam(name="loginID") String loginID,	//初期のログインID	
								  @RequestParam(name="loginId") String loginId,	//変更後のログインID
								  @RequestParam(name="userName") String userName,
								  @RequestParam(name="icon") String icon,
								  @RequestParam(name="profile") String profile,
								  ModelAndView mav) {
		
		boolean check = false;
		boolean checkID = false;
		
		//変更後のログインID検索
		UserInfo user = repository.findByLoginId(loginId);
		
		//変更前と変更後が同じ場合
		if(loginId.equals(loginID)) {
			mav.addObject("userId", userId);
			mav.addObject("loginId", loginId);
			mav.addObject("loginID", loginID);
			mav.addObject("userName", userName);
			mav.addObject("icon", icon);
			mav.addObject("profile", profile);
			mav.setViewName("UserUpdateConfirm");
		}else {
			mav.addObject("userId", userId);
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
	
	//更新結果画面へ遷移
	@RequestMapping(value="/UserUpdateResult", method=RequestMethod.POST)
	public ModelAndView updateResult(@ModelAttribute UserInfo userInfo,
									 @RequestParam(name="userId") Long userId,
									 @RequestParam(name="loginId") String loginId,
									 @RequestParam(name="userName") String userName,
									 @RequestParam(name="icon") String icon,
									 @RequestParam(name="profile") String profile,
									 ModelAndView mav) {
		//更新
		repository.saveAndFlush(userInfo);
		
		//更新結果画面表示
		mav.addObject("loginId", loginId);
		mav.addObject("userName", userName);
		mav.addObject("icon", icon);
		mav.addObject("profile", profile);
		mav.setViewName("UserUpdateResult");
		return mav;
	}
}
