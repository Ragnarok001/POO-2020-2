import java.util.Scanner;
public class Tamagotchi{
    int energy, energyMax;
    int hunger, hungerMax;
    int clean, cleanMax;
    int diamonds;
    int age;
    boolean alive;

    Tamagotchi(int energyMax, int hungerMax, int cleanMax){
        this.energy = energyMax;
        this.hunger = hungerMax;
        this.clean = cleanMax;
        this.energyMax = energyMax;
        this.hungerMax = hungerMax;
        this.cleanMax = cleanMax;
        this.alive = true;
    }

    private void setEnergy(int value){
        this.energy = value;
    }
    private void sethunger(int value){
        this.hunger = value;
    }
    private void setClean(int value){
        this.clean = value;
    }
    public int getEnergy(){
        return this.energy;
    }
    public int getEnergyMax(){
        return this.energyMax;
    }
    public int getClean(){
        return this.clean;
    }
    public int getCleanMax(){
        return this.cleanMax;
    }
    public int gethunger(){
        return this.hunger;
    }
    public int gethungerMax(){
        return this.hungerMax;
    }
    public int getDiamonds(){
        return this.diamonds;
    }
    public int getAge(){
        return this.age;
    }
    public boolean isAlive(){
        if(this.alive){
            if(energy <= 0){
                this.alive = false;
                System.out.println("Pet morreu de cansaço");
            }
            if(clean <= 0){
                this.alive = false;
                System.out.println("Pet morreu de sujeira");
            }
            if(hunger <= 0){
                this.alive = false;
                System.out.println("Pet morreu de fome");
            }
        }
        return this.alive;
    }

    public String toString(){
        return "E:" + this.energy + "/" + this.energyMax + " S:" + this.hunger + "/" + this.hungerMax + 
        " L:" + this.clean + "/" + this.cleanMax + " D:" + this.diamonds + " I:" + this.age;
    }

    public void play(){
        if(!this.isAlive()){
            System.out.println("Seu pet está morto!");
            return;
        }
        this.energy-=2;
        this.hunger-=1;
        this.clean-=3;
        this.diamonds+=1;
        this.age+=1;
        this.isAlive();
        this.broker();
    }
    public void shower(){
        if(!this.isAlive()){
            System.out.println("Seu pet está morto!");
            return;
        }
        this.energy-=3;
        this.hunger-=1;
        this.clean = this.cleanMax;
        this.age+=2;
        this.isAlive();
        this.broker();
    }
    public void eat(){
        if(!this.isAlive()){
            System.out.println("Seu pet está morto!");
            return;
        }
        this.energy-=1;
        this.hunger+=4;
        this.clean-=2;
        this.age+=1;
        this.isAlive();
        this.broker();
    }
    public void sleep(){
        if( (this.energyMax - this.energy) < 5){
            System.out.println("O pet não está com sono");
        }
        this.age += this.energyMax - this.energy;
        this.energy = this.energyMax;
    }
    public void show(){
        System.out.println(this);
    }

    private void broker(){
        if(this.hunger > this.hungerMax) this.hunger = this.hungerMax;
        if(this.energy < 0) this.energy = 0;
        if(this.clean < 0) this.clean = 0;
        if(this.hunger < 0) this.hunger = 0;
    }
}

class Game{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Tamagotchi tamas = null;
        while(true){
            System.out.println("Você deseja *criar* um pet ou *sair*?");
            String test = scan.nextLine().toLowerCase();
            if(test.equals("sair")) break;
            else if(test.equals("criar")){
                System.out.println("Olá, crie um pet para começar a jogar. Diga seus atributos(números inteiros):");
                System.out.print("Energia: ");
                int energy = scan.nextInt();
                System.out.print("Fome: ");
                int hunger = scan.nextInt();
                System.out.print("Limpeza: ");
                int clean = scan.nextInt();
                scan.nextLine();
                tamas = new Tamagotchi(energy,hunger,clean);
                while(tamas.isAlive()){
                    System.out.println("O que deseja fazer? (*Brincar*, dar *banho*, *alimentar*, colocar pra *dormir*, *ver* seu pet ou *voltar*)");
                    String option = scan.nextLine().toLowerCase();
                    if(option.equals("brincar")) tamas.play();
                    else if(option.equals("banho")) tamas.shower();
                    else if(option.equals("alimentar")) tamas.eat();
                    else if(option.equals("dormir")) tamas.sleep();
                    else if(option.equals("ver")) tamas.show();
                    else if(option.equals("voltar")) break;
                    else System.out.println("Huh??");
                }
                while(true){
                    System.out.println("Deseja *ver* o seu pet, *sair* do jogo ou voltar ao *menu*?");
                    String option = scan.nextLine().toLowerCase();
                    if(option.equals("sair")) return;
                    else if(option.equals("ver")) tamas.show();
                    else if(option.equals("menu")) break;
                }
            }
            else System.out.println("??");
        }
        scan.close();
    }
} 