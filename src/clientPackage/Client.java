package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client pas encore connecté");

            Socket socket = new Socket("localhost", 12345);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                String operation = effectuerOperation(scanner);
                envoyerOperation(operation, socket);

                System.out.println("Voulez-vous faire une autre opération ? (1 = oui / 2 = non)");
                String reponse = scanner.nextLine().trim();

                if (!reponse.equals("1")) {
                    System.out.println("Merci d'avoir utilisé notre service. Au revoir !");
                    break;
                }
            }

            socket.close();
            scanner.close();
        } catch (IOException e) {
            System.out.println("Erreur de connexion au serveur: " + e.getMessage());
        }
    }

    static String effectuerOperation(Scanner scanner) {
        System.out.println("Donnez l'opération à effectuer (ex: 5*3):");
        String operation = scanner.nextLine();

        while (!operation.matches("\\d+[+\\-*/]\\d+")) {
            System.out.println("Opération invalide. Entrez une opération valide (ex: 5*3):");
            operation = scanner.nextLine();
        }

        return operation;
    }

    static void envoyerOperation(String operation, Socket socket) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(operation);
        out.flush();

        DataInputStream in = new DataInputStream(socket.getInputStream());
        int result = in.readInt();
        System.out.println("Le résultat est : " + result);
    }
}