
import java.io.*;
import java.util.*;

public class Xkcdpwgen {
    Random rand;
    ArrayList<String> list;
   String[] symbols = {"~","!","@","#","$","%","^","&","*",".",":",";"};
   String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};



    Xkcdpwgen(){
        this.rand = new Random();
        this.list= new ArrayList<String>();

    }


    public void createPassword1(String args){

        try{
            File fileName = new File("C:\\Users\\apara\\OneDrive\\Documents\\College\\Summer2\\FoundationsofCybersecurity\\words.txt");
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String word = bufferedReader.readLine();
            while(word != null){
                list.add(word);
                word = bufferedReader.readLine();
            }

            //prints out the help text
            if ((args.compareTo("-h") == 0) || (args.compareTo("--help") == 0)) {
                System.out.println("Usage : xkcdpwgen [-h] [-w WORDS] [-c CAPS] " +
                        "[-n NUMBERS] [-s SYMBOLS]");
                System.out.println("Generate a secure, memorable password using the XKCD method");
                System.out.println("Optional arguments : ");
                System.out.println("-h, --help   show this help message and exit");
                System.out.println("-w WORDS, --words WORDS   include WORDS words in the password (default = 4");
                System.out.println("-c CAPS, --caps CAPS   capitalize the first letter of CAPS random words (default = 0");
                System.out.println("-n NUMBERS, --numbers NUMBERS   insert NUMBERS random numbers in the password (default = 0)");
                System.out.println("-s SYMBOLS, --symbols SYMBOLS   insert SYMBOLS random symbols in the password (default = 0)");
            }


            //produces a password with 4 random english words, in lowercase, from the list
            else if (args.compareTo("") == 0){
                for(int i = 0; i < 4; i++){
                    System.out.print(list.get(rand.nextInt(list.size())).toLowerCase());
                }
            }

            //allows the user to override the number of words in the password
            else if (args.contains("-w")  || args.contains("--words")){
                for(int i = 0; i < Integer.parseInt(args.substring(args.length()-1)); i++){
                    System.out.print(list.get(rand.nextInt(list.size())).toLowerCase());
                }
            }

            //capitalizes the first letters of random words
            else if (args.contains("-c")  || args.contains("--caps")){
                ArrayList<String> l = new ArrayList<String>();

                for(int i = 0; i < 4; i++){
                    l.add(list.get(rand.nextInt(list.size())));
                }

                for (int j = 0; j < Integer.parseInt(args.substring(args.length()-1)); j++){
                    String str;
                    int index = rand.nextInt(4);
                    str = l.get(index).substring(0,1);
                    l.set(index, str.toUpperCase());
                }

                for(int i = 0; i < 4; i++){
                   System.out.print(l.get(i));
                }
            }

            //adds random numbers into the password
            else if (args.contains("-n")  || args.contains("--numbers")){
                ArrayList<String> l = new ArrayList<String>();

                for(int i = 0; i < 4; i++){
                    l.add(list.get(rand.nextInt(list.size())));
                }

                for(int j = 0; j < Integer.parseInt(args.substring(args.length()-1)); j++){
                    l.add(numbers[rand.nextInt(numbers.length)]);
                }

                //shuffle the array list and print it out
                Collections.shuffle(l);

                for (String s : l) {
                    System.out.print(s);
                }

            }

            //adds random symbols into the password
            else if (args.contains("-s")  || args.contains("--symbols")){
                ArrayList<String> l = new ArrayList<String>();

                for(int i = 0; i < 4; i++){
                    l.add(list.get(rand.nextInt(list.size())));
                }

                for(int j = 0; j < Integer.parseInt(args.substring(args.length()-1)); j++){
                    l.add(symbols[rand.nextInt(symbols.length)]);
                }

                //shuffle the arraylist and print it out
                Collections.shuffle(l);

                for (String s : l) {
                    System.out.print(s);
                }

            }

            //print an error message if any other arguments are used
            else{
                System.out.print("Error : No such arguments available");
            }

                bufferedReader.close();

        } catch (IOException e){
            System.out.println("Error reading the file : " + e.getMessage());
        }
    }
}
