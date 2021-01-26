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
        number = number.replaceAll(" ", "");
        if(number.contains("(") || number.contains(")")){
            String prefix = number.replaceFirst("\\(", "").replaceFirst("\\)", "");
            prefix = prefix.replaceAll("\\d", "");
            if(!prefix.equals("")) return false;
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
                    if(fn.getId() != null && fn.getNumber() != null)
                        this.contacts.get(i).addPhone(fn.getId(), fn.getNumber());
                return true;
             }
         }
         this.contacts.add(new Contact(name));
         for(Phone fn : phones)
            if(fn.getId() != null && fn.getNumber() != null)
                this.contacts.get(this.contacts.size()-1).addPhone(fn.getId(), fn.getNumber());
         return true;
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
    public ArrayList<Contact> search(String pattern){
        ArrayList<Contact> response = new ArrayList<>();
        for(Contact ct : this.contacts){
            if(ct.toString().contains(pattern))
                response.add(ct);
        }
        return (response.isEmpty() ? null : response);
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
        Schedule schedule;
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Você deseja *criar* uma agenda ou *sair*?");
            String test = scan.nextLine().toLowerCase();
            if(test.equals("sair")) return;
            else if(test.equals("criar")){
                schedule = new Schedule();
                while(true){
                    System.out.println("O que deseja fazer? (*Adicionar* contato, *remover* contato,"
                    + "remover *fone*, *ver* contatos, *buscar* um contato por número ou nome ou *voltar*)");
                    String option = scan.nextLine().toLowerCase();
                    if(option.equals("voltar")) break;
                    else if(option.equals("ver")) System.out.print(schedule);
                    else if(option.equals("adicionar")){
                        System.out.print("Digite o nome do contato: (Digite o nome de um contato já existente "
                        + "para adicionar outro fone ao mesmo) ");
                        String name = scan.nextLine();
                        System.out.print("Digite a quantidade de números que deseja vincular a esse contato: ");
                        int quantity = scan.nextInt();
                        scan.nextLine();
                        ArrayList<Phone> phones = new ArrayList<>();
                        while(quantity-- > 0){
                            System.out.print("Digite um identificador para o número: ");
                            String label = scan.nextLine();
                            System.out.print("Digite o número do telefone: ");
                            String phone = scan.nextLine();
                            phones.add(new Phone(label,phone));
                        }
                        schedule.addContact(name, phones);
                    } else if(option.equals("remover")){
                        System.out.print("Digite o nome do contato que deseja remover: ");
                        String name = scan.nextLine();
                        schedule.rmContact(name);
                    } else if(option.equals("fone")){
                        System.out.print("Digite o nome do contato que deseja remover algum número: ");
                        String name = scan.nextLine();
                        System.out.print("Digite a quantidade de números que deseja remover: ");
                        int quantity = scan.nextInt();
                        scan.nextLine();
                        while(quantity-- > 0){
                            System.out.print("Digite o índice do número que deseja remover: ");
                            int index = scan.nextInt();
                            schedule.rmPhone(name, index);
                        }
                        scan.nextLine();
                    }else if(option.equals("buscar")){
                        System.out.print("Digite um nome ou número de um contato para buscar: ");
                        String pattern = scan.nextLine();
                        if(pattern.toLowerCase().equals("voltar")) break;
                        ArrayList<Contact> tmp = schedule.search(pattern);
                        while(true){
                            if(tmp == null){
                                System.out.print("Contato não encontrado! Digite novamente ou digite *voltar* para retornar ao menu: ");
                                pattern = scan.nextLine();
                                if(pattern.toLowerCase().equals("voltar")) break;
                            } else break;
                        }
                        if(tmp != null)
                            for(Contact ct : tmp)
                                System.out.println(ct);
                    } else{
                        System.out.println("Huh? Digite alguma das opções entre asterisco!");
                    }
                }
            } else System.out.println("Huh? Digite alguma das opções entre asterisco!");
        }
    }
}