interface SunProducer {
    int produce_sun();
}

interface Attacker {
    enum RangeType {SINGLE_LINE, AREA_OF_EFFECT, LIMITED_RANGE, FREE_RANGE};
    int attack();
    RangeType rangeType();
}

interface InstantKiller {
    enum KillType {INSTANT, CLOSE_CONTACT};
    KillType killType();
}

interface PlantUpgrade {
    int concurrentSunCost();
}

interface Upgradable {
    PlantUpgrade upgrade();
}