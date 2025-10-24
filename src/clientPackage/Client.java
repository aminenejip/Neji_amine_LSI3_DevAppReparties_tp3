package clientPackage;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import operation.Operation;

public class Client {   
    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client pas encore connecté");
            Socket socket = new Socket("192.168.1.28", 12345);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
           

            while (true) {
                System.out.println("donner l'operation à effectuer (ex: 5*3):");
                String operation = scanner.nextLine();
                 while (!operation.matches("\\d+[+\\-*/]\\d+")) {
                    System.out.println("Opération invalide. Entrez une opération valide (ex: 5*3):");
                    operation = scanner.nextLine();
                }
                String operator = operation.replaceAll("\\d", "");
                operator=operator.trim();
                int nb1 = Integer.parseInt(operation.split("\\" + operator)[0].trim());
                int nb2 = Integer.parseInt(operation.split("\\" + operator)[1].trim());
                Operation operation1 = new Operation(nb1, nb2, operator);
                out.writeObject(operation1);
              

                int result = (int) in.readObject();
                System.out.println("Le résultat est : " + result);

                System.out.println("Voulez-vous faire une autre opération ? (1 = oui / 2 = non)");
                String reponse = scanner.nextLine().trim();

                if (!reponse.equals("1")) {
                    System.out.println("Merci d'avoir utilisé notre service. Au revoir !");
                    break;
                }
                
            }
           

            socket.close();
            scanner.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur de connexion au serveur: " + e.getMessage());
        }
    }

    
   
}