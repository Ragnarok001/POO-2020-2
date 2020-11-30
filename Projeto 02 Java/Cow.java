public class Cow{
    private int milk;
    private int milk_capacity;
    private int hunger;
    private boolean rested;

    public static void main(String[] args){
        Cow mycow = new Cow(1000);
        System.out.println(mycow);
        mycow.milkCow();
        System.out.println(mycow.rest());
        mycow.feed(70);
        System.out.println(mycow.moo());
        System.out.println(mycow.rest());
        System.out.println(mycow);
        System.out.println(mycow.moo());
    }

    public Cow(int milk_capacity){
        this.rested = true;
        this.hunger = 100;
        this.milk = 100;
        this.milk_capacity = milk_capacity;
    }

    public String moo(){
        if(hunger <= 30) return "Moooo!";
        if(hunger <= 70) return "Moo!";
        return "Mu";
    }

    public int milkCow(){
        if(this.rested){
            int bucket = (this.milk*milk_capacity)/100;
            this.rested = false;
            this.milk = 0;
            return bucket;
        }
        return 0;
    }
    public String rest(){
        if(hunger < 70){
            int milk_percent = 100-this.hunger;
            this.rested = true;
            this.milk = ( milk_percent == 0 ? 100 : milk_percent);
            this.hunger = 100;
            return "Slept like an angel cow";
        }
        return "It's hungry";
    }
    public void feed(int food_quantity){
        if(food_quantity >= this.hunger){
            this.hunger = 0;
        }else{
            this.hunger -= food_quantity;
        }
    }
    public String toString(){
        return "Milk: " + this.milk + "%\nMilk capacity: " + this.milk_capacity +
        "\nHunger: " + this.hunger + "\n" + (this.rested == true ? "Rested" : "Tired");
    }
}