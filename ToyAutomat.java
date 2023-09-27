
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class ToyAutomat {
    private ArrayList <Toy> automat = new ArrayList<>();
    private PriorityQueue <Toy> drawList = new PriorityQueue<>();

    public void addToy(String input) {
        String[] product = input.split(";");
        automat.add(new Toy(Integer.parseInt(product[0]), product[1], Integer.parseInt(product[2])));
    }

    public ArrayList<Toy> getToyList() {
        return automat;
    }

    public void printToyList() {
        for (Toy product : automat) {
            System.out.println(product);
        }
    }

    public void draw(){                                                                                     
        for (Toy position : automat) {                                                                  
            int count = position.getCount();                                                                
            while (count > 0) {
                int id = (int) (Math.random() * 100 + 1);                                                 
                drawList.add(new Toy(position.getId(),position.getName(),position.getCount(), id ));   
                count --;
           }
        }
    }

    public void file(){                                                             
        try {
            if ( ! Files.isRegularFile(Path.of("result.txt"))){               
                Files.createFile(Path.of("result.txt"));}
            else {
                Files.delete(Path.of("result.txt"));                          
                Files.createFile(Path.of("result.txt"));}                     
        } catch (IOException e) {
            System.out.println("Ошибра при работе с файлом: "+e.getMessage());      
        }
    }

    public String playDraw() {
        Toy product = drawList.poll();
        if(product!= null){
            product.setCount(product.getCount()-1);
            Path path = Paths.get("result.txt");
            String text = product.getId()+","+product.getName()+","+product.getChance()+"\n";               
            try {
                Files.write(path, text.getBytes(), StandardOpenOption.APPEND);                              
                return (product.getId() +", "+ product.getName());
            } catch (Exception e) {
                return ("Запись в файл была с ошибкой: "+e.getMessage());
            } 
        }
        else return "Спасибо за розыгрыш\n";
    }

    public void endDraw(){                                                                                  
        Toy product;
        Path path = Paths.get("result.txt");
        try {                                                                                               
            String text0 = "\nНе разыгранный товар \n";
            Files.write(path, text0.getBytes(), StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.out.println("Запись в файл была с ошибкой: "+e.getMessage());
        }
        while((product = drawList.poll())!= null) {                                                        
            String text = product.getId()+","+product.getName()+","+product.getChance()+"\n";               
            try {
                Files.write(path, text.getBytes(), StandardOpenOption.APPEND);                              
            } catch (Exception e) {
                System.out.println("Запись в файл была с ошибкой: "+e.getMessage());
            } 
        } 
    }

    


}
