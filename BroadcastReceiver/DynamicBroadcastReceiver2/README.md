<h1>Dynamic Receivers</h1>

A dynamic broadcast receiver, on the other hand, is registered and unregistered programmaticall
y at runtime using the registerReceiver() and unregisterReceiver() methods. This allows you to 
dynamically listen for broadcast intents and handle them based on your application's specific 
requirements. Dynamic receivers are usually used when you need to start or stop listening for 
\broadcasts based on certain conditions, or when you want to limit the receiver's lifespan to the 
lifetime of a specific component (e.g., an activity or a service).