package serverPackage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import operation.Operation;

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
                synchronized (serverSocket) {
                    numero_client++;
               
                System.out.println("Un nouveau client est connecté sont numéro est: " + numero_client);
                System.out.println("Adresse IP du client: " + socket.getInetAddress().getHostAddress());}
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
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    while (true) {
                        Operation operation = (Operation) in.readObject();
                        int result = 0;
                        // Extraction de l'opérateur
                        String operator = operation.getOperator();
                        //extraaction de nombre 
                        int nb1 = operation.getNb1();
                        int nb2 = operation.getNb2();
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
                        out.writeObject(result);
                        out.flush();
                    }
                } catch (ClassNotFoundException | IOException e) {
                    System.out.println("Le client numéro " + numero_client + " s'est déconnecté.");
                }
                
            }
        }
    }
            

     