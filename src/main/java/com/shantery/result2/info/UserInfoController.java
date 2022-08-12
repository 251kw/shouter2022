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
import static com.shantery.common.constants.*;

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
	public ModelAndView UserInfoInput(@RequestParam(value = LOGINID, required = false) String loginId,
			@RequestParam(value = PASSWORD, required = false) String password,
			@RequestParam(value = USERNAME, required = false) String userName,
			@RequestParam(value = ICON, required = false) String icon,
			@RequestParam(value = PROFILE, required = false) String profile,
			@RequestParam(value = DISPLAY_BACK, required = false) String back, ModelAndView mav) {
		if (back != null) {
			mav.setViewName(DISPLAY_OF_INDEX);
		} else {
			mav.addObject(ADDNAME_LOGINID, loginId);
			mav.addObject(ADDNAME_PASSWORD, password);
			mav.addObject(ADDNAME_USERNAME, userName);
			mav.addObject(ADDNAME_ICON, icon);
			mav.addObject(ADDNAME_PROFILE, profile);
			mav.setViewName(DISPLAY_OF_USERINFO_INPUT);
		}
		return mav;
	}

	// 新規登録確認画面へ遷移
	@RequestMapping(value = "/UserInfoConfirm", method = RequestMethod.POST)
	public ModelAndView UserInfoConfirm(@Validated @ModelAttribute UserInfoData userinfodata, BindingResult result,
			@RequestParam(value = LOGINID, required = false) String loginId,
			@RequestParam(value = PASSWORD, required = false) String password,
			@RequestParam(value = USERNAME, required = false) String userName,
			@RequestParam(value = ICON, required = false) String icon,
			@RequestParam(value = PROFILE, required = false) String profile,
			@RequestParam(value = DISPLAY_BACKS, required = false) String backs, ModelAndView mav) {
		
		//戻るボタンが押されたら登録入力画面に戻る
		if (backs != null) {
			mav.addObject(ADDNAME_LOGINID, loginId);
			mav.addObject(ADDNAME_PASSWORD, password);
			mav.addObject(ADDNAME_USERNAME, userName);
			mav.addObject(ADDNAME_ICON, icon);
			mav.addObject(ADDNAME_PROFILE, profile);
			mav.setViewName(DISPLAY_OF_USERINFO_INPUT);
		}
		UserInfoData user = repository.findByLoginId(loginId);
		boolean INFO_CHECK = false;
		// 入力チェックに引っかかった場合、新規登録画面に戻る
		if (result.hasErrors()) {
			mav.addObject(ADDNAME_ERROR, result.hasErrors());
			mav.addObject(ADDNAME_LOGINID, loginId);
			mav.addObject(ADDNAME_PASSWORD, password);
			mav.addObject(ADDNAME_USERNAME, userName);
			mav.addObject(ADDNAME_ICON, icon);
			mav.addObject(ADDNAME_PROFILE, profile);
			mav.setViewName(DISPLAY_OF_USERINFO_INPUT);
		} else {
			// ユーザー情報重複なしだったら、結果表示。重複ありだったらエラー表示
			if (user != null) {
				INFO_CHECK = true;
				mav.addObject("error2", INFO_CHECK);
				mav.addObject(ADDNAME_LOGINID, loginId);
				mav.addObject(ADDNAME_PASSWORD, password);
				mav.addObject(ADDNAME_USERNAME, userName);
				mav.addObject(ADDNAME_ICON, icon);
				mav.addObject(ADDNAME_PROFILE, profile);
				// 新規登録入力画面へ遷移
				mav.setViewName(DISPLAY_OF_USERINFO_INPUT);
			} else {
				mav.addObject(ADDNAME_LOGINID, loginId);
				mav.addObject(ADDNAME_PASSWORD, password);
				mav.addObject(ADDNAME_USERNAME, userName);
				mav.addObject(ADDNAME_ICON, icon);
				mav.addObject(ADDNAME_PROFILE, profile);
				// 新規登録確認画面へ遷移
				mav.setViewName(DISPLAY_OF_USERINFO_CONFIRM);
			}
		}
		return mav;
	}

	// 新規登録結果画面へ遷移
	@RequestMapping(value = "/InfoResult", method = RequestMethod.POST)
	public ModelAndView UserInfoCResult(@ModelAttribute UserInfoData userinfodata,
			@RequestParam(value = LOGINID, required = false) String loginId,
			@RequestParam(value = PASSWORD, required = false) String password,
			@RequestParam(value = USERNAME, required = false) String userName,
			@RequestParam(value = ICON, required = false) String icon,
			@RequestParam(value = PROFILE, required = false) String profile,
			@RequestParam(value = DISPLAY_BACK, required = false) String back, ModelAndView mav) {
		// データベースに登録
		infoService.create(userinfodata);

		// 登録結果画面表示
		mav.addObject(ADDNAME_LOGINID, loginId);
		mav.addObject(ADDNAME_PASSWORD, password);
		mav.addObject(ADDNAME_USERNAME, userName);
		mav.addObject(ADDNAME_ICON, icon);
		mav.addObject(ADDNAME_PROFILE, profile);
		mav.setViewName(DISPLAY_OF_USERINFO_RESULT);
		if (back != null) {
			mav.setViewName(DISPLAY_OF_INDEX);
		}
		return mav;
	}
}
