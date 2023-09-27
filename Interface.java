
import java.util.Scanner;

public class Interface {
    Scanner sc = new Scanner(System.in);
    
    public void start(){
        
        ToyAutomat automat = new ToyAutomat();
        while (true) {
            System.out.print("\033[H\033[J");
            System.out.println();                                                   
            System.out.println("[1] Добавить товар к розыгрышу");
            System.out.println("[2] Показать список разыгрываемых товаров");
            System.out.println("[3] Перейти к розыгрышу");
            System.out.println("[0] выйти из программы");
            
            int command = inputCommand();

            if (command == 1) addToy(automat);

            if (command == 2) showToyList(automat);

            if (command == 3) startDrow(automat);

            if (command == 0){                                  
                break;
            }
        }
        sc.close();
    }

    private void addToy(ToyAutomat automat) {
        System.out.print("\033[H\033[J");                               
        automat.addToy(addName());
        System.out.println("Товар успешно добавлен в список розыгрыша! Нажмите Enter, чтобы вернуться в меню.");
        sc.nextLine();
    }

    private void showToyList(ToyAutomat automat) {
        System.out.print("\033[H\033[J");
        if (automat.getToyList().isEmpty()) System.out.println("Список разыгрыша пуст!");
        else {                                
            System.out.println("Список разыгрываемых товаров:");
            automat.printToyList();
        }
        System.out.println("Нажмите Enter, чтобы вернуться в меню.");
        sc.nextLine();
    }

    private void startDrow(ToyAutomat automat) {
        System.out.print("\033[H\033[J");
                if (automat.getToyList().isEmpty()) {
                    System.out.println("Список розыгрыша пуст!");
                }
                automat.file();
                automat.draw();
                System.out.println();
                System.out.println("Нажмите любую цифру для розыгрыша следующего предмета");
                System.out.println("[0] для завершения розыгрыша");
                while (true){
                    int command = inputCommand();
                    sc.nextLine();
                    if (command == 0){                          
                        automat.endDraw();                         
                        break; }                                
                    else {
                        String text = automat.playDraw();        
                        System.out.println(text);               
                        if(text.equals("Спасибо за розыгрыш\n")){    
                            break;                              
                        }
                    }
                }
                // break; 
    }
    
    private int inputCommand(){
        System.out.println("Введите команду: ");
        String input = sc.nextLine();
        try {
            int command = Integer.parseInt(input);
            return command;
        } catch (NumberFormatException e) {
            System.out.println("Введён неверный формат! Попробуйте снова!");
            sc.nextLine();
            return inputCommand();
        }
    }
    

    private String addName(){                                                                   
        while (true){
            System.out.println("Введите разыгрываемый товар в виде:\nАртикул товара;Название товара;количество товара");
            String text = sc.nextLine();
            String [] test = text.split(";");                                           
            if (test.length == 3){
                try {
                    Integer.parseInt(test[2]);                                                 
                    if (( ! test[0].equals(""))&&( ! test[1].equals(""))){  
                        return text;
                    }
                    else {System.out.println("Не заполнено Артикул или название товара");}
                } catch (Exception e) {
                    System.out.println("На позиции числа, должно быть целое число");
                }
            }
            else {System.out.println("Формат ввода неверен");}
        }
    }
}
