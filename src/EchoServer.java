import java.io.*;
import java.net.*;

public class EchoServer{

    public static void main(String args[]) throws Exception{
        String clientSentence;
        String reversedSentence;
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));

        //keep running on loop...
        while(true){

            //the connection socket that accepts the call from the server socket
            Socket connectionSocket = serverSocket.accept();

            //bufferedreader to read what the client is typing.
            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            //outputstream to relay the message back to the client.
            DataOutputStream clientOut = new DataOutputStream(connectionSocket.getOutputStream());

            String s;
            StringBuffer sb;

            //keep running on loop waiting to hear response from client
            while((s = inFromClient.readLine()) != null){

                //if client sends # or $ then break out of loop
                if(s.equals("#") || s.equals("$")){
                    break;
                }
                //print out what client sent, just to show on the server side..
                System.out.println("Received: " + s);

                //string builder for reversing/inverting purposes - why rebuild the wheel, right?
                sb = new StringBuffer(s);
                reversedSentence = sb.reverse() + "\n";
                //write reversed message back to client.
                clientOut.writeBytes(reversedSentence);
            }

            //client sent # or $ so it is time to close the connection socket
            connectionSocket.close();
            //closing dataoutputstream
            clientOut.close();
            break;
        }
        //closing server socket.
        serverSocket.close();
    }
}