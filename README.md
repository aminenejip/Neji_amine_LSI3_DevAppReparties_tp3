## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
# Neji_amine_LSI3_DevAppReparties_tp3
commit tp3_1:
Pour cette partie du TP3, j’ai complété l’activité du TP2 par l’implémentation d’un serveur multithread.
Lors de l’exécution, le serveur démarre et crée un ServerSocket qui écoute sur le port 12345, en attendant la connexion d’un client. À chaque connexion, il affiche le numéro du client ainsi que son adresse IP, puis lance un thread avec le ClientHandler chargé de gérer la communication avec ce client.