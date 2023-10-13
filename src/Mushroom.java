public abstract class Mushroom extends Plant{
    boolean state;
    public Mushroom(String name, int sun_cost, boolean state) {
        super(name, sun_cost);
        this.state = state;
    }

    public boolean isAwake() {
        return state;
    }

    public void awaken(CoffeeBean coffeeBean) {
        state = true;
        System.out.println(coffeeBean.die());
    }

    public static class SunShroom extends Mushroom implements SunProducer {

        public SunShroom(boolean state) {
            super("Sun-shroom", 25, state);
        }

        @Override
        public int produce_sun() {
            if (isAwake()) {
                System.out.println(name + " produces 10 suns");
                return 10;
            }
            System.out.println(name + " is asleep and cannot produce sun");
            return 0;
        }
    }

    public static class PuffShroom extends Mushroom implements Attacker {

        public PuffShroom(boolean state) {
            super("Puff-shroom", 0, state);
        }

        @Override
        public int attack() {
            if (isAwake()) {
                System.out.println(name + " attacks");
                return 1;
            }
            System.out.println(name + " is asleep and cannot attack");
            return 0;
        }

        @Override
        public RangeType rangeType() {
            return RangeType.LIMITED_RANGE;
        }
    }

    public static class DoomShroom extends Mushroom implements Attacker, InstantKiller {

        public DoomShroom(boolean state) {
            super("Doom-shroom", 125, state);
        }

        @Override
        public String die() {
            hp = 0;
            return name + " dies while exploding and leaves a crater";
        }

        @Override
        public int attack() {
            if (isAwake()) {
                System.out.println(name + " attacks");
                System.out.println(die());
                return 10;
            }
            System.out.println(name + " is asleep and cannot attack");
            return 0;
        }

        @Override
        public RangeType rangeType() {
            return RangeType.AREA_OF_EFFECT;
        }

        @Override
        public KillType killType() {
            return KillType.INSTANT;
        }
    }
}