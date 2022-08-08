package com.shantery.result2.login;

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
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String indexback() {
		return "index";
	}
	
	//新規登録画面へ遷移
	@RequestMapping(value = "/Info", method = RequestMethod.POST)
	public ModelAndView UserInfoInput(ModelAndView mav) {
		mav.setViewName("UserInfoInput");
		return mav;
	}
	
	//ログイン画面で入力されたログインIDとパスワードをもとにユーザー情報を検索
	//やること　バリデーションチェック、ユーザー情報の取得、シャウト情報の取得
	@RequestMapping(value="/shouter", method=RequestMethod.POST)
	public ModelAndView shouter(@RequestParam(value="loginId",required=true)String loginId,
								@RequestParam(value="password",required=true)String password,
								@ModelAttribute("formModel")@Validated UserLoginData userdata,
								BindingResult result,ModelAndView mav) {
		String logcheck = null;
		if(!result.hasErrors()) {
			//検索メソッド
			List<UserLoginData> list = service.getAll(loginId, password);
			if(list.size() != 0) {
				//検索結果があればログインできる
				mav.addObject("datalist", list);
				//結果が入ったリストのサイズが0だったらエラー
				//shoutsデーブルにあるデータをすべて取得し、結果を格納
				List<ShoutData> shoutList = repository.findAll();
				mav.addObject("shoutlist", shoutList);
				mav.setViewName("top");
				
			}else {
				//検索結果のリストサイズが0だったらログインIDかパスワードが一致していないのでエラーを出す
				logcheck = "differntInformation";
				mav.addObject("error", logcheck);
				mav.setViewName("index");
			}
		}else {
			logcheck = "blank";
			//未入力欄があったらエラー
			mav.addObject("error", logcheck);
			mav.setViewName("index");
		}
		return mav;
	}	
	
	//データベースへ叫ぶボタンでshoutした内容を追加し、表示させる
	//ヴァリデーションチェック、叫んだ内容追加、再表示用のリスト追加
	@RequestMapping(value="/addshout",method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView addshout(@RequestParam(value="shout",required=false)String writing,
								 @ModelAttribute("formModel")@Validated ShoutData shoutdata,
								 BindingResult result, ModelAndView mav) {
		if(!result.hasErrors()) {
			//エラーがなかったら叫んだ内容をデータベースに追加
			repository.saveAndFlush(shoutdata);
			List<ShoutData> shoutList = repository.findAll();
			mav.addObject("shoutlist", shoutList);
		}else {
			//エラーがあったらその結果を格納
			mav.addObject("error", result.hasErrors());
		}
		mav.setViewName("top");
		return mav;
	}
	
	//logoutボタンからindex.htmlに遷移
	@RequestMapping(value="/logout",method=RequestMethod.POST)
	public String logout() {
		return "index";
	}
}
	
