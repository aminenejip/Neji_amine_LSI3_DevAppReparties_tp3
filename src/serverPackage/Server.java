package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server  {
    static int numero_client = 0;
    
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Je suis un serveur en attente de la connexion d'un nouveau client...");
            while (true) {
                Socket socket = serverSocket.accept();
                numero_client++;
                System.out.println("Un nouveau client est connecté sont numéro est: " + numero_client);
                System.out.println("Adresse IP du client: " + socket.getInetAddress().getHostAddress());
                new Thread(new Clienthandler(socket,numero_client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static class Clienthandler implements Runnable {
            private Socket socket;
            private int numero_client;  
            public Clienthandler(Socket socket, int numero_client) {
                this.numero_client = numero_client;  
                this.socket = socket;
            }
            
            @Override
            public void run() {
                try {
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    while (true) {
                        String operation = in.readUTF();
                        int result = 0;
                        //nettoyer l'operation
                        operation = operation.replaceAll("\\s", "");
                        // Extraction de l'opérateur
                        String operator = operation.replaceAll("\\d", "");
                        //extraaction de nombre 
                        int nb1 = Integer.parseInt(operation.split("\\" + operator)[0]);
                        int nb2 = Integer.parseInt(operation.split("\\" + operator)[1]);
                        //faire l'operation adaptée
                        switch (operator) {
                            case "+":
                                result = nb1 + nb2;
                                break;
                            case "*": 
                                result = nb1 * nb2;
                                break;
                            case "/":
                                result = nb1 / nb2;
                                break;
                            case "-":
                                result = nb1 - nb2;
                                break;
                        
                            default:
                                break;
                        }
                        out.writeInt(result);
                        out.flush();
                    }
                } catch (IOException e) {
                    System.out.println("Le client numéro " + numero_client + " s'est déconnecté.");
                }
                
            }
        }
    }
            

     