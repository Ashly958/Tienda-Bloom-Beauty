package com.mycompany.tiendaromay;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class JsonManager {
    // PATRON SINGLETON
    private static JsonManager instance;
    private final Gson gson;

    // Constructor privado
    private JsonManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Metodo para obtener la instancia unica
    public static JsonManager getInstance() {
        if (instance == null) {
            instance = new JsonManager();
        }
        return instance;
    }

    // Metodos NO estaticos
    public List<Producto> cargarProductos() {
        try {
            var json = Files.readString(Path.of("productos.json"));
            var listType = new com.google.gson.reflect.TypeToken<List<Producto>>(){}.getType();
            return gson.fromJson(json, listType);
        } catch (Exception e) {
            return crearProductosIniciales();
        }
    }

    public void guardarProductos(List<Producto> productos) {
        try {
            Files.writeString(Path.of("productos.json"), gson.toJson(productos));
        } catch (IOException e) {
            System.out.println("Error al guardar productos: " + e.getMessage());
        }
    }

    public Map<String, String> cargarUsuarios() {
        try {
            var json = Files.readString(Path.of("usuarios.json"));
            var mapType = new com.google.gson.reflect.TypeToken<Map<String, String>>(){}.getType();
            return gson.fromJson(json, mapType);
        } catch (Exception e) {
            return crearUsuariosIniciales();
        }
    }

    public void guardarUsuarios(Map<String, String> usuarios) {
        try {
            Files.writeString(Path.of("usuarios.json"), gson.toJson(usuarios));
        } catch (IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public List<Ticket> cargarTickets() {
        try {
            var json = Files.readString(Path.of("tickets.json"));
            var listType = new com.google.gson.reflect.TypeToken<List<Ticket>>(){}.getType();
            return gson.fromJson(json, listType);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void guardarTicket(Ticket ticket) {
        try {
            var tickets = cargarTickets();
            tickets.add(ticket);
            Files.writeString(Path.of("tickets.json"), gson.toJson(tickets));
        } catch (IOException e) {
            System.out.println("Error al guardar ticket: " + e.getMessage());
        }
    }

    private List<Producto> crearProductosIniciales() {
        var productos = new ArrayList<Producto>();

        productos.add(ProductoFactory.crearProducto("labial"));
        productos.add(ProductoFactory.crearProducto("base"));
        productos.add(ProductoFactory.crearProducto("polvo"));
        productos.add(ProductoFactory.crearProducto("delineador"));
        productos.add(ProductoFactory.crearProducto("rimel"));
        productos.add(ProductoFactory.crearProducto("sombras"));
        productos.add(ProductoFactory.crearProducto("rubor"));

        guardarProductos(productos);
        return productos;
    }

    private Map<String, String> crearUsuariosIniciales() {
        var usuarios = new HashMap<String, String>();

        usuarios.put("bloom", "store2024");
        usuarios.put("admin", "1234");
        usuarios.put("ashly", "0958");
        usuarios.put("kevin", "1111");
        usuarios.put("jaider", "5623");

        guardarUsuarios(usuarios);
        return usuarios;
    }
}