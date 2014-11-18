var wsUri = "ws://" + document.location.host + document.location.pathname + "chat";
var websocket = new WebSocket(wsUri);

var users = [];

var username;
websocket.onopen = function (evt) {
    onOpen(evt)
};
websocket.onmessage = function (evt) {
    onMessage(evt)
};
websocket.onerror = function (evt) {
    onError(evt)
};
websocket.onclose = function () {
    onClose()
};
var output = document.getElementById("output");

function doSend(username, message) {
    var json = {
        'username': username,
        'message': message
    };
    websocket.send(JSON.stringify(json));
}

function join() {
    username = textField.value;
    doSend(username, "joined");
}

function send_message() {
    doSend(username, textField.value);
}

function leave() {
    doSend(username, "left the chat");
    username = ""
}

function list_users() {
    userField.innerHTML = "";
    for (index = 0; index < users.length; index++) {
        userField.innerHTML += users[index] + "\n";
    }
}

function onOpen() {
    writeToScreen("Connected to " + wsUri);
}

function onMessage(evt) {
    console.log("onMessage: " + evt.data);
    var json = JSON.parse(evt.data);
    if (json.message.indexOf("joined") != -1) {
        users.push(json.username);
        userField.innerHTML = "";
        list_users();
    } else if (json.message.indexOf("left the chat") != -1) {
        var index = users.indexOf(json.username);
        if (index > -1) {
            users.splice(index, 1);
        }
        list_users();
    } else {
        chatlogField.innerHTML += json.username + ": " + json.message + "\n";
    }
}

function onClose() {
    output.innerHTML += "Session closed"
}

function onError(evt) {
    writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}

