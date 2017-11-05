package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

import entity.Event;
import enums.EventStatus;
import enums.ExecResult;

import service.EventService;
import utils.StrutsUtil;

public class EventAction {

	String jsonEvent;
	
	int user_id;
	String status;
	
	int group_id;
	
	long timestamp;
	
	int event_id;
	
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public String getJsonEvent() {
		return jsonEvent;
	}
	public void setJsonEvent(String jsonEvent) {
		this.jsonEvent = jsonEvent;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void assignEvent(){
		HttpServletResponse response = ServletActionContext.getResponse();
		if(jsonEvent == null){
			try {
				StrutsUtil.write(response,ExecResult.failed.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
			
		}
		Event event = new Gson().fromJson(jsonEvent, Event.class);
		Event ultimate = null;
		try {
			ultimate = new EventService().assignEvent(event);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(ultimate == null){
			try {
				StrutsUtil.write(response,ExecResult.failed.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				StrutsUtil.write(response,new Gson().toJson(ultimate));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void getDatesHavingEvents(){
		HttpServletResponse response = ServletActionContext.getResponse();
		if(user_id == 0 || status == null){
			try {
				StrutsUtil.write(response,ExecResult.failed.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
			
		}
		List<Long> dates = new EventService().getDatesHavingEvents(user_id, status);
		
		try {
			StrutsUtil.write(response,new Gson().toJson(dates));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void getEventsByGroup(){
		HttpServletResponse response = ServletActionContext.getResponse();
		if(user_id == 0 || group_id == 0){
			try {
				StrutsUtil.write(response,ExecResult.failed.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
			
		}
		
		List<Event> events = new EventService().getEventByGroup(user_id, group_id);
		
		try {
			StrutsUtil.write(response,new Gson().toJson(events));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void getEventsByDate(){
		HttpServletResponse response = ServletActionContext.getResponse();
		if(user_id == 0 || timestamp == 0){
			try {
				StrutsUtil.write(response,ExecResult.failed.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
			
		}
		
		List<Event> events = new EventService().getEventsOfOneDate(user_id, timestamp);
		
		try {
			StrutsUtil.write(response,new Gson().toJson(events));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteEvent(){
		HttpServletResponse response = ServletActionContext.getResponse();
		if(event_id == 0){
			try {
				StrutsUtil.write(response,ExecResult.failed.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
			
		}
		
		boolean res = new EventService().deleteEvent(event_id);
		
		if(!res){
			try {
				StrutsUtil.write(response,ExecResult.failed.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				StrutsUtil.write(response,ExecResult.success.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void updateStatusOfEvent(){
		HttpServletResponse response = ServletActionContext.getResponse();
		if(event_id == 0 || status == null || (!status.equals(EventStatus.started )&& !status.equals(EventStatus.finished))){
			try {
				StrutsUtil.write(response,ExecResult.failed.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
			
		}
		
		boolean res = new EventService().updateStatusOfEvent(event_id, status);
		
		if(!res){
			try {
				StrutsUtil.write(response,ExecResult.failed.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			try {
				StrutsUtil.write(response,ExecResult.success.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
