package tb.soft;

import java.util.*;
import java.util.Map.Entry;
import java.util.Collections;
import java.util.Collection;


public class PersonConsoleApp {
	private static final String COLLECTIONS =
			"    WYBÓR KOLEKCJI  		\n" +
			"1 - HashSet 				\n" +
			"2 - TreeSet				\n" +
			"3 - ArrrayList 			\n" +
			"4 - LinkedList				\n" +
			"5 - HashMap 				\n" +
			"6 - TreeMap				\n" +
			"0 - Zakończ program        \n";

	private static final String MENU = 
			"    M E N U   G Ł Ó W N E  	\n" +
			"1 - Podaj dane nowej osoby		\n" +
			"2 - Wypisz listę osób			\n" +
			"3 - Dodaj 2 razy tą samą osobę \n" +
			"4 - Wyczyść listę osób 		\n" +
			"0 - Zakończ program       		\n";


	private static final ConsoleUserDialog UI = new ConsoleUserDialog();


	public static void main(String[] args) throws PersonException {
		PersonConsoleApp application = new PersonConsoleApp();
		application.runMainLoop();
	}


	Collection<Person> people;
	Map<Integer, Person> peopleM;
	int x;

	public void runMainLoop() throws PersonException {

		switch (UI.enterInt(COLLECTIONS + "==>> ")) {
			case 1: {
				people = new HashSet();
				x=0;
			}
			break;

			case 2: {
				people = new TreeSet();
				x=0;
			}
			break;

			case 3: {
				people = new ArrayList();

				x=0;
			}
			break;

			case 4: {
				people = new LinkedList();
				x=0;
			}
			break;

			case 5: {
				peopleM = new HashMap();
				x=1;
			}
			break;

			case 6: {
				peopleM = new TreeMap();
				x=1;
			}
			break;

			case 0:
				UI.printInfoMessage("\nProgram zakończył działanie!");
				System.exit(0);
		}

		Person person1 = new Person("Marek", "Kowalski");
		Person person2 = new Person("Jan", "Nowak");
		Person person3 = new Person("Piotr", "Wiśniewski");
		Person person4 = new Person("Katarzyna", "Kowalczyk");
		Person person5 = new Person("Magda", "Wójcik");
		Person person6 = new Person("Szymon", "Lewandowski");

		if(x==0) {
			people.add(person1);
			people.add(person2);
			people.add(person3);
			people.add(person4);
			people.add(person5);
		}

		if(x==1) {
			peopleM.put(11, person1);
			peopleM.put(22, person2);
			peopleM.put(33, person3);
			peopleM.put(44, person4);
			peopleM.put(55, person5);

		}

		while (true) {
			UI.clearConsole();

			switch (UI.enterInt(MENU + "==>> ")) {
			case 1: {
				if(x==0) {
					people.add(createNewPerson());
				}

				if(x==1) {
					int k;
					k=UI.enterInt("Podj numer osoby: ");
					peopleM.put(k, createNewPerson());
				}
			}
				break;

			case 2: {
				if (x == 0) {
					printList();
				}
				if(x==1) {
					printMap();
				}
			}
			break;

			case 3:{
				if(x==0) {
					System.out.println("Dane osoby: Szymon Lewandowski\n");
					people.add(person6);
					System.out.println("Pierwsze dodanie:");
					printList();

					people.add(person6);
					System.out.println("\nDrugie dodanie:");
					printList();
				}

				if(x==1){
					System.out.println("Dane osoby: 66 Szymon Lewandowski\n");
					peopleM.put(66, person6);
					System.out.println("Pierwsze dodanie:");
					printMap();

					peopleM.put(66, person6);
					System.out.println("\nDrugie dodanie:");
					printMap();
				}
			}
			break;

			case 4:{
				if(x==0) {
					people.clear();
				}
				if(x==1) {
					peopleM.clear();
				}
			}
			break;
			case 0:
				UI.printInfoMessage("\nProgram zakończył działanie!");
				System.exit(0);
			}
		}
	}
	
	void printMap(){
		Set<Entry<Integer, Person>> entrySet = peopleM.entrySet();
		for(Entry<Integer, Person> entry: entrySet) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

	void printList(){
		for (Person item : people) {
			System.out.println(item);
		}
	}

	static Person createNewPerson(){
		String first_name = UI.enterString("Podaj imię: ");
		String last_name = UI.enterString("Podaj nazwisko: ");
		Person person;
		try {
			person = new Person(first_name, last_name);

		} catch (PersonException e) {
			UI.printErrorMessage(e.getMessage());
			return null;
		}
		return person;
	}



}
