import java.awt.*;

public class Bala {
    private int x, y;

    public Bala(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 5, 10); // Dibuja la bala como un rectángulo rojo
    }

    public void mover() {
        y -= 5; // Mueve la bala hacia arriba
    }

    public boolean fueraDePantalla() {
        return y < 0; // Comprueba si la bala está fuera de pantalla (arriba)
    }

    public boolean colisionaCon(Enemigo enemigo) {
        return x >= enemigo.getX() && x <= enemigo.getX() + 30 && 
               y >= enemigo.getY() && y <= enemigo.getY() + 30; // Verifica colisión con el enemigo
    }
}