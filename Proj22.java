import java.lang.Math;


class Proj22 {
    public static void main(String[] args)
    {
        Gamestore gStop = new Gamestore();
        Employee cashier = gStop.Employee;
        cashier.arrive();
        cashier.count();
        cashier.vacuum();
        cashier.stack();
        cashier.open();
        cashier.order();
        cashier.close();
    }
}


class Gamestore {
    // declare fields
    public int inventory;
    public int soldGames = 0;
    public double cash = 0;
    public List<Game> shelf;
    public List<Game> newGames;
    public List<Game> damagedGames;
    public Employee cashier;
 
    Gamestore() {
        Random r = new Random();
        shelf = new ArrayList<>();
        newGames = new ArrayList<>();
        damagedGames = new ArrayList<>();

        if(r.nextDouble() > 0.5){
            cashier = new Cashier("Burt", 10, this);
        }else{
            cashier = new Cashier("Ernie", 5, this);
        }
        for(int i = 0; i < 3; i++){
            shelf.add(new Monopoly());
            shelf.add(new Clue());
            shelf.add(new Life());

            shelf.add(new Mousetrap());
            shelf.add(new Candyland());
            shelf.add(new ConnectFour());
            
            shelf.add(new Magic());
            shelf.add(new Pokemon());
            shelf.add(new Netrunner());
            
            shelf.add(new Catan());
            shelf.add(new Risk());
            shelf.add(new Gloomhaven());
        }

        inventory = shelf.size();
    }

}

interface Employee {
    String name = " ";
    Gamestore store;

    void arrive();
    void count();
    void vacuum();
    void stack();
    void open();
    void order();
    void close();
}
 
class Cashier implements Employee {
    String name = " ";
    int damage;
    Gamestore store;
 
    // constructor
    Cashier(String name, int damage, Gamestore store)
    {
        this.name = name;
        this.damage = damage;
        this.store = store;
    }
 
    @Override public void arrive(int day)
    {
        System.out.println(name + " the Cashier has arrived at the store on Day " + String.valueOf(day));
        System.out.println(String.valueOf(store.newGames.size()) + " game(s) has arrived at the store on Day " + String.valueOf(day));
        store.shelf.add(store.newGames);
        store.newGames = NULL;
        store.inventory = store.shelf.size();
    }
 
    @Override public void count()
    {
        if(store.cash < 100){
            System.out.println("The cash count is " + String.valueOf(store.cash + 1000));
            return (store.cash + 1000);
        }
        System.out.println("The cash count is " + String.valueOf(store.cash));
    }
 
    @Override public void vacuum()
    {
        System.out.println(name + " is vacuuming the store");
        Random r = new Random();
        int randomInt = r.nextInt(100) + 1
        if(randomInt <= damage){
            Game randGame = store.shelf.remove(r.nextInt(inventory.size()));
            store.damagedGames.add(randGame);
            store.inventory = store.shelf.size();
            System.out.println(randGame.Title + " has been damaged");
        }
    }
 
    @Override public void stack()
    {
        if(name.equals("Ernie")){
            List<Game> newOrder = new ArrayList<>();
            while(store.shelf.size() > 0){
                int index = 0;
                for(int i = 0; i < store.shelf.size(); i ++){
                    if(store.shelf.get(i).size.height < store.shelf.get(index).size.height){
                        index = i;
                    }
                };
                newOrder.add(store.shelf.remove(index));
            }
            store.shelf = newOrder;
        }else{
            List<Game> newOrder = new ArrayList<>();
            while(store.shelf.size() > 0){
                int index = 0;
                for(int i = 0; i < store.shelf.size(); i ++){
                    if(store.shelf.get(i).size.width > store.shelf.get(index).size.width){
                        index = i;
                    }
                };
                newOrder.add(store.shelf.remove(index));
            }
            store.shelf = newOrder;
        }
    }
 
    @Override public void open()
    {
        Random r = new Random();
        int customers = r.nextInt(5);

        for(int i = 0; i < customers; i ++){
            int num = r.nextInt(3);
            for(int j = 0; j <= num; j++){
                double chance = r.nextDouble();
                switch(j){
                    case 0:
                        if(chance < 0.2){
                            if(store.inventory >= 1){
                                purchasedGame = store.shelf.remove(0);
                                store.cash += purchasedGame.price;
                                store.inventory = store.shelf.size();
                                System.out.println("Customer has bought " + purchasedGame.title);
                            }
                        }
                    break;
                    case 1:
                        if(chance < 0.18){
                            if(store.inventory >= 2){
                                purchasedGame = store.shelf.remove(1);
                                store.cash += purchasedGame.price;
                                store.inventory = store.shelf.size();
                                System.out.println("Customer has bought " + purchasedGame.title);
                            }
                        }
                    break;
                    case 2:
                        if(chance < 0.16){
                            if(store.inventory >= 3){
                                purchasedGame = store.shelf.remove(2);
                                store.cash += purchasedGame.price;
                                store.inventory = store.shelf.size();
                                System.out.println("Customer has bought " + purchasedGame.title);
                            }
                        }
                    break;
                }
            }
            System.out.println("Customer has not bought anything");
        }
    }
 
    @Override public void order()
    {
        List<Game> games = new ArrayList<Game>();
            games.add(new Monopoly());
            games.add(new Clue());
            games.add(new Life());

            games.add(new Mousetrap());
            games.add(new Candyland());
            games.add(new ConnectFour());
            
            games.add(new Magic());
            games.add(new Pokemon());
            games.add(new Netrunner());
            
            games.add(new Catan());
            games.add(new Risk());
            games.add(new Gloomhaven());

            for(int i = 0; i < games.size(); i++){
                int j = 0;
                while(j < 3){
                    boolean out = true;
                    for(int k = 0; k < store.shelf.size(); k++){
                        if(store.shelf.get(k).title.equals(games.get(i).title)){
                            out = false;
                        }
                    }
                    if(out){
                        j++;
                        List<Game> order = new ArrayList<Game>();
                        Game g1 = new Game(games.get(i).title, games.get(i).type);
                        Game g2 = new Game(games.get(i).title, games.get(i).type);
                        Game g3 = new Game(games.get(i).title, games.get(i).type);
                        order.add(g1);
                        order.add(g2);
                        order.add(g3);
                        store.newGames.add()
                        store.cash += (g1.price/2) + (g2.price/2) + (g3.price/2);
                    }
                }
            }
    }
 
    @Override public void close()
    {
        System.out.println("The Cashier is leaving and the store is closed.");
    }
}

abstract class Game {
    // declare fields
    String title = " ";
    String type = " ";
    double price;
    scale size;
 
    Game(String title, String type) {
        this.title = title;
        this.type = type;
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}

abstract class Family extends Game{
    // declare fields
    String title = " ";
    String type = "Family";
    double price;
    scale size;
 
    Game(String title) {
        this.title = title;
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}
abstract class Kids extends Game{
    // declare fields
    String title = " ";
    String type = "Kids";
    double price;
    scale size;
 
    Game(String title){
        this.title = title;
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}
abstract class Card extends Game{
    // declare fields
    String title = " ";
    String type = "Card";
    double price;
    scale size;
 
    Card(String title){
        this.title = title;
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}
abstract class Board extends Game{
    // declare fields
    String title = " ";
    String type = "Board";
    double price;
    scale size;
 
    Board(String title){
        this.title = title;
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}

class Monopoly extends Family{
    // declare fields
    String title = "Monopoly";
    String type = "Board";
    double price;
    scale size;
 
    Monopoly(){
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}

class Clue extends Family{
    // declare fields
    String title = "Clue";
    String type = "Board";
    double price;
    scale size;
 
    Clue(){
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}

class Life extends Family{
    // declare fields
    String title = "Life";
    String type = "Board";
    double price;
    scale size;
 
    Life(){
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}

class Mousetrap extends Kids{
    // declare fields
    String title = "Mousetrap";
    String type = "Kids";
    double price;
    scale size;
 
    Mousetrap({
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}
class Candyland extends Kids{
    // declare fields
    String title = "Candyland";
    String type = "Kids";
    double price;
    scale size;
 
    Candyland(){
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}
class ConnectFour extends Kids{
    // declare fields
    String title = "ConnectFour";
    String type = "Kids";
    double price;
    scale size;
 
    ConnectFour() {
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}

class Magic extends Card{
    // declare fields
    String title = "Magic";
    String type = "Cards";
    double price;
    scale size;
 
    Mgic() {
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}
class Pokemon extends Card{
    // declare fields
    String title = "Pokemon";
    String type = "Cards";
    double price;
    scale size;
 
    Pokemon() {
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}
class Netrunner extends Card{
    // declare fields
    String title = "Netrunner";
    String type = "Cards";
    double price;
    scale size;
 
    Netrunner() {
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}

class Catan extends Board{
    // declare fields
    String title = "Catan";
    String type = "Board";
    double price;
    scale size;
 
    Catan() {
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}
class Risk extends Board{
    // declare fields
    String title = "Risk";
    String type = "Board";
    double price;
    scale size;
 
    Risk() {
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}
class Gloomhaven extends Board{
    // declare fields
    String title = "Gloomhaven";
    String type = "Board";
    double price;
    scale size;
 
    Gloomhaven() {
        size = new scale()
        Random r = new Random();
        price = r.nextDouble(96) + 5;
    }

}

class scale{
    int length;
    int width;
    int height;

    scale(){
        Random r = new Random();
        length = r.nextInt(38) + 1;
        width = r.nextInt(38) + 1;
        height = r.nextInt(38) + 1;
    }
}
