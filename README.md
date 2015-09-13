# YavaSkypePoster
Provides a rest endpoint to post to Skype, using a single jar. 

Extract, then execute with "java -jar YavaSkypePoster-all.jar". Your active skype will prompt you to let java interact with it.
Will read config.properties if present to set your binding address

To use, send a post to the bound address\skype (or, by default, http://localhost:5555/skype ), ensure you have - 

**Headers:** Content:Type = application/json

**Body:** json string, in the format

```
{
    "chatId":"UserID or Chat ID",
    "message":" Jovial Sux"
}
```
To get chat ID's, you can SQLite into main.db in %appdata%\skype\username\main.db , or try 
http://stackoverflow.com/questions/11595994/getting-the-chat-id-from-call-id-with-the-skype-api

Try to avoid using Accepts headers, as jax-rs is picky about response types.
