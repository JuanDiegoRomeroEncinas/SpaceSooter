import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Nave {
    private int x, y;
    private int dx;
    private ArrayList<Bala> balas;

    public Nave(int x, int y) {
        this.x = x;
        this.y = y;
        this.balas = new ArrayList<>();
    } 
     //metodos set
     public void setX(int x) { this.x = x; }
     public void setY(int y) { this.y = x; }
    
    public void dibujar(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 50, 30); // Dibuja la nave
        for (Bala bala : balas) {
            bala.dibujar(g); // Dibuja cada bala
        }
    }

    public void mover() {
        x += dx; // Mueve la nave según dx
        if (x < 0) x = 0; // Limita el movimiento a la izquierda
        if (x > 550) x = 550; // Limita el movimiento a la derecha
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) dx = -5; // Mueve a la izquierda
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) dx = 5; // Mueve a la derecha
        if (e.getKeyCode() == KeyEvent.VK_SPACE) disparar(); // Dispara al presionar espacio
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) dx = 0; // Detiene el movimiento horizontal al soltar las teclas
    }

    protected void disparar() {
        balas.add(new Bala(x + 20, y)); // Dispara desde el centro de la nave
    }

    public ArrayList<Bala> getBalas() {
        return balas; // Método para obtener las balas
    }

    public int getX() { return x; } // Obtener posición X de la nave
    public int getY() { return y; } // Obtener posición Y de la nave
}