package com.mycompany.tiendaromay;

public class ProductoFactory {
    private static int contadorId = 1;

    // Metodo Factory para crear productos segun tipo
    public static Producto crearProducto(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "labial" -> new Producto(contadorId++, "Labial Matte", 18000, 50);
            case "base" -> new Producto(contadorId++, "Base Líquida", 35000, 30);
            case "polvo" -> new Producto(contadorId++, "Polvo Compacto", 25000, 25);
            case "delineador" -> new Producto(contadorId++, "Delineador Negro", 15000, 40);
            case "rimel" -> new Producto(contadorId++, "Rímel Volumen", 22000, 35);
            case "sombras" -> new Producto(contadorId++, "Paleta de Sombras", 45000, 20);
            case "rubor" -> new Producto(contadorId++, "Rubor", 20000, 30);
            default -> throw new IllegalArgumentException("Tipo de producto no valido: " + tipo);
        };
    }

    // Metodo para crear producto personalizado
    public static Producto crearProductoPersonalizado(int id, String nombre, double precio, int cantidad) {
        return new Producto(id, nombre, precio, cantidad);
    }

    // Reiniciar contador (util para testing)
    public static void reiniciarContador() {
        contadorId = 1;
    }
}