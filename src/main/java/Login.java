import client.ClientMain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login {

    static String name;

    public static void main(String[] args) throws IOException {
        ClientMain client1 = new ClientMain();
        ClientMain client2 = new ClientMain();
        ClientMain client3 = new ClientMain();
        ClientMain client4 = new ClientMain();
        client1.start();
        client2.start();
        client3.start();
        client4.start();


        //Input reader
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        /*
        choose wich client --> the Client will send a Message to the server
        The server will read the String and sends it to all other Clients
         */
        while (name != "quit") {
            String name = reader.readLine();
            switch (name){
                case "1":
                    //stuffhere
                    client1.sendMessage("Client1");

                    break;
                case "2":
                    //stuffhere
                    client2.sendMessage("Client2");

                    break;
                case "3":
                    //stuffhere
                    client3.sendMessage("Client3");
                    break;
            }
        }
    }
}
