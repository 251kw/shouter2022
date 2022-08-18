package com.shantery.result2.login;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shantery.result2.repositories.ShoutRepository;
import static com.shantery.common.constants.*;


/**
 * @author s.ogata
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	ShoutRepository repository;
	
	@Autowired
	private LoginService service;
	
	//index.htmlへ接続
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return DISPLAY_OF_INDEX;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String indexback() {
		return DISPLAY_OF_INDEX;
	}
	
	//ログイン画面で入力されたログインIDとパスワードをもとにユーザー情報を検索
	@RequestMapping(value=URL_BOARD, method=RequestMethod.POST)
	public ModelAndView shouter(@RequestParam(value=LOGINID,required=true)String loginId,
								@RequestParam(value=PASSWORD,required=true)String password,
								@ModelAttribute(FORM_MODEL)@Validated UserLoginData userdata,
								BindingResult result,ModelAndView mav) {
		//ログインチェック用フラグ
		String logcheck = null;
		if(!result.hasErrors()) {
			//エラーなし(未入力がなかった場合)
			//入力されたログインIDとパスワードに一致するユーザー情報を検索
			List<UserLoginData> list = service.getAll(loginId, password);
			if(list.size() != 0) {
				//検索結果があればログインでき、結果を格納する
				mav.addObject(ADDNAME_DATALIST, list);
				//shoutsデーブルにあるデータをすべて取得し、結果を格納
				List<ShoutData> shoutList = repository.findAllByOrderByDateDesc();
				mav.addObject(ADDNAME_SHOUTLIST, shoutList);
				//ログインしたユーザーの情報を保持
				mav.addObject(ADDNAME_LOGINID, loginId);
				mav.addObject(ADDNAME_PASSWORD, password);
				//掲示板画面に遷移
				mav.setViewName(DISPLAY_OF_TOP);
			}else {
				//検索結果のリストサイズが0だったらログインIDかパスワードが一致していないのでエラーを出す
				//ログインチェック用フラグで一致しなかったことを格納
				logcheck = DIFFERENT_INFORMATION;
				mav.addObject(ADDNAME_ERROR, logcheck);
				//ログイン画面へ遷移して、エラーを表示させる
				mav.setViewName(DISPLAY_OF_INDEX);
			}
		}else {
			int loginError = result.getFieldErrorCount(FIELD_LOGINID);
			if(loginError == 2 || loginError == 0) {
				//未入力の場合エラーを表示する
				logcheck = BLANK;
				mav.addObject(ADDNAME_ERROR, logcheck);
			}else {
				//ログインIDが半角英数字ではない場合
				mav.addObject(ADDNAME_ERROR2, result.hasErrors());
			}
			//ログイン画面へ遷移
			mav.setViewName(DISPLAY_OF_INDEX);
		}
		return mav;
	}	
	
	//データベースに叫ぶボタンでshoutした内容を追加し、表示させる
	@RequestMapping(value=URL_ADD_SHOUT,method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView addshout(@RequestParam(value=LOGINID,required=false)String loginId,
								 @RequestParam(value=PASSWORD,required=false)String password,
								 @ModelAttribute(FORM_MODEL)@Validated ShoutData shoutdata,
								 BindingResult result, ModelAndView mav) {
		if(!result.hasErrors()) {
			//叫ぶ内容を入力していたらエラーなし
			//叫んだ時の時刻を取得し、ShoutDataエンティティにセット
			Calendar calender =Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(calender.getTime());
			shoutdata.setDate(date);
			//叫んだ内容をデータベースに追加
			repository.saveAndFlush(shoutdata);
			
		}else {
			//叫ぶ内容が未入力の場合、エラー結果を格納
			mav.addObject(ADDNAME_ERROR, result.hasErrors());
		}
		//再表示用のデータを取得し、リストに格納
		List<UserLoginData> userdata = service.getAll(loginId, password);
		mav.addObject(ADDNAME_DATALIST, userdata);
		//新たに叫んだ内容を追加したデータを取得し、格納
		List<ShoutData> shoutList = repository.findAllByOrderByDateDesc();
		mav.addObject(ADDNAME_SHOUTLIST, shoutList);
		//ログイン情報を保持
		mav.addObject(LOGINID, loginId);
		mav.addObject(PASSWORD, password);
		//掲示板画面へ遷移
		mav.setViewName(DISPLAY_OF_TOP);
		return mav;
	}
	
	//logoutボタンからindex.htmlに遷移
	@RequestMapping(value=URL_LOGOUT,method=RequestMethod.POST)
	public String logout() {
		return DISPLAY_OF_INDEX;
	}
}
	
