import java.awt.*;

public class Enemigo {
    private int x, y;

    public Enemigo(int x, int y) {
        this.x = x;
        this.y = y;
    }
    // metodo set
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public void dibujar(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x, y, 30, 30); // Dibuja el enemigo como un círculo azul
    }

    public void mover() {
        // Movimiento simple hacia abajo
        y += 1; 
        
        if (y > 500) { // Si sale de la pantalla, reiniciar en la parte superior
            y = -30; 
            x = (int)(Math.random() * 550); // Cambiar posición horizontal aleatoria
        }
    }

    public boolean tocaNave(Nave nave) {
        return x >= nave.getX() && x <= nave.getX() + 50 &&
               y >= nave.getY() && y <= nave.getY() + 30; // Verifica si el enemigo toca la nave
    }

    public int getX() { return x; } // Obtener posición X del enemigo
    public int getY() { return y; } // Obtener posición Y del enemigo
}