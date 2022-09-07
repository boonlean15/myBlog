package com.cheney.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public class EpollServer {

    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress("127.0.0.1",8000));
            ssc.configureBlocking(false);
            //创建一个ServerSocketChannel，和一个Selector，
            // 并且把这个server channel 注册到 selector上，注册的时间指定，
            // 这个channel 所感觉兴趣的事件是 SelectionKey.OP_ACCEPT，这个事件代表的是有客户端发起TCP连接请求。
            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            writeBuffer.put("received".getBytes());
            writeBuffer.flip();

            while (true){
                //使用 select 方法阻塞住线程，当select 返回的时候，线程被唤醒
                int select = selector.select();
                //再通过selectedKeys方法得到所有可用channel的集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                System.out.println("打印key---");
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if(key.isAcceptable()){
                        SocketChannel socketChannel = ssc.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);
                        System.out.println("s有客户端connect 连接到服务端 服务端的accept");
                    } else if(key.isReadable()){
                        System.out.println("is Readable");
                        SocketChannel channel = (SocketChannel) key.channel();
                        readBuffer.clear();
                        int read = channel.read(readBuffer);
                        System.out.println("readBuffer 长度" + readBuffer.array().length + " channel read 值 -- " + read );
                        if(read == -1){
                            System.out.println("channel is not connected");
                            key.cancel();
                            if(channel != null){
                                channel.close();
                            }
                        }else {
                            System.out.println("readBuffer 长度" + readBuffer.array().length + " channel read 值 -- " + read );
                            readBuffer.flip();
                            System.out.println("服务端读操作 server received :" + new String(readBuffer.array()));
                            key.interestOps(SelectionKey.OP_WRITE);
                        }
//                        if(channel.isConnected()){
//                            readBuffer.clear();
//                            int read = channel.read(readBuffer);
//                            System.out.println("readBuffer 长度" + readBuffer.array().length + " channel read 值 -- " + read );
//                            readBuffer.flip();
//                            System.out.println("服务端读操作 server received :" + new String(readBuffer.array()));
//                            key.interestOps(SelectionKey.OP_WRITE);
//                        }else {
//                            System.out.println("channel is not connected");
//                            key.cancel();
//                            if(channel != null){
//                                channel.close();
//                            }
//                        }
                    } else if(key.isWritable()){
                        System.out.println("is isWritable");
                        SocketChannel channel = (SocketChannel) key.channel();
                        writeBuffer.rewind();
                        channel.write(writeBuffer);
                        key.interestOps(SelectionKey.OP_READ);
                        System.out.println("服务端写操作 server write a writeBuffer to client");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
