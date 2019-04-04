package com.indi.rpc;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2019/3/12.
 */
public class RpcProxyServerX {
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    public void publisher(final Object service, int port){
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            while(true){
                final Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket));
            }
        }catch (Exception e){

        }finally {
            try{

            }catch (Exception e){}
        }
    }
}
