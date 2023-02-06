public class Sombrero2{
	public static java.util.Scanner console = new java.util.Scanner(System.in);
	public static java.util.Random dado = new java.util.Random();

	public static final int NUMERO_CASE = 4;
	public static final int GRIFFINDOR = 0;
	public static final int RAVENCLAW = 1;
	public static final int HUFLEPUFF = 2;
	public static final int SLYTHERIN = 3;

    public static final int RANDOM_ADD= 3;
	
	public static String ask(String prompt){ //lo usiamo per salvare un variabile di testo
		System.out.print(prompt + ": ");
		String answer = console.nextLine();  //legge cosa abbiamo scritto
		return answer;
	}  
	 public static int askNumber(String promt){ //idem ma con un numero
	 		System.out.print(promt + ": ");
			String answer = console.nextLine(); //legge cosa ha scritto l'utente
			int n = Integer.parseInt(answer);
			return n;
	}

	public static int hashName(String name){                           //passa il nome tutto a minuscolo e crea un hash da esso
		String noSpace = name.replace(" ","").toLowerCase();
		int hash = 0;
		for(int i=0;i<noSpace.length();i++){                            //trasforma i nomi in n
			hash= hash + noSpace.codePointAt(i) * (int)Math.pow(7, i);
		}
		return hash;
	}//hashName

	public static boolean isHouseFull(int pos, String[][] c){ //controllo se la casa è piena
	  int capienza = c[pos].length;
	  if (c[pos][capienza-1] != null){
	    return true;                                  //succede se una casa e piena
	  }//if
	  return false;                                   //non c'e bisogna di "else" -- succede se la casa non é piena
	}//isHouseFull


	public static int addToHouse(String n, int p, String[][] c){ //aggiunge un string(corrispondente al nome alunno) al primo posto libero
		String[] house = c[p];
		int col = 0;
		while(house[col] != null){                                //controlla se quella posizione della colonna (inizia da 0) è già occupata
		  col++;							                      //se è già occupata aggiunge un posto alla colonna e la ricontrolla
		}
		house[col] = n;                                           //trovata la colonna libera aggiunge l'elemento nuovo, il nome nuovo
		return col;                                               //ritorna la posizione della colonna in caso ci servisse
	}

	//Stampiamo il procedere della matrice
	public static void mostrandoCase(String[][] matriceCase){

        for (int i = 0; i< matriceCase.length; i++){
            switch(i) {
                case GRIFFINDOR:
                    System.out.print("* Griffindor: ");
                    break;
                case RAVENCLAW:
                    System.out.print("* Ravenclaw: ");
                    break;
                case HUFLEPUFF:
                    System.out.print("* Huflepuff: ");
                    break;
                case SLYTHERIN:
                    System.out.print("* Slytherin: ");
                    break;

            }
            for (int j = 0; j<matriceCase[i].length; j++){
				if (matriceCase[i][j] != null && j == matriceCase[i].length - 1){
					System.out.print(matriceCase[i][j]);
				} else if (matriceCase[i][j] != null){
					System.out.print(matriceCase[i][j] + ", ");
				}
                
            }
		System.out.println();
        }
	}

	public static String[][]  allungandoRiga(String[][] matrizOrg, int posizione, int cap){
		String[][] matrizCopia = new String[NUMERO_CASE][];
		while(matrizOrg[posizione].length-1 > cap){
				posizione++;
			}
			for(int i =0;i < NUMERO_CASE; i++){
				if(i == posizione){
					matrizCopia[i] = new String[cap + 1];
				} else {
				      matrizCopia[i] = new String[matrizOrg[i].length];
				  }
			}
			for (int i = 0; i < matrizOrg.length; i++){
				for (int j = 0; j < matrizOrg[i].length; j++){					
					matrizCopia[i][j] = matrizOrg[i][j];	
				}
			}
		return matrizCopia;
	}

	public static void funzionando(int capienza, int rimanenti, int pos, String[][] houses, String nome,int ripetizioni){
		for (int i = 1; i <= ripetizioni ; i++ ){
			nome = ask("\n\n\n--Inserisci il nome del prossimo studente oppure basta per terminare");
			if ((nome.replace(" ","").toLowerCase()).equals("basta") ){
				mostrandoCase(houses);
				System.exit(0);
			}
            //dobbiamo aggiungere una forma per permettere di smettere di riempire la matrice se vogliamo smettere
		  	int hn = hashName(nome);  
		   	int r = dado.nextInt(4);
		  	if (r == 0){
		    	 hn += RANDOM_ADD;
		    }//if

            pos = hn % NUMERO_CASE;
            boolean full = isHouseFull(pos,houses);
			
            if (!full){
		       int col = addToHouse(nome,pos,houses); //se non è piena aggiunge il nome                   
            } else {
                    do { //se è piena aumenta la posizione a un altra casa
                        pos = (pos + 1) % NUMERO_CASE;
                    }while(isHouseFull(pos, houses));
                    int col = addToHouse(nome,pos,houses);
            }
			if ((hn % 27) == 0){
				System.out.println("Difficile, molto difficile... Coraggio da vendere, vedo, e anche un cervello niente male. C'è talento, oh sì! E desiderio di mettersi alla prova, ma dove ti colloco?! ");
			}
			switch(pos) {
                case GRIFFINDOR:
                    System.out.println(nome +" inserito nella casa di... Griffindor!!!");
                    break;
                case RAVENCLAW:
                    System.out.println(nome +" inserito nella casa di... Ravenclaw!!!");
                    break;
                case HUFLEPUFF:
                    System.out.println(nome +" inserito nella casa di... Huflepuff!!!");
                    break;
                case SLYTHERIN:
                    System.out.println(nome +" inserito nella casa di... Slytherin!!!");
                    break;

            }		
            //mostrandoCase(houses);
		 }//for
	}

	public static void main(String[] args){
		System.out.println("***Benvenuti al nuovo anno di Java Howarts!***");
		System.out.println("Il cappelo selezionatore vi asegnara a le vostre case...");

		int n = askNumber("Quanti studenti ci sono questo anno magico? ");
		int capienza = n / NUMERO_CASE;
		int rimanenti = n % NUMERO_CASE;
		int pos = 0;
		String [][] houses = new String [NUMERO_CASE][capienza];
		String nome="mimmo";
		int tuttiAggiunti=rimanenti;
		
		funzionando(capienza, rimanenti, pos, houses, nome, capienza* NUMERO_CASE); //si riempono tutte le case al pari
		
		while(tuttiAggiunti > 0){
			int numCasuale = dado.nextInt(4);
			while(houses[numCasuale].length == capienza + 1){ //controlla se la casa a quell'indice ha già la capienza aumentata di uno
				numCasuale = dado.nextInt(4);
			}												//lancerà un "dado" casuale finchè non troverà una casa con una capienza standard, non ingrandita
			houses = allungandoRiga(houses, numCasuale, capienza);
			tuttiAggiunti--; 								//scala il contatore, arrivato a 0 avrà aggiunto tutti i "rimanenti"
		}
		
		if(rimanenti > 0 ){
			funzionando(capienza, rimanenti, pos, houses, nome, rimanenti);
		}
		
		mostrandoCase(houses);
		
    }//main

}//class





/*
+con input da tastiera per riempire l'array
+dovrà creare un array di stringhe con nome e cognome
+dovrà modificare l'array togliendo lo spazio e farle diventare minuscole
+per ogni nome deve eseguire un algoritmo che prenda il nome e genererà un numero intero
sum=c0*7^0 + c1*7^1 + c2*7^2 + c3*7^3...
+in maniera random una volta su quattro aggiungere il numero 3(se capita) al totale, questo per ogni persona
+dividere il numero totale per 4, se resto:
		0 = gryffindor
		1 = slytherin
		2 = hufflepuff
		3 = ravenclaw

+calcolare la grandezza massima in base a quanti siamo e tenere traccia delle assegnazioni, quando una casa raggiunge il numero massimo
verrà esclusa dalle assegnazioni quindi verrà assegnata alla casa dopo disponibile (quindi dobbiamo dividerci equamente)
+se ci dovessero essere delle persone restanti e le case sono tutte piene 
i restanti verranno assegnati a caso ad una casa tramite un numero casuale, (massimo 1 per casa)

*/
