import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

interface LaunchState {
    void handle(RocketContext context);
}

class PreLaunchState implements LaunchState {
    @Override
    public void handle(RocketContext context) {
        System.out.println("Pre-Launch Stage: All systems are 'Go' for launch.");
        context.setState(new FirstStageState());
    }
}

class FirstStageState implements LaunchState {
    @Override
    public void handle(RocketContext context) {
        if (context.getFuel() > 20) {
            context.setFuel(context.getFuel() - 10);
            context.setAltitude(context.getAltitude() + 10);
            context.setSpeed(context.getSpeed() + 1000);
            System.out.printf("Stage: 1, Fuel: %d%%, Altitude: %d km, Speed: %d km/h%n",
                    context.getFuel(), context.getAltitude(), context.getSpeed());
            if (context.getFuel() <= 50) {
                System.out.println("Stage 1 complete. Separating stage. Entering Stage 2.");
                context.setState(new SecondStageState());
            }
        } else {
            System.out.println("Mission Failed due to insufficient fuel.");
            context.setState(new FailedState());
        }
    }
}

class SecondStageState implements LaunchState {
    @Override
    public void handle(RocketContext context) {
        if (context.getFuel() > 5) {
            context.setFuel(context.getFuel() - 5);
            context.setAltitude(context.getAltitude() + 20);
            context.setSpeed(context.getSpeed() + 2000);
            System.out.printf("Stage: 2, Fuel: %d%%, Altitude: %d km, Speed: %d km/h%n",
                    context.getFuel(), context.getAltitude(), context.getSpeed());
            if (context.getAltitude() >= 100) {
                System.out.println("Orbit achieved! Mission Successful.");
                context.setState(new SuccessState());
            }
        } else {
            System.out.println("Mission Failed due to insufficient fuel.");
            context.setState(new FailedState());
        }
    }
}

class SuccessState implements LaunchState {
    @Override
    public void handle(RocketContext context) {
        System.out.println("Mission already successful. No further updates.");
    }
}

class FailedState implements LaunchState {
    @Override
    public void handle(RocketContext context) {
        System.out.println("Mission failed. No further updates.");
    }
}

class RocketContext {
    private LaunchState state;
    private int fuel = 100;
    private int altitude = 0;
    private int speed = 0;

    public RocketContext() {
        this.state = new PreLaunchState();
    }

    public void setState(LaunchState state) {
        this.state = state;
    }

    public LaunchState getState() {
        return state;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getAltitude() {
        return altitude;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void startLaunchSequence() {
        while (!(state instanceof SuccessState || state instanceof FailedState)) {
            state.handle(this);
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Simulation interrupted.");
            }
        }
    }
}

interface Command {
    void execute();
}

class StartChecksCommand implements Command {
    private RocketContext rocketContext;

    public StartChecksCommand(RocketContext rocketContext) {
        this.rocketContext = rocketContext;
    }

    @Override
    public void execute() {
        rocketContext.getState().handle(rocketContext);
    }
}

// Concrete Command: Launch Rocket
class LaunchCommand implements Command {
    private RocketContext rocketContext;

    public LaunchCommand(RocketContext rocketContext) {
        this.rocketContext = rocketContext;
    }

    @Override
    public void execute() {
        rocketContext.startLaunchSequence();
    }
}

// Concrete Command: Fast Forward Simulation
class FastForwardCommand implements Command {
    private RocketContext rocketContext;
    private int seconds;

    public FastForwardCommand(RocketContext rocketContext, int seconds) {
        this.rocketContext = rocketContext;
        this.seconds = seconds;
    }

    @Override
    public void execute() {
        for (int i = 0; i < seconds; i++) {
            rocketContext.getState().handle(rocketContext);
        }
    }
}

public class RocketLaunchSimulator {
    private static final Logger logger = Logger.getLogger(RocketLaunchSimulator.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RocketContext rocket = new RocketContext();

        Command startChecks = new StartChecksCommand(rocket);
        Command launchRocket = new LaunchCommand(rocket);

        System.out.println("Welcome to the Rocket Launch Simulator!");
        System.out.println("Commands: start_checks, launch, fast_forward X");

        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("start_checks")) {
                    startChecks.execute();
                } else if (input.equalsIgnoreCase("launch")) {
                    launchRocket.execute();
                } else if (input.startsWith("fast_forward")) {
                    String[] parts = input.split(" ");
                    if (parts.length == 2) {
                        int seconds = Integer.parseInt(parts[1]);
                        Command fastForward = new FastForwardCommand(rocket, seconds);
                        fastForward.execute();
                    } else {
                        System.out.println("Invalid command. Try: fast_forward X");
                    }
                } else {
                    System.out.println("Unknown command. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error parsing input. Make sure to provide a valid number.");
                logger.log(Level.WARNING, "Invalid fast_forward input", e);
            } catch (Exception e) {
                System.out.println("An unexpected error occurred.");
                logger.log(Level.SEVERE, "Unexpected error", e);
            }
        }
    }
}