package org.cultofclang.onelife;

public class BaseHunger implements Runnable {
    final int BASE_EXHAUSTION = 1;

    @Override
    public void run() {

        player.setExhaustion(player.getExhaustion() + BASE_EXHAUSTION );
    }
}
