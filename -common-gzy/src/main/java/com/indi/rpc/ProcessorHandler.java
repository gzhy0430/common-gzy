package com.indi.rpc;

import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by Administrator on 2019/3/12.
 */
public class ProcessorHandler implements Runnable {
    Socket socket;
    public ProcessorHandler(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        try{
            inputStream = new ObjectInputStream(socket.getInputStream());
            //

//            RpcRequest rpcRequest = (RpcRequest) inputStream.readObject();
        }catch (Exception e){

        }
        /*private Objetc invoke(RpcRequest request){
            Object[] args = request.getParameters();
            Class<?>[] types = new Class[args.length];
            for (int i = 0; i < args.length; i++){
                types[i] = args[i].getClass();
            }
            Class clazz = Class.forName(request.getClasName());
            Method method = clazz.getMethod(request.getMethodName(), types);
            Objetct object = method.invoke();
            return result;
        }*/
    }
}
