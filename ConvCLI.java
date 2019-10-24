package sqlConverterBeta;
import java.util.*;

public class ConvCLI {
	public static ArrayList<String> Lines=new ArrayList<>();
	public static ArrayList<String> ConvL=new ArrayList<>();
	public static ArrayList<String> tableN=new ArrayList<>();
	public static ArrayList<String> tableV=new ArrayList<>();
	
	public static int i=0, j=0, t=-1;
	public static String db="db",cb="cb", tb="tb", ct="ct", show="show", insert="ins", s=" ", delete="del", set="set", tab="	" ;
	public static String command, conv="conv", terminate="terminate", dt="dte", open="open", clear="clear" ;
	public static Scanner in= new Scanner(System.in);
	public static void main(String[] args) {
		Conversion Convert= new Conversion();
		
		do{
			command=in.nextLine();
			if (command.startsWith(terminate))for (int y=0;y<tableV.size();y++) System.out.println(tableV.get(y));// System.exit(i);
			else if (command.startsWith(conv)) convertToSQL();
			else if (command.startsWith(open));
			else if (command.startsWith(clear)) ConvL.clear();
		}while (command.equals(terminate)==false);
		
		
	}
	public static void convertToSQL() {
		Conversion Convert= new Conversion();
		boolean continuavel=true;
		while (continuavel==true) {
			Lines.add(in.nextLine());
			t++;
			if (Lines.get(t).equalsIgnoreCase("stop")) continuavel=false;
		}
		for (int gamma=0;gamma<Lines.size();gamma++) {
			if (Lines.get(gamma).startsWith(cb)) {
				ConvL.add(Convert.createBase(Lines.get(gamma)));
			}
			else if (Lines.get(gamma).startsWith(ct)) {
				ConvL.add(Convert.CreateTable(Lines.get(gamma)));
			}
			else if (Lines.get(gamma).startsWith(show));
			else if (Lines.get(gamma).startsWith(insert)) {
				ConvL.add(InserIn(Lines.get(gamma)));
			}
			else if (Lines.get(gamma).startsWith(delete)) {
				ConvL.add(Delete(Lines.get(gamma)));
			}
			else if (Lines.get(gamma).startsWith("upd")) {
				ConvL.add(Update(Lines.get(gamma)));
			}
		}
		System.out.println("\n_____________\n");
		System.out.println("Código convertido para sql:\n");
		for (int y=0;y<ConvL.size();y++) System.out.println(ConvL.get(y));
		System.out.println("\n\n");
	}
	//public static String CB(String CB) {CB=CB.replace("cb", "§§");char m;String M, mn, dbName;m=CB.charAt(3);mn= Character.toString(m);M=Character.toString(m).toUpperCase();CB=CB.replace(mn, M);CB=CB.replace("§§", "cb");int temdb=CB.indexOf("db");if (temdb==-1) dbName=CB.substring(3);else dbName="db"+CB.substring(5);if (temdb==-1) CB=CB.replace(cb+s, "create database db");else CB=CB.replace(cb, "create database");CB=CB+"\ngo\nuse db"+dbName;return CB;}
	//public static String CT(String CT) {List<String> cTblN=new ArrayList<>();List<String> cTupV=new ArrayList<>();List<String> valores=new ArrayList<>();char m;CT=CT.replace("ct", "§§");String M, mn, cTN, cVN;m=CT.charAt(3);mn= Character.toString(m);M=Character.toString(m).toUpperCase();CT=CT.replaceFirst(mn, M);CT=CT.replace("§§", "ct");int temtb=CT.indexOf(tb), brckpos=CT.indexOf('{'), temfk=CT.indexOf("fk"), dotpos, codpos, h=1;if (temtb==-1) cTN=CT.substring(3, (brckpos-1));else cTN=CT.substring(5, (brckpos-1));dotpos= CT.indexOf("!");String ctC=CT, into;into= CT.substring(CT.indexOf('{'), CT.indexOf('}')+1);into=into.replace("{", "°");into=into.replace(";", "°");into=into.replace(",", cTN+",");into=into.replace("}", ",");into=into.replace("!", "");into=into.replace("fk", "cod");//System.out.println(into);while (into.indexOf(",")!=-1 && into.indexOf("°")!=-1) {//System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");cTupV.add(into.substring(into.indexOf("°")+1, into.indexOf(",")));//System.out.println(into.substring(into.indexOf("°")+1, into.indexOf(",")));into=into.replaceFirst("°", " ");into=into.replaceFirst(",", " ");}String tuplascon="";for (int y=0;y<cTupV.size();y++) {if (y!=cTupV.size()-1) tuplascon+=cTupV.get(y)+", ";else tuplascon+=cTupV.get(y);}V.clear();valores.add(tuplascon);//System.out.println(cTupV.get(0));//System.out.println("-------\n"+tuplascon+"\n------");while (ctC.indexOf("fk")!=-1) {ctC=ctC.replaceFirst("fk", "");h--;}while (temfk>=0) {j++;CT=CT.replaceFirst("fk", "cod");temfk=CT.indexOf("fk");//System.out.println(j);h++;}int qtdFk=h*1;j=0;while(CT.indexOf("!")!=-1) {j++;codpos= CT.indexOf("cod");cVN= CT.substring((codpos+3), (dotpos+qtdFk));///System.out.println(cVN);CT=CT.replaceFirst("cod", "FK");CT=CT.replaceFirst("!", " int foreign key references tb"+cVN+" (fk"+cVN+")");//CT=CT.replaceFirst("!", "");dotpos=CT.indexOf("!");qtdFk--;}CT=CT.replace("FK", "cod");CT=CT.replace("fk", "cod");if (temtb==-1) CT=CT.replace(ct+s, "create table tb");else CT=CT.replace(ct, "create table");CT=CT.replace(",", cTN+" ");CT=CT.replace("{", "(\n"+tab+"cod"+cTN+" int primary key identity (1,1)\n	,");CT=CT.replace(";", "\n	,");CT=CT.replace("vc", "varchar");CT=CT.replace("nn", "not null");CT=CT.replace("dte", "smalldatetime");CT=CT.replace("}", "\n)");tableN.add(cTN.replace("°", ", "));//System.out.println("-----\n"+tableN.get(0));tableV.add(tuplascon);return CT;}
	public static String InserIn (String II) {
		II=II.replace("ins", "§§§");
		char m;
		String M, mn, cTN, cVN="";
		II=II.replace("tb", "");
		m=II.charAt(4);
		mn= Character.toString(m);
		M=Character.toString(m).toUpperCase();
		String currTableName, Icopy=II, CTN;
		currTableName=Icopy.substring(4, Icopy.indexOf('{')).trim();
		CTN= currTableName;
		m=CTN.charAt(0);
		mn=Character.toString(m);
		M=mn.toUpperCase();
		CTN=CTN.replaceFirst(mn, M);
		II=II.replace("§§§", "ins");
		II=II.replaceFirst(currTableName, CTN);
		II=II.replaceFirst(" ", " tb");
		
		Icopy=Icopy.replace("tb", "");
		
		for (int y=0;y<tableN.size();y++) {
			if (CTN.equals(tableN.get(y))) cVN=tableV.get(y); //System.out.println(currTableName); //System.out.println("eureka");//System.out.println(cVN);
		}
		II=II.replaceFirst("ins", "insert into");
		II=II.replace("{", "("+cVN+")\nvalues (");
		II=II.replace('}', ')');
		II=II.replace('°', ' ');
		return II;
	}
	public static String Update (String updt) {
		char m, tab='	';
		String M, mn, currV, cVN="";
		updt=updt.replace("tb", "");
		m=updt.charAt(4);
		mn= Character.toString(m);
		M=Character.toString(m).toUpperCase();
		updt=updt.replaceFirst(mn, M);
		updt=updt.replaceFirst(" ", " tb");
		String currTableName, upcopy=updt;
		upcopy=upcopy.replace("tb", "");
		currTableName=upcopy.substring(4, upcopy.indexOf('{')).trim();
		for (int y=0;y<tableN.size();y++) {
			if (currTableName.equals(tableN.get(y))) cVN=tableV.get(y); //System.out.println(currTableName); //System.out.println("eureka");//System.out.println(cVN);
		}
		currV=updt.substring(updt.indexOf('[')+1, updt.indexOf('#'));
		String valuetobechanged=updt.substring(updt.lastIndexOf('[')+1, updt.lastIndexOf('#'));
		String cvcon=currV+currTableName, valtbc=valuetobechanged+currTableName;
		updt=updt.replaceFirst("upd", "update");
		updt=updt.replaceFirst("set", "\n"+tab+"set");
		updt=updt.replace("wh", "\n"+tab+"where");
		updt=updt.replaceFirst(currV, cvcon);
		if (updt.indexOf("fk")==-1 && updt.indexOf("pk")==-1)updt=updt.replace(valuetobechanged, valtbc);
		if (updt.indexOf("pk")>=0)updt=updt.replace("pk", "cod"+currTableName);
		if (updt.indexOf("fk")>=0)updt=updt.replace("fk", "cod");
		updt=updt.replace("{", "");
		updt=updt.replace("}", "");
		updt=updt.replace("[", "");
		updt=updt.replace("]", "");
		updt=updt.replace("#", "");
		return updt;
	}
	public static String Delete (String Del) {
		char m, tab='	';
		String M, mn;
		Del=Del.replace("tb", "");
		m=Del.charAt(4);
		mn= Character.toString(m);
		M=Character.toString(m).toUpperCase();
		Del=Del.replaceFirst(mn, M);
		Del=Del.replaceFirst(" ", " tb");
		String currTableName, upcopy=Del;
		upcopy=upcopy.replace("tb", "");
		currTableName=upcopy.substring(4, upcopy.indexOf("{")).trim();
		for (int y=0;y<tableN.size();y++) {
			if (currTableName.equals(tableN.get(y))) tableV.get(y);
		}
		String valuetobechanged="", valtbD="";
		if (Del.indexOf("#")<=0) {
			valuetobechanged=Del.substring(Del.lastIndexOf('[')+1, Del.lastIndexOf('#'));
			valtbD=valuetobechanged+currTableName;;
		}
		if (Del.indexOf("fk")==-1 && Del.indexOf("pk")==-1)Del=Del.replace(valuetobechanged,valtbD );
		Del=Del.replaceFirst("del", "delete from");
		Del=Del.replace("wh", "\n"+tab+"where");
		Del=Del.replace("pk", "cod"+currTableName);
		Del=Del.replace("fk", "cod");
		Del=Del.replace("[", "");
		Del=Del.replace("]", "");
		Del=Del.replace("}", "");
		Del=Del.replace("#", "");
		Del=Del.replace("{", "");
		return Del;
	}
	public static String Select (String toShow) {
		
		return toShow;
	}
}
