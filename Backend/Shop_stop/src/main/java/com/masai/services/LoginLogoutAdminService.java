package com.masai.services;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.LoginException;
import com.masai.exceptions.LogoutException;
import com.masai.exceptions.UserException;
import com.masai.model.Admin;
import com.masai.model.CurrentAdminSession;
import com.masai.model.User;

public interface LoginLogoutAdminService {

    CurrentAdminSession loginAdmin(User user) throws LoginException, AdminException;

    String logoutAdmin(String key) throws LogoutException;

    Admin authenticateAdmin(User user, String key) throws UserException, AdminException, LoginException;

    Admin validateAdmin(String key) throws AdminException, LoginException;
}
