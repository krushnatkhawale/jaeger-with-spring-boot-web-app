package com.my.bhandar.service;

import io.jaegertracing.internal.JaegerSpanContext;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SprintService {

    private final Tracer tracer;
    private String[] team = {"player-1", "player-2", "player-3", "player-4"};

    public SprintService(Tracer tracer) {
        this.tracer = tracer;
    }

    public void sprint(String relayId) {
        for (int i = 0; i < team.length; i++) {
            executeSprint(i, relayId, team[i]);
        }
    }

    private void executeSprint(int index, String relayId, String player) {
        int count = index + 1;
        Span sprintSpan = tracer.buildSpan("sprint-" + count)
                .withTag("player", player).start();

        doSomeRunning(player);

        sprintSpan.finish();
    }

    private void doSomeRunning(String player) {
        int sprintTime = getTime();
        long end, start = System.currentTimeMillis();
        do {
            end = System.currentTimeMillis();
        }
        while ((end - start) < sprintTime * 1000);
        System.out.println("Sprint by " + player + " in " + sprintTime + " seconds");
    }

    private int getTime() {
        Random random = new Random();
        while (true) {
            int i = random.nextInt(9);
            if (i > 1)
                return i;
        }
    }
}