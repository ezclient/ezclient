package clientname;

import clientname.gui.SplashProgress;

public class Client {
	
	private static final Client INSTANCE = new Client();
	public static final Client getInstance() { return INSTANCE; }
	
	private DiscordRP discordRP = new DiscordRP();
	
	public void init() {
		SplashProgress.setProgress(1, "Client - Initialising Discord RP!");
		discordRP.start();
	}
	
	public void shutdown() {
		discordRP.shutdown();
	}
	
	public DiscordRP getDiscordRP() {
		return discordRP;
	}
}
