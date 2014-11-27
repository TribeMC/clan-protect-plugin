package Methoden;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Clan {

	public static void add(String clan, String p) {

		File f = new File("plugins/ClanV3/Clans", clan.toLowerCase() + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		java.util.List<String> cmember = cfg.getStringList("member");

		for (String s : cmember) {
			@SuppressWarnings("deprecation")
			OfflinePlayer of = Bukkit.getOfflinePlayer(s);
			if (of.isOnline()) {
				Player op = (Player) of;
				op.sendMessage(V3Clan.msg.preifx + V3Clan.msg.ClanAddNewPlayer);
			}
		}
		cmember.add(p.toLowerCase());
		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void remove(String clan, String p) {
		File f = new File("plugins/ClanV3/Clans", clan.toLowerCase() + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		java.util.List<String> cmember = cfg.getStringList("member");
		cmember.remove(p);

		for (String s : cmember) {
			@SuppressWarnings("deprecation")
			OfflinePlayer of = Bukkit.getOfflinePlayer(s);
			if (of.isOnline()) {
				Player op = (Player) of;
				op.sendMessage(V3Clan.msg.preifx + V3Clan.msg.ClanRemovePlayer);
			}
		}

		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isInClan(String clan, String p) {
		boolean isClan = false;
		File pf = new File("plugins/ClanV3/Clans", clan.toLowerCase() + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(pf);

		java.util.List<String> cmember = cfg.getStringList("member");
		if (cmember.contains(p.toLowerCase())) {
			isClan = true;
		}
		return isClan;
	}

	public static boolean isClanMember(String p) {
		boolean isClan = false;
		File pf = new File("plugins/ClanV3/Clans");
		for (File f : pf.listFiles()) {
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

			java.util.List<String> cmember = cfg.getStringList("member");
			if (cmember.contains(p.toLowerCase())) {
				isClan = true;
			}
		}
		return isClan;
	}
	
	public static boolean isClanOwner(String c, String p){
		File f = new File("plugins/ClanV3/Clans", c.toLowerCase() + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		if(p.toLowerCase().equals(cfg.getString("Owner").toLowerCase())){
			return true;
		}else{
			return false;
		}
	}

	static HashMap<String, String> invites = new HashMap<>();

	public static void invite(String clan, Player p, Player invitor) {
		if (!isClanMember(p.getName())) {
			if (!invites.containsKey(p.getName())) {

				invites.put(p.getName(), clan.toLowerCase());
				p.sendMessage(V3Clan.msg.preifx + V3Clan.msg.InviteNewInvite);
				invitor.sendMessage(V3Clan.msg.preifx
						+ V3Clan.msg.InviteInvited);

			} else {
				invitor.sendMessage(V3Clan.msg.preifx
						+ V3Clan.msg.InviteAlready);
			}
		} else {
			invitor.sendMessage(V3Clan.msg.preifx + V3Clan.msg.InviteIsInClan);
		}
	}

	public static void inviteAccept(Player p) {

		if (invites.containsKey(p.getName())) {

			add(invites.get(p.getName()), p.getName());
			Online.addPlayer(p);
			p.sendMessage(V3Clan.msg.preifx + V3Clan.msg.InviteAccept);
		} else {
			p.sendMessage(V3Clan.msg.preifx + V3Clan.msg.InviteNoInvites);
		}
	}

	public static void inviteDeny(Player p) {
		if (invites.containsKey(p.getName())) {

			invites.remove(p);
			p.sendMessage(V3Clan.msg.preifx + V3Clan.msg.InviteDeny);
		} else {
			p.sendMessage(V3Clan.msg.preifx + V3Clan.msg.InviteNoInvites);
		}
	}

	public static String getClan(Player p) {
		String clan = "";

		File pf = new File("plugins/ClanV3/Clans");
		for (File f : pf.listFiles()) {
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

			java.util.List<String> cmember = cfg.getStringList("member");
			if (cmember.contains(p)) {
				clan = cfg.getString("clan");
			}
		}

		return clan;
	}
	public static String getClanOff(String string) {
		String clan = "";

		File pf = new File("plugins/ClanV3/Clans");
		for (File f : pf.listFiles()) {
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

			java.util.List<String> cmember = cfg.getStringList("member");
			if (cmember.contains(string)) {
				clan = cfg.getString("clan");
			}
		}

		return clan;
	}

	// Clan create

	public static boolean clanExist(String c) {
		File f = new File("plugins/ClanV3/Clans", c.toLowerCase() + ".yml");

		return f.exists();

	}

	public static void clanCreate(String c, Player p) {
		File f = new File("plugins/ClanV3/Clans", c.toLowerCase() + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		try {
			f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Displayname
		//NAme
		//Owner
		//Kills
		//Members
		
		cfg.set("DisplayName", c);
		cfg.set("Name", c);
		cfg.set("Owner", p.getName().toLowerCase());
		cfg.set("ClanKills", 0);
		java.util.List<String> cmember = cfg.getStringList("member");
		cmember.add(p.getName());
		cfg.set("member", cmember);
		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void clanDelete(String c){
		File f = new File("plugins/ClanV3/Clans", c.toLowerCase() + ".yml");
		f.delete();
	}
	
	
	public static void setDisplay(String c, String display){
		if(display.length() >= 3){
			display = display.substring(0, 2);
		}
		File f = new File("plugins/ClanV3/Clans", c.toLowerCase() + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		cfg.set("DisplayName", display);
		
		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
