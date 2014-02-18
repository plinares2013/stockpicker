package com.baysphere.stockpicker.server;

import com.baysphere.stockpicker.client.LoginInfo;
import com.baysphere.stockpicker.client.services.LoginService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements
		LoginService {

	public LoginInfo login (String requestURI) {
		UserService userService = UserServiceFactory.getUserService();
		LoginInfo loginInfo =  new LoginInfo();
		User user = userService.getCurrentUser();
		if (user != null) {
			loginInfo.setLoggedIn(true);
			loginInfo.setEmailAddress(user.getEmail());
			loginInfo.setNickname(user.getNickname());
			loginInfo.setLogoutUrl(userService.createLogoutURL(requestURI));
		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(requestURI));
		}
		
		return loginInfo;
	}
	
}
