import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            // Conectando ao servidor na porta 6789
            Socket clientSocket = new Socket("localhost", 6789);

            // Criando o Scanner para entrada do usuário
            Scanner scanner = new Scanner(System.in);

            // Criando o BufferedReader para receber mensagens do servidor
            BufferedReader doServidor = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Criando o DataOutputStream para enviar mensagens ao servidor
            DataOutputStream paraServidor = new DataOutputStream(clientSocket.getOutputStream());

            String fraseCliente;
            String resposta;

            System.out.println("Digite sua pergunta (sem usar acentos) ou digite SAIR para finalizar:");

            while (true) {
                // Capturando a pergunta do usuário
                System.out.print("Você: ");
                fraseCliente = scanner.nextLine();

                // Enviando a pergunta ao servidor
                paraServidor.writeBytes(fraseCliente + "\n");

                // Recebendo a resposta do servidor
                resposta = doServidor.readLine();
                System.out.println(resposta);

                // Encerrando a conexão se o usuário digitar "SAIR"
                if (fraseCliente.equalsIgnoreCase("SAIR")) {
                    break;
                }
            }

            // Fechando recursos
            clientSocket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
