package com.model2.mvc.webSocket;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;





@ServerEndpoint("/chat2")
public class Broadsocket {
	
	 private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	 public Broadsocket() {
		 System.out.println("Broadcast!!!");
	 }
	 
		 @OnOpen // 웹 소켓이 접속되면 세션을 넣는다
		 public void onOpen(Session session) {
	
		  System.out.println(session);
		  clients.add(session);
		 }

		 @OnMessage//웹 소켓으로 부터 메세지가 오면 호출한다.
		 public void onMessage(String message, Session session) throws IOException {
		  System.out.println(message);
		  if(message.contains("\\")) {
		  
		  String fileName = message.substring(message.lastIndexOf("\\")+1,message.lastIndexOf(".")+4);
		  String path = message.substring(0,message.indexOf(fileName));
		  System.out.println(path+"::::경로 출력");
		  
		  File file = new File(path+fileName);
		  byte[] bytes = new byte[(int)file.length()];
		  DataInputStream dateInput = new DataInputStream(new FileInputStream(file));
		  dateInput.readFully(bytes);
		  ByteBuffer buf = ByteBuffer.wrap(bytes);
		  
		  session.getBasicRemote().sendBinary(buf);
		  
		  }
		  //해당 세션으로 메세지를 보낸다.
		  synchronized (clients) {
		   	  for (Session client : clients) {
				  if (!client.equals(session)) {
					  client.getBasicRemote().sendText(message);
					  //나에게는 안보이고 상대방에게 보내면 상대방 화면에서만 나옴
					  	
				  }
			  }
		  	}
		 }
	
		
		 @OnClose//웹소켓 닫은후 행동
		 public void onClose(Session session) {

		  clients.remove(session);
		 }
}