package study0920.interface_abstract;

	abstract class Animal{
		private String name;
		
		public Animal(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
		public abstract void move();
	}
	
	interface Soundable{
		void makeSound();
	}
	
	class Dog extends Animal implements Soundable{
		
		public Dog(String name) {
			super(name);
		}
		public void move() {
			System.out.println(getName() + " 가 네발로 걷습니다.");
		}
		public void makeSound() {
			System.out.println(getName() + " 멍멍 ");
		}
	}
	
	class Cat extends Animal implements Soundable{
		public Cat(String name) {
			super(name);
		}
		public void move() {
			System.out.println(getName() + " 가 살금살금 걷습니다.");
		}
		public void makeSound() {
			System.out.println(getName() + " 야옹!");
		}
	}
	
	
	
public class Animal_sound_app {
	public static void main(String[] args) {
		Dog dog = new Dog("댕댕이");
		Cat cat = new Cat("야용이");
		
		dog.move();
		dog.makeSound();
		cat.move();
		cat.makeSound();
	}
}
