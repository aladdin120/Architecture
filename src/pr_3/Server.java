package pr_3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;


class ServerLogic extends Thread {

    private final Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ServerLogic(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Server.story.printStory(out);
        start();
    }

    @Override
    public void run() {
        String word;
        try {
            word = in.readLine();
            try {
                out.write(word + "\n");
                out.flush();
            } catch (IOException ignored) {}
            try {
                new Sender(Server.story, out).start();
                while (true) {
                    word = in.readLine();
                    if(word.equals("stop")) {
                        for (ServerLogic vr : Server.serverList) {
                            Server.story.printStory(vr.out);
                        }
                        this.downService();
                        break;
                    }
                    Server.story.addStoryEl(word);
                }
            } catch (NullPointerException ignored) {}
        } catch (IOException e) {
            this.downService();
        }
    }


    private void downService() {
        try {
            if(!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (ServerLogic vr : Server.serverList) {
                    if(vr.equals(this)) vr.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {}
    }

    private class Sender extends java.lang.Thread {
        Story story;
        BufferedWriter writer;

        public Sender(Story story, BufferedWriter writer) {
            this.story = story;
            this.writer = writer;
        }

        @Override
        public void run() {
            try {
                while(true) {
                    story.printStory(writer);
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Story {

    private LinkedList<String> story = new LinkedList<>();

    public void addStoryEl(String el) {
        if (story.size() >= 10) {
            story.removeFirst();
            story.add(el);
        } else {
            story.add(el);
        }
    }


    public void printStory(BufferedWriter writer) {
        if(story.size() > 0) {
            try {
                writer.write("New messages" + "\n");
                for (String vr : story) {
                    writer.write(vr + "\n");
                }
                writer.flush();
            } catch (IOException ignored) {}
        }

    }
}

public class Server {

    public static final int PORT = 8080;
    public static LinkedList<ServerLogic> serverList = new LinkedList<>();
    public static Story story;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        story = new Story();
        System.out.println("Server Started");
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerLogic(socket)); // добавить новое соединенние в список
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}
