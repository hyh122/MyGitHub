package com.example.domain.service;

import com.example.dataModal.User;

public interface ILogin_Register_Service {

	public abstract boolean addUser(User user);

	public User findUserByName(String name);

}