package me.statuxia.dolbit_normalno;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class DOLBIT_NORMALNO extends JavaPlugin {

    public static boolean SWITCH = false;
    private static final Random random = new Random();

    private static final String NAME = "Erelima";

    @Override
    public void onEnable() {
        register();
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            Bukkit.getScheduler().runTaskLater(this, () -> {
                aboba();
            }, random.nextInt(15) * 20);
        }, 25 * 20, 25 * 20);
    }

    private void aboba() {
        if (!SWITCH) {
            return;
        }
        int rnd = ThreadLocalRandom.current().nextInt(4);
        switch (rnd) {
            case 0 -> {
                // Швыряет игрока во все стороны и спавнит на месте, где игрок стоял, тнт.
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getName().equals(NAME))
                        continue;
                    player.sendActionBar("§5§krandomsymbolsrandomsymbols");
                    for (int i = 0; i < 10; i++) {
                        Bukkit.getScheduler().runTaskLater(this, () -> {
                            Location location = player.getLocation();
                            Vector direction = new Vector();
                            direction.setX(0.0D + Math.random() - Math.random());
                            direction.setY(Math.random() - Math.random());
                            direction.setZ(0.0D + Math.random() - Math.random());
                            player.setVelocity(direction.multiply(5));

                            TNTPrimed tnt = (TNTPrimed) location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
                            tnt.setFuseTicks(20);
                            tnt.setGravity(false);
                        }, 5 * i);
                    }
                }
            }
            // Забирает один предмет у игрока.
            case 1 -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getName().equals(NAME))
                        continue;
                    Inventory inv = player.getInventory();
                    int invRnd = ThreadLocalRandom.current().nextInt(inv.getSize());
                    ItemStack item = inv.getItem(invRnd);
                    player.getInventory().setItem(invRnd, null);
                    if (item != null) {
                        player.getWorld().dropItem(player.getLocation().add(random.nextInt(5) * (random.nextBoolean() ? -1 : 1), 5,
                                random.nextInt(5) * (random.nextBoolean() ? -1 : 1)), item);
                        player.sendActionBar("§cЗлые силы выкинули ваш предмет.");
                    }
                }
            }
            // Дает случайный бафф.
            case 2 -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getName().equals(NAME))
                        continue;
                    player.sendActionBar("§dСлучайный бафф!");
                    int potionRnd = ThreadLocalRandom.current().nextInt(PotionEffectType.values().length);
                    PotionEffect effect = new PotionEffect(PotionEffectType.values()[potionRnd], 60 * 20, 2);
                    player.addPotionEffect(effect);
                }
            }
            // Звук. Не более.
            case 3 -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getName().equals(NAME))
                        continue;
                    player.sendActionBar("§3Вы разбудили деда.");
                    player.playSound(player, Sound.ENTITY_WARDEN_EMERGE, 1.0f, 1.0f);
                }
            }
        }
    }

    public void register() {
        Bukkit.getPluginCommand("dolbitnormalno").setExecutor(new Commands());
    }
}
