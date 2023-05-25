<h1>Static Broadcast Receiver</h1>

A static broadcast receiver is defined in the AndroidManifest.xml file and declared as a component
of your application. It allows your application to listen for system-wide or application-specific
broadcast intents, even when your app is not running. Static receivers are typically used for handling
system events or responding to broadcasts from other apps. They are registered automatically when your
app is installed.