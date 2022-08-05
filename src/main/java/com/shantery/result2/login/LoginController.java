package com.shantery.result2.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shantery.result2.info.UserInfoData;

/**
 * @author y.nakaya
 *
 */
@Controller
public class LoginController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}

	// POST用のパラメータを受け取る
	@RequestMapping(value = "UserInfoInput", method = RequestMethod.POST)
	public ModelAndView InfoInput(ModelAndView mav) {
		mav.setViewName("UserInfoInput");
		// 既存のログインIDでないか確認
		if (user == null) {
			mav.setViewName("UserInfoInput");
		} else {
			check = true;
			mav.addObject("error", check);
			mav.setViewName("UserInfoInput");
		}
		return mav;
	}

	@RequestMapping(value = "UserInfoConfirm", method = RequestMethod.POST)
	public ModelAndView InfoInfoConfirm(@RequestParam(value = "loginId", required = false) String loginId,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "icon", required = false) String icon,
			@RequestParam(value = "profile", required = false) String profile, ModelAndView mav) {
		mav.setViewName("UserInfoConfirm");
		// modelに設定して画面に表示するようにする
		mav.addObject("loginId", loginId);
		mav.addObject("password", password);
		mav.addObject("userName", userName);
		mav.addObject("icon", icon);
		mav.addObject("profile", profile);
		return mav;
	}

}
