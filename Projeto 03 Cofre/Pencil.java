import java.util.Scanner;

class Graphite{
    private float caliber;
    private String hardness;
    private int size;

    Graphite(float caliber, String hardness, int size){
        this.caliber = caliber;
        this.hardness = hardness;
        this.size = size;
    }
    public int sheetWear(){
        if(this.hardness.equals("2B")) return 2;
        if(this.hardness.equals("4B")) return 4;
        if(this.hardness.equals("6B")) return 6;
        return 1;
    }
    public float getCaliber(){
        return this.caliber;
    }
    public int getSize(){
        return this.size;
    }
    public void setSize(int size){
        this.size = size;
    }
    public String toString(){
        return this.caliber + ":" + this.hardness + ":" + this.size;
    }
}
public class Pencil{
        float caliber;
        Graphite graphite;
    
        Pencil(float caliber){
            this.caliber = caliber;
        }
    
        boolean insert(Graphite gr){
            if(graphite != null){
                System.out.println("Já existe grafite!");
                return false;
            }
            if(gr.getCaliber() != this.caliber){
                System.out.println("Calibre incompatível");
                return false;
            }
            this.graphite = gr;
            System.out.println("Inserido com sucesso!");
            return true;
        }
        public Graphite remove(){
            if(this.graphite == null){
                System.out.println("Não tem grafite pra remover");
                return null;
            }
            Graphite temp = graphite;
            this.graphite = null;
            return temp;
        }
        public void write(int sheets){
            if(graphite == null){
                System.out.println("A lapiseira está sem grafite");
                return;
            }
            float spent = (float) graphite.getSize()/graphite.sheetWear();
            if(spent < sheets){
                System.out.println("Só foram escritas completamente " + (int)spent + " folhas");
                graphite = null;
                return;
            }
            if(spent > sheets){
                float aux = (spent - sheets)*graphite.sheetWear();
                graphite.setSize((int)aux);
                return;
            }
            System.out.println("Todas as folhas foram escritas, consumindo todo o grafite");
            graphite = null;
        }
        public String toString(){
            return "Calibre: " + this.caliber + " Grafite: " + ( (this.graphite == null) ? "null" : this.graphite.toString() );
        }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o calibre da lapiseira que você quer obter: ");
        Pencil pencilcase = new Pencil(input.nextFloat());
        System.out.println("Digite sair para sair, inserir para inserir grafite, remover para remover grafite, escrever para escrever e ver para ver lapiseira");
        while(true){
            String option = input.next().toLowerCase();
            if(option.equals("sair"))break;
            else if(option.equals("escrever")){
                System.out.println("Digite a quantidade de folhas que deseja rabiscar (ou escrever)");
                int sheets = input.nextInt();
                pencilcase.write(sheets);
            } else if(option.equals("inserir")){
                while(true){
                    float cl;
                    String hard;
                    int size;
                    System.out.println("Digite o calibre do grafite");
                    cl = input.nextFloat();
                    System.out.println("Digite o tamanho do grafite (Use números inteiros, por favor)");
                    size = input.nextInt();
                    while(true){
                        System.out.println("Qual a dureza do grafite? (HB, 2B, 4B ou 6B)");
                        hard = input.next().toUpperCase();
                        if((hard.equals("HB") || hard.equals("2B") || hard.equals("4B") || hard.equals("6B")) )break;
                    }
                    Graphite aux = new Graphite(cl,hard,size);
                    if(pencilcase.insert(aux))break;
                }
            } else if(option.equals("remover")){
                Graphite junk = pencilcase.remove(); // Esse retorno vai pro lixo, ora, num sei o que fazer... Garbage COLECTOOOORRR
            } else if(option.equals("ver")) System.out.println(pencilcase);
            else{
                System.out.println("Opção inválida, veja as opções no menu:");
                System.out.println("Digite sair para sair, inserir para inserir grafite, remover para remover grafite e ver para ver lapiseira");
            }
        }
    }
}