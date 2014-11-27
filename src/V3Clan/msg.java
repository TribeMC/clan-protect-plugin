package V3Clan;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public class msg {

	public static String InviteAlready;
	public static String InviteIsInClan;
	public static String InviteNewInvite;
	public static String InviteInvited;
	public static String InviteNoInvites;
	public static String ClanAddNewPlayer;
	public static String ClanRemovePlayer;
	public static String InviteAccept;
	public static String InviteDeny;
	public static String preifx;
	public static String alreadyInvited;
	public static String noPerm;
	public static String clanSuzessDelete;
	public static String noOwner;
	public static String notInClan;
	public static String notOnline;
	public static String clanAlrdyexist;
	public static String createSuzess;
	public static String notinthisClan;

	
	public static void loadMSG(){
		
		File f = new File("plugins/ClanV3", "messages.yml");
		
		if(!f.exists()){
			createMSG();
		}
		
		loadTestweise();
		
	}


	private static void loadTestweise() {
		// TODO Auto-generated method stub
		preifx = ChatColor.GOLD + "ClanPLugin >> " + ChatColor.GRAY;
		
		InviteAccept = "Invite akzptiert";
		InviteAlready = "Bereits eingeladen";
		InviteDeny = "Einladung abgelehnt";
		InviteIsInClan = "Bereits in nem Clan";
		InviteInvited = "Eingeladen";
		InviteNewInvite = "Du wurdest in eien clan eingeladen";
		InviteNoInvites = "Keine einladungen";
		
		clanAlrdyexist = "Clan existiert bereits";
		clanSuzessDelete = "Clan gelöscht";
		ClanAddNewPlayer = "Neuer Spieler dabei";
		ClanRemovePlayer = "Spieler aus clan raus";
		
		alreadyInvited = "Bereits eingeladen";
		
		noOwner = "Du bist nicht Clanowner";
		noPerm = "Keine Rechte- Machtlos";
		notInClan = "In keinem Clan";
		notinthisClan = "Ist nich in diesem Clan";
		notOnline = "target is nich online";
		
		
		
		
	}


	private static void createMSG() {
		// TODO Auto-generated method stub
		File f = new File("plugins/ClanV3", "messages.yml");
		
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		//KP wie ich den Dafault File bekomme '-'
		cfg.set("Made by", "V3lop5");
	}
}
