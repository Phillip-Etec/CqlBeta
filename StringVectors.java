package metodos;
import java.util.*;
public class StringVectors {
	int[] allIndexesOf(String fullString, String toFind) {
		int[] rA;
		int ka=0;
		ArrayList<Integer> indexes= new ArrayList<>();
		if (fullString.indexOf(toFind)==-1) rA=new int[] {-1};
		else {
		while (fullString.indexOf(toFind)>=0) {
			indexes.add(fullString.indexOf(toFind)+ka);
			fullString=fullString.replaceFirst(toFind, "");
			ka++;
		}
		rA= new int[indexes.size()];
		for (int i=0; i<indexes.size();i++) rA[i]=indexes.get(i);
		}
		return rA;
	}
	int[] allIndexesOf(String[] fullString, String toFind) {
		int[] rA;
		int  q=0;
		ArrayList<Integer> indexes= new ArrayList<>();
		for (int i=0;i<fullString.length;i++) {
			if (fullString[i].indexOf(toFind)>=0) {
				q++;
				indexes.add(i);
			}
		}
		if (q==0) rA=new int[] {-1};
		else {
		rA= new int[q];
		for (int i=0; i<indexes.size();i++) rA[i]=indexes.get(i);
		}
		return rA;
	}
	String[] allWords (String fullString) {
		fullString=fullString.trim()+" ";
		String[] Allwords;
		int[] OsIndices =allIndexesOf(fullString, " ");
		ArrayList<String> palavras= new ArrayList<>();
		palavras.add(fullString.substring(0, OsIndices[0]));
		for (int j=1, i=0; i<OsIndices.length-1;i++, j++) palavras.add(fullString.substring(OsIndices[i]+1, OsIndices[j]));
		Allwords= new String [palavras.size()];
		for (int i=0;i<palavras.size();i++) Allwords[i]=palavras.get(i);
		return Allwords;
	}
	String[] everythingBetween (String fullString, String inBetweener) {
		fullString=fullString.trim()+" ";
		String[] Allwords;
		int[] OsIndices =allIndexesOf(fullString, inBetweener);
		ArrayList<String> palavras= new ArrayList<>();
		for (int j=1, i=0; i<OsIndices.length-1;i++, j++) palavras.add(fullString.substring(OsIndices[i]+1, OsIndices[j]));
		Allwords= new String [palavras.size()];
		for (int i=0;i<palavras.size();i++) Allwords[i]=palavras.get(i);
		return Allwords;
	}
	String[] everythingInside (String fullString, String inBetweener) {
		fullString=fullString.trim()+" ";
		String[] Allwords;
		int[] OsIndices =allIndexesOf(fullString, inBetweener);
		ArrayList<String> palavras= new ArrayList<>();
		for (int j=1, i=0; i<OsIndices.length-1;i++, j++) if (i%2==0) palavras.add(fullString.substring(OsIndices[i]+1, OsIndices[j]));
		Allwords= new String [palavras.size()];
		for (int i=0;i<palavras.size();i++) Allwords[i]=palavras.get(i);
		return Allwords;
	}
	String[] everythingOutside (String fullString, String inBetweener) {
		fullString=fullString.trim();
		String[] Allwords;
		int  j, i;
		ArrayList<String> palavras= new ArrayList<>();
		int[] OsIndices =allIndexesOf(fullString, inBetweener);
		palavras.add(fullString.substring(0, OsIndices[0]));
		for ( j=1, i=0; i<OsIndices.length-1;i++, j++) {
			if (i%2!=0) {
				palavras.add(fullString.substring(OsIndices[i]+1, OsIndices[j]));
			}
		}
		palavras.add(fullString.substring( OsIndices[j-1]+1));
		Allwords= new String [palavras.size()];
		for ( i=0;i<palavras.size();i++) Allwords[i]=palavras.get(i);
		return Allwords;
	}
	
	String randomWord () {
		String toReturn="";
		Random rand= new Random();
		int f=rand.nextInt(9);
		switch (f) {
		case 0:f=rand.nextInt(5);
		//the four seasons
			if (f==0) toReturn="Spring";
			else if (f==1) toReturn="Summer";
			else if (f==2) toReturn="Fall";
			else if (f==3) toReturn="Auttum";
			else if (f==4) toReturn="Winter";
			break;
		case 1:f=rand.nextInt(7);
		//the seven seas (modern)
				if (f==0) toReturn="Arctic Ocean";
				else if (f==1) toReturn="North Atlantic Ocean";
				else if (f==2) toReturn="South Atlantic Ocean";
				else if (f==3) toReturn="Indian Ocean";
				else if (f==4) toReturn="North Pacific Ocean";
				else if (f==5) toReturn="South Pacific Ocean";
				else if (f==6) toReturn="Antartic Ocean";
			break;
		case 2:f=rand.nextInt(7);
		//the seven wonders of the ancient world
				if (f==0) toReturn="The Great Pyramid of Giza";
				else if (f==1) toReturn="The Lighthouse of Alexandria";
				else if (f==2) toReturn="The Colossus of Rhodes";
				else if (f==3) toReturn="The Mausoleum at Halicarnassus";
				else if (f==4) toReturn="The Temple of Artemis at Ephesus";
				else if (f==5) toReturn="The Statue of Zeus at Olympia";
				else if (f==6) toReturn="The Hanging Gardens of Babylon";
			break;
		case 3:f=rand.nextInt(7);
		//the seven alchemical metals
				if (f==0) toReturn="Gold";
				else if (f==1) toReturn="Silver";
				else if (f==2) toReturn="Quicksilver";
				else if (f==3) toReturn="copper";
				else if (f==4) toReturn="Iron";
				else if (f==5) toReturn="Tin";
				else if (f==6) toReturn="Lead";
			break;
		case 4:f=rand.nextInt(7);
		//the seven classical planets
				if (f==0) toReturn="Sun";
				else if (f==1) toReturn="Moon";
				else if (f==2) toReturn="Mercury";
				else if (f==3) toReturn="Venus";
				else if (f==4) toReturn="Mars";
				else if (f==5) toReturn="Jupiter";
				else if (f==6) toReturn="Saturn";
				
			break;
		case 5:f=rand.nextInt(7);
		//the seven musical notes
	 		if (f==0) toReturn="Si";
	 		else if (f==1) toReturn="Do";
	 		else if (f==2) toReturn="Re";
	 		else if (f==3) toReturn="Mi";
	 		else if (f==4) toReturn="Fa";
	 		else if (f==5) toReturn="Sol";
	 		else if (f==6) toReturn="La";
			break;
			
	  	case 6:f=rand.nextInt(7);
		//the seven continents
 			if (f==0) toReturn="Africa";
 			else if (f==1) toReturn="Asia";
 			else if (f==2) toReturn="Europe";
 			else if (f==3) toReturn="Oceania";
 			else if (f==4) toReturn="Australia";
 			else if (f==5) toReturn="America";
 			else if (f==6) toReturn="Antarctica";
		break;
	  	case 7:f=rand.nextInt(7);
		//the seven seas (medieval Europe)
 			if (f==0) toReturn="Adriactic sea";
 			else if (f==1) toReturn="Mediterrenean sea";
 			else if (f==2) toReturn="Black Sea";
 			else if (f==3) toReturn="Caspian sea";
 			else if (f==4) toReturn="Persian Gulf";
 			else if (f==5) toReturn="Arabian sea";
 			else if (f==6) toReturn="Red sea";
		break;
	  	case 8:f=rand.nextInt(7);
		//the seven archangels
 			if 		(f==0) toReturn="Gabriel";
 			else if (f==1) toReturn="Michael";
 			else if (f==2) toReturn="Raphael";
 			else if (f==3) toReturn="Uriel";
 			else if (f==4) toReturn="Camael";
 			else if (f==5) toReturn="Jophiel";
 			else if (f==6) toReturn="Zadkiel";
		break;

		}
		
		/**
		  	case x:f=rand.nextInt(7);
			//the seven something
	 			if 		(f==0) toReturn="";
	 			else if (f==1) toReturn="";
	 			else if (f==2) toReturn="";
	 			else if (f==3) toReturn="";
	 			else if (f==4) toReturn="";
	 			else if (f==5) toReturn="";
	 			else if (f==6) toReturn="";
			break;
	
		}
		 */
		
		return toReturn;
	}
}
