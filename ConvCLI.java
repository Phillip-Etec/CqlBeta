package sqlConverterBeta;
import java.util.*;

public class ConvCLI {
	public static ArrayList<String> Lines=new ArrayList<>();
	public static ArrayList<String> ConvL=new ArrayList<>();
	
	public static Scanner in= new Scanner(System.in);
	public static void main(String[] args) {
		String command, conv="conv", terminate="terminate", open="open", clear="clear" ;
		do{
			command=in.nextLine();
			if (command.startsWith(terminate))System.exit(0);
			else if (command.startsWith(conv)) convertToSQL();
			else if (command.startsWith(open));
			else if (command.startsWith(clear)) {
				ConvL.clear();
				Lines.clear();
			}
		}while (command.equals(terminate)==false);
		
		
	}
	public static void convertToSQL() {
		Conversion Convert= new Conversion();
		int t=-1;
		boolean continuavel=true;
		while (continuavel==true) {
			Lines.add(in.nextLine());
			t++;
			if (Lines.get(t).equalsIgnoreCase("stop") || Lines.get(t).toLowerCase().startsWith("stop") || Lines.get(t).toLowerCase().endsWith("stop")) {
				continuavel=false;
				Lines.remove(t);
			}
		}
		for (int gamma=0;gamma<Lines.size();gamma++) {
			if (Lines.get(gamma).startsWith("cb")) {
				ConvL.add(Convert.createBase(Lines.get(gamma)));
			}
			else if (Lines.get(gamma).startsWith("ct")) {
				ConvL.add(Convert.CreateTable(Lines.get(gamma)));
			}
			else if (Lines.get(gamma).startsWith("sel"));
			else if (Lines.get(gamma).startsWith("ins")) {
				ConvL.add(Convert.InsertInto(Lines.get(gamma)));
			}
			else if (Lines.get(gamma).startsWith("del")) {
				ConvL.add(Convert.InsertInto(Lines.get(gamma)));
			}
			else if (Lines.get(gamma).startsWith("upd")) {
				//ConvL.add(Update(Lines.get(gamma)));
			}
			else ConvL.add("--"+Lines.get(gamma));
		}
		System.out.println("\n_____________\n");
		System.out.println("Código convertido para sql:\n");
		for (int y=0;y<ConvL.size();y++) System.out.println(ConvL.get(y));
		System.out.println("\n\n");
	}
	
}
