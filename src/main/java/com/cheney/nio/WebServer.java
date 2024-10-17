package com.cheney.nio;


import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class WebServer {
    public static void main(String args[]) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress("127.0.0.1",8000));
            SocketChannel socketChannel = ssc.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(5);
            ByteBuffer secondBuffer = ByteBuffer.allocate(5);
            ByteBuffer[] byteBuffers = {byteBuffer,secondBuffer};
            socketChannel.read(byteBuffers);
            byteBuffer.flip();
            secondBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.println((char)byteBuffer.get());
            }
            System.out.println("-----------");
            while (secondBuffer.hasRemaining()){
                System.out.println((char)secondBuffer.get());
            }
            socketChannel.close();
            ssc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
