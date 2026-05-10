package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> simulations;
    private final List<Thread> threads = new ArrayList<>();
    private final ExecutorService threadPool = Executors.newFixedThreadPool(4);

    public SimulationEngine(List<Simulation> input) {
        this.simulations = input;
    }

    public void runSync() {
        for (Simulation simulation : this.simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        for (Simulation simulation : this.simulations) {
            Thread simThread = new Thread(simulation);
            threads.add(simThread);
            simThread.start();
        }
    }

    public void runAsyncInThreadPool() {
        for (Simulation simulation : this.simulations) {
            threadPool.submit(simulation);
        }
        threadPool.shutdown();
    }

    public void awaitSimulationsEnd() throws InterruptedException {
        for (Thread simThread : this.threads) {
            simThread.join();
        }

        if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
            threadPool.shutdownNow();
        }
    }
}
