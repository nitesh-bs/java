/**
 * 
 */
var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
	
	let names=$("#name").val()
	localStorage.setItem("name",names);
        setConnected(true);
    var socket = new SockJS('/server1');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
       
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/return-to', function (greeting) {
            showGreeting(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
	let jsonOb={
		name:localStorage.getItem("name"),
		content:$("#content").val()
	}
    stompClient.send("/app/message", {}, JSON.stringify(jsonOb));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message.name +": "+message.content + "</td></tr>");
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});