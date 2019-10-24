package sqlConverterBeta;

import java.io.IOException;
import java.util.*;

public class Autoteste {
	private static List<String> concatTableValues= new ArrayList<>();
	private static List<String> tableNames= new ArrayList<>();
	public static void main(String[] args) throws IOException {
		String[] comando=new String [3];
		comando[0]="cb exola";
		comando[1]="ct sala{nome}";
		//comando[2]="ins sala";
		comando[0]=createBase(comando[0]);
		comando[1]=CreateTable(comando[1]);
		//comando[2]=InsertInto(comando[2]);
		for (int i=0;i<comando.length;i++) {
			System.out.println(comando[i]);
		}
		//System.out.println(comando.indexOf(';'));
	}
	
	public static String InsertInto(String fullCommand) {
		String toReturn="", currTableName="", tValues="";
				//add spaces before and after brackets
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
			//databaseNames.add(databaseName);
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
				else if (everything[i].equals("nn")) everything[i]="not null";
				else if (everything[i].equalsIgnoreCase("nome")) everything[i]="nome, varchar (64) not null";
				else if (everything[i].equals("cpf")) everything[i]="cpf, varchar (14) not null";
				else if (everything[i].equalsIgnoreCase("rg")) everything[i]="rg, varchar (13) not null";
				else if (everything[i].equalsIgnoreCase("dtn")) everything[i]="dataNasc, smalldatetime not null";
				else if (everything[i].equalsIgnoreCase("val")) everything[i]="valor, smallmoney not null";
				else if (everything[i].equalsIgnoreCase("vtot")) everything[i]="valorTotal, smallmoney not null";
				else if (everything[i].equalsIgnoreCase("dte")) everything[i]="data, smalldatetime";
				else if (everything[i].equalsIgnoreCase("desc")) everything[i]="descricao, varchar (128)";
				else if (everything[i].equalsIgnoreCase("num")) everything[i]="num, int";
				else if (everything[i].equalsIgnoreCase("pag")) everything[i]="tipo, varchar (24) not null";
				else if (everything[i].equalsIgnoreCase("qtd")) everything[i]="quantidade, int not null";
				//add regular values into the values list 
				if (everything[i].indexOf(';')==-1 && everything[i].indexOf(",")>=0 && everything[i].indexOf('@')==-1) {
					String currValue=everything[i].substring(0, everything[i].indexOf(','));
					currValue=currValue+tableName;
					tuplas.add(currValue);
				}
				else if (everything[i].indexOf(";")>=0 && everything[i].indexOf(",")>=0 && everything[i].indexOf('@')==-1) {
					String currValue=everything[i].substring(1, everything[i].indexOf(","));
					currValue=currValue+tableName;
					tuplas.add(currValue);
				}
				//add foreign keys to the list
				else if (everything[i].indexOf("@")>=0) {
					String forKey=toLocalUppercase(everything[i].substring(1), 0);
					tuplas.add("cod"+forKey);
					everything[i]="cod"+forKey+" int foreign key references tb"+forKey+" (cod"+forKey+")";
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
			else concatValues=concatValues+tuplas.get(i);
		}
		//System.out.println(concatValues);
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
	
}