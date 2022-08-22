package com.shantery.result2.delete;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shantery.result2.repositories.SearchRepository;
import com.shantery.result2.repositories.UserDeleteRepository;
import com.shantery.result2.search.SearchService;
import com.shantery.result2.search.UserData;

/**
 * @author k.iwai
 *
 */
import static com.shantery.common.constants.*;

@Controller
public class DeleteController {
	@Autowired
	UserDeleteRepository udRepository;

	@Autowired
	SearchRepository repository;

	@Autowired
	private SearchService service;

	// 削除確認画面へ移動
	@RequestMapping(value = URL_DELETE_CONFIRM, method = RequestMethod.POST)
	public ModelAndView deleteConfirm(
			// それぞれの値を取得
			@RequestParam(name = CHECKBOX, required = false) Long[] userId,
			@RequestParam(value = LOGINID) String loginId, @RequestParam(value = USERNAME) String userName,
			@RequestParam(value = ICON) String icon, @RequestParam(value = PROFILE) String profile,
			@RequestParam(value = DISPLAY_BACK, required = false) String back,
			@Validated @ModelAttribute UserInfom userinfom, BindingResult result, ModelAndView mav) {
		// チェックボックスにチェックが入っていたら
		if (userId != null) {

			// UserInfom型リストを作成
			List<UserInfom> list = null;

			// ループに入る前にチェックボックス0番目の値を保持
			List<UserInfom> lists = udRepository.findAllByUserId(userId[0]);

			// チェックボックスの数だけユーザーIDから値を検索
			for (int i = 1; i < userId.length; i++) {
				list = udRepository.findAllByUserId(userId[i]);
				lists.addAll(list);
			}
			// リストに追加(削除確認画面で表示するデータ)
			mav.addObject(ADDNAME_DATALIST, lists);

			// 検索条件の保持
			mav.addObject(ADDNAME_LOGINID, loginId);
			mav.addObject(ADDNAME_USERNAME, userName);
			mav.addObject(ADDNAME_ICON, icon);
			mav.addObject(ADDNAME_PROFILE, profile);
			mav.setViewName(DISPLAY_OF_DELETE_CONFIRM);
		} else {
			if (result.hasErrors()) {
				// チェックボックスにチェックが入っていなければエラーを表示
				mav.addObject(ADDNAME_ERROR, result.hasErrors());
				// 検索条件の保持
				mav.addObject(ADDNAME_LOGINID, loginId);
				mav.addObject(ADDNAME_USERNAME, userName);
				mav.addObject(ADDNAME_ICON, icon);
				mav.addObject(ADDNAME_PROFILE, profile);

				// アイコン1種類の時用リスト
				List<UserData> list = null;
				// アイコンが2種類選択された時用の追加リスト
				List<UserData> list2 = null;
				// listとlist2を結合させたリスト
				List<UserData> lists = null;
				if (icon.equals(ICON_NOCHECK)) {
					// チェックなしの場合は空文字にする
					icon = "";
					lists = service.getAll(loginId, userName, icon, profile);
				} else if (icon.equals(ICON_CHECKS)) {
					icon = ICON_MALE;
					// icon-maleの場合の検索結果
					list = service.getAll(loginId, userName, icon, profile);
					icon = ICON_FEMALE;
					// icon-femaleの場合の検索結果
					list2 = service.getAll(loginId, userName, icon, profile);
					// 2種類のアイコンで検索した結果を結合したリスト
					lists = Stream.concat(list.stream(), list2.stream()).collect(Collectors.toList());
				} else {
					// icon-male.icon-femaleのどちらか
					lists = service.getAll(loginId, userName, icon, profile);
				}
				mav.addObject(ADDNAME_DATALIST, lists);
				mav.setViewName(DISPLAY_OF_SEARCH_RESULT);
			}
		}
		return mav;
	}

	// 削除結果画面へ移動
	@RequestMapping(value = URL_DELETE_RESULT, method = RequestMethod.POST)
	public ModelAndView deleteResult(
			// 値を取得
			@RequestParam(name = USERID, required = false) Long[] userId,
			@RequestParam(value = LOGINID2) String loginId, @RequestParam(value = USERNAME2) String userName,
			@RequestParam(value = ICON2) String icon, @RequestParam(value = PROFILE2) String profile,
			@Validated @ModelAttribute UserInfom userinfom, BindingResult result, ModelAndView mav) {

		// UserInfo型リストを作成
		List<UserInfom> list = null;

		// ループに入る前にチェックボックス0番目の値を保持
		List<UserInfom> lists = udRepository.findAllByUserId(userId[0]);

		// チェックボックスの数だけユーザーIDから値を検索
		for (int i = 1; i < userId.length; i++) {
			list = udRepository.findAllByUserId(userId[i]);
			lists.addAll(list);
		}
		// リストに追加(削除結果画面表示用)
		mav.addObject(ADDNAME_DATALIST, lists);

		if (lists.size() != 0) {

			// 1件づつ削除を実行
			for (int i = 0; i < userId.length; i++) {
				udRepository.deleteByUserId(userId[i]);
			}
			// 検索条件保持

			mav.addObject(ADDNAME_LOGINID, loginId);
			mav.addObject(ADDNAME_USERNAME, userName);
			mav.addObject(ADDNAME_ICON, icon);
			mav.addObject(ADDNAME_PROFILE, profile);
			mav.setViewName(DISPLAY_OF_DELETE_RESULT);
		} else {
			boolean check = true;
			// 削除重複の場合のエラー
			mav.addObject(ADDNAME_ERROR, check);

			// 検索条件の保持
			mav.addObject(ADDNAME_LOGINID, loginId);
			mav.addObject(ADDNAME_USERNAME, userName);
			mav.addObject(ADDNAME_ICON, icon);
			mav.addObject(ADDNAME_PROFILE, profile);
			mav.setViewName(DISPLAY_OF_DELETE_RESULT);

		}
		return mav;
	}

}
