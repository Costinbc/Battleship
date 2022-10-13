package battleship;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    //public static String[][] map = new String[11][11];
    //public static String[][] map_p2 = new String[11][11];
    public static boolean afs;
    public static boolean hit;

    static class Ship_locations
    {
        public int poz1_c, poz2_c, dir;
        public char poz1_l, poz2_l;
        public boolean sank = false;
        public Ship_locations(char poz1_l, int poz1_c, char poz2_l, int poz2_c, int dir, boolean sank) {
            this.poz1_l = poz1_l;
            this.poz1_c = poz1_c;
            this.poz2_l = poz2_l;
            this.poz2_c = poz2_c;
            this.dir = dir;
            this.sank = sank;
        }
    }

    //public static Ship_locations[] ship_locations = new Ship_locations[5];

    public static final String[] ships = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
    public static void createMap(String[][] map) {

        int i, j;

        map[0][0] = " ";

        for (i = 1; i <= map.length - 1; i++) {
            map[0][i] = Character.toString((char) (48 + i));
            map[i][0] = Character.toString((char) (64 + i));
        }

        map[0][10] ="10";

        for (i = 1; i <= map.length - 1; i++){
            for (j = 1; j <= map[i].length - 1; j++){
                map[i][j] = "~";
            }

        }


    }
    public static void pressEnter() {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean ship_alive(String[][] map,Ship_locations[] ship_locations, char poz_l, int poz_c)
    {
        for(int i = 0; i < ship_locations.length; i++){
            if(ship_locations[i].dir == 0)
            {
                if(poz_l <= ship_locations[i].poz1_l && poz_l >= ship_locations[i].poz2_l && poz_c == ship_locations[i].poz1_c){
                    for(int j = ship_locations[i].poz2_l; j <= ship_locations[i].poz1_l; j++){
                        if(map[j - 64][poz_c].equals("O"))
                            return true;
                    }
                    ship_locations[i].sank = true;
                }
            }
            else if(ship_locations[i].dir == 1)
            {
                if(poz_l >= ship_locations[i].poz1_l && poz_l <= ship_locations[i].poz2_l && poz_c == ship_locations[i].poz1_c){
                    for(int j = ship_locations[i].poz1_l; j <= ship_locations[i].poz2_l; j++){
                        if(map[j - 64][poz_c].equals("O"))
                            return true;
                    }
                    ship_locations[i].sank = true;
                }
            }
            else if(ship_locations[i].dir == 2)
            {
                if(poz_c <= ship_locations[i].poz1_c && poz_c >= ship_locations[i].poz2_c && poz_l == ship_locations[i].poz1_l){
                    for(int j = ship_locations[i].poz2_c; j <= ship_locations[i].poz1_c; j++){
                        if(map[poz_l - 64][j].equals("O"))
                            return true;
                    }
                    ship_locations[i].sank = true;
                }
            }
            else if(ship_locations[i].dir == 3)
            {
                if(poz_c >= ship_locations[i].poz1_c && poz_c <= ship_locations[i].poz2_c && poz_l == ship_locations[i].poz1_l){
                    for(int j = ship_locations[i].poz1_c; j <= ship_locations[i].poz2_c; j++){
                        if(map[poz_l - 64][j].equals("O"))
                            return true;
                    }
                    ship_locations[i].sank = true;
                }
            }
        }
        return false;
    }

    public static boolean tooClose(String[][] map, int poz1_l, int poz2_l, int poz1_c, int poz2_c, int cells){


            int dir = 5;

            if(poz1_l > poz2_l ){ dir = 0;}
            else if(poz1_l < poz2_l ){ dir = 1;}
            else if(poz1_c > poz2_c ){ dir = 2;}
            else if( poz1_c < poz2_c ){ dir = 3;}

            int i = 1;

            while (i <= cells){

                if(map[poz1_l][poz1_c].equals("O")){ return true;}
                else if(poz1_l > 1 && map[poz1_l-1][poz1_c].equals("O")){ return true;}
                else if(poz1_l > 1 && poz1_l < 10 && map[poz1_l-1][poz1_c+1].equals("O")){ return true;}
                else if(poz1_c < 10 && map[poz1_l][poz1_c+1].equals("O")){ return true;}
                else if(poz1_l < 10 && poz1_c < 10 && map[poz1_l+1][poz1_c+1].equals("O")){ return true;}
                else if(poz1_l < 10 && map[poz1_l+1][poz1_c].equals("O")){ return true;}
                else if(poz1_l < 10 && poz1_c > 1 && map[poz1_l+1][poz1_c-1].equals("O")){ return true;}
                else if(poz1_c > 1 && map[poz1_l][poz1_c-1].equals("O")){ return true;}
                else if(poz1_l > 1 && poz1_c > 1 && map[poz1_l-1][poz1_c-1].equals("O")){ return true;}

                if(dir == 0){poz1_l --;}
                else if(dir == 1){poz1_l ++;}
                else if(dir == 2){poz1_c --;}
                else {poz1_c ++;}

                i++;

                }

                return false;

    }
    public static void afisare(String [][] map){

        for (int i = 0; i <= map.length - 1; i++){
            for (int j = 0; j <= map[i].length - 1; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void afisare_ascunsa(String [][] map){

        for (int i = 0; i <= map.length - 1; i++){
            for (int j = 0; j <= map[i].length - 1; j++){
                if(map[i][j].equals("O")) { System.out.print("~ ");}
                else {System.out.print(map[i][j] + " ");}
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void placeShip(String[][] map,Ship_locations[] ship_locations, int cells, int nr_ship){
        String poz1 = scanner.next();
        String poz2 = scanner.next();

        char poz1_l = poz1.charAt(0);
        char poz2_l = poz2.charAt(0);
        int poz1_c = (int) (poz1.charAt(1)) - 48;
        int poz2_c = (int) (poz2.charAt(1)) - 48;

        if(poz1.length() > 2) poz1_c = 10;
        if(poz2.length() > 2) poz2_c = 10;

        if(poz1_l != poz2_l && poz1_c != poz2_c){
            System.out.println("Error! Wrong ship location! Try again:");
            placeShip(map, ship_locations, cells, nr_ship);
        }

        else if(poz1_l == poz2_l  && Math.abs(poz1_c - poz2_c) + 1 != cells){
            System.out.println("Error! Wrong length of the " + ships[nr_ship] + "! Try again:");
            placeShip(map, ship_locations, cells, nr_ship);
        }

        else if(poz1_c == poz2_c  && Math.abs(poz1_l - poz2_l) + 1 != cells){
            System.out.println("Error! Wrong length of the " + ships[nr_ship] + "! Try again:");
            placeShip(map, ship_locations, cells, nr_ship);
    }

        else if(tooClose(map,poz1_l - 64, poz2_l - 64, poz1_c, poz2_c, cells)){
            System.out.println("Error! You placed it too close to another one. Try again:");
            placeShip(map, ship_locations, cells, nr_ship);
        }

        if(afs) return;

        int dir = 5;

        if(poz1_l > poz2_l ){ dir = 0;}
        else if(poz1_l < poz2_l ){ dir = 1;}
        else if(poz1_c > poz2_c ){ dir = 2;}
        else if( poz1_c < poz2_c ){ dir = 3;}

        map[poz2_l - 64][poz2_c] = "O";
        /*
        ship_locations[nr_ship].poz1_l = poz1_l;
        ship_locations[nr_ship].poz1_c = poz1_c;
        ship_locations[nr_ship].poz2_l = poz2_l;
        ship_locations[nr_ship].poz2_c = poz2_c;
        ship_locations[nr_ship].dir = dir;
        ship_locations[nr_ship].sank = false;
        */

        ship_locations[nr_ship] = new Ship_locations(poz1_l, poz1_c, poz2_l, poz2_c, dir, false);
        if(dir == 0){
            while(poz1_l != poz2_l){
                map[poz1_l - 64][poz1_c] = "O";
                poz1_l --;
            }
        }
        if(dir == 1){
            while(poz1_l != poz2_l){
                map[poz1_l - 64][poz1_c] = "O";
                poz1_l ++;
            }
        }
        if(dir == 2){
            while(poz1_c != poz2_c){
                map[poz1_l - 64][poz1_c] = "O";
                poz1_c --;
            }
        }
        if(dir == 3){
            while(poz1_c != poz2_c){
                map[poz1_l - 64][poz1_c] = "O";
                poz1_c ++;
            }
        }

        afs = true;
    }

    public static int takeAShot(String[][] map, String [][] map_p2, Ship_locations[] ship_locations, int ship_number){
        String shot = scanner.next();

        char shot_l = shot.charAt(0);
        if(shot_l > 'J' || shot_l < 'A') {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            ship_number = takeAShot(map, map_p2, ship_locations, ship_number);
            return ship_number;
        }
        int shot_c = (int) (shot.charAt(1)) - 48;
        if(shot.charAt(1) == '0') {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            ship_number = takeAShot(map, map_p2, ship_locations, ship_number);
            return ship_number;
        }
        if(shot.length() > 2 && shot.charAt(2) == '0') {shot_c = 10;}
        else if(shot.length() > 2 && shot.charAt(2) != '0') {
            System.out.println("Error! You entered the wrong coordinates! Try again:");
            ship_number = takeAShot(map, map_p2, ship_locations, ship_number);
            return ship_number;
        }

        //if(afs) return ship_number;

        if(map_p2[shot_l - 64][shot_c].equals("X")){
            System.out.println("You hit a ship!");
            //afisare_ascunsa(map);
            //takeAShot(map,map_p2, ship_locations, ship_locations_p2, ship_number);
            //if(hit) return ship_number;
            return ship_number;
        }
        if(map_p2[shot_l - 64][shot_c].equals("O") ) {
            map_p2[shot_l - 64][shot_c] = "X";
            //afisare_ascunsa(map);
            if(!ship_alive(map_p2,ship_locations, shot_l, shot_c)){
                ship_number --;
                if(ship_number == 0) {
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    hit = true;
                    return ship_number;
                }
                else {System.out.println("You sank a ship!"); return ship_number;}
            }
            else {System.out.println("You hit a ship!"); return ship_number;}


            //ship_number = takeAShot(map,map_p2, ship_locations, ship_locations_p2, ship_number);
            //if(hit) return ship_number;
        }
        else
        {
            map_p2[shot_l - 64][shot_c] = "M";
            //afisare_ascunsa(map);
            System.out.println("You missed!");
            //takeAShot(map,map_p2, ship_locations, ship_locations_p2, ship_number);
            //if(hit) return;
            return ship_number;
        }
        //afisare(map);
        //afs = true;
    }
    public static void citire(String[][] map, Ship_locations[] ship_locations){

        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        afs = false;
        placeShip(map,ship_locations, 5, 0);
        afisare(map);

        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        afs = false;
        placeShip(map,ship_locations,4, 1);
        afisare(map);

        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        afs = false;
        placeShip(map,ship_locations ,3, 2);
        afisare(map);

        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
        afs = false;
        placeShip(map, ship_locations,3, 3);
        afisare(map);

        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        afs = false;
        placeShip(map, ship_locations,2, 4);
        afisare(map);

        /*
        System.out.println("The game starts!");
        afisare_ascunsa(map);

        System.out.println("Take a shot!");
        afs = false;
        takeAShot(map);
            */

    }
    public static void gameStart(String[][] map, String[][] map_p2, Ship_locations[] ship_locations, Ship_locations[] ship_locations_p2, int ship_number, int ship_number_p2){

        int player = 1;
        System.out.println("The game starts!");
        while(ship_number != 0 && ship_number_p2 != 0) {
            if(player == 1) {
                afisare_ascunsa(map_p2);
                System.out.println("---------------------");
                afisare(map);
                System.out.println("Player 1, it's your turn:");
                afs = false;
                ship_number = takeAShot(map, map_p2, ship_locations_p2, ship_number);
                player = 2;
            }
            else{
                afisare_ascunsa(map);
                System.out.println("---------------------");
                afisare(map_p2);
                System.out.println("Player 2, it's your turn:");
                afs = false;
                ship_number_p2 = takeAShot(map_p2, map, ship_locations, ship_number_p2);
                player = 1;
            }

            pressEnter();
        }
    }

    public static void main(String[] args) {

        String[][] map = new String[11][11];
        String[][] map_p2 = new String[11][11];
        Ship_locations[] ship_locations = new Ship_locations[5];
        Ship_locations[] ship_locations_p2 = new Ship_locations[5];
        int ship_number, ship_number_p2;

        //Init player 1
        createMap(map);
        System.out.println("Player 1, place your ships on the game field");
        afisare(map);
        citire(map, ship_locations);
        ship_number = 5;

        pressEnter();

        //Init player 2
        createMap(map_p2);
        System.out.println("Player 2, place your ships to the game field");
        afisare(map_p2);
        citire(map_p2, ship_locations_p2);
        ship_number_p2 = 5;

        pressEnter();

        gameStart(map, map_p2, ship_locations, ship_locations_p2, ship_number, ship_number_p2);


    }
}
