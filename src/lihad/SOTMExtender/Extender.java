package lihad.SOTMExtender;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

import com.alee.laf.WebLookAndFeel;

import lihad.SOTMExtender.GUI.Interface;
import lihad.SOTMExtender.Logger.Logger;
import lihad.SOTMExtender.Objects.Environment;
import lihad.SOTMExtender.Objects.Game;
import lihad.SOTMExtender.Objects.Hero;
import lihad.SOTMExtender.Objects.Player;
import lihad.SOTMExtender.Objects.TableEntity;
import lihad.SOTMExtender.Objects.Villain;

public class Extender {

	private static Logger logger;
	private static String directory = "C:\\testing\\sotm";
	private static Set<Player> players;
	private static Set<Hero> heroes;
	private static Set<Villain> villains;
	private static Set<Environment> environments;
	private static Set<Game> games;
	
	private static final double build = 5;
	
	private static int base_experience = 1000; //TODO: make this configurable

	private static Interface gui;

	public static void main(String[] args) {
		File log = new File(directory+"\\extender.log");
		try {log.createNewFile();} catch (IOException e) {e.printStackTrace();}

		logger = new Logger(log);
		logger.info(Extender.class, "Starting Lihad's 'Sentinel's of the Multiverse' Extender.  Alpha Build "+build);
		logger.info(Extender.class, "This extender is not endorsed by 'Sentinel's of the Multiverse'.");

		players = new HashSet<Player>();
		heroes = new HashSet<Hero>();
		villains = new HashSet<Villain>();
		environments = new HashSet<Environment>();		
		games = new HashSet<Game>();		

		logger.info(Extender.class, "Loading player save data...");
		loadPlayerData();
		logger.info(Extender.class, "... Complete. Loaded "+players.size()+" classes."); 

		logger.info(Extender.class, "Loading hero save data...");
		loadHeroData();
		logger.info(Extender.class, "... Complete. Loaded "+heroes.size()+" classes."); 

		logger.info(Extender.class, "Loading villain save data...");
		loadVillainData();
		logger.info(Extender.class, "... Complete. Loaded "+villains.size()+" classes."); 

		logger.info(Extender.class, "Loading environment save data...");
		loadEnvironmentData();
		logger.info(Extender.class, "... Complete. Loaded "+environments.size()+" classes."); 

		logger.info(Extender.class, "Loading previous game data...");
		loadGameData();
		logger.info(Extender.class, "... Complete. Loaded "+games.size()+" classes."); 
		
		logger.info(Extender.class, "Creating interface");
		WebLookAndFeel.install();
		gui = new Interface();
	}

	private static void loadData(String directory, String filetype){
		FileInputStream fis;
		ObjectInputStream in;
		File _dir = new File(directory);
		_dir.mkdirs();
		for(File f : _dir.listFiles()){
			String extension = "";
			int i = f.getName().lastIndexOf('.');
			if (i > 0) {extension = f.getName().substring(i+1);}
			if(extension.equalsIgnoreCase(filetype)){
				try {
					fis = new FileInputStream(f);
					in = new ObjectInputStream(fis);
					Object object = in.readObject();
					if(object instanceof Hero && filetype.equalsIgnoreCase("smh")) heroes.add((Hero) object);
					else if(object instanceof Villain && filetype.equalsIgnoreCase("smv")) villains.add((Villain) object);
					else if(object instanceof Environment && filetype.equalsIgnoreCase("sme")) environments.add((Environment) object);
					else if(object instanceof Player && filetype.equalsIgnoreCase("smp")){ players.add((Player) object);}
					else if(object instanceof Game && filetype.equalsIgnoreCase("smg")){ games.add((Game) object);}
					in.close();
				} catch (InvalidObjectException ioe) {
					logger.severe(Extender.class, "["+f.getName()+"] is corrupt. "+ioe.toString());
				} catch (Exception ex) {
					logger.error(Extender.class, ex.toString(), ex.getStackTrace());
				}
			}
		}
	}
	private static void loadGameData(){
		loadData(directory+"\\games\\", "smg");
	}
	
	private static void loadEnvironmentData(){
		loadData(directory+"\\environments\\", "sme");
	}

	private static void loadVillainData(){
		loadData(directory+"\\villains\\", "smv");
	}

	private static void loadHeroData(){
		loadData(directory+"\\heroes\\", "smh");
	}

	private static void loadPlayerData(){
		loadData(directory+"\\players\\", "smp");
	}

	private static void saveData(Object object, String filename){
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(object);
			out.close();
		} catch (Exception ex) {
			logger.error(Extender.class, ex.toString(), ex.getStackTrace());
		}
	}
	
	public static void saveGameData(Game game){
		saveData(game, directory+"\\games\\"+game.getTimestamp()+".smg");
	}

	public static void savePlayerData(Player player){
		saveData(player, directory+"\\players\\"+player.getName()+".smp");
	}

	public static void saveVillainData(Villain villain){
		saveData(villain, directory+"\\villains\\"+villain.getName()+(villain.isAdvanced() ? " ADV" : "")+".smv");
	}

	public static void saveHeroData(Hero hero){
		saveData(hero, directory+"\\heroes\\"+hero.getName()+".smh");
	}
	
	public static void saveTableEntityData(TableEntity tableentity){
		if(tableentity instanceof Villain) saveVillainData((Villain) tableentity);
		else if(tableentity instanceof Hero) saveHeroData((Hero) tableentity);
		else if(tableentity instanceof Environment) saveEnvironmentData((Environment) tableentity);
		else{
			getLogger().severe(Extender.class, "a TableEntity was attempted to be saved, which is unrecognizable.  ignoring.  no data saved ["+tableentity.getName()+"]");
		}
	}

	public static void saveEnvironmentData(Environment environment){
		saveData(environment, directory+"\\environments\\"+environment.getName()+".sme");
	}

	public static Set<Hero> getHeroes(){
		return heroes;
	}
	
	public static Set<Villain> getVillains(){
		return villains;
	}
	
	public static Villain getOppositeVillain(Villain villain){
		for(Villain v : getVillains()){
			if(v.getName().equalsIgnoreCase(villain.getName()) && (v.isAdvanced() != villain.isAdvanced())) return v;
		}
		return villain;
	}
	
	public static Set<Environment> getEnvironments(){
		return environments;
	}
	
	public static Set<Player> getPlayers(){
		return players;
	}
	
	public static Set<Game> getGames(){
		return games;
	}
	
	public static boolean addHero(Hero hero){
		return heroes.add(hero);
	}

	public static boolean addVillain(Villain villain){
		return villains.add(villain);
	}
	
	public static boolean addPlayer(Player player){
		return players.add(player);
	}

	public static boolean addEnvironment(Environment environment){
		return environments.add(environment);
	}
	
	public static void addGame(Game game){
		games.add(game);
	}
	
	public static int getBaseExperience(){
		return base_experience;
	}
	
	public static Logger getLogger(){
		return logger;
	}
	
	public static Interface getGUI(){
		return gui;
	}
}
