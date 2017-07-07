package fr.esilv.simpleparql.crashTest;

import com.google.gson.*;
import org.apache.jena.atlas.json.io.parser.JSONParser;

import java.io.*;
import java.net.*;

public class Client extends Socket {

    public static void main(String[] zero) {
        Socket socket;
        try {
            socket = new Socket("localhost", 1234);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            PrintWriter output = new PrintWriter(new OutputStreamWriter(os));
            JsonObject jsonObject = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(new JsonPrimitive("dbpedia"));
            jsonObject.add("bases", jsonArray);
            jsonArray = new JsonArray();
            jsonArray.add(new JsonPrimitive("dbo:<http://dbpedia.org/ontology/>"));
            jsonObject.add("prefixes", jsonArray);
            jsonObject.addProperty("plateform", "simpleparql");
            jsonObject.addProperty("query", " SELECT ?place WHERE { \"John Smith\" dbo:birthPlace London. }");
            output.print(jsonObject);
            System.out.println("message sent");
            output.flush();
            socket.shutdownOutput();
            byte[] buffer = new byte[10];
            int read;
            String message = "";
            while ((read = is.read(buffer)) != -1) {
                String THEoutput = new String(buffer, 0, read);
                message += THEoutput;
            }
            System.out.println(message);
            socket.close();
            System.out.println("Socket closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

