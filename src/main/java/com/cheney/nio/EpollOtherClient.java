package com.cheney.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class EpollOtherClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.socket().bind(new InetSocketAddress(8001));
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));
            ByteBuffer writeBuffer = ByteBuffer.allocate(32);
            ByteBuffer readBuffer = ByteBuffer.allocate(32);

            writeBuffer.put("hello 8002, I am 8001".getBytes());
            writeBuffer.flip();
            int count = 5;
            while (count > 0) {
                writeBuffer.rewind();
                socketChannel.write(writeBuffer);
                readBuffer.clear();
                socketChannel.read(readBuffer);
                System.out.println("client received " + new String(readBuffer.array()));
                count--;
            }
            writeBuffer.clear();
            writeBuffer.put("close".getBytes());
            writeBuffer.flip();
            socketChannel.write(writeBuffer);
            socketChannel.shutdownOutput();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
