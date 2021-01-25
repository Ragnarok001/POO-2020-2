import java.util.*;

class Phone{
    private String id;
    private String number;

    // public Phone(string serial){

    // }
    public Phone(String id, String number){
        if(!validate(number)){
            System.out.println("Número inválido!");
            return;
        }
        this.id = id;
        this.number = number;
    }
    public String getId(){
        return this.id;
    }
    public String getNumber(){
        return this.number;
    }
    private boolean validate(String number){

        if(number.contains("(") || number.contains(")")){
            String prefix = number.substring(0,3);
            String prefixcompare = prefix.substring(1,2).replaceAll("\\d", "");
            if(prefix.charAt(0) != '(' || prefix.charAt(3) != ')') return false;
            if(prefixcompare.equals("")) return false;
        }
        String compare = number.replaceAll("\\d", "").replaceAll("[()]","");
        if(compare.equals("")) return true;
        return false;
    }
    public String toString(){
        return this.id + ": " + this.number;
    }
}

class Contact{
    private String name;
    private ArrayList<Phone> phones;

    public Contact(String name){
        this.name = name;
        phones = new ArrayList<>();
    }
    public String getName(){
        return this.name;
    }
    public int getPhonesSize(){
        return this.phones.size();
    }
    public boolean addPhone(String id, String number){
        this.phones.add(new Phone(id,number));
        return true;
    }
    public boolean rmPhone(int index){
        if(index < 0 || index >= this.phones.size()){
            System.out.println("Índice inválido");
            return false;
        }
        this.phones.remove(index);
        return true;
    }
    public String toString(){
        int i = 0;
        String response = this.name;
        for(Phone pn : this.phones){
            response += "[" + i++ + ":" + pn.getId() + ":" + pn.getNumber() + "]"; 
        }
        return response;
    }
}
class ContactCmp implements Comparator<Contact>{
    public int compare(Contact contact, Contact other){
        if(contact.getName().compareTo(other.getName()) < 0) return -1;
        if(contact.getName().compareTo(other.getName()) > 0) return 1;
        else return 0;
    }
}

class Schedule{
    private ArrayList<Contact> contacts;

    public Schedule(){
        this.contacts = new ArrayList<>();
    }
    public boolean addContact(String name, ArrayList<Phone> phones){
        if(name.equals("")){
            System.out.println("Digite um nome para o contato");
            return false;
        }
         for(int i = 0; i < this.contacts.size(); i++){
             if(this.contacts.get(i) != null && this.contacts.get(i).getName().equals(name)){
                 for(Phone fn : phones)
                    this.contacts.get(i).addPhone(fn.getId(), fn.getNumber());
                return true;
             }
         }
         this.contacts.add(new Contact(name));
         for(Phone fn : phones){
            this.contacts.get(this.contacts.size()-1).addPhone(fn.getId(), fn.getNumber());
         }
         return true;
    }
    public boolean rmPhone(String name, int index){
        for(int i = 0; i < this.contacts.size(); i++){
            if(this.contacts.get(i) != null && this.contacts.get(i).getName().equals(name)){
                if(index < 0 || index >= this.contacts.get(i).getPhonesSize()){
                    System.out.println("Índice inválido!");
                    return false;
                }
                this.contacts.get(i).rmPhone(index);
                return true;
            }
        }
        System.out.println("Não foi possível localizar o contato!");
        return false;
    }
    public boolean rmContact(String name){
        int i = 0;
        for(Contact contact : this.contacts){
            if(contact.getName().equals(name)){
                this.contacts.remove(i);
                System.out.println("Removido com sucesso!");
                return true;
            }
            i++;
        }
        return false;
    }
    public ArrayList<Contact> search(String pattern){
        ArrayList<Contact> response = new ArrayList<>();
        for(Contact ct : this.contacts){
            if(ct.toString().contains(pattern))
                response.add(ct);
        }
        return response;
    }
    public ArrayList<Contact> getContacts(){
        return this.contacts;
    }
    public String toString(){
        Collections.sort(this.contacts, new ContactCmp());
        String response = "";
        for(Contact contact : this.contacts){
            response += contact + "\n";
        }
        return response;
    }
    public static void main(String args[]){
        
    }
}