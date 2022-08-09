package com.shantery.result2.info;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shantery.result2.repositories.UserInfoRepository;

/**
 * @author y.nakaya
 *
 */
@Controller
public class UserInfoController {

	@Autowired
	UserInfoRepository repository;

	@Autowired
	UserInfoService infoService;

	// 新規登録入力画面遷移
	@RequestMapping(value = "/UserInfoInput", method = RequestMethod.POST)
	public ModelAndView UserInfoInput(@RequestParam(value = "loginId", required = false) String loginId,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "icon", required = false) String icon,
			@RequestParam(value = "profile", required = false) String profile,
			@RequestParam(value = "back", required = false) String back, ModelAndView mav) {
		if (back != null) {
			mav.setViewName("/index");
		} else {
			mav.addObject("loginId", loginId);
			mav.addObject("password", password);
			mav.addObject("userName", userName);
			mav.addObject("icon", icon);
			mav.addObject("profile", profile);
			mav.setViewName("UserInfoInput");
		}
		return mav;
	}

	// 新規登録確認画面へ遷移
	@RequestMapping(value = "/UserInfoConfirm", method = RequestMethod.POST)
	public ModelAndView UserInfoConfirm(@Validated @ModelAttribute UserInfoData userinfodata, BindingResult result,
			@RequestParam(value = "loginId", required = false) String loginId,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "icon", required = false) String icon,
			@RequestParam(value = "profile", required = false) String profile,
			@RequestParam(value = "back", required = false) String backs, ModelAndView mav) {

		if (backs != null) {
			mav.addObject("loginId", loginId);
			mav.addObject("password", password);
			mav.addObject("userName", userName);
			mav.addObject("icon", icon);
			mav.addObject("profile", profile);
			mav.setViewName("/UserInfoInput");
		}
		UserInfoData user = repository.findByLoginId(loginId);
		boolean info = false;
		// 入力チェックに引っかかった場合、新規登録画面に戻る
		if (result.hasErrors()) {
			mav.addObject("error", result.hasErrors());
			mav.addObject("loginId", loginId);
			mav.addObject("password", password);
			mav.addObject("userName", userName);
			mav.addObject("icon", icon);
			mav.addObject("profile", profile);
			mav.setViewName("UserInfoInput");
		} else {
			// ユーザー情報重複なしだったら、結果表示。重複ありだったらエラー表示
			if (user != null) {
				info = true;
				mav.addObject("error2", info);
				mav.addObject("loginId", loginId);
				mav.addObject("password", password);
				mav.addObject("userName", userName);
				mav.addObject("icon", icon);
				mav.addObject("profile", profile);
				mav.setViewName("UserInfoInput");
			} else {
				mav.addObject("loginId", loginId);
				mav.addObject("password", password);
				mav.addObject("userName", userName);
				mav.addObject("icon", icon);
				mav.addObject("profile", profile);
				mav.setViewName("UserInfoConfirm");
			}
		}
		return mav;
	}

	// 新規登録結果画面へ遷移
	@RequestMapping(value = "/InfoResult", method = RequestMethod.POST)
	public ModelAndView UserInfoCResult(@ModelAttribute UserInfoData userinfodata,
			@RequestParam(value = "loginId", required = false) String loginId,
			@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "icon", required = false) String icon,
			@RequestParam(value = "profile", required = false) String profile,
			@RequestParam(value = "back", required = false) String back, ModelAndView mav) {
		// データベースに登録
		infoService.create(userinfodata);
		mav.addObject("loginId", loginId);
		mav.addObject("password", password);
		mav.addObject("userName", userName);
		mav.addObject("icon", icon);
		mav.addObject("profile", profile);
		mav.setViewName("UserInfoResult");
		if (back != null) {
			mav.setViewName("/index");
		}
		return mav;
	}
}
