import java.util.Scanner;
public class Coche {
    String color;
    String marca;
    int km;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);        
        Coche coche1 = new Coche();
        
        coche1.color = sc.nextLine();
        coche1.marca = sc.nextLine();
        coche1.km = sc.nextInt();

        System.out.println("Color "+ coche1.color);
        System.out.println("Marca "+ coche1.marca);
        System.out.println("KM "+ coche1.km);
    }
}