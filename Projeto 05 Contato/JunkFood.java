import java.util.ArrayList;
import java.util.Scanner;

class Spiral{
    private String name;
    private int quantity;
    private float price;

    public Spiral(String name,int quantity,float price){
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName(){
        return this.name;
    }

    public float getPrice(){
        return this.price;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public void decreaseQuantity(){
        this.quantity -=1;
    }

    public String toString(){
        return (this.name == "" ? "empty" : this.name)  + " : " + this.quantity + "U " + "R$ " + this.price;
    }
}

class Machine{
    private ArrayList<Spiral> spirals;
    private float balance;
    private float profit;
    private int maxproducts;
    //private spiral : Spiral[];

    public Machine(int spiralquantity, int maxproducts){
        this.maxproducts = maxproducts;
        spirals = new ArrayList<>();
        for(int i = 0; i < spiralquantity; i++){
            this.spirals.add(new Spiral("",0,0f));
        }
    }

    public String toString(){
        String str = "Saldo: " + this.balance + "\n";
        int i = 0;
        for(Spiral spiral : this.spirals){
            str += i++ + " " + spiral + "\n"; 
        }
        return str;
    }

    public boolean insertMoney(float value){
        this.balance += value;
        return true;
    }

    public float askChange(){
        float temp = this.balance;
        this.balance = 0;
        System.out.println("Você recebeu " + temp);
        return temp;
    }

    public boolean sell(int index){
        if(index < 0 || index >= this.spirals.size()){
            System.out.println("Índice inválido");
            return false;
        }
        if(this.balance < this.spirals.get(index).getPrice()){
            System.out.println("Por favor, insira mais dinheiro!");
            return false;
        }
        if(this.spirals.get(index).getQuantity() <= 0){
            System.out.println("Sem estoque!");
            return false;
        }
        Spiral temp = this.spirals.get(index);
        this.balance -= temp.getPrice();
        this.profit += temp.getPrice();
        temp.decreaseQuantity();
        this.spirals.set(index, temp);
        System.out.println("Você comprou " + this.spirals.get(index).getName());
        return true;
    }

    public boolean changeSpiral(int index, String name, int quantity, float price){
        if(index < 0 || index >= this.spirals.size()){
            System.out.println("Índice inválido");
            return false;
        }
        if(quantity > this.maxproducts){
            System.out.println("A quantidade de produtos é maior do que o limite!");
            return false;
        }
        this.spirals.set(index,new Spiral(name,quantity,price));
        return true;
    }
    
    public boolean cleanSpiral(int index){
        if(index < 0 || index >= this.spirals.size()){
            return false;
        }
        this.spirals.set(index, new Spiral("",0,0f));
        return true;
    }

    public float getBalance(){
        return this.balance;
    }

}

class JunkFood{
    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);
        Machine machine = null;
        while(true){
            System.out.println("Você deseja *criar* uma máquina ou *sair*?");
            String test = scan.nextLine().toLowerCase();
            if(test.equals("sair")) break;
            else if(test.equals("criar")){
                System.out.print("Digite a quantidade de espirais: ");
                int qtdEsp = scan.nextInt();
                System.out.print("Digite a quantidade máxima de produtos em cada um deles: ");
                int qtdProd = scan.nextInt();
                machine = new Machine(qtdProd,qtdEsp);
                scan.nextLine();
                while(true){
                    System.out.println("O que deseja fazer? (*Inserir* dinheiro, *comprar* algo, *ver* seu saldo e os produtos da máquina," 
                    + "pedir seu *troco*, *alterar* um espiral, *limpar* um espiral ou *voltar*)");
                    String option = scan.nextLine().toLowerCase();
                    if(option.equals("inserir")){
                        System.out.print("Digite a quantidade de dinheiro que quer inserir: ");
                        machine.insertMoney(scan.nextFloat());
                        scan.nextLine();
                    }
                    else if(option.equals("comprar")){
                        System.out.print("Digite o índice do espiral que tem o produto que quer comprar: ");
                        machine.sell(scan.nextInt());
                        scan.nextLine();
                    }
                    else if(option.equals("ver")) System.out.println(machine);
                    else if(option.equals("troco")) machine.askChange();
                    else if(option.equals("alterar")){
                        while(true){
                            System.out.print("Digite o índice do espiral que deseja alterar: ");
                            int index = scan.nextInt();
                            System.out.println("Digite o nome do produto: ");
                            scan.nextLine();
                            String name = scan.nextLine();
                            System.out.println("Digite a quantidade do produto: ");
                            int qtd = scan.nextInt();
                            System.out.println("Digite o preço do produto: ");
                            float price = scan.nextFloat();
                            scan.nextLine();
                            if(machine.changeSpiral(index, name, qtd, price)) break;
                        }
                        System.out.println("Alterado com sucesso!");
                    }
                    else if(option.equals("limpar")){
                        while(true){
                            System.out.print("Digite o índice do espiral que deseja limpar ou -1 para sair: ");
                            int index = scan.nextInt();
                            scan.nextLine();
                            if(index == -1) break;
                            machine.cleanSpiral(index);
                        }
                    }
                    else if(option.equals("voltar")) break;
                    else System.out.println("Huh??");
                }
            }
            else System.out.println("??");
        }
        scan.close();

    }
}