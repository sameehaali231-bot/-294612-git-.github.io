package com.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // Create context for handling requests
        server.createContext("/", new HomeHandler());
        server.createContext("/api/data", new ApiHandler());
        
        server.setExecutor(null); // Creates a default executor
        server.start();
        System.out.println("Server running on http://localhost:8080");
    }
    
    // Handler for home page
    static class HomeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            // Basic static file serving for our small site
            if (path.equals("/") || path.equals("/index.html")) {
                // Serve the main home page
                byte[] response = Files.readAllBytes(Paths.get("src/main/resources/index.html"));
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response);
                }
            } else if (path.equals("/index2.html")) {
                // Serve the quiz creation page
                byte[] response = Files.readAllBytes(Paths.get("src/main/resources/index2.html"));
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response);
                }
            } else if (path.equals("/grade1-math-topics.html")) {
                // Serve the grade 1 math topics page
                byte[] response = Files.readAllBytes(Paths.get("src/main/resources/grade1-math-topics.html"));
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response);
                }
            } else if (path.equals("/grade1-addition.html")) {
                // Serve the grade 1 addition page
                byte[] response = Files.readAllBytes(Paths.get("grade1-addition.html"));
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response);
                }
            } else if (path.equals("/grade1-subtraction.html")) {
                // Serve the grade 1 subtraction page
                byte[] response = Files.readAllBytes(Paths.get("grade1-subtraction.html"));
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response);
                }
            } else if (path.equals("/grade1-counting.html")) {
                // Serve the grade 1 counting page
                byte[] response = Files.readAllBytes(Paths.get("grade1-counting.html"));
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response);
                }
            } else if (path.endsWith(".css") || path.endsWith(".js")) {
                // Serve static CSS/JS assets from resources
                String filePath = "src/main/resources" + path;
                java.nio.file.Path p = Paths.get(filePath);
                if (Files.exists(p)) {
                    byte[] response = Files.readAllBytes(p);
                    String contentType = path.endsWith(".css") ? "text/css" : "application/javascript";
                    exchange.getResponseHeaders().set("Content-Type", contentType);
                    exchange.sendResponseHeaders(200, response.length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(response);
                    }
                } else {
                    exchange.sendResponseHeaders(404, -1);
                }
            } else {
                // Fallback - not found
                exchange.sendResponseHeaders(404, -1);
            }
        }
        }
    }
    
    // Handler for API endpoints
    static class ApiHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "{\"message\": \"Hello from Java Backend!\", \"timestamp\": " + System.currentTimeMillis() + "}";
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
