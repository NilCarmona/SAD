import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server{

    private static Selector selector = null;

    public static void main(String[] args) {
        try {
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress("localhost", 1234));
            serverSocketChannel.configureBlocking(false);
            int validops = serverSocketChannel.validOps();
            serverSocketChannel.register(selector, validops, null);
            System.out.println("Servidor connectat, esperant clients...");
            
            while (true) {
                selector.select();
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> i = selectedKeys.iterator();
                while (i.hasNext()) {
                    SelectionKey key = i.next();
                    if (key.isAcceptable()) {                  
                        newUserAdded_NIO(serverSocketChannel);
                    }else if (key.isReadable()) {
                        messageNIO(key);
                    }
                    i.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void newUserAdded_NIO(ServerSocketChannel mySocket) throws IOException {
        SocketChannel client = mySocket.accept();
        client.configureBlocking(false);                                    
        int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        client.register(selector, interestSet, buffer);
    }

    private static void messageNIO(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        int bytes_read = client.read(buffer);
        buffer.flip();
        if (bytes_read > 0) {
            byte[] bytes = new byte[bytes_read];    int i = 0;
            while(buffer.hasRemaining()){
                bytes[i] = (byte) buffer.get(); 
                i++;
            }
            String data = new String(bytes);

            System.out.print(data);
            broadcast(data, key);
        }
    }

    private static void broadcast(String data, SelectionKey senderKey){
        ByteBuffer messageByteBuffer =ByteBuffer.wrap(data.getBytes());
        for(SelectionKey key : selector.keys()) {
            try{
                if(key.isWritable() && (key.channel() instanceof SocketChannel) && !key.equals(senderKey)) {
                    SocketChannel sChannel=(SocketChannel) key.channel();
                    sChannel.write(messageByteBuffer);
                    messageByteBuffer.rewind();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }   
        }
    }

   
}