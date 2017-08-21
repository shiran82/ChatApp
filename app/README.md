Hi and welcome to my chatApp

I really enjoyed working on the project.

Please pay attention to the following:

Instructions:
* The app can be downloaded on several devices that connect to a socket and can chat with each other
* I implemented the echo message - whenever client sends a message he get's the same message back.

Feature roadmap:
* I would use REALM to implement data persistence
* There needs to be data validation for user input to prevent spamming the server and for denial of service attacks (DoS or DDoS)
* Very easy to implement onType (when other side is typing) by just adding a listener like the listeners for the connect etc.)
* There needs to be a decision for what happens when a user is trying to send a message while offline, should it try to resend it, should user get a notification to try himself, should send button just be disabled, there needs to be an event sent from the sever side (like the event for incoming message implemented by the demo server I used) to tell client that the message has been received 
* Every ChatItem that comes from the server can be handled by using a different viewholder for each item. Just like MessageItem and TimestampItem - it is also possible to implement a Bill class and support it by creating a new viewholder that knows how to represent the data for that class. -> Modularity
