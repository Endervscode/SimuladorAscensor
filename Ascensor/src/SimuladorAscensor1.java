import java.awt.EventQueue; // importa EventQueue para gestionar ejecución en el hilo de eventos de Swing
import java.awt.Font; // importa Font para establecer fuentes en componentes
import java.awt.event.ActionEvent; // importa ActionEvent para manejar eventos de temporizador y botones
import java.util.logging.Logger; // importa Logger para registrar errores e información
import javax.swing.JButton; // importa JButton para usar botones
import javax.swing.JLabel; // importa JLabel para mostrar texto (display)
import javax.swing.Timer; // importa Timer para animaciones periódicas

public class SimuladorAscensor1 extends javax.swing.JFrame { // clase principal que extiende JFrame (ventana)

    private static final Logger logger = Logger.getLogger(SimuladorAscensor1.class.getName()); // logger estático para registros

    // Movimiento y estado
    private final int Y_PISO_3 = 70; // coordenada Y de la cabina para el piso 3
    private final int Y_PISO_2 = 220; // coordenada Y de la cabina para el piso 2
    private final int Y_PISO_1 = 370; // coordenada Y de la cabina para el piso 1
    private int pisoActual = 1; // piso en el que se encuentra actualmente la cabina
    private int pisoDestino = 1; // piso al que se ha solicitado ir
    private Timer timerAnimacion; // timer que mueve la cabina (animación vertical)
    private Timer timerPuertas; // timer que anima las puertas (apertura/cierre)
    private JLabel lblDisplay; // etiqueta que muestra el número de piso en el panel de botones

    // Puertas
    private javax.swing.JPanel leftDoor; // panel que representa la puerta izquierda
    private javax.swing.JPanel rightDoor; // panel que representa la puerta derecha
    private boolean puertasAbiertas = true; // estado inicial: puertas arrancan abiertas
    private enum DoorAction { IDLE, OPENING, CLOSING } // enum para el estado de acción de las puertas
    private DoorAction doorAction = DoorAction.IDLE; // acción actual de las puertas (inicial idle)
    private final int DOOR_SPEED = 3; // velocidad de desplazamiento de las puertas en píxeles por tick
    private final int CABINA_WIDTH = 90; // ancho de la cabina en píxeles
    private final int CABINA_HEIGHT = 100; // alto de la cabina en píxeles
    private final int DOOR_HALF_WIDTH = CABINA_WIDTH / 2; // ancho de cada puerta (la cabina se divide en dos mitades)

    /**
     * Creates new form SimuladorAscensor1
     */
    public SimuladorAscensor1() { // constructor de la ventana principal
        initComponents(); // inicializa componentes gráficos
        iniciarLogica(); // configura la lógica (timers y comportamiento)
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() { // método que crea y posiciona componentes Swing

        panelPrincipal = new javax.swing.JPanel(); // panel contenedor principal
        tuboAscensor = new javax.swing.JPanel(); // panel que representa el "tubo" del ascensor
        cabina = new javax.swing.JPanel(); // panel que representa la cabina móvil
        cable1 = new javax.swing.JPanel(); // panel que simula el primer cable
        cable2 = new javax.swing.JPanel(); // panel que simula el segundo cable
        btnPiso3 = new javax.swing.JButton(); // botón para solicitar piso 3
        btnPiso1 = new javax.swing.JButton(); // botón para solicitar piso 1
        btnPiso2 = new javax.swing.JButton(); // botón para solicitar piso 2
        lblDisplay = new JLabel("1"); // etiqueta display que inicia mostrando "1"
        setLocationRelativeTo(null); // centra la ventana en pantalla

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // cerrar app al cerrar ventana

        panelPrincipal.setBackground(java.awt.Color.gray); // fondo gris para el panel principal
        panelPrincipal.setLayout(null); // layout absoluto para posicionar manualmente componentes

        tuboAscensor.setBackground(new java.awt.Color(255, 255, 255)); // fondo blanco para el tubo
        tuboAscensor.setLayout(null); // layout absoluto dentro del tubo

        // cabina: usar layout absoluto para puertas internas
        cabina.setBackground(new java.awt.Color(51, 153, 255)); // color azul para la cabina
        cabina.setLayout(null); // layout absoluto para posicionar puertas dentro de la cabina
        cabina.setBounds(50, Y_PISO_1, CABINA_WIDTH, CABINA_HEIGHT); // posiciona la cabina en el piso 1

        // crear puertas (cada una ocupa la mitad de la cabina)
        leftDoor = new javax.swing.JPanel(); // instancia del panel puerta izquierda
        leftDoor.setBackground(new java.awt.Color(45, 100, 200)); // color de las puertas
        leftDoor.setBounds(-DOOR_HALF_WIDTH, 0, DOOR_HALF_WIDTH, CABINA_HEIGHT); // inicia abierta: izquierda fuera (x negativo)
        cabina.add(leftDoor); // añade la puerta izquierda dentro de la cabina

        rightDoor = new javax.swing.JPanel(); // instancia del panel puerta derecha
        rightDoor.setBackground(new java.awt.Color(45, 100, 200)); // color de las puertas (misma que izquierda)
        rightDoor.setBounds(CABINA_WIDTH, 0, DOOR_HALF_WIDTH, CABINA_HEIGHT); // inicia abierta: derecha fuera (x = ancho de cabina)
        cabina.add(rightDoor); // añade la puerta derecha dentro de la cabina

        tuboAscensor.add(cabina); // añade la cabina al tubo del ascensor

        cable1.setBackground(new java.awt.Color(51, 51, 51)); // color oscuro para el cable 1

        javax.swing.GroupLayout cable1Layout = new javax.swing.GroupLayout(cable1); // layout para el panel cable1
        cable1.setLayout(cable1Layout); // asigna el layout al panel cable1
        cable1Layout.setHorizontalGroup( // configura grupo horizontal (vacío, tamaño fijo)
            cable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        cable1Layout.setVerticalGroup( // configura grupo vertical (vacío, tamaño fijo)
            cable1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        cable1.setBounds(70, 0, 10, 520); // posiciona el cable1 dentro del tubo
        tuboAscensor.add(cable1); // añade el cable1 al tubo

        cable2.setBackground(new java.awt.Color(51, 51, 51)); // color oscuro para el cable 2

        javax.swing.GroupLayout cable2Layout = new javax.swing.GroupLayout(cable2); // layout para el panel cable2
        cable2.setLayout(cable2Layout); // asigna el layout al panel cable2
        cable2Layout.setHorizontalGroup( // configura grupo horizontal (vacío, tamaño fijo)
            cable2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        cable2Layout.setVerticalGroup( // configura grupo vertical (vacío, tamaño fijo)
            cable2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        cable2.setBounds(110, 0, 10, 520); // posiciona el cable2 dentro del tubo
        tuboAscensor.add(cable2); // añade el cable2 al tubo

        // líneas que simulan cada piso (dentro del tubo)
        javax.swing.JPanel lineaPiso3 = new javax.swing.JPanel(); // panel que dibuja la línea del piso 3
        lineaPiso3.setBackground(new java.awt.Color(100, 100, 100)); // color gris para la línea
        lineaPiso3.setBounds(10, Y_PISO_3 + CABINA_HEIGHT - 5, 170, 3); // posiciona la línea del piso 3
        tuboAscensor.add(lineaPiso3); // añade la línea al tubo

        javax.swing.JPanel lineaPiso2 = new javax.swing.JPanel(); // panel que dibuja la línea del piso 2
        lineaPiso2.setBackground(new java.awt.Color(100, 100, 100)); // color gris para la línea
        lineaPiso2.setBounds(10, Y_PISO_2 + CABINA_HEIGHT - 5, 170, 3); // posiciona la línea del piso 2
        tuboAscensor.add(lineaPiso2); // añade la línea al tubo

        javax.swing.JPanel lineaPiso1 = new javax.swing.JPanel(); // panel que dibuja la línea del piso 1
        lineaPiso1.setBackground(new java.awt.Color(100, 100, 100)); // color gris para la línea
        lineaPiso1.setBounds(10, Y_PISO_1 + CABINA_HEIGHT - 5, 170, 3); // posiciona la línea del piso 1
        tuboAscensor.add(lineaPiso1); // añade la línea al tubo

        tuboAscensor.setBounds(60, 30, 190, 520); // dimensiona y posiciona el panel del tubo en el panel principal
        panelPrincipal.add(tuboAscensor); // añade el tubo al panel principal

        // estilo común para los botones (cuadrados y más profesionales)
        java.awt.Font botonFuente = new Font("Times New Roman", Font.BOLD, 24); // fuente común para botones
        javax.swing.border.Border botonBorder = javax.swing.BorderFactory.createLineBorder(new java.awt.Color(120, 120, 120), 2); // borde común para botones

        btnPiso3.setText("3"); // texto del botón piso 3
        btnPiso3.setFont(botonFuente); // asigna la fuente al botón
        btnPiso3.setFocusPainted(false); // elimina el borde de foco pintado
        btnPiso3.setBackground(new java.awt.Color(245, 245, 245)); // color de fondo del botón
        btnPiso3.setForeground(java.awt.Color.black); // color del texto
        btnPiso3.setOpaque(true); // hace el fondo opaco
        btnPiso3.setBorder(botonBorder); // aplica el borde personalizado
        btnPiso3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // cursor en forma de mano
        btnPiso3.addActionListener(this::btnPiso3ActionPerformed); // asigna listener método referencia para acción
        btnPiso3.setBounds(390, 130, 80, 80); // posiciona el botón 3 en el panel principal
        panelPrincipal.add(btnPiso3); // añade el botón 3 al panel principal

        btnPiso2.setText("2"); // texto del botón piso 2
        btnPiso2.setFont(botonFuente); // asigna la fuente al botón
        btnPiso2.setFocusPainted(false); // elimina el borde de foco pintado
        btnPiso2.setBackground(new java.awt.Color(245, 245, 245)); // color de fondo del botón
        btnPiso2.setForeground(java.awt.Color.black); // color del texto
        btnPiso2.setOpaque(true); // hace el fondo opaco
        btnPiso2.setBorder(botonBorder); // aplica el borde personalizado
        btnPiso2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // cursor en forma de mano
        btnPiso2.addActionListener(this::btnPiso2ActionPerformed); // asigna listener para acción del botón 2
        btnPiso2.setBounds(390, 260, 80, 80); // posiciona el botón 2
        panelPrincipal.add(btnPiso2); // añade el botón 2 al panel principal

        btnPiso1.setText("1"); // texto del botón piso 1
        btnPiso1.setFont(botonFuente); // asigna la fuente al botón
        btnPiso1.setFocusPainted(false); // elimina el borde de foco pintado
        btnPiso1.setBackground(new java.awt.Color(245, 245, 245)); // color de fondo del botón
        btnPiso1.setForeground(java.awt.Color.black); // color del texto
        btnPiso1.setOpaque(true); // hace el fondo opaco
        btnPiso1.setBorder(botonBorder); // aplica el borde personalizado
        btnPiso1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // cursor en forma de mano
        btnPiso1.addActionListener(this::btnPiso1ActionPerformed); // asigna listener para acción del botón 1
        btnPiso1.setBounds(390, 390, 80, 80); // posiciona el botón 1
        panelPrincipal.add(btnPiso1); // añade el botón 1 al panel principal


        int buttonsCenterX = 390 + 80 / 2; // calcula el centro X de la columna de botones
        int displayX = buttonsCenterX - 60 / 2; // calcula la X para centrar el display sobre los botones
        int displayY = 40; // Y del display (más arriba)
        lblDisplay.setBounds(displayX, displayY, 60, 40); // posiciona y dimensiona el display
        lblDisplay.setFont(new Font("Times New Roman", Font.PLAIN, 20)); // fuente del display
        lblDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); // centra el texto horizontalmente
        lblDisplay.setForeground(new java.awt.Color(255, 0, 0)); // color rojo para el texto del display
        lblDisplay.setOpaque(true); // fondo opaco para el display
        lblDisplay.setBackground(java.awt.Color.black); // fondo negro para resaltar el número
        lblDisplay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(80, 80, 80), 2)); // borde para el display
        panelPrincipal.add(lblDisplay); // añade el display al panel principal

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane()); // crea layout para el content pane
        getContentPane().setLayout(layout); // asigna layout al content pane
        layout.setHorizontalGroup( // define el grupo horizontal del layout principal
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE))
        );
        layout.setVerticalGroup( // define el grupo vertical del layout principal
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack(); // ajusta la ventana al tamaño preferido de los componentes
        setLocationRelativeTo(null); // centra la ventana en la pantalla
    } // </editor-fold>

    private void btnPiso3ActionPerformed(java.awt.event.ActionEvent evt) { // handler cuando se pulsa botón piso 3
        solicitarPiso(3); // solicita ir al piso 3
    }

    private void btnPiso1ActionPerformed(java.awt.event.ActionEvent evt) { // handler cuando se pulsa botón piso 1
        solicitarPiso(1); // solicita ir al piso 1
    }

    private void btnPiso2ActionPerformed(java.awt.event.ActionEvent evt) { // handler cuando se pulsa botón piso 2
        solicitarPiso(2); // solicita ir al piso 2
    }

    private void iniciarLogica() { // configura timers y comportamiento de puertas/animación
        timerAnimacion = new Timer(15, (ActionEvent e) -> moverAscensor()); // timer para movimiento vertical (cada 15 ms)

        // Timer para animar puertas
        timerPuertas = new Timer(15, (ActionEvent e) -> { // timer que controla apertura/cierre de puertas
            if (doorAction == DoorAction.CLOSING) { // si la acción es cerrar puertas
                // acercar puertas al centro (posición cerrada: leftX=0, rightX=DOOR_HALF_WIDTH)
                int lx = leftDoor.getX(); // posición X actual de la puerta izquierda
                int rx = rightDoor.getX(); // posición X actual de la puerta derecha
                boolean leftDone = false, rightDone = false; // flags para saber si terminaron de moverse

                if (lx < 0) { // si la izquierda está fuera (abierta hacia la izquierda)
                    lx += DOOR_SPEED; // mover hacia la derecha
                    if (lx > 0) lx = 0; // no sobrepasar la posición cerrada
                    leftDoor.setLocation(lx, 0); // aplicar nueva posición
                } else leftDone = true; // ya está en posición cerrada

                if (rx > DOOR_HALF_WIDTH) { // si la derecha está fuera (abierta hacia la derecha)
                    rx -= DOOR_SPEED; // mover hacia la izquierda
                    if (rx < DOOR_HALF_WIDTH) rx = DOOR_HALF_WIDTH; // no sobrepasar la posición cerrada
                    rightDoor.setLocation(rx, 0); // aplicar nueva posición
                } else rightDone = true; // ya está en posición cerrada

                cabina.repaint(); // repinta la cabina para reflejar cambios

                if (leftDone && rightDone) { // si ambas puertas terminaron de cerrarse
                    timerPuertas.stop(); // detener animación de puertas
                    puertasAbiertas = false; // actualizar estado
                    doorAction = DoorAction.IDLE; // estado idle
                    // cuando puertas cerradas, iniciar movimiento
                    timerAnimacion.start(); // arrancar movimiento de la cabina
                }
            } else if (doorAction == DoorAction.OPENING) { // si la acción es abrir puertas
                // separar puertas hacia fuera (abiertas: leftX = -DOOR_HALF_WIDTH, rightX = CABINA_WIDTH)
                int lx = leftDoor.getX(); // posición X actual de la puerta izquierda
                int rx = rightDoor.getX(); // posición X actual de la puerta derecha
                boolean leftDone = false, rightDone = false; // flags para fin de movimiento

                if (lx > -DOOR_HALF_WIDTH) { // si la izquierda no está aún completamente abierta
                    lx -= DOOR_SPEED; // mover hacia la izquierda
                    if (lx < -DOOR_HALF_WIDTH) lx = -DOOR_HALF_WIDTH; // no sobrepasar límite
                    leftDoor.setLocation(lx, 0); // aplicar nueva posición
                } else leftDone = true; // izquierda ya abierta

                if (rx < CABINA_WIDTH) { // si la derecha no está aún completamente abierta
                    rx += DOOR_SPEED; // mover hacia la derecha
                    if (rx > CABINA_WIDTH) rx = CABINA_WIDTH; // no sobrepasar límite
                    rightDoor.setLocation(rx, 0); // aplicar nueva posición
                } else rightDone = true; // derecha ya abierta

                cabina.repaint(); // repinta la cabina

                if (leftDone && rightDone) { // si ambas puertas terminaron de abrirse
                    timerPuertas.stop(); // detener animación de puertas
                    puertasAbiertas = true; // actualizar estado
                    doorAction = DoorAction.IDLE; // volver a idle
                    // una vez abiertas, habilitar botones
                    habilitarBotones(true); // reactivar botones de piso
                }
            }
        });
    }

    private void solicitarPiso(int pisoSeleccionado) { // lógica al solicitar un piso mediante botones
        if (pisoSeleccionado == pisoActual) return; // si ya estamos en ese piso no hacer nada
        pisoDestino = pisoSeleccionado; // guarda el destino solicitado
        lblDisplay.setText(String.valueOf(pisoDestino)); // muestra destino en el display
        habilitarBotones(false); // deshabilita botones mientras se realiza la operación
        // cerrar puertas antes de mover
        if (puertasAbiertas) { // si puertas están abiertas, iniciar cierre
            doorAction = DoorAction.CLOSING; // establecer acción de puertas a cerrar
            timerPuertas.start(); // arrancar timer de puertas (cuando termine arrancará el movimiento)
        } else { // si ya estaban cerradas
            // si ya estaban cerradas, iniciar movimiento directamente
            timerAnimacion.start(); // iniciar movimiento de la cabina
        }
    }

    private void moverAscensor() { // método llamado por timerAnimacion para mover la cabina
        int yActual = cabina.getY(); // obtiene la Y actual de la cabina
        int yObjetivo = obtenerYObjetivo(pisoDestino); // calcula la Y objetivo según piso destino
        int velocidad = 3; // velocidad de movimiento vertical en píxeles por tick

        if (yActual < yObjetivo) { // Bajar (Y aumenta)
            yActual += velocidad; // incrementar Y para bajar
            if (yActual >= yObjetivo) finalizarMovimiento(yObjetivo); // si llega o supera, finalizar movimiento
            else cabina.setLocation(cabina.getX(), yActual); // si no, actualizar posición intermedia
        } else if (yActual > yObjetivo) { // Subir (Y disminuye)
            yActual -= velocidad; // decrementar Y para subir
            if (yActual <= yObjetivo) finalizarMovimiento(yObjetivo); // si llega o pasa, finalizar
            else cabina.setLocation(cabina.getX(), yActual); // actualizar posición intermedia
        }
    }

    private void finalizarMovimiento(int yFinal) { // acciones al completar movimiento hasta yFinal
        cabina.setLocation(cabina.getX(), yFinal); // asegurar posición final exacta
        timerAnimacion.stop(); // detener timer de animación
        pisoActual = pisoDestino; // actualizar piso actual
        lblDisplay.setText(String.valueOf(pisoActual)); // mostrar piso actual en el display
        // abrir puertas al llegar
        doorAction = DoorAction.OPENING; // indicar que las puertas deben abrir
        timerPuertas.start(); // arrancar la animación de puertas
    }

    private int obtenerYObjetivo(int piso) { // devuelve la coordenada Y objetivo según el número de piso
        switch (piso) {
            case 3: return Y_PISO_3; // piso 3
            case 2: return Y_PISO_2; // piso 2
            case 1: return Y_PISO_1; // piso 1
            default: return Y_PISO_1; // por defecto piso 1
        }
    }

    private void habilitarBotones(boolean habilitar) { // habilita o deshabilita los botones de piso
        btnPiso1.setEnabled(habilitar); // habilita/deshabilita botón 1
        btnPiso2.setEnabled(habilitar); // habilita/deshabilita botón 2
        btnPiso3.setEnabled(habilitar); // habilita/deshabilita botón 3
    }

    public static void main(String args[]) { // método main de la aplicación
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) { // intenta configurar "Nimbus"
                if ("Nimbus".equals(info.getName())) { // busca la apariencia Nimbus
                    javax.swing.UIManager.setLookAndFeel(info.getClassName()); // aplica Nimbus si está disponible
                    break; // sale del bucle tras aplicar
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) { // captura errores de LAF
            logger.log(java.util.logging.Level.SEVERE, null, ex); // registra el error
        }

        // Mostrar ventana de bienvenida primero
        EventQueue.invokeLater(() -> { // asegura ejecución en el hilo de eventos Swing
            VentanaBienvenida vb = new VentanaBienvenida(); // crea instancia de la ventana de bienvenida
            vb.setVisible(true); // muestra la ventana de bienvenida
        });
    }

    // Ventana de bienvenida (clase estática dentro del mismo archivo)
    private static class VentanaBienvenida extends javax.swing.JFrame { // clase interna para la pantalla inicial
        VentanaBienvenida() { // constructor de la ventana de bienvenida
            setTitle("Bienvenida"); // título de la ventana
            setSize(420, 220); // tamaño de la ventana de bienvenida
            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); // cerrar app si se cierra esta ventana
            setLayout(null); // layout absoluto para posicionar componentes manualmente

            JLabel lbl = new JLabel("Bienvenido al simulador de ascensor", javax.swing.SwingConstants.CENTER); // etiqueta de texto centrada
            lbl.setBounds(20, 30, 380, 30); // posición y tamaño de la etiqueta
            lbl.setFont(new Font("Times New Roman", Font.PLAIN, 18)); // fuente de la etiqueta
            add(lbl); // añade la etiqueta a la ventana

            JButton btnIniciar = new JButton("iniciar"); // botón para iniciar el simulador
            btnIniciar.setBounds(160, 110, 100, 40); // posiciona el botón iniciar
            btnIniciar.addActionListener(e -> { // acción al pulsar iniciar
                // abrir simulador y cerrar esta ventana
                SimuladorAscensor1 sim = new SimuladorAscensor1(); // crea instancia del simulador
                sim.setVisible(true); // muestra la ventana del simulador
                dispose(); // cierra la ventana de bienvenida
            });
            add(btnIniciar); // añade el botón iniciar a la ventana

            setLocationRelativeTo(null); // centra la ventana de bienvenida en pantalla
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnPiso1; // botón piso 1 declarado para uso en la clase
    private javax.swing.JButton btnPiso2; // botón piso 2 declarado para uso en la clase
    private javax.swing.JButton btnPiso3; // botón piso 3 declarado para uso en la clase
    private javax.swing.JPanel cabina; // panel de la cabina declarado
    private javax.swing.JPanel cable1; // panel del cable1 declarado
    private javax.swing.JPanel cable2; // panel del cable2 declarado
    private javax.swing.JPanel panelPrincipal; // panel principal declarado
    private javax.swing.JPanel tuboAscensor; // panel del tubo del ascensor declarado
    // End of variables declaration
}
