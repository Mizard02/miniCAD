package is;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.StringReader;
import java.util.Scanner;

import is.Interpreter.*;

import is.command.*;

import is.shapes.controller.*;
import is.shapes.model.*;
import is.shapes.specificcommand.*;
import is.shapes.view.*;

import javax.swing.*;

public class TestGraphicsInterpreter {

    public static void main(String[] args) {

        JFrame f = new JFrame();

        JToolBar toolbar = new JToolBar();
        JButton undoButt = new JButton("Undo");

        final HistoryCommandHandler handler = new HistoryCommandHandler();

        undoButt.addActionListener(evt -> handler.handle(HistoryCommandHandler.NonExecutableCommands.UNDO));

        toolbar.add(undoButt);

        final GraphicObjectPanel gpanel = new GraphicObjectPanel();
        gpanel.setPreferredSize(new Dimension(400, 400)); //settiamo una dimensione del pannello

        //accoppiamo gli oggetti context con gli oggetti flyweight
        gpanel.installView(RectangleObject.class, new RectangleObjectView());
        gpanel.installView(CircleObject.class, new CircleObjectView());
        gpanel.installView(ImageObject.class, new ImageObjectView());

        //creaiamo e configuriamo un button per la creazione di un rettangolo
        AbstractGraphicObject go = new RectangleObject(new Point(180, 80), 20, 50);
        JButton rectButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        rectButton.setText(go.getType());
        toolbar.add(rectButton);

        //ora di un cerchio
        go = new CircleObject(new Point2D.Double(100, 200), 10);
        JButton circButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        circButton.setText(go.getType());
        toolbar.add(circButton);

        //e infine l'immagine
        go = new ImageObject(new ImageIcon("C:\\Users\\mizar\\OneDrive\\UNI\\SECONDO ANNO\\2 SEM\\Ing Del Software\\codice sorgente esempi\\ObserverCommandFlyweigth\\src\\is\\shapes\\model\\NyaNya.gif"), new Point(240, 187));
        JButton imgButton = new JButton(new CreateObjectAction(go, gpanel, handler));
        imgButton.setText(go.getType());
        toolbar.add(imgButton);

        final GraphicObjectController goc = new GraphicObjectController(handler);

        gpanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                goc.setControlledObject(gpanel.getGraphicObjectAt(e.getPoint()));
            }
        });

        JPanel controlPanel = new JPanel(new FlowLayout());

        controlPanel.add(goc);
        f.add(toolbar, BorderLayout.NORTH);
        f.add(new JScrollPane(gpanel), BorderLayout.CENTER);
        f.setTitle("mini CAD");
        f.getContentPane().add(controlPanel, BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);


        boolean continua = true;
        Scanner sc = new Scanner(System.in);

        while(continua){
            System.out.println("scegli un opzione: \n" +
                    "1) inserisci comando \n" +
                    "2) esci \n");
            int scelta = sc.nextInt();
            switch(scelta){
                case 1:
                    System.out.print("Fornisci il comando: \n");
                    sc.nextLine();
                    String combinazione = sc.nextLine();

                    StringReader sr = new StringReader(combinazione);//Leggiamo una stringa come sequenza di caratteri

                    Parser p = new Parser(sr);

                    p.getComando().interpreta(gpanel, handler);
                    //System.out.println("l'ID dell'oggetto creato è " + id);
                    break;
                case 2:
                    continua = false;
                    break;
            }
        }
    }
}
//new circle (50.0) (100.0, 100.0)
//new rectangle (100.0, 100.0) (200.0, 200.0)
//new img ("C:\\Users\\mizar\\OneDrive\\UNI\\SECONDO ANNO\\2 SEM\\Ing Del Software\\mini CAD\\src\\is\\shapes\\model\\NyaNya.gif") (300.0, 300.0)
//mv 1 (2.0, 4.0)