package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import service.GroupService;
import utils.StrutsUtil;

import com.google.gson.Gson;

import entity.Group;
import enums.ExecResult;

public class GroupAction {

	int user_id;
	int group_id;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	
	// user_id, group_id
	public void dropFromGroup(){
		HttpServletResponse response = ServletActionContext.getResponse();  
		GroupService gs = new GroupService();
		
		if(user_id==0 || group_id==0){
			try {
				StrutsUtil.write(response,ExecResult.failed.toString());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		boolean res = gs.dropFromGroup(group_id, user_id);
		
		if(res){
			try {
				StrutsUtil.write(response,ExecResult.success.toString());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}else{
			try {
				StrutsUtil.write(response,ExecResult.failed.toString());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		
	}
	
	// user_id, group_id
	public void joinGroup(){
		HttpServletResponse response = ServletActionContext.getResponse();  
		GroupService gs = new GroupService();
		
		if(user_id==0 || group_id==0){
			try {
				StrutsUtil.write(response,ExecResult.failed.toString());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		boolean res = gs.joinGroup(group_id, user_id);
		
		if(res){
			try {
				StrutsUtil.write(response,ExecResult.success.toString());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}else{
			try {
				StrutsUtil.write(response,ExecResult.failed.toString());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		
	}
}
