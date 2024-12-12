import java.util.Random;

public class HW_4 {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;

    public static int[] heroesHealth = {270, 260, 250, 300};
    public static int[] heroesDamage = {20, 15, 10, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medical"};

    public static int roundNumber;

    public static void main(String[] args) {
        printStatistics();

        while (!isGameOver()) {
            playRound();
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossAttack();
        heroesAttack();
        healing();
        printStatistics();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void heroesAttack() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coef = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage = heroesDamage[i] * coef;
                    System.out.println("Critical Damage: " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static void bossAttack() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void healing() {
        Random random = new Random();
        int indexMedical = 3;
        boolean healUsed = false;
        if (heroesHealth[indexMedical] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (i != indexMedical && heroesHealth[i] > 0 && heroesHealth[i] < 100) {
                    int randomHealPoint = random.nextInt(20) + 30;
                    heroesHealth[i] += randomHealPoint;
                    System.out.println("Medic healed " + randomHealPoint + "HP for " + heroesAttackType[i]);
                    healUsed = true;
                    break;
                }
            }
            if (!healUsed) {
                System.out.println("Никто не нуждается в хиле");
            }
        } else {
            System.out.println("Медик мертв");

        }

    }

    public static void printStatistics() {
        System.out.println("ROUND: " + roundNumber + " ----------------");
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: "
                + (bossDefence == null ? "No defence" : bossDefence)); // ternary condition
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
    }
}
