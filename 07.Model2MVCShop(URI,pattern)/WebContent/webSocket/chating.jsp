<%@ page contentType="text/html; charset=EUC-KR"%>
<%@ page pageEncoding="EUC-KR"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>채팅및파일업로드</title>
</head>
<body>
	
	<textarea id="messageWindow" rows="10" cols="50" readonly="true"></textarea>
     <br/>
     <input id="inputMessage" type="text" onkeypress="if(event.keyCode == 13){ send()};"/>
     <input type="submit" value="전송" onclick="send()" />

    <input id="file" type="file" >
    
    <input id="send" type="button" value="send">
</body>
<script type="text/javascript">
        var textarea = document.getElementById("messageWindow");
        var serverIP = "ws://"+"192.168.0.28"+":8080/chat";
        var webSocket = new WebSocket(serverIP);
        var inputMessage = document.getElementById('inputMessage');
        
        webSocket.binaryType="arraybuffer";
        
    webSocket.onerror = function(event) {
      onError(event)
    };
    webSocket.onopen = function(event) {
      onOpen(event)
    };
    webSocket.onmessage = function(event) {
      onMessage(event)
    };
    function onMessage(event) {
    	
    	if('${user.role}'=="user"){
    		textarea.value +=  "운영자"+":z " + event.data + "\n";
    	}else if('${user.role}'=="admin"){
    		textarea.value +=  "${clientIP}"+":x " + event.data + "\n";	
    	}
        
        
        
    }
    function onOpen(event) {
    	if('${user.userId}'!=null){
        textarea.value += "연결되었습니다.\n";
    	}else{
    	textarea.value += "로그인시 이용가능한기능입니다.\n";
    	}
    }
    function onError(event) {
      alert(event.data);
    }
    function send() {
    	if('${user.role}'=="user"){
        textarea.value += "${user.userId}"+" 보냄1: " + inputMessage.value + "\n";
        webSocket.send(inputMessage.value);
        inputMessage.value = "";
    	}else if('${user.role}'=="admin"){
    		textarea.value += "${user.userId}"+" 보냄2: " + inputMessage.value + "\n";
            webSocket.send(inputMessage.value);
            inputMessage.value = "";	
    	}
    	
    }
    
    function sendFile(){
        var file = document.getElementById('file').files[0];
        webSocket.send('filename:'+file.name);
        alert('test');
        
        
        var reader = new FileReader();
        var rawData = new ArrayBuffer();            

        reader.loadend = function() {

        }
        
        reader.onload = function(e) {
            rawData = e.target.result;
            webSocket.send(rawData);
            alert("파일 전송이 완료 되었습니다.")
           
        }

        reader.readAsArrayBuffer(file);
    }

    function addEvent(){
        document.getElementById("send").addEventListener("click", sendFile, false);
    }
    
    window.addEventListener("load", addEvent, false);
    
    
    
  </script>
  

</html>