package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.GroupDao;

import entity.Group;
import entity.User;

public class GroupService {

	private GroupDao groupDao = null;
	
	public GroupService(){
		groupDao = new GroupDao();
		
	}
	
	/** 
	* @Title: getGroupsOfUser 
	* @Description: TODO 
	* @param @param user_id
	* @param @return    
	* @return List<Group>    
	* @throws 
	*/
	public List<Group> getGroupsOfUser(int user_id){
		try {
			return groupDao.getGroupsOfUser(user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Group>();
	}
	
	
	/** 
	* @Title: getUsersOfGroup 
	* @Description: TODO 
	* @param @param group_id
	* @param @return    
	* @return List<User>    
	* @throws 
	*/
	public List<User> getUsersOfGroup(int group_id){
		try {
			return groupDao.getUsersOfGroup(group_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ArrayList<User>();
	}
}
