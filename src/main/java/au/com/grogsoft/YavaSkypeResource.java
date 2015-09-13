package au.com.grogsoft;

import com.skype.ChatProxy;
import com.skype.SkypeException;
import com.sun.jersey.core.spi.factory.ResponseBuilderImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/skype")
public class YavaSkypeResource {

    private SecurityReader securityReader = SecurityReader.getInstance();

    @GET
    @Path("/status")
    public String getStatus() {
        return "JovieSux";
    }

    @POST
    @Produces("text/html")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getMessage(Message message, @HeaderParam("ApiKey") String apikey ){
        String prefix = "";
        if( securityReader.useSecurity()) {
            if (null == apikey)
                return new ResponseBuilderImpl().status(401).build();
            if( !securityReader.getApiKeyToBotName().containsKey(apikey))
                return new ResponseBuilderImpl().status(401).build();
            prefix = "["+securityReader.getApiKeyToBotName().get(apikey)+"]: ";
        }

        try {
            ChatProxy.getChatInstance(message.chatId).send(prefix + message.message);
        } catch (SkypeException e) {
            return new ResponseBuilderImpl().status(500).build();
        }
        return new ResponseBuilderImpl().status(204).build();
    }

    public static class Message {
        public String chatId;
        public String message;
        public Message() {}
    }
}
