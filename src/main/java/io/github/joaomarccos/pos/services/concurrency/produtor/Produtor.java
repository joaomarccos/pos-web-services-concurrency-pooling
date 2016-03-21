package io.github.joaomarccos.pos.services.concurrency.produtor;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
public class Produtor extends Thread {

    //Mapa com hashes disponíveis
    private final Map<String, Boolean> hashMap;
    //Mapa com respostas processadas e disponíveis para consumo
    private final Map<String, Long> responses;
    //Pilha de execução
    private final Queue<Task> taskQueue;

    public Produtor() {
        this.hashMap = new HashMap<>();
        this.responses = new HashMap<>();
        this.taskQueue = new ConcurrentLinkedQueue<>();
        initHashes();
    }

    private void initHashes() {
        this.hashMap.put("1", Boolean.FALSE);
        this.hashMap.put("2", Boolean.FALSE);
        this.hashMap.put("3", Boolean.FALSE);
        this.hashMap.put("4", Boolean.FALSE);
        this.hashMap.put("5", Boolean.FALSE);
        this.hashMap.put("6", Boolean.FALSE);
        this.hashMap.put("7", Boolean.FALSE);
        this.hashMap.put("8", Boolean.FALSE);
        this.hashMap.put("9", Boolean.FALSE);
        this.hashMap.put("10", Boolean.FALSE);
    }

    @Override
    public void run() {
        while (true) {
            try {
                //Pausa de 10 a 30 segundos
                Thread.sleep(Math.round((Math.random() * 20000) + 10000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Produtor.class.getName()).log(Level.SEVERE, null, ex);
            }

            Task currentTask = taskQueue.poll();
            if (currentTask != null) {
                responses.put(currentTask.getHash(), System.currentTimeMillis() - currentTask.getinitialTime());
            }
        }
    }

    public void addTask(String hash) {
        this.taskQueue.add(new Task(hash, System.currentTimeMillis()));
    }

    public String getResponse(String hash) {
        if (hashMap.containsKey(hash) && hashMap.get(hash) && responses.containsKey(hash)) {
            hashMap.put(hash, Boolean.FALSE);
            return hash + ":" + responses.get(hash);
        }

        return "noresponse";
    }

    public String requestSession(String id) {
        String sessionId = "nosession";
        if (hashMap.containsValue(false)) {
            for (String h : hashMap.keySet()) {
                if (hashMap.get(h) == false) {
                    sessionId = h;
                    hashMap.put(h, Boolean.TRUE);
                    break;
                }
            }
        }
        return id + ":" + sessionId;
    }

    private class Task {

        private final String hash;
        private final Long initialTime;

        public Task(String hash, long data) {
            this.hash = hash;
            this.initialTime = data;
        }

        public String getHash() {
            return hash;
        }

        public long getinitialTime() {
            return initialTime;
        }

    }

}
