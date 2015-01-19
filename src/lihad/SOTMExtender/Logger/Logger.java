package lihad.SOTMExtender.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
	private BufferedWriter writer;
	private SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private boolean enabled = true;
	
	public Logger(File file){ file.getParentFile().mkdirs(); loadLog(file);}
	public void loadLog(File file){
		try {
			writer = new BufferedWriter(new FileWriter(file, true));
		} catch (IOException e) {
			System.out.println("failed to start.getLogger().  invalid file: "+file.getPath());
			error(null, e.toString(),e.getStackTrace());
		}
	}
	public void toggle_enabled(boolean t){ warning(null, "[logging has been changed = "+t+"]"); enabled = t; }
	
	public void buff(int x){
		if(enabled) try {for(int i = 0;i<x;i++){writer.newLine();} writer.flush();} catch (IOException e){error(null, e.toString(),e.getStackTrace());}
	}
	public void noformat(String string){
		if(enabled) try { writer.write(string); writer.newLine(); writer.flush(); System.out.println(string);} catch (IOException e){error(null, e.toString(),e.getStackTrace());}
	}
	public void info(Class<?> clas, String string){
		if(enabled) try { String s = (dateformat.format(Calendar.getInstance().getTime())+" [info] "+"["+clas.getName()+"] "+string); writer.write(s); writer.newLine();  writer.flush();
		System.out.println(s);} catch (IOException e){error(clas, e.toString(),e.getStackTrace());}
	}
	public void warning(Class<?> clas, String string){
		try { String s = (dateformat.format(Calendar.getInstance().getTime())+" [warning] "+"["+clas.getName()+"] "+string); writer.write(s); writer.newLine();  writer.flush();
		System.out.println(s);} catch (IOException e){error(clas, e.toString(),e.getStackTrace());}
	}
	public void severe(Class<?> clas, String string){
		try { String s = (dateformat.format(Calendar.getInstance().getTime())+" [severe] "+"["+clas.getName()+"] "+string); writer.write(s); writer.newLine();  writer.flush();
		System.out.println(s);} catch (IOException e){error(clas, e.toString(),e.getStackTrace());}
	}
	public void error(Class<?> clas, String s, StackTraceElement[] t){
		severe(clas, s);
		for(StackTraceElement t_a : t)
			try {writer.write(t_a.toString()); writer.newLine();  writer.flush(); System.out.println(t_a.toString());} catch (IOException e){e.printStackTrace();}
	}
	public void debug(Class<?> clas, String string){
		try { String s = (dateformat.format(Calendar.getInstance().getTime())+" [debug] "+"["+clas.getName()+"] "+string); writer.write(s); writer.newLine();  writer.flush();
		System.out.println(s);} catch (IOException e){e.printStackTrace();}
	}
}
