public class Car{
    private int tank;
    private int passengers;
    private int kilometers;

    private Car(){
        this.tank = 0;
        this.passengers = 0;
        this.kilometers = 0;
    }

    public boolean in(){
        if(this.passengers < 2){
            this.passengers += 1;
            return true;
        }
        System.out.println("People limit reached!");
        return false;
    }
    public boolean out(){
        if(this.passengers > 0){
            this.passengers -= 1;
            return true;
        }
        System.out.println("No one is on the car");
        return false;
    }
    public void fuel(int quantity){
        if(quantity >= 100) tank = 100;
        else tank = quantity;
    }
    public boolean drive(int distance){
        if(this.passengers == 0){
            System.out.println("No one is on the car");
            return false;
        }
        if(this.tank == 0){
            System.out.println("The tank is empty!");
            return false;
        }
        if(this.tank >= distance){
            this.tank -= distance;
            this.kilometers += distance;
            return true;
        }
        System.out.println("Tank empty after " + this.tank + " kilometers");
        this.kilometers += tank;
        this.tank = 0;
        return false;
    }
    public String toString(){
        return "Passengers: " + this.passengers + "\nFuel: " + this.tank +
        "\nKilometers: " + this.kilometers;
    }

    public static void main(String[] args){
        Car car = new Car();
        System.out.println(car);
        //pass: 0, gas: 0, km: 0
        car.in();
        car.in();
        System.out.println(car);
        //pass: 2, gas: 0, km: 0
        car.in();
        //fail: limite de pessoas atingido
        System.out.println(car);
        //pass: 2, gas: 0, km: 0
        car.out();
        car.out();
        car.out();
        //fail: nao ha ninguem no carro
        System.out.println(car);
        //pass: 0, gas: 0, km: 0

        car = new Car();
        car.fuel(60);
        System.out.println(car);
        //pass: 0, gas: 60, km: 0

        car.drive(10);
        //fail: nao ha ninguem no carro

        car.in();
        car.drive(10);
        System.out.println(car);
        //pass: 1, gas: 50, km: 10

        car.drive(70);
        //fail: tanque vazio apos andar 50 km
        car.drive(10);
        //fail: tanque vazio
        System.out.println(car);
        //pass: 1, gas: 0, km: 60

        car.fuel(200);
        System.out.println(car);
        //pass: 1, gas: 100, km: 60
    }
}