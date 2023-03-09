package com.example.demo.Message;

import java.util.Date;

/**
 * Message class that creates message for websocket feature
 * For our application, email is essentially the users username
 * @author Nick Pehl and Cameron Lettieri
 *
 */

public class Message {

    private Long id;
	
    private String email;
	
    private String content;
	
    private Date sent = new Date();
	
	/**
	 * Default Constructor
	 */
	public Message(){
		
	}
	
	/**
	 * Constructor with parameters
	 * @param email
	 * @param content
	 */
	public Message(String email, String content) {
		this.email = email;
		this.content = content;
	}

	/**
	 * returns id
	 * @return id
	 */
    public Long getId() {
        return id;
    }

    /**
     * sets id
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

	/**
	 * returns email
	 * @return email
	 */
    public String getEmail() {
        return email;
    }

    /**
     * sets email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

	/**
	 * returns string/contents
	 * @return string/contents
	 */
    public String getContent() {
        return content;
    }

    /**
     * sets content of message
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * returns Date the message got sent
     * @param email
     */
    public Date getSent() {
        return sent;
    }
    
    /**
     * sets Date the message will be sent
     * @param sent
     */
    public void setSent(Date sent) {
        this.sent = sent;
    }

    
}

