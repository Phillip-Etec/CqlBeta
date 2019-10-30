package sqlConverterBeta;

import java.io.IOException;
import java.util.*;

public class Autoteste {
	private static List<String> concatTableValues= new ArrayList<>();
	private static List<String> tableNames= new ArrayList<>();
	private static List<String> tableValues= new ArrayList<>();
	private static List<String> databaseNames= new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		String[] comando=new String [4];
		comando[0]="cb exola";
		comando[1]="ct sala{cpf}";
		comando[2]="ins sala{'Summer ', 'aaa', 'aaa'}";
		comando[3]="del sala w pk=1 && cpf=4";
		comando[0]=createBase(comando[0]);
		comando[1]=CreateTable(comando[1]);
		comando[2]=InsertInto(comando[2]);
		comando[3]=DeleteFrom(comando[3]);
		for (int i=0;i<comando.length;i++) {
			System.out.println(comando[i]);
		}
	}
	
	public static String DeleteFrom (String fullCommand) {
		String toReturn="", tbName, tableName;
		if (fullCommand.charAt(3)!=' ') {
			String start, end;
			start = fullCommand.substring(0 , 3);
			end = fullCommand.substring(3);
			fullCommand = start+" "+end;
		}
		int[] todosOsIguais=allIndexesOf(fullCommand, "=");
		if (todosOsIguais[0]!=-1) {
			for (int i=0;i<todosOsIguais.length;i++) {
				if (fullCommand.charAt(todosOsIguais[i]+i-1)!=' ') {
					String start , end;
					start= fullCommand.substring(0, todosOsIguais[i]+i);
					end = fullCommand.substring(todosOsIguais[i]+i);
					fullCommand=start+" "+end;
				}
			}
		}
		//System.out.println(fullCommand);
		String[] everything= allWords(fullCommand);
		//System.out.println(everything.length);
		if (everything.length==2) {
			tbName="tb"+toLocalUppercase(everything[1], 0);
			everything[0]= "delete from";
			everything[1]= tbName+"\n--tu se esqueceu do where arrombado";
		}
		else if (everything.length>2) {
			tbName="tb"+toLocalUppercase(everything[1], 0);
			tableName=toLocalUppercase(everything[1], 0);
			for (int i=2;i<everything.length;i++) {
				if (everything[i].equalsIgnoreCase(""));
			}
		for (int i=0;i<everything.length;i++) {
			for (int y=0;y<tableValues.size();y++) {
				if (everything[i].indexOf("@")==-1) {
					if (tableValues.get(y).startsWith(everything[i]) && tableValues.get(y).endsWith(tableName)) everything[i]=tableValues.get(y);
			}
			else if (everything[i].indexOf("@")>=0) {
				if (tableValues.get(y).equals(everything[i])) everything[i]=tableValues.get(y);
			}
			
			}
			if (everything[i].equalsIgnoreCase("w")) everything[i]="	where";
			else if (everything[i].equalsIgnoreCase("pk")) everything[i]="cod"+tableName;
			else if (everything[i].equalsIgnoreCase("&&")) everything[i]="and";
			else if (everything[i].equalsIgnoreCase("||")) everything[i]="or";
			else if (everything[i].equalsIgnoreCase("==")) everything[i]="=";
		}
		everything[0]="delete from ";
		everything[1]=tbName+"\n";
		for (int i=0;i<everything.length;i++) {
			toReturn+=everything[i]+" ";
		}
		}
		return toReturn;
	}
	
	
	public static String InsertInto(String fullCommand) {
		String toReturn="", currTableName="", tValues="";
				//add spaces before and after brackets
				if (fullCommand.indexOf('{')>=0) {
				if (fullCommand.charAt(fullCommand.indexOf('{'))+1!=' ') {
					String comeco, meio, fim;
					comeco=fullCommand.substring(0, fullCommand.indexOf('{')+1);
					meio=fullCommand.substring(fullCommand.indexOf('{')+1, fullCommand.lastIndexOf('}'));
					fim=fullCommand.substring(fullCommand.lastIndexOf('}'));
					fullCommand=comeco+" "+meio+" "+fim;
				}
				//add space before the first bracket
				if (fullCommand.indexOf('{')>=0) {
					if (fullCommand.charAt(fullCommand.indexOf('{')-1)!=' ') {
					String comeco, fim;
					comeco=fullCommand.substring(0, fullCommand.indexOf('{'));
					fim=fullCommand.substring( fullCommand.indexOf('{'));
					fullCommand=comeco+" "+fim;
					}
				}
				}
				int [] allCommas=allIndexesOf(fullCommand, ",");
				//add spaces before and after commas
				for (int i=0;i<allCommas.length;i++) {
					if (fullCommand.charAt(allCommas[i]+1+i)!=' ') {
						String comeco, fim;
						comeco=fullCommand.substring(0, (allCommas[i])+i+1);
						fim=fullCommand.substring(allCommas[i]+i+1);
						fullCommand=comeco+" "+fim;
					}
				}
				allCommas=allIndexesOf(fullCommand, ",");
				for (int i=0;i<allCommas.length;i++) {
					if (allCommas[i]>=0) {
					if (fullCommand.charAt(allCommas[i]-1+i)!=' ') {
						String comeco, fim;
						comeco=fullCommand.substring(0, (allCommas[i])+i);
						fim=fullCommand.substring(allCommas[i]+i);
						fullCommand=comeco+" "+fim;
					}
					}
				}
		
				
		//now, you can start to convert it
		String[] everyThing= allWords(fullCommand);
		if (everyThing.length>2) {
			if (everyThing[1].startsWith("tb")) {
				currTableName=toLocalUppercase(everyThing[1].substring(2), 0);
			}
			else {
				currTableName=toLocalUppercase(everyThing[1], 0);
			}
			for (int i=0;i<concatTableValues.size();i++) {
				if (currTableName.equals(tableNames.get(i))) tValues=concatTableValues.get(i);
			}
			everyThing[0]="insert into tb"+currTableName.trim();
			everyThing[1]="("+tValues+")\nvalues";
			for (int i=0;i<everyThing.length;i++) {
				everyThing[i]=everyThing[i].replace("{", "(").replace("}", ")");
			}
			for (int i=0;i<everyThing.length;i++) {
				toReturn+=everyThing[i]+" ";
			}
		}
		else {
			toReturn="escreve o comando direito vadia puta";
		}
		return toReturn;
	}
	
	
	
	public static String createBase(String CB) {
		String toReturn="", dbName="", databaseName="";
		String[] everyThing= allWords(CB);
		if (everyThing.length>2) {
			toReturn="escreve o comado direito vadia puta";
		}
		else if(everyThing[0].startsWith("cb")) {
			if (everyThing[1].startsWith("db")) {
				databaseName=toLocalUppercase(everyThing[1].substring(2),0);
				dbName=toLocalUppercase(everyThing[1], 2);
			}else {
				databaseName=toLocalUppercase(everyThing[1], 0);
				dbName="db"+toLocalUppercase(everyThing[1], 0);
			}
			databaseNames.add(databaseName);
			everyThing[0]="create database "+dbName+"\ngo\nuse "+dbName;
			toReturn+=everyThing[0];
		}
		else {
			toReturn="escreve o comando direito vadia puta";
		}
		return toReturn;
	}
	
	
	
	public static String CreateTable (String ogLine) throws IOException {
		String toReturn="", tableName, tbName, concatValues="";
		List<String> tuplas= new ArrayList<>();
		if (ogLine.indexOf("}")<0 && ogLine.indexOf("{")>=0) {
			ogLine= ogLine+"}";
		}
		//delete semicolon at the end
		if (ogLine.charAt(ogLine.length()-2)==';') {
			String comeco, fim;
			comeco=ogLine.substring(0, ogLine.length()-2);
			fim=ogLine.substring(ogLine.length()-1);
			ogLine=comeco+fim;
		}
		//add spaces before and after brackets
		if (ogLine.charAt(ogLine.indexOf('{'))+1!=' ') {
			String comeco, meio, fim;
			comeco=ogLine.substring(0, ogLine.indexOf('{')+1);
			meio=ogLine.substring(ogLine.indexOf('{')+1, ogLine.lastIndexOf('}'));
			fim=ogLine.substring(ogLine.lastIndexOf('}'));
			ogLine=comeco+" "+meio+" "+fim;
		}
		//add space before the first bracket
		if (ogLine.indexOf('{')>=0) {
			if (ogLine.charAt(ogLine.indexOf('{')-1)!=' ') {
			String comeco, fim;
			comeco=ogLine.substring(0, ogLine.indexOf('{'));
			fim=ogLine.substring( ogLine.indexOf('{'));
			ogLine=comeco+" "+fim;
			}
		}
		int [] allCommas=allIndexesOf(ogLine, ","), allSemiColons;
		//delete spaces before commas
		for (int i=0;i<allCommas.length;i++) {
			if (ogLine.charAt(allCommas[i]+1-i)==' ') {
				String comeco, fim;
				comeco=ogLine.substring(0, (allCommas[i])+1-i);
				fim=ogLine.substring(allCommas[i]+2-i);
				ogLine=comeco+fim;
			}
		}
		allSemiColons=allIndexesOf(ogLine, ";");
		//add spaces before semicolons
		for (int i=0;i<allSemiColons.length;i++) {
			if (allSemiColons.length>=5) {
			if (ogLine.charAt(allSemiColons[i]-1+i)!=' ') {
				String comeco, fim;
				comeco=ogLine.substring(0, allSemiColons[i]+i);
				fim=ogLine.substring(allSemiColons[i]+i);
				ogLine=comeco+" "+fim;
			}
			}
		}
		allSemiColons=allIndexesOf(ogLine, ";");
		for (int i=0;i<allSemiColons.length;i++) {
			if (ogLine.charAt(allSemiColons[i]+1+i)!=' ') {
				String comeco, fim;
				comeco=ogLine.substring(0, allSemiColons[i]+i+1);
				fim=ogLine.substring(allSemiColons[i]+i+1);
				ogLine=comeco+" "+fim;
			}
		}
		//after all the mistakes have been corrected, you can finally start to convert it
		String[] everything=allWords(ogLine);
		tableName=everything[1];
		//remove tb from the table's name
		if (tableName.indexOf("tb")>=0) {
			tableName=toLocalUppercase(tableName.substring(2), 0);
		}
		else {
			tableName=toLocalUppercase(tableName, 0);
		}
		if (everything.length>2) {
			for (int i=2;i<everything.length;i++) {
				//replace the abbreviations and such
				if (everything[i].equalsIgnoreCase("{")) everything[i]="(\n	cod"+tableName+" int primary key identity (1:1)	;";
				if (everything[i].equalsIgnoreCase("}")) everything[i]="\n)";
				if (everything[i].equals("nn")) everything[i]="not null";
				if (everything[i].equalsIgnoreCase("nome") || everything[i].equals("nome;")) everything[i]="nome, varchar (64) not null;";
				else if (everything[i].equals("cpf")|| everything[i].equals("cpf;")) everything[i]="cpf, varchar (14) not null;";
				else if (everything[i].equalsIgnoreCase("rg")|| everything[i].equals("rg;")) everything[i]="rg, varchar (13) not null;";
				else if (everything[i].equalsIgnoreCase("dtn")|| everything[i].equals("dtn;")) everything[i]="dataNasc, smalldatetime not null;";
				else if (everything[i].equalsIgnoreCase("val")|| everything[i].equals("val;")) everything[i]="valor, smallmoney not null;";
				else if (everything[i].equalsIgnoreCase("vtot")|| everything[i].equals("vtot;")) everything[i]="valorTotal, smallmoney not null;";
				else if (everything[i].equalsIgnoreCase("dte")|| everything[i].equals("dte;")) everything[i]="data, smalldatetime;";
				else if (everything[i].equalsIgnoreCase("desc")|| everything[i].equals("desc;")) everything[i]="descricao, varchar (128);";
				else if (everything[i].equalsIgnoreCase("num")|| everything[i].equals("num;")) everything[i]="num, int;";
				else if (everything[i].equalsIgnoreCase("pag")|| everything[i].equals("pag;")) everything[i]="tipo, varchar (24) not null;";
				else if (everything[i].equalsIgnoreCase("qtd")|| everything[i].equals("qtd;")) everything[i]="quantidade, int not null;";
				if (i==everything.length-2 && everything[i].indexOf(";")>=0) everything[i]=everything[i].replace(";", "");
				//add regular values into the values list 
				//System.out.println(everything[i]);
				if (everything[i].indexOf(';')==-1 && everything[i].indexOf(",")>=0 && everything[i].indexOf('@')==-1) {
					String currValue=everything[i].substring(0, everything[i].indexOf(','));
					currValue=currValue+tableName;
					tuplas.add(currValue);
					tableValues.add(currValue);
					//System.out.println(currValue);
				}
				else if (everything[i].indexOf(";")>=0 && everything[i].indexOf(";")!=everything[i].length()-1 && everything[i].indexOf(",")>=0 && everything[i].indexOf('@')==-1) {
					String currValue=everything[i].substring(1, everything[i].indexOf(","));
					currValue=currValue+tableName;
					tuplas.add(currValue);
					tableValues.add(currValue);
					//System.out.println(currValue);
				}
				else if (everything[i].indexOf(";")>=0 && everything[i].indexOf(";")==everything[i].length()-1 && everything[i].indexOf(",")>=0 && everything[i].indexOf('@')==-1) {
					String currValue=everything[i].substring(0, everything[i].indexOf(","));
					currValue=currValue+tableName;
					tuplas.add(currValue);
					tableValues.add(currValue);
					//System.out.println(currValue);
				}
				//add foreign keys to the list
				else if (everything[i].indexOf("@")>=0) {
					String forKey=toLocalUppercase(everything[i].substring(1), 0), currValue;
					tuplas.add("cod"+forKey);
					everything[i]="cod"+forKey+" int foreign key references tb"+forKey+" (cod"+forKey+")";
					currValue="@"+forKey;
					tableValues.add(currValue);
				}
				everything[i]=everything[i].replace("vc", "varchar");
				everything[i]=everything[i].replace(",", tableName+" ");
				everything[i]=everything[i].replace(";", "\n\t,");
				everything[i]=everything[i].replace(":", ",");
				//  else if (everything[i].equalsIgnoreCase("")) everything[i]="";
			}
		}
		if (tableName.indexOf("tb")<0) tbName="tb"+tableName;
		else tbName=tableName;
		everything[0]="create table";
		everything[1]=tbName;
		for (int i=0;i<everything.length;i++) {
			toReturn+=everything[i]+" ";
		}
		for (int i=0;i<tuplas.size();i++) {
			//System.out.println(tuplas.get(i));
			if (i!=tuplas.size()-1) concatValues=concatValues+tuplas.get(i)+", ";
			else concatValues+=tuplas.get(i);
		}
		//System.out.println("\n_______"+concatValues+"\n______");
		concatTableValues.add(concatValues);
		tableNames.add(tableName);
		return toReturn;
	}
	
	
	
	
	
	
	
	public static String toLocalUppercase (String fullString, int index) {
		String toReturn="", start="", end="", M="" ;
		char toUp=Character.toUpperCase(fullString.charAt(index));
		start=fullString.substring(0, index);
		end=fullString.substring(index+1);
		M=Character.toString(toUp);
		toReturn=start+M+end;
		return toReturn;
	}
	public static int[] allIndexesOf(String fullString, String toFind) {
		int[] AllIndexes;
		int ka=0;
		ArrayList<Integer> indexes= new ArrayList<>();
		if (fullString.indexOf(toFind)==-1) AllIndexes=new int[] {-1};
		else {
		while (fullString.indexOf(toFind)>=0) {
			indexes.add(fullString.indexOf(toFind)+ka);
			fullString=fullString.replaceFirst(toFind, "");
			ka++;
		}
		AllIndexes= new int[indexes.size()];
		for (int i=0; i<indexes.size();i++) AllIndexes[i]=indexes.get(i);
		}
		return AllIndexes;
	}
	public static String[] allWords (String fullString) {
		fullString=fullString.trim()+" ";
		String[] Allwords;
		int[] OsIndices =allIndexesOf(fullString, " ");
		ArrayList<String> palavras= new ArrayList<>();
		palavras.add(fullString.substring(0, OsIndices[0]));
		for (int j=1, i=0; i<OsIndices.length-1;i++, j++) {
			palavras.add(fullString.substring(OsIndices[i]+1, OsIndices[j]));
		}
		Allwords= new String [palavras.size()];
		for (int i=0;i<palavras.size();i++) {
			Allwords[i]=palavras.get(i);
		}
		return Allwords;
	}
	public static String[] everythingBetween (String fullString, String inBetweener) {
		fullString=fullString.trim()+" ";
		String[] Allwords;
		int[] OsIndices =allIndexesOf(fullString, inBetweener);
		ArrayList<String> palavras= new ArrayList<>();
		for (int j=1, i=0; i<OsIndices.length-1;i++, j++) palavras.add(fullString.substring(OsIndices[i]+1, OsIndices[j]));
		Allwords= new String [palavras.size()];
		for (int i=0;i<palavras.size();i++) Allwords[i]=palavras.get(i);
		return Allwords;
	}
	public static String[] everythingInside (String fullString, String inBetweener) {
		fullString=fullString.trim()+" ";
		String[] Allwords;
		int[] OsIndices =allIndexesOf(fullString, inBetweener);
		ArrayList<String> palavras= new ArrayList<>();
		for (int j=1, i=0; i<OsIndices.length-1;i++, j++) if (i%2==0) palavras.add(fullString.substring(OsIndices[i]+1, OsIndices[j]));
		Allwords= new String [palavras.size()];
		for (int i=0;i<palavras.size();i++) Allwords[i]=palavras.get(i);
		return Allwords;
	}
	
}
