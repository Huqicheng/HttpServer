package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

import entity.Group;
import entity.User;

import service.GroupService;
import utils.StrutsUtil;

public class GroupChatAction {

	int user_id;
	int group_id;
	
	
	public int getGroup_id() {
		return group_id;
	}



	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}



	public int getUser_id() {
		return user_id;
	}



	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}



	public void getGroupOfUser(){
		GroupService gs = new GroupService();
		ArrayList<Group> groups = (ArrayList<Group>) gs.getGroupsOfUser(user_id);
		
		try {
			HttpServletResponse response = ServletActionContext.getResponse();   
			
			StrutsUtil.write(response,new Gson().toJson(groups));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getUsersOfGroup(){
		GroupService gs = new GroupService();
		ArrayList<User> users = (ArrayList<User>) gs.getUsersOfGroup(group_id);
		try {
			HttpServletResponse response = ServletActionContext.getResponse();   
			
			StrutsUtil.write(response,new Gson().toJson(users));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
