/**
 * 
 */
package com.emog.model;

/**
 * @author admin
 *
 */
public class City {
	private long id;
	private String state;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "City [id=" + id + ", state=" + state + "]";
	}
	

}
