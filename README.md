# YavaSkypePoster
Provides a REST endpoint to post to Skype, using a single jar.

Extract, then execute with "java -jar YavaSkypePoster-all.jar". Your active skype will prompt you to let Java interact with it.

Will read `config.properties` if present to set your binding address. If `apikeys.properties` is present, it will read this file and restrict people from using the service without supplying an API key in their post header.

To use, send a POST to the bound address\skype (or, by default, http://localhost:5555/skype), ensure you have:

**Headers:** ContentType = application/json, if security is enabled ApiKey = [apikey]

**Body:** JSON string, in the format

```
{
    "chatId": "UserID or ChatID to send the message to",
    "message": "Message body"
}
```

To get chat ID's, you can SQLite into main.db in %appdata%\skype\username\main.db, or try 
http://stackoverflow.com/questions/11595994/getting-the-chat-id-from-call-id-with-the-skype-api

Try to avoid using Accepts headers, as jax-rs is picky about response types.
