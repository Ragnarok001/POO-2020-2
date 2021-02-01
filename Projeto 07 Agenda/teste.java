package agenda;
import java.util.Scanner;
import java.util.ArrayList;

class Fone{
    String label;
    String number;

    public Fone(String serial){
        String array[]= new String[2];
        array=serial.split(":");
        label=array[0];
        number=array[1];
    }
    
    static boolean validate(String number){   
        return true;
    }
    
    Fone(int id, String label, String number){
        this.label=label;
        this.number=number;        
    }
    
    String setLabel(String label){
        this.label=label;
        return label;
    }
    
    String setNumber(String number){    
        this.label=number;
        return number;
    }
    
    public String toString(){
        return label + "  " + number;
    }
}

class Contato{
    private String name;
    private ArrayList<Fone>fones;
    int nextId=0;
    
    public String getName(){
        return name;
    }
    
    public Contato(String name){
        this.name=name;
        fones=new ArrayList<>();
    }
    
    void addFone(String label, String number){
        fones.add(new Fone(nextId, label, number));
        nextId=nextId+1;
    }
    
    void rmFone(int index){
        fones.remove(index);
    }
    
    public String toString(){
        return fones + " " + name;
    }   
}

public class Agenda {
    private ArrayList<Contato>contatos;
    public Agenda(){
        contatos = new ArrayList<>();
    }
    
    private int procurarContato(String name){
        for(int i=0; i<contatos.size(); i++){
        Contato contato = contatos.get(i);
                if(contato != null && contato.getName().equals(name)){
                   return i;         
                }
        }
        return -1;
    }
    
    void addContato(String name, Fone fone){ //PEDRO, OI 8888
        Contato contato=this.getContato(name); //tá retornando null
            if(contato==null){
                contato = new Contato(name);
                contato.addFone(fone);
            }
        contato.addFone(fone.label, fone.number);   
    }
    
    boolean rmContato(String name){
        Contato contato=this.getContato(name);
        int aux=procurarContato(name);
            if(aux!=-1){            
                this.contatos.remove(aux);
                contato.rmFone(aux);
                return true;
            }
        System.out.println("Esse contato não existe");
        return false;
    }
    
     ArrayList <Contato> getContatos(){
        for(int i=0; i<contatos.size(); i++){
            System.out.println(contatos.get(i));
        }
        return contatos;
     }
    
    Contato getContato(String name){
        Contato contato;
            for(int i=0; i<contatos.size(); i++){
                contato = contatos.get(i);
                    if(contatos != null && contato.getName().equals(name)){
                        return contato;         
                    }
                }
            return null;
    }
    
    
    public static void main(String[] args) {
        Agenda telefone = new Agenda();
       //PEDRO OI 999
       telefone.addContato("Pedro", (new Fone("oi:888")));
       

    }
    
}
