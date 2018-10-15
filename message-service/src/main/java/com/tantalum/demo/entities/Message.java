package com.tantalum.demo.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



@Entity
@Table(name="Messages")
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7904714611383119248L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String message;
	
	private String uuid;
	
	@Version
	private Long version;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStamp;
	
	@UpdateTimestamp
	private Date updatetime;
	


	public Message() {
		super();
	}

	public Message(String message) {
		super();
		this.message = message;	
	}

	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

		/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}

	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * @return the updatetime
	 */
	public Date getUpdatetime() {
		return updatetime;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\"{id\":\"" + id + "\", \"message\":\"" + message + "\", \"uuid\":\"" + uuid + "\", \"version\":\""
				+ version + "\", \"timeStamp\":\"" + timeStamp + "\", \"updatetime\":\"" + updatetime + "}\"";
	}

	







}
