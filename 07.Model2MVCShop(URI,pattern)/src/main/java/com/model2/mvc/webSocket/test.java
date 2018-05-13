package com.model2.mvc.webSocket;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;





@ServerEndpoint("/chat")
public class test {
	
	 /*DataInputStream dateInput;*/
	    String path = "C:\\Users\\Bit\\Desktop";
	    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	    Random random = new Random();
	    // 메세지를 받으면 호출된다.
	    @OnMessage
	    public void onMessage(Session session, String message) throws Exception {
	        System.out.println(message);
	        // 클라이언트에서 파일이 끝났다는 신호로 "end" 문자열을 보낸다.
	        // msg가 end가 아니라면 파일로 연결된 스트림을 연다.
	        if(message.contains("\\")){
	            
	            // 클라이언트에서 파일을 전송하기전 파일이름을 "file name:aaa.aaa" 형식으로 보낸다.
	            String fileName = message.substring(message.indexOf(":")+1);
	            System.out.println(fileName);
	            File file = new File(path + fileName);
	            byte[] bytes = new byte[(int)file.length()];
	            try {
/*	                dateInput = new DataInputStream(new FileInputStream(file));
	                dateInput.readFully(bytes);*/
	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            
	        // msg 가 end가 왔다면 전송이 완료되었음을 알리는 신호이므로 outputstream 을 닫아준다.
	        }else{
	        	
	        	synchronized (clients) {
	  		   	  for (Session client : clients) {
	  				  if (!client.equals(session)) {
	  					  client.getBasicRemote().sendText(message);
	  					  //나에게는 안보이고 상대방에게 보내면 상대방 화면에서만 나옴
	  					  	
	  				  }
	  			  }
	  		  	}
	        }	
	            
	    }
	    
	    // 바이너리 데이터가 오게되면 호출된다.
	    @OnMessage
	    public void binaryMessage(byte[] bytes, boolean last, Session session) {
	    	System.out.println("데이터옴");
	    	System.out.println(bytes.length+":::---");
	    	
	    	try {
	    	/*DataOutputStream copyfile = new DataOutputStream(new FileOutputStream("C:\\Users\\Bit\\Desktop\\test\\test.jpg",true));*/
	    	DataOutputStream copyfile = new DataOutputStream(new FileOutputStream("\\\\192.168.0.26\\Users\\Bit\\Desktop\\test.jpg",true));
	    	//IP의 공유 폴더를 통해 전송할 수 있다.
	    	copyfile.write(bytes);
		    copyfile.close();
		    if (last) {
		    
		    	
		         
		        
		        
		        		 
		        //이름을 바꿀 파일의 디렉토리 세팅
		        final String folder = "C:\\Users\\Bit\\Desktop\\test\\";
		        File file = new File(folder);
		         
		        //해당 디렉토리가 존재하는지 검사
		        if(!file.isDirectory()){
		            System.exit(-1);
		        }
		        
		        Map<String, String> map = new HashMap<String, String>();
		        
		        //파일들의 리스트를 읽어온다.
		           
		        String[] fileList = file.list();
		        File[] Files = file.listFiles();
		        for(int i=0; i<fileList.length; i++){
		        	//map.put("현재파일명", "바꿔야할 파일명");
			        map.put("test.jpg", "test"+fileList.length+".jpg");
		            for(int j=0; j<map.size(); j++){
		                if(map.containsKey(fileList[i])){ //map에 이름이 있는지 (있으면 TRUE, 없으면 FALSE)
		                    File newFileName = new File(folder + "/" + map.get(fileList[i])); //있다면 해당하는 value값(바꿔야할 이름)을 빼온다.
		                    if(Files[i].renameTo(newFileName)){
		                        System.out.println(i + "번째 파일명 : " + fileList[i] + "===> 변경 성공");
		                        map.remove(fileList[i]); //변경성공후 map비움
		                        break;
		                    }
		                }
		            }
		        }

		    	/* dateInput.close();*/
	            System.out.println("서버-->클라이언트 바이너리 전송완료");
	           
	        }   
	            
	                
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	    

	    @OnOpen
	    public void open(Session session) {
	        System.out.println("WebSocket File Server Open......");
	        clients.add(session);
	    }

	   /* @OnError
	    public void error(Session session, Throwable t) {
	        System.out.println("error.......");
	    }*/

	    @OnClose
	    public void closedConnection(Session session) {
	    	clients.remove(session);
	    	System.out.println("연결종료........");
	    }
}