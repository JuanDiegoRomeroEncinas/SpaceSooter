import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class Juego extends JPanel implements ActionListener {
    protected Nave nave;
    private ArrayList<Enemigo> enemigos;
    private ArrayList<PowerUp> powerUps;
    private Stack<Bala> pilasBalas; // Pila de balas disparadas
    private Stack<Enemigo> pilasEnemigosEliminados; // Pila de enemigos eliminados
    private Queue<Enemigo> colasEnemigos; // Cola de enemigos generados
    private Queue<PowerUp> colasPowerUps; // Cola de power-ups recogidos
    protected Timer timer;
    protected Timer enemigoTimer;
    private Timer powerUpTimer;
    private boolean juegoTerminado;

    public Juego() {
        nave = new Nave(250, 400);
        enemigos = new ArrayList<>();
        powerUps = new ArrayList<>();
        pilasBalas = new Stack<>();
        pilasEnemigosEliminados = new Stack<>();
        colasEnemigos = new LinkedList<>();
        colasPowerUps = new LinkedList<>();
        timer = new Timer(20, this);
        timer.start();  
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                nave.keyPressed(e);
            }
            public void keyReleased(KeyEvent e) {
                nave.keyReleased(e);
            }
        });

        enemigoTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarEnemigo();
            }
        });
        enemigoTimer.start();

        powerUpTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarPowerUp();
            }
        });
        powerUpTimer.start();

        juegoTerminado = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        nave.dibujar(g);
        for (Enemigo enemigo : enemigos) {
            enemigo.dibujar(g);
        }
        
        for (PowerUp powerUp : powerUps) {
            powerUp.dibujar(g);
        }
        
        if (juegoTerminado) {
            g.setColor(Color.RED);
            g.drawString("¡Juego Terminado!", 250, 250);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!juegoTerminado) {
            nave.mover();
            
            for (int i = 0; i < nave.getBalas().size(); i++) {
                Bala bala = nave.getBalas().get(i);
                bala.mover();
                
                if (bala.fueraDePantalla()) { 
                    nave.getBalas().remove(i);
                    i--;
                } else {
                    for (int j = 0; j < enemigos.size(); j++) {
                        Enemigo enemigo = enemigos.get(j);
                        if (bala.colisionaCon(enemigo)) {
                            nave.getBalas().remove(i);
                            pilasBalas.push(bala); // Almacena la bala en la pila
                            enemigos.remove(j);
                            pilasEnemigosEliminados.push(enemigo); // Almacena el enemigo en la pila
                            i--;
                            break;
                        }
                    }
                }
            }

            for (int i = 0; i < enemigos.size(); i++) {
                Enemigo enemigo = enemigos.get(i);
                enemigo.mover();

                if (enemigo.tocaNave(nave)) {
                    juegoTerminado = true;
                }
            }

            for (int i = 0; i < powerUps.size(); i++) {
                PowerUp powerUp = powerUps.get(i);
                powerUp.mover();

                if (powerUp.tocaNave(nave)) { 
                    aplicarPowerUp(powerUp); 
                    powerUps.remove(i); 
                    colasPowerUps.add(powerUp); // Almacena el power-up en la cola
                    i--; 
                }

                if (powerUp.fueraDePantalla()) { 
                    powerUps.remove(i); 
                    i--; 
                }
            }

            repaint();
        }
    }

    protected void generarEnemigo() {
        int x = (int)(Math.random() * 550); 
        Enemigo nuevoEnemigo = new Enemigo(x, -30);
        enemigos.add(nuevoEnemigo);
        colasEnemigos.add(nuevoEnemigo); // Agrega el nuevo enemigo a la cola
    }

    protected void generarPowerUp() {
        int x = (int)(Math.random() * 550); 
        PowerUp nuevoPowerUp = new PowerUp(x, -20);
        powerUps.add(nuevoPowerUp); // Genera un nuevo Power-Up en una posición aleatoria en X
    }

    protected void aplicarPowerUp(PowerUp powerUp) {
       if (powerUp.getTipo().equals("5puntos")) {
           System.out.println("¡5puntos!");
           // Aquí puedes agregar lógica para aumentar vidas o salud.
       } else if (powerUp.getTipo().equals("10puntos")) {
           System.out.println("¡10puntos!");
           // Aquí puedes agregar lógica para aumentar la velocidad de disparo.
       }
   }

   public static void main(String[] args) {
       JFrame frame = new JFrame("Space Shooter");
       Juego game = new Juego();
       frame.add(game);
       frame.setSize(600, 500);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
   }
}