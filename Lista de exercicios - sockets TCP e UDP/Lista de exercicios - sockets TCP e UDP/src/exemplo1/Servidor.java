package ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    public static void main(String[] args) {
        
        try {
            //Criação do socket (socket->bind->listen)
            ServerSocket servidor = new ServerSocket(1234);
            //servidor.setSoTimeout(5000); //timeout do accept
            
            while(true){
                //Aguardar uma conexão
                System.out.println("\n\nAguardando um cliente se conectar...");
                Socket cliente = servidor.accept(); //bloqueante
                System.out.println("Um cliente se conectou -> "+cliente.getInetAddress().getHostAddress()+":"+cliente.getPort());

                //Aguarda uma mensagem do cliente
                //cliente.setSoTimeout(5000);//timeout do read
                DataInputStream dis = new DataInputStream(cliente.getInputStream());
                System.out.println("Aguardando mensagem do cliente...");
                String mensagem = dis.readUTF();//espera uma mensagem do cliente (bloqueante)
                System.out.println("Recebi do cliente -> "+mensagem);

                //Servidor envia uma mensagem ao cliente
                String resposta = mensagem.toUpperCase(); //deixa a mensagem que o cliente enviou em caixa alta
                System.out.println("Vou responder "+resposta);
                DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
                dos.writeUTF(resposta);//envia msg pro cliente
            }
            
            
        } catch (IOException ex) {
            System.out.println("Erro no servidor: "+ex.getMessage());
        }
    }
}
