import java.awt.*;
import java.util.Random;

public class PowerUp {
    private int x, y;
    private String tipo; // Tipo de power-up (ejemplo: "vida", "disparo rápido")
    private Random random;

    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
        this.random = new Random();
        this.tipo = random.nextBoolean() ? "5puntos" : "10puntos"; // Asigna un tipo aleatorio
    }

    public void dibujar(Graphics g) {
        if (tipo.equals("5puntos")) {
            g.setColor(Color.GREEN);
            g.fillOval(x, y, 20, 20); // Dibuja un círculo verde para vida
        } else if (tipo.equals("10puntos")) {
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, 20, 10); // Dibuja un rectángulo amarillo para disparo rápido
        }
    }

    public void mover() {
        y += 2; // Mueve el power-up hacia abajo
    }

    public boolean tocaNave(Nave nave) {
        return x >= nave.getX() && x <= nave.getX() + 50 &&
               y >= nave.getY() && y <= nave.getY() + 30; // Verifica si toca la nave
    }

    public boolean fueraDePantalla() {
        return y > 500; // Comprueba si el power-up está fuera de pantalla
    }

    public String getTipo() {
        return tipo; // Devuelve el tipo de power-up
    }
}