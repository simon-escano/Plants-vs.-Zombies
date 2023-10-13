import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Plant> plants = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Game Mode: ");
        String mode = sc.nextLine();

        boolean state = (mode.equals("Night") || mode.equals("Fog"));

        String input;
        do {
            System.out.print("Add a plant: ");
            input = sc.nextLine();
            switch (input) {
                case "DONE":
                    break;
                case "Sunflower":
                    plants.add(new Plant.Sunflower());
                    break;
                case "Twin Sunflower":
                    for (int i = 0; i < plants.size(); i++) {
                        Plant p = plants.get(i);
                        if (p instanceof Plant.Sunflower) {
                            plants.remove(i);
                            plants.add(i, new Plant.TwinSunflower());
                            break;
                        }
                    }
                    break;
                case "Peashooter":
                    plants.add(new Plant.Peashooter());
                    break;
                case "Wall Nut":
                    plants.add(new Plant.WallNut());
                    break;
                case "Squash":
                    plants.add(new Plant.Squash());
                    break;
                case "Jalapeno":
                    plants.add(new Plant.Jalapeno());
                    break;
                case "Coffee Bean":
                    for (Plant p : plants) {
                        if (p instanceof Mushroom && !((Mushroom) p).isAwake()) {
                            ((Mushroom) p).awaken(new Plant.CoffeeBean());
                            break;
                        }
                    }
                    break;
                case "Lily Pad":
                    plants.add(new Plant.LilyPad());
                    break;
                case "Cattail":
                    for (int i = 0; i < plants.size(); i++) {
                        Plant p = plants.get(i);
                        if (p instanceof Plant.LilyPad) {
                            plants.remove(i);
                            plants.add(i, new Plant.Cattail());
                            break;
                        }
                    }
                    break;
                case "Sun-shroom":
                    plants.add(new Mushroom.SunShroom(state));
                    break;
                case "Puff-shroom":
                    plants.add(new Mushroom.PuffShroom(state));
                    break;
                case "Doom-shroom":
                    plants.add(new Mushroom.DoomShroom(state));
                    break;
                default:
                    System.out.println(input + " is not a plant");
            }
        } while (!input.equals("DONE"));

        do {
            System.out.print("Do something: ");
            input = sc.nextLine();

            int n = 0, x = 0;
            switch (input) {
                case "DONE":
                    break;
                case "Produce Sun":
                    for (Plant p : plants) {
                        if (p instanceof SunProducer && p.isAlive()) {
                            n++;
                            x += ((SunProducer) p).produce_sun();
                        }
                    }
                    if (n > 0) {
                        System.out.println(n + " sun producers gather " + x + " suns");
                    } else {
                        System.out.println("You have no sun producers");
                    }
                    break;
                case "Attack":
                    for (Plant p : plants) {
                        if (p instanceof Attacker && p.isAlive()) {
                            n++;
                            x += ((Attacker) p).attack();
                        }
                    }
                    if (n > 0) {
                        System.out.println(n + " attackers dealing " + x + " damage");
                    } else {
                        System.out.println("You have no attackers");
                    }
                    break;
                case "Instant Kill Status":
                    for (Plant p : plants) {
                        if (p instanceof InstantKiller && p.isAlive()) {
                            switch (((InstantKiller) p).killType()) {
                                case INSTANT:
                                    System.out.println(p.name + " can kill instantly");
                                    break;
                                case CLOSE_CONTACT:
                                    System.out.println(p.name + " can kill on contact");
                                    break;
                            }
                            n++;
                        }
                    }
                    if (n == 0) System.out.println("You have no plants which can kill instantly");
                    break;
                case "Attacker Status":
                    for (Plant p : plants) {
                        if (p instanceof Attacker && p.isAlive()) {
                            switch (((Attacker) p).rangeType()) {
                                case SINGLE_LINE:
                                    System.out.println(p.name + " can attack on a single line");
                                    break;
                                case LIMITED_RANGE:
                                    System.out.println(p.name + " can attack only when enemy is nearby");
                                    break;
                                case AREA_OF_EFFECT:
                                    System.out.println(p.name + " can attack using area-of-effect");
                                    break;
                                case FREE_RANGE:
                                    System.out.println(p.name + " can attack any enemies from anywhere");
                                    break;
                            }
                            n++;
                        }
                    }
                    if (n == 0) System.out.println("You have no attackers");
                    break;
                case "Sort by HP":
                    plants.sort(new Plant.HPComparator());
                    for (Plant p : plants) System.out.println(p);
                    break;
                case "Sort by Name":
                    plants.sort(new Plant.NameComparator());
                    for (Plant p : plants) System.out.println(p);
                    break;
                case "Sort by Sun Cost":
                    plants.sort(new Plant.SunCostComparator());
                    for (Plant p : plants) System.out.println(p);
                    break;
                default:
                    System.out.println("Unknown action: " + input);
            }
        } while (!input.equals("DONE"));
    }
}