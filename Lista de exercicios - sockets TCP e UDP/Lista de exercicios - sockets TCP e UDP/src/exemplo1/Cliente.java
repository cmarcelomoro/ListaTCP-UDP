
package ex1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    public static void main(String[] args) {
        try {
            //Criação de um socket TCP cliente (socket->connect)
            Socket socket = new Socket("localhost", 1234);
            
            //Cliente envia uma mensagem ao servidor
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String mensagem = "boa noite";
            dos.writeUTF(mensagem);//envia a mensagem
            
            //Cliente espera uma mensagem do servidor
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String resposta = dis.readUTF();//espera uma mensagem (bloqueante)
            System.out.println("Resposta do servidor: "+resposta);
            
        } catch (IOException ex) {
            System.out.println("Servidor não encontrado: "+ex.getMessage());
        }
    }
}
