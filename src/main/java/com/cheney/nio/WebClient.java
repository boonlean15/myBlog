package com.cheney.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class WebClient {
    public static void main(String[] args) {
        SocketChannel socketChannel = null;

        try {
            socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("127.0.0.1",8000));
            ByteBuffer byteBuffer = ByteBuffer.allocate(128);
            ByteBuffer secondBuffer = ByteBuffer.allocate(64);
            byteBuffer.put("hello".getBytes(StandardCharsets.UTF_8));
            secondBuffer.put("nio".getBytes(StandardCharsets.UTF_8));
            byteBuffer.flip();
            secondBuffer.flip();
            ByteBuffer[] byteBuffers = {byteBuffer,secondBuffer};
            socketChannel.write(byteBuffers);
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
