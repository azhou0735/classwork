package recursion;

public class Pokemon {

	private int level;
	private int hp;
	private String name;
	private boolean poisoned;
	
	public Pokemon(String name, int lvl) {
		this.name = name;
		this.level = lvl;
		this.hp = 100;
		
	}
	
	public void iChooseYou() {
		System.out.println(name);
		System.out.println(name);
	}
	
	public int getHP() {
		return hp;
	}
	
	public String getName() {
		return name;
	}
	
	public void setHP(int newHP) {
		this.hp = newHP;
	}
	
	public void setPoisoned(boolean b) {
		this.poisoned = b;
	}
	
	public void lapse() {
		if(this.poisoned) {
			setHP(this.hp - 15);
		}
	}
	
	public void attack(Pokemon target, Attack attack){
		 if(Math.random() < .9){
			 attack.attack(target);
			 System.out.println("The attack hit");
		 }else{
			 System.out.println("The attack missed");
		 }
	}
	
	public void levelUp(Effect e) {
		this.level ++;
		e.happens(this);
	}
	
	public int getLevel() {
		return level;
	}
	
	public static void main(String[] args) {
		 Pokemon squirtle = new Pokemon("Squirtle",26);
		 Pokemon bulbasaur = new Pokemon("Bulbasaur",26);
		 squirtle.iChooseYou();
		 bulbasaur.iChooseYou();
		 System.out.println("Squirtle is preparing to attack with water blast");
		 squirtle.attack(bulbasaur, new Attack() {
		 
		 public void attack(Pokemon target) {
		 int hp = target.getHP();
		 target.setHP(hp/2);
		 }
		 });
		 System.out.println("Bulbasaur is preparing to attack with poison.");
		 bulbasaur.attack(squirtle, new Attack() {
		 
		 public void attack(Pokemon target) {
		 target.setPoisoned(true);
		 }
		 });
		 squirtle.lapse();
		 bulbasaur.lapse();
		 printScore(squirtle, bulbasaur);
		 
		 System.out.println("Bulbasaur is preparing to attack with tail-whip.");
		 squirtle.attack(bulbasaur, new Attack() {
			
			@Override
			public void attack(Pokemon target) {
				int oldHP = target.getHP();
				target.setHP(oldHP - 3);
				
			}
		}); 
		 squirtle.lapse();
		 bulbasaur.lapse();
		 printScore(squirtle, bulbasaur);
		 
		 bulbasaur.levelUp(new Effect() {
			
			@Override
			public void happens(Pokemon thisone) {
				System.out.println("Bulbasaur levels up to lvl" + thisone.getLevel());
				
			}
		});
	}

	private static void printScore(Pokemon squirtle, Pokemon bulbasaur) {
		System.out.println(squirtle.getName()+": "+squirtle.getHP()+"hp");
		System.out.println(bulbasaur.getName()+": "+bulbasaur.getHP()+"hp");
		
	}
}
