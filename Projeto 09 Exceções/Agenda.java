import java.util.*;


class Phone{
    private String id;
    private String number;

    public Phone(String serial){
        String splitted[] = serial.split(":");
        try{
            if(!validate(splitted[1])){
                throw new RuntimeException("Número inválido!");
            }
            this.id = splitted[0];
            this.number = splitted[1];
        }catch(ArrayIndexOutOfBoundsException ex){
            throw new RuntimeException("Serial inválida!");
        }
    }
    public Phone(String id, String number){
        if(!validate(number)){
            throw new RuntimeException("Número inválido!");
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
    // private boolean favorite;

    public Contact(String name){
        this.name = name;
        phones = new ArrayList<>();
    }
    // public void bookmark(){
    //     this.favorite = true;
    // }
    public String getName(){
        return this.name;
    }
    public int getPhonesSize(){
        return this.phones.size();
    }
    // public boolean isFavorite(){
    //     return this.favorite;
    // }
    public boolean addPhone(String id, String number){
        try{
            this.phones.add(new Phone(id,number));
        }catch(RuntimeException ex){
            System.out.println(ex.getMessage());
        }
        return true;
    }
    public boolean addPhone(String serial){
        try{
            this.phones.add(new Phone(serial));
        }catch(RuntimeException ex){
            System.out.println(ex);
            this.phones.remove(this.phones.size()-1);
        }
        return true;
    }
    public boolean rmPhone(int index){
        try{
            this.phones.remove(index);
        }catch(IndexOutOfBoundsException ex){
            return false;
        }
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
    private TreeMap<String, Contact> contacts;
    private TreeMap<String, Contact> bookmarks;

    public Schedule(){
        this.contacts = new TreeMap<>();
        this.bookmarks = new TreeMap<>();
    }
    public boolean bookmarkContact(String name){
        if(this.contacts.containsKey(name)){
            if(!this.bookmarks.containsKey(name)){
                bookmarks.put(name, this.contacts.get(name));
                return true;
            }
            System.out.println("Contato já é um favorito!");
        }
        System.out.println("Contato não foi encontrado!");
        return false;
    }
    public boolean unBookmarkContact(String name){
        if(this.bookmarks.containsKey(name)){
            this.bookmarks.remove(name);
            System.out.println("Removido com sucesso!");
            return true;
        }
        System.out.println("Não existe um contato favorito com esse nome!");
        return false;
    }
    public ArrayList<Contact> getBookmarkedContacts(){
        ArrayList bcontacts = new ArrayList(this.bookmarks.values());
        return bcontacts;
    }
    public boolean addContact(String name, ArrayList<Phone> phones){
        if(name.equals("")){
            System.out.println("Digite um nome para o contato");
            return false;
        }
        if(this.contacts.containsKey(name)){
            for(Phone fn : phones){
                this.contacts.get(name).addPhone(fn.getId(), fn.getNumber());
            }
            return true;
        }
         this.contacts.put(name, new Contact(name));
         for(Phone fn : phones)
            if(fn.getId() != null && fn.getNumber() != null)
                this.contacts.get(name).addPhone(fn.getId(), fn.getNumber());
         return true;
    }
    public boolean rmContact(String name){
        if(this.contacts.containsKey(name)){
            this.contacts.remove(name);
            System.out.println("Removido com sucesso!");
            return true;
        }
        return false;
    }
    public boolean rmPhone(String name, int index){
        if(this.contacts.containsKey(name)){
            if(!this.contacts.get(name).rmPhone(index)){
                System.out.println("Índice inválido!");
                return false;
            }
            return true;
        }
        System.out.println("Não foi possível localizar o contato!");
        return false;
    }
    public ArrayList<Contact> search(String pattern){
        ArrayList<Contact> response = new ArrayList<>();
        for(Contact ct : this.contacts.values()){
            if(ct.toString().contains(pattern))
                response.add(ct);
        }
        return (response.isEmpty() ? null : response);
    }
    public TreeMap<String,Contact> getContacts(){
        return this.contacts.values();
    }
    public String toString(){
        String response = "";
        for(Contact contact : this.bookmarks.values()){
            response += "@ " + contact + "\n";
        }
        for(Contact contact : this.contacts.values()){
            if(!this.bookmarks.containsKey(contact.getName()))
                response += "- " + contact + "\n";
        }
        return response;
    }
    public static void main(String args[]){
        Schedule schedule;
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Você deseja *criar* uma agenda ou *sair*?");
            String test = scan.nextLine().toLowerCase();
            if(test.equals("sair")) break;
            else if(test.equals("criar")){
                schedule = new Schedule();
                while(true){
                    System.out.println("O que deseja fazer?\n(*Adicionar* contato, *remover* contato,"
                    + "remover *fone*, *ver* contatos, *buscar* um contato por número ou nome, *favoritar*"
                    + " um contato, *desfavoritar* um contato, ver *favoritos* ou *voltar*)");
                    String option = scan.nextLine().toLowerCase();
                    if(option.equals("voltar")) break;
                    else if(option.equals("ver")) System.out.print(schedule);
                    else if(option.equals("favoritos")){
                        for(Contact contact : schedule.getBookmarkedContacts())
                            System.out.println(contact);
                    }
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
                                tmp = schedule.search(pattern);
                            } else break;
                        }
                        if(tmp != null)
                            for(Contact ct : tmp)
                                System.out.println(ct);
                    } else if(option.equals("favoritar")){
                        System.out.print("Digite o nome do contato que deseja favoritar: ");
                        String name = scan.nextLine();
                        schedule.bookmarkContact(name);
                    } else if(option.equals("desfavoritar")){
                        System.out.print("Digite o nome do favorito que deseja desfavoritar: ");
                        String name = scan.nextLine();
                        schedule.unBookmarkContact(name);
                    } else{
                        System.out.println("Huh? Digite alguma das opções entre asterisco!");
                    }
                }
            } else System.out.println("Huh? Digite alguma das opções entre asterisco!");
        }
        scan.close();
    }
}