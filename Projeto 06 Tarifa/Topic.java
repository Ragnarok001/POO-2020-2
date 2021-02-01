import java.util.ArrayList;
import java.util.Scanner;

class Passenger{
    String name;
    int age;

    public Passenger(String name, int age){
        this.name = name;
        this.age = age;
    }

    public int getAge(){
        return this.age;
    }

    public String getName(){
        return this.name;
    }

    public boolean compare(Passenger other){
        if(this.name == other.getName() && this.age == other.getAge()) return true;
        return false;
    }

    public String toString(){
        return this.name + ":" + this.age + " ";
    }
}

public class Topic{
    private ArrayList<Passenger> chairs;
    private int pref_quantity;

    public boolean in(Passenger foreigner){
        for(Passenger passenger : chairs){
            if(passenger != null && foreigner.compare(passenger)){
                System.out.println("Passageiro já está na topic!");
                return false;
            }
        }
        if(foreigner.getAge() >= 65){
            for(int i = 0; i < this.pref_quantity; i++){
                if(this.chairs.get(i) == null){
                    this.chairs.set(i, foreigner);
                    return true;
                }
            }
        }
        for(int i = this.pref_quantity; i < this.chairs.size(); i++){
            if(this.chairs.get(i) == null){
                this.chairs.set(i, foreigner);
                return true;
            }
        }
        for(int i = 0; i < this.pref_quantity; i++){
            if(this.chairs.get(i) == null){
                this.chairs.set(i, foreigner);
                return true;
            }
        }
        System.out.println("Ônibus lotado!");
        return false;
    }

    public boolean out(String name){
        int i = 0;
        for(Passenger passengers : this.chairs){
            if(passengers != null && passengers.name.equals(name)){
                this.chairs.set(i,null);
                return true;
            }
            i++;
        }
        System.out.println("Passageiro não encontrado!");
        return false;
    }
    
    public Topic(int chairs, int pref){
        this.pref_quantity = pref;
        this.chairs = new ArrayList<>(chairs);
        for(int i = 0; i < chairs; i++) this.chairs.add(null); 
    }

    public String toString(){
        String str = "";
        int i = 0;
        for(Passenger passenger : this.chairs){
            str += (i < this.pref_quantity ? "@" : "=") + (passenger == null ? " " : passenger);
            i++; 
        }
        return str;
    }

    public static void main(String args[]){
        Topic cooperativa;
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Você deseja *criar* uma topic ou *sair*?");
            String test = scan.nextLine().toLowerCase();
            if(test.equals("sair")) break;
            else if(test.equals("criar")){
                System.out.print("Digite a quantidade de cadeiras: ");
                int chairs = scan.nextInt();
                System.out.print("Digite quantas delas são preferenciais: ");
                int pref = scan.nextInt();
                cooperativa = new Topic(chairs, pref);
                scan.nextLine();
                while(true){
                    System.out.println("O que deseja fazer? (*embarcar* passageiro, *desembarcar* passageiro, *ver* o ônibus ou *voltar*)");
                    String option = scan.nextLine().toLowerCase();
                    if(option.equals("voltar")) break;
                    else if(option.equals("ver")) System.out.println(cooperativa);
                    else if(option.equals("embarcar")){
                        System.out.print("Digite o nome do passageiro: ");
                        String name = scan.nextLine();
                        System.out.print("Digite a idade do passageiro: ");
                        int age = scan.nextInt();
                        if(cooperativa.in(new Passenger(name,age))){
                            System.out.println("Passageiro embarcou!");
                            scan.nextLine();
                        }
                    } else if(option.equals("desembarcar")){
                        System.out.print("Digite o nome do passageiro: ");
                        String name = scan.nextLine();
                        if(cooperativa.out(name)){
                            System.out.println("Desembarcado com sucesso!");
                        }
                    }
                }
            }
        }
        scan.close();
    }
}