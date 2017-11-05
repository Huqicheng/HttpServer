package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entity.Event;
import enums.EventStatus;


import utils.Dbconn;

public class EventDao {

private Dbconn db = null;
	
	public EventDao(){
		db = new Dbconn();
	}
	
	/** 
	* @Title: getDatesHavingEvents 
	* @Description: TODO
	* @param @param user_id
	* @param @param status
	* @param @return
	* @param @throws SQLException    
	* @return List<Long>    
	* @throws 
	*/
	public List<Long> getDatesHavingEvents(int user_id, String status) throws SQLException{
		List<Long> dates = new ArrayList<Long>();
		
		Connection conn = db.getConnection();
		CallableStatement c = null;
		
		try {
			c = conn.prepareCall("{call get_dates_having_events(?,?)}");
			c.setInt(1, user_id);
			c.setString(2, status);
			
			ResultSet rs = c.executeQuery();
			while(rs.next()){
				dates.add(rs.getTimestamp("eventDate").getTime());
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			c.close();
			db.dispose();
		}
		return dates;
	}
	
	/** 
	* @Title: getEventsOfOneDate 
	* @Description: TODO 
	* @param @param user_id
	* @param @param date
	* @param @return
	* @param @throws SQLException    
	* @return List<Event>    
	* @throws 
	*/
	public List<Event> getEventsOfOneDate(int user_id, Date date) throws SQLException{
		List<Event> events = new ArrayList<Event>();
		Connection conn = db.getConnection();
		CallableStatement c = null;
		
		try {
			// TODO implement this procedure
			c = conn.prepareCall("{call get_events_of_one_date(?,?)}");
			c.setInt(1, user_id);
			c.setDate(2, new java.sql.Date(date.getTime()));
			
			ResultSet rs = c.executeQuery();
			while(rs.next()){
				Event event = new Event();
				event.setAssignedBy(rs.getInt("assignedBy"));
				event.setAssignedTo(rs.getInt("assignedTo"));
				event.setGroupId(rs.getInt("group_id"));
				event.setEventID(rs.getInt("id"));
				event.setEventTitle(rs.getString("eventName"));
				event.setEventDescription(rs.getString("eventDescription"));
				event.setDeadLine(rs.getTimestamp("eventDeadLine").getTime());
				event.setEventStatus(rs.getString("eventStatus"));
				event.setCreatedAt(rs.getTimestamp("createdAt").getTime());
				event.setUpdatedAt(rs.getTimestamp("updatedAt").getTime());
				events.add(event);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			c.close();
			db.dispose();
		}
		return events;
		
	}
	
	
	/** 
	* @Title: updateStatusOfEvent 
	* @Description: TODO 
	* @param @param eventId
	* @param @throws SQLException    
	* @return void    
	* @throws 
	*/
	public void updateStatusOfEvent(int eventId,String status) throws SQLException{
		Connection conn = db.getConnection();
		CallableStatement c = null;
		
		try {
			// TODO implement this procedure
			c = conn.prepareCall("{call update_status_of_event(?,?)}");
			c.setInt(1, eventId);
			c.setString(2, status);
			c.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			c.close();
			db.dispose();
		}
		
	}
	
	/** 
	* @Title: deleteEvent 
	* @Description: TODO 
	* @param @param eventId
	* @param @throws SQLException    
	* @return void    
	* @throws 
	*/
	public void deleteEvent(int eventId) throws SQLException{
		Connection conn = db.getConnection();
		CallableStatement c = null;
		
		try {
			// TODO implement this procedure
			c = conn.prepareCall("{call delete_event(?)}");
			c.setInt(1, eventId);
			c.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			c.close();
			db.dispose();
		}
		
	}
	
	
	/** 
	* @Title: assignEvent 
	* @Description: TODO 
	* @param @param event
	* @param @return
	* @param @throws SQLException    
	* @return int    0 - assign failed    >0 - id of this event
	* @throws 
	*/
	public int assignEvent(Event event) throws SQLException{
		Connection conn = db.getConnection();
		CallableStatement c = null;
		
		try {
			c = conn.prepareCall("{call assign_event(?,?,?,?,?,?,?,?)}");
			c.setLong(1, event.getGroupId());
			c.setString(2, event.getEventTitle());
			c.setString(3, event.geteventDescription());
			c.setDate(4, new java.sql.Date(event.getDeadLine()));
			c.setInt(5, event.getAssignedBy());
			c.setString(6, EventStatus.started.toString());
			c.setInt(7, event.getAssignedTo());
			c.registerOutParameter(8, Types.INTEGER);
			c.execute();
			
			return c.getInt(8);
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			c.close();
			db.dispose();
		}
		
		return 0;
	}
	
	/** 
	* @Title: getEventById 
	* @Description: TODO 
	* @param @param eventId
	* @param @return
	* @param @throws SQLException    
	* @return Event    
	* @throws 
	*/
	public Event getEventById(int eventId) throws SQLException{
		Connection conn = db.getConnection();
		CallableStatement c = null;
		
		try {
			c = conn.prepareCall("{call get_event_by_id(?)}");
			c.setInt(1, eventId);
			
			ResultSet rs = c.executeQuery();
			if(rs.next()){
				Event event = new Event();
				event.setAssignedBy(rs.getInt("assignedBy"));
				event.setAssignedTo(rs.getInt("assignedTo"));
				event.setGroupId(rs.getInt("group_id"));
				event.setEventID(rs.getInt("id"));
				event.setEventTitle(rs.getString("eventName"));
				event.setEventDescription(rs.getString("eventDescription"));
				event.setDeadLine(rs.getTimestamp("eventDeadLine").getTime());
				event.setEventStatus(rs.getString("eventStatus"));
				event.setCreatedAt(rs.getTimestamp("createdAt").getTime());
				event.setUpdatedAt(rs.getTimestamp("updatedAt").getTime());
				
				event.setGroupName(rs.getString("groupName"));
				
				return event;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			c.close();
			db.dispose();
		}
		return null;
	}
	
	/** 
	* @Title: getEventByGroup 
	* @Description: TODO 
	* @param @param user_id
	* @param @param group_id
	* @param @return
	* @param @throws SQLException    
	* @return List<Event>    
	* @throws 
	*/
	public List<Event> getEventByGroup(int user_id, int group_id) throws SQLException{
		Connection conn = db.getConnection();
		CallableStatement c = null;
		List<Event> events = new ArrayList<Event>();
		
		try {
			c = conn.prepareCall("{call get_event_by_group(?,?)}");
			c.setInt(1, group_id);
			c.setInt(2, user_id);
			ResultSet rs = c.executeQuery();
			while(rs.next()){
				Event event = new Event();
				event.setAssignedBy(rs.getInt("assignedBy"));
				event.setAssignedTo(rs.getInt("assignedTo"));
				event.setGroupId(rs.getInt("group_id"));
				event.setEventID(rs.getInt("id"));
				event.setEventTitle(rs.getString("eventName"));
				event.setEventDescription(rs.getString("eventDescription"));
				event.setDeadLine(rs.getTimestamp("eventDeadLine").getTime());
				event.setEventStatus(rs.getString("eventStatus"));
				event.setCreatedAt(rs.getTimestamp("createdAt").getTime());
				event.setUpdatedAt(rs.getTimestamp("updatedAt").getTime());
				
				event.setGroupName(rs.getString("groupName"));
				
				events.add(event);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			c.close();
			db.dispose();
		}
		return events;
	}
	//TODO update an event
	
}
