package com.shantery.common;

public class constants {
	//name属性名
	public static final String LOGINID = "loginId";
	public static final String USERNAME = "userName";
	public static final String PASSWORD = "password";
	public static final String ICON = "icon";
	public static final String PROFILE = "profile";
	public static final String WRITING = "writing";
	
	//iconのvalue属性
	public static final String ICONMALE = "icon-male";
	public static final String ICONFEMALE = "icon-female";
	
	//戻るボタンのvalue値
	public static final String DISPLAY_BACK = "back";
	
	//　SQL文関連
	public static final String WHERE_LOGINID = "from UserData where loginId =";
	public static final String WHERE_USERNAME = "from UserData where userName LIKE";
	public static final String WHERE_ICON = "from UserData where icon =";
	public static final String WHERE_PROFILE = "from UserData where profile LIKE";
	public static final String AND_USERNAME = "AND userName LIKE";
	public static final String AND_ICON = "AND icon =";
	public static final String AND_PROFILE = "AND profile LIKE";
	public static final String AND_PASSWORD = "AND password =";
	
	//iconの種類判断用
	public static final String ICON_CHECKS = "checks";
	public static final String ICON_NOCHECK = "noCheck";
	public static final String ICON_MALE = "icon-male";
	public static final String ICON_FEMALE = "icon-female";
	
	//Entityクラスのテーブル名、フィールド名、カラム名
	public static final String TABLE_USERS = "users";
	public static final String FIELD_LOGINID = "loginId";
	public static final String FIELD_USERNAME = "userName";
	public static final String FIELD_ICON = "icon";
	public static final String FIELD_PROFILE = "profile";
	public static final String COLUMN_USERID = "userid";
	public static final String COLUMN_LOGINID = "loginid";
	public static final String COLUMN_USERNAME = "username";
	
	//コントローラー機能
	public static final String FORM_MODEL = "formModel";
	
	//画面遷移先
	public static final String SEARCH_RESULT = "UserSearchResult";
	public static final String SEARCH_INPUT = "UserSearchInput";
	
	//addObject,値保管時の第一引数名
	public static final String ADDNAME_LOGINID = "loginId";
	public static final String ADDNAME_USERNAME = "userName";
	public static final String ADDNAME_ICON = "icon";
	public static final String ADDNAME_PROFILE = "profile";
	public static final String ADDNAME_DATALIST = "datalist";
	public static final String ADDNAME_RESULT = "result";
	public static final String ADDNAME_ERROR = "error";
	
	
	
	
	
}
