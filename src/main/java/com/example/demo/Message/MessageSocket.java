package com.example.demo.Message;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.stereotype.Component;

@ServerEndpoint("/message/{id}")
@Component
public class MessageSocket {

	//Store all socket session and their corresponding id.
	private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
	private static Map<String, Session> idSessionMap = new Hashtable<>();

	private final Logger logger = LoggerFactory.getLogger(MessageSocket.class);
  
	@OnOpen
	public void onOpen(Session session, @PathParam("id") String id) 
    throws IOException {

		logger.info("Entered into Open");

		//store connecting user information
		sessionUsernameMap.put(session, id);
		idSessionMap.put(id, session);

	}

	@OnMessage
	public void onMessage(Session session, String message) throws IOException {

		// Handles new message
		logger.info("Entered into Message: Got Message:" + message);
		String id = sessionUsernameMap.get(session);


		//send the message to a user
		sendMessageToUser(id, message);

	} 


	@OnClose
	public void onClose(Session session) throws IOException {
		logger.info("Entered into Close");

		//remove the user connection information
		String id = sessionUsernameMap.get(session);
		sessionUsernameMap.remove(session);
		idSessionMap.remove(id);

	}


	@OnError
	public void onError(Session session, Throwable throwable) {
		// Do error handling here
		logger.info("Entered into Error");
		throwable.printStackTrace();
	}


	private void sendMessageToUser(String id, String message) {
		try {
			idSessionMap.get(id).getBasicRemote().sendText(message);
		} 
		catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
	}
}

