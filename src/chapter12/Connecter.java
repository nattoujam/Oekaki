/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author local-nattou
 */
public class Connecter implements Runnable {
    
    private final NetworkSender ns;
    
    public Connecter(NetworkSender ns) {
        this.ns = ns;
    }

    @Override
    public void run() {
        try{
           final ServerSocket server = new ServerSocket(10000);
           //OutputStream os = sender.;//送信用バイトストリーム取得
           

            System.out.println("Waiting now ...");
            
                try{
                    //サーバー側ソケット作成
                    Socket sc = server.accept();
                    System.out.println("Welcom!");
                    ns.addClient(sc);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                   
                }
            
        }
        catch(Exception e){
                e.printStackTrace();
        }
    }
    
}
