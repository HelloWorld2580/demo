package com.dhcc.ms.ims.dao;

import com.dhcc.ms.ims.po.User;
import com.github.abel533.mapper.Mapper;

public interface UserDao extends Mapper<User> {
/*	  @Select("select * from user;")
	  public List<User> getAllUsers();
	  @Select("select * from user where id=#{userId};")
	  public User getUser(String userId);
	  @Select("select * from user where id=#{userId};")
	  public User getUsersByPage(String userId);*/
}
