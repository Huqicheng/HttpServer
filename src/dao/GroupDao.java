package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Group;
import entity.User;

import utils.Dbconn;

public class GroupDao {

	private Dbconn db = null;
	
	public GroupDao(){
		db = new Dbconn();
	}
	
	/** 
	* @Title: getGroupsOfUser 
	* @Description: TODO 
	* @param @param user_id
	* @param @return
	* @param @throws SQLException    
	* @return List<Group>    
	* @throws 
	*/
	public List<Group> getGroupsOfUser(int user_id) throws SQLException{
		List<Group> groups = new ArrayList<Group>();
		
		Connection conn = db.getConnection();
		CallableStatement c = null;
		
		try {
			c = conn.prepareCall("{call get_groups_of_user(?)}");
			c.setInt(1, user_id);
			
			ResultSet rs = c.executeQuery();
			while(rs.next()){
				Group group = new Group();
				group.setGroupId(rs.getLong("id"));
				group.setGroupDescription("groupDescription");
				group.setGroupName("groupName");
				group.setProjectId(rs.getInt("project_id"));
				group.setUpdatedAt(rs.getTimestamp("updatedAt").getTime());
				group.setCreatedAt(rs.getTimestamp("createdAt").getTime());
				groups.add(group);
			}
			
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			c.close();
			db.dispose();
		}
		
		
		
		return groups;
	}
	
	
	/** 
	* @Title: getUsersOfGroup 
	* @Description: TODO 
	* @param @param group_id
	* @param @return
	* @param @throws SQLException    
	* @return List<User>    
	* @throws 
	*/
	public List<User> getUsersOfGroup(int group_id) throws SQLException{
		List<User> users = new ArrayList<User>();
		
		Connection conn = db.getConnection();
		CallableStatement c = null;
		
		try {
			c = conn.prepareCall("{call get_users_of_group(?)}");
			c.setInt(1, group_id);
			
			ResultSet rs = c.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setUserId(rs.getLong("id"));
				user.setAccountType(rs.getString("accountType"));
				user.setCreatedAt(rs.getTimestamp("createdAt").getTime());
				user.setUpdateAt(rs.getTimestamp("updatedAt").getTime());
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFacebook(rs.getString("facebook"));
				
				//TODO blob of avatar
				users.add(user);
			}
			
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
			c.close();
			
			db.dispose();
		}
		
		
		
		return users;
		
	}
}
