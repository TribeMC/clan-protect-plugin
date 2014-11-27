package Demage;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.potion.PotionType;

import Methoden.Online;
import V3Clan.main;

public class Events implements Listener {

	public Events(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	// Spieler im gleichen Clan sind nun geschützt
	@EventHandler
	public void onDMG(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (e.getDamager() instanceof Player) {

				Player p1 = (Player) e.getDamager();
				if (Online.sameClan(p, p1)) {
					e.setCancelled(true);

				}

			}

		}
	}

	@EventHandler
	public void potionthrow(PotionSplashEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			for (Entity z : e.getAffectedEntities()) {
				if (z instanceof Player) {
					Player p1 = (Player) z;
					if (Online.sameClan(p, p1)) {
						if (!e.getPotion().getType()
								.equals(PotionType.INSTANT_HEAL)
								|| !e.getPotion().getType()
										.equals(PotionType.SPEED)
								|| !e.getPotion().getType()
										.equals(PotionType.FIRE_RESISTANCE)
								|| !e.getPotion().getType()
										.equals(PotionType.REGEN)) {
							e.setCancelled(true);
						}
					}
				}
			}
			
		}
	}
	
	
}
