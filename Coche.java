public class Coche {
    String color;
    String marca;
    int km;

    public static void main(String[] args) {
        Coche coche1 = new Coche();

        coche1.color = "Blanco";
        coche1.marca = "Mazda";
        coche1.km = 0;

        System.out.println("Color "+ coche1.color);
        System.out.println("Marca "+ coche1.marca);
        System.out.println("KM "+ coche1.km);

    }
}