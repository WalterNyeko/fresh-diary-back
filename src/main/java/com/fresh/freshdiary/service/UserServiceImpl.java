package com.fresh.freshdiary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fresh.freshdiary.model.UserInfo;
import com.fresh.freshdiary.repository.UserInfoRepository;

@Service
public class UserServiceImpl implements UserDetailsService{

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user = userInfoRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
	
	public Iterable<UserInfo> listAllUsers() {
		Iterable<UserInfo> users = userInfoRepository.findAll();
		return users;
	}

}
