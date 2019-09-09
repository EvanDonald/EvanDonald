import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
            Socket Socket = new Socket(hostName, portNumber);
        ) {
            ClientQA c = new ClientQA(Socket);
            c.handler();
        } 
        catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } 
        catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        } 
	   catch (Exception e) {
            System.err.println("Interrupted Exception.");
            System.exit(1);
        } 

    }
}