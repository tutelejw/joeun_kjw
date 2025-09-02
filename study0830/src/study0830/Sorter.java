package study0830;

import java.util.Arrays;

interface Sortable {
	int compareTo(Sortable other);
}

class Person implements Sortable{
	private String name;
	private int age;
	
	//constructor
	public Person (String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public int compareTo(Sortable other) {
		if (other instanceof Person) {
			Person otherPerson = (Person) other;
			return this.age - otherPerson.age;
		}
		return 0;
	}
	public String toString() {
		return name + " / "+ age;
	}
}



public class Sorter {
	public static void main(String[] args) {
		Person[] people = { new Person("철수", 30), new Person("영희", 25), new Person("민수", 35)};
		System.out.println(people[0]);
//		String people1[][] = { {"철수", "30"}, {"영희", "25"}, {"민수", "35"}};
//		System.out.println(people1[1][1]);
		
	
		System.out.println("정렬전" + Arrays.toString(people));
		

		
	}// end of main
	public static void sort(Sortable[] array) {
		for (int i = 0; i< array.length - 1 ; i++) {
			for (int j = 0; j < array.length -1 - i; j++) {
				if (array[j].compareTo(array[j+1]) > 0) {
					Sortable temp = array[j];
					array[j] = array[j+1];
					array[j+1]=temp;
				}
				}
			}
		}
	
	
} // end of class
