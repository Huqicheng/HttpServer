package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.EventDao;
import entity.Event;

public class EventService {

	EventDao eventDao = null;
	
	public EventService(){
		eventDao = new EventDao();
	}
	
	public List<Long> getDatesHavingEvents(int user_id,String status){
		try {
			return eventDao.getDatesHavingEvents(user_id, status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ArrayList<Long>();
	}
	
	public Event assignEvent(Event event) throws SQLException{
		int id = eventDao.assignEvent(event);
		
		return eventDao.getEventById(id);
		
		
	}
	
	public List<Event> getEventByGroup(int user_id, int group_id){
		try {
			return eventDao.getEventByGroup(user_id, group_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Event>();
	}
	public static void main(String[] args) throws SQLException {
		Event event = new Event();
		event.setAssignedBy(2);
		event.setAssignedTo(2);
		event.setDeadLine(new Date().getTime());
		event.setDescription("232323");
		event.setEventTitle("title2");
		event.setGroupId(2);
		
		
		EventService es = new EventService();
		//System.out.println(es.assignEvent(event).getGroupName());
		
		System.out.println(es.getEventByGroup(2,2).size());
		
		//System.out.println(es.getDatesHavingEvents(1, "started"));
	}
}
