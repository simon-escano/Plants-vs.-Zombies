import java.util.Comparator;

public abstract class Plant {
    public static final int INFINITE = Integer.MAX_VALUE;

    String name;
    int hp;
    int sun_cost;

    public Plant(String name, int sun_cost) {
        this.name = name;
        this.hp = 6;
        this.sun_cost = sun_cost;
    }

    public Plant(String name, int hp, int sun_cost) {
        this.name = name;
        this.hp = hp;
        this.sun_cost = sun_cost;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public String die() {
        hp = 0;
        return name + " dies";
    }

    @Override
    public String toString() {
        if (hp == INFINITE) return name + " (∞) - cost: " + sun_cost;
        return name + " (" + hp + ") - cost: " + sun_cost;
    }

    // Add Plant subclasses here, and
    // Hint: You can also add Comparator inner classes here
    // WallNut and CoffeeBean given for free
    public static class Sunflower extends Plant implements SunProducer, Upgradable {

        public Sunflower() {
            super("Sunflower", 50);
        }

        @Override
        public int produce_sun() {
            System.out.println(name + " produces 25 suns");
            return 25;
        }

        @Override
        public PlantUpgrade upgrade() {
            return null;
        }
    }

    public static class TwinSunflower extends Plant implements SunProducer, PlantUpgrade{

        public TwinSunflower() {
            super("Twin Sunflower", 250);
        }

        @Override
        public int produce_sun() {
            System.out.println(name + " produces 50 suns");
            return 50;
        }

        @Override
        public int concurrentSunCost() {
            return 50;
        }
    }

    public static class Peashooter extends Plant implements Attacker {

        public Peashooter() {
            super("Peashooter", 100);
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            return 1;
        }

        @Override
        public RangeType rangeType() {
            return RangeType.SINGLE_LINE;
        }
    }

    public static class WallNut extends Plant{
        public WallNut() {
            super("Wall Nut", 25, 50);
        }
    }

    public static class Squash extends Plant implements Attacker, InstantKiller {

        public Squash() {
            super("Squash", INFINITE, 50);
        }

        @Override
        public String die() {
            hp = 0;
            return name + " dies while squashing zombies";
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            System.out.println(die());
            return 3;
        }

        @Override
        public RangeType rangeType() {
            return RangeType.LIMITED_RANGE;
        }

        @Override
        public KillType killType() {
            return KillType.CLOSE_CONTACT;
        }
    }

    public static class Jalapeno extends Plant implements Attacker, InstantKiller {

        public Jalapeno() {
            super("Jalapeno", INFINITE, 125);
        }

        @Override
        public String die() {
            hp = 0;
            return name + " dies while exploding";
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            System.out.println(die());
            return 5;
        }

        @Override
        public RangeType rangeType() {
            return RangeType.SINGLE_LINE;
        }

        @Override
        public KillType killType() {
            return KillType.INSTANT;
        }
    }

    public static class CoffeeBean extends Plant {
        public CoffeeBean() {
            super("Coffee Bean", INFINITE, 75);
        }
    }

    public static class LilyPad extends Plant implements Upgradable {

        public LilyPad() {
            super("Lily Pad", 25);
        }

        @Override
        public PlantUpgrade upgrade() {
            return null;
        }
    }

    public static class Cattail extends Plant implements Attacker, PlantUpgrade {

        public Cattail() {
            super("Cattail", 225);
        }

        @Override
        public int attack() {
            System.out.println(name + " attacks");
            return 1;
        }

        @Override
        public RangeType rangeType() {
            return RangeType.FREE_RANGE;
        }

        @Override
        public int concurrentSunCost() {
            return 25;
        }
    }

    public static class HPComparator implements Comparator<Plant> {

        @Override
        public int compare(Plant p1, Plant p2) {
            if (p1.hp == p2.hp) return p1.name.compareTo(p2.name);
            return -Integer.compare(p1.hp, p2.hp);
        }
    }

    public static class NameComparator implements Comparator<Plant> {

        @Override
        public int compare(Plant p1, Plant p2) {
            return p1.name.compareTo(p2.name);
        }
    }

    public static class SunCostComparator implements Comparator<Plant> {

        @Override
        public int compare(Plant p1, Plant p2) {
            if (p1.sun_cost == p2.sun_cost) return p1.name.compareTo(p2.name);
            return -Integer.compare(p1.sun_cost, p2.sun_cost);
        }
    }
}