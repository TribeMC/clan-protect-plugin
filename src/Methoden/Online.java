package Methoden;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class Online {

	
	static HashMap<Player, String> onlinePlayers = new HashMap<>();
	
	public static void addPlayer(Player p){
		onlinePlayers.put(p, Clan.getClan(p));
	}
	
	public static boolean sameClan(Player p, Player p1){
		if(onlinePlayers.get(p).equals(onlinePlayers.get(p))){
			return true;
		}else{
			return false;
		}
		
	}
	
	public static void remPlayer(Player p){
		if(onlinePlayers.containsKey(p)){
			onlinePlayers.remove(p);
		}
	}
}
