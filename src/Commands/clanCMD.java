package Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Methoden.Clan;
import V3Clan.main;
import V3Clan.msg;

public class clanCMD implements CommandExecutor {

	public clanCMD(main main) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if (cs instanceof Player) {
			Player sen = (Player) cs;
			if (args.length == 1) {
				// Ranking
				if (args[0].equalsIgnoreCase("ranking")) {
					if (sen.hasPermission("clan.ranking")) {

						rankingColors(sen);
					} else {
						noPerm(sen);
					}
				}
				// Stats
				else if (args[0].equalsIgnoreCase("stats")) {
					if (sen.hasPermission("clan.stats")) {

						clanStats(sen);
					} else {
						noPerm(sen);
					}
				}
				// TopClan
				else if (args[0].equalsIgnoreCase("top")) {
					if (sen.hasPermission("clan.topclan")) {

						clanTop(sen);
					} else {
						noPerm(sen);
					}
				}
				// accept
				else if (args[0].equalsIgnoreCase("accept")) {
					if (sen.hasPermission("clan.accept")) {

						inviteaccept(sen);
					} else {
						noPerm(sen);
					}
				}
				// deny
				else if (args[0].equalsIgnoreCase("deny")) {
					if (sen.hasPermission("clan.deny")) {

						inviteDeny(sen);
					} else {
						noPerm(sen);
					}
				}
				// close
				else if (args[0].equalsIgnoreCase("close")) {
					if (sen.hasPermission("clan.close")) {

						closeClan(sen);
					} else {
						noPerm(sen);
					}
				} else {
					sendHelp(sen);
				}
			} else if (args.length == 2) {
				// invite
				if (args[0].equalsIgnoreCase("invite")) {
					if (sen.hasPermission("clan.invite")) {

						clanInvite(sen, args[1]);
					} else {
						noPerm(sen);
					}
				}
				// create
				else if (args[0].equalsIgnoreCase("create")) {
					if (sen.hasPermission("clan.create")) {

						clanCreate(sen, args[1]);
					} else {
						noPerm(sen);
					}
				}
				// kick
				else if (args[0].equalsIgnoreCase("kick")) {
					if (sen.hasPermission("clan.kick")) {

						clanKick(sen, args[1]);
					} else {
						noPerm(sen);
					}
				} else {
					sendHelp(sen);
				}
			} else {
				sendHelp(sen);
			}

		} else {
			cs.sendMessage("Nur Spieler können Clanzeugs machen!");
		}
		return false;
	}

	private void clanKick(Player sen, String string) {
		// TODO Auto-generated method stub
		if (Clan.isClanMember(sen.getName())) {
			String c = Clan.getClan(sen);
			if(Clan.isClanOwner(c, sen.getName().toLowerCase())){
				if (Clan.isClanMember(string)) {
					String c1 = Clan.getClanOff(string);
					if(c.equals(c1)){
						
						Clan.remove(c, string);
				}else{
					sen.sendMessage(msg.preifx + msg.notinthisClan);
				}
			}
			}else{
				sen.sendMessage(msg.preifx + msg.noOwner);
			}
		}else{
			sen.sendMessage(msg.preifx + msg.notInClan);
		}
		
	}

	private void clanCreate(Player sen, String string) {
		// TODO Auto-generated method stub

		if(!Methoden.Clan.clanExist(string)){
			Clan.clanCreate(string, sen);
			sen.sendMessage(msg.preifx + msg.createSuzess);
		}else{
			sen.sendMessage(msg.preifx + msg.clanAlrdyexist);
		}
	}

	private void clanInvite(Player sen, String string) {
		// TODO Auto-generated method stub
		if (Clan.isClanMember(sen.getName())) {
			String c = Clan.getClan(sen);
			@SuppressWarnings("deprecation")
			OfflinePlayer inv = Bukkit.getOfflinePlayer(string);
			if (inv.isOnline()) {

				Player invon = (Player) inv;
				
				Clan.invite(c, invon, sen);
			}else{
				sen.sendMessage(msg.preifx + msg.notOnline);

			}
		} else {
			sen.sendMessage(msg.preifx + msg.notInClan);

		}
	}

	private void closeClan(Player sen) {
		// TODO Auto-generated method stub
		if (Clan.isClanMember(sen.getName())) {
			String c = Clan.getClan(sen);
			if (Clan.isClanOwner(c, sen.getName())) {
				Clan.clanDelete(c);
				sen.sendMessage(msg.preifx + msg.clanSuzessDelete);
			} else {
				sen.sendMessage(msg.preifx + msg.noOwner);
			}
		} else {
			sen.sendMessage(msg.preifx + msg.notInClan);

		}

	}

	private void inviteDeny(Player sen) {
		// TODO Auto-generated method stub
		Clan.inviteDeny(sen);
	}

	private void inviteaccept(Player sen) {
		// TODO Auto-generated method stub
		Clan.inviteAccept(sen);
	}

	private void clanTop(Player sen) {
		// TODO Auto-generated method stub
		sen.sendMessage(msg.preifx + ChatColor.RED + "TopClan");
	}

	private void clanStats(Player sen) {
		// TODO Auto-generated method stub
		sen.sendMessage(msg.preifx + ChatColor.RED + "Clanstats");
	}

	private void rankingColors(Player sen) {
		// TODO Auto-generated method stub
		sen.sendMessage(msg.preifx + ChatColor.RED + "ranking :");
	}

	private void noPerm(Player sen) {
		// TODO Auto-generated method stub
		sen.sendMessage(msg.preifx + msg.noPerm);
	}

	private void sendHelp(Player sen) {
		// TODO Auto-generated method stub

		sen.sendMessage(msg.preifx + "Hilfe");
		sen.sendMessage(msg.preifx + ChatColor.RED + "/clan invite <player>");
		sen.sendMessage(msg.preifx + ChatColor.RED + "/clan kick <player>");
		sen.sendMessage(msg.preifx + ChatColor.RED + "/clan create <Name>");
		sen.sendMessage(msg.preifx + ChatColor.RED + "/clan close");
		sen.sendMessage(msg.preifx + ChatColor.RED + "/clan deny");
		sen.sendMessage(msg.preifx + ChatColor.RED + "/clan accpet");
		sen.sendMessage(msg.preifx + ChatColor.RED + "/clan stats");
		sen.sendMessage(msg.preifx + ChatColor.RED + "/clan top");
		sen.sendMessage(msg.preifx + ChatColor.RED + "/clan ranking");
		sen.sendMessage(msg.preifx + ChatColor.RED + "");
	}

}
