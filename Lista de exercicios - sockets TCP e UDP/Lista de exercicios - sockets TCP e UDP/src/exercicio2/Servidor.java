/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author laboratorio
 */
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

           
                DataInputStream dis = new DataInputStream(cliente.getInputStream());
                System.out.println("Aguardando numeros vindos do cliente");
                String op = dis.readUTF();
                double num2 = dis.readDouble();//espera uma mensagem do cliente (bloqueante)
                double num1 = dis.readDouble();
                
                System.out.println("Recebi do cliente -> "+num1 + num2);
                double resultado = 0;
                //Servidor envia uma mensagem ao cliente
                if(op.equals("soma")){
                    resultado = num1 + num2;
                    System.out.println(num1+" + "+num2+" = "+resultado);
                }
                if(op.equals("sub")){
                     resultado = num1 - num2;
                      System.out.println(num1+" - "+num2+" = "+resultado);
                }
                if(op.equals("multi")){
                    resultado = num1 * num2;
                    System.out.println(num1+" * "+num2+" = "+resultado);
                }
                if(op.equals("div")){
                    resultado = num1 / num2;
                    System.out.println(num1+" / "+num2+" = "+resultado);
                }
               
               
                DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
                dos.writeDouble(resultado);//envia msg pro cliente
                
                resultado = 0;
                
                
            }
            
            
        } catch (IOException ex) {
            System.out.println("Erro no servidor: "+ex.getMessage());
        }
    }
    
    
}
