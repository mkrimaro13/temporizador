import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class interfaz extends JFrame implements ActionListener {
    int ss=0,mm=0,hh=0;
    boolean est = true;
    JPanel panel= new JPanel();
    private JTextField textField;
    private JButton start, pause, stop, more;
    private JLabel hhmmss, msl;
    private ImageIcon icn=new ImageIcon("src/images/logo.jpeg");
    public interfaz() {
        //Propiedades del Layout
        setLayout(new FlowLayout(1,1,1));
        setBounds(0,0,550,70);
        setTitle("Cronometro");
        setResizable(false);
        setUndecorated(false);
        setAlwaysOnTop(true);
        setIconImage(icn.getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Inicio del texto del cronometro
        hhmmss=new JLabel("00:00:00:");
        add(hhmmss);

        //Cuadro de texto para ingresar el número o solicitud
        textField=new JTextField("Número Caso", 10);
        add(textField);

        //Creación de botones
        start=new JButton("Iniciar");
        add(start);
        start.addActionListener(this);

        pause=new JButton("Pausar");
        add(pause);
        pause.addActionListener(this);

        stop=new JButton("Detener");
        add(stop);
        stop.addActionListener(this);

        more=new JButton("Anadir");
        add(more);
        more.addActionListener(this);

        // Habilita para mostrar todo el JFrame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == start) {
                est=true;
                String caso=textField.getText();
                System.out.printf(caso);
                Thread h = new Thread(){
                  public void run(){
                      for(;;){
                          if(est==true){
                              try{
                                  sleep(1);
                                  if(ss>=60){
                                     ss=0;
                                     mm++;
                                  }else if(mm>=0){
                                      ss=0;
                                      mm=0;
                                      hh++;
                                  }
                                  hhmmss.setText(hh+" : "+mm+" : "+ss+" : ");
                                  ss++;
                              }catch (Exception e){

                              }
                          }else{
                              break;
                          }
                      }
                  }
                };
                h.start();

            }if( e.getSource() == pause){//Solo pausa el contador
                est=false;

            }if( e.getSource() == stop){// Para y borra el contenido
                est=false;
                ss=0;
                mm=0;
                hh=0;
                hhmmss.setText("00:00:00");
                msl.setText("0000");

            }if(e.getSource() == more){
                new interfaz();
                setBounds(getX()+50,getY()+50,getWidth(),getHeight());
            }
    }
    public static void main(String[] args) {
        new interfaz();

    }

}