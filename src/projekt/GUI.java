package projekt;

import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class GUI{ //ta klasa dziedzicyz z klasy table info zeby miec dostep do funkcji tabelki wyswietlaacej
    private static Logger logger= Logger.getLogger(GUI.class); //w kazdej klasie tak robie zeby zbierac logi
    private static JButton USD, EUR, AUD, CAD, GBP, CHF, SEK, DKK, BGN, NOK, RON, CZK, UAH, HUF,JPY,TRY,ILS,CNY,AED,RUB;
    protected static String[] currenciesCodes ={"USD", "EUR", "AUD", "CAD", "GBP", "CHF", "SEK", "DKK", "BGN", "NOK", "RON", "CZK", "UAH", "HUF","JPY","TRY","ILS","CNY","AED","RUB"};
    public static void createAndShowGUI() {
        JFrame jf= new JFrame("Virtual Exchange");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setPreferredSize(new Dimension(600,800));
        JPanel jp = new JPanel();
        jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
        jp.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel l= new JLabel( "Choose currency");
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setMaximumSize(new Dimension(500,40));
        l.setFont(new Font("Arial",Font.ROMAN_BASELINE,25));
        jp.add(l);
        //tworzenie przycisków dla kazdej waluty do ktorej mozemy przekonwertowac z PLN
        USD = new JButton("USD");
        USD.setToolTipText("PLN to US dollar");
        EUR = new JButton("EUR");
        EUR.setToolTipText("PLN to Euro");
        AUD= new JButton("AUD");
        AUD.setToolTipText("PLN to Australian Dollar");
        CAD = new JButton("CAD");
        CAD.setToolTipText("PLN to Canadian Dollar");
        GBP = new JButton("GBP");
        GBP.setToolTipText("PLN to British pound");
        CHF = new JButton("CHF");
        CHF.setToolTipText("PLN to Swiss franc");
        SEK = new JButton("SEK");
        SEK.setToolTipText("PLN to Swedish krona");
        DKK = new JButton("DKK");
        DKK.setToolTipText("PLN to Danish krone");
        BGN = new JButton("BGN");
        BGN.setToolTipText("PLN to Bulgarian lev");
        NOK = new JButton("NOK");
        NOK.setToolTipText("PLN to Norwegian krone");
        RON = new JButton("RON");
        RON.setToolTipText("PLN to Romanian leu");
        CZK = new JButton("CZK");
        CZK.setToolTipText("PLN to Czech koruna");
        UAH = new JButton("UAH");
        UAH.setToolTipText("PLN to Ukrainian hryvnia");
        HUF = new JButton("HUF");
        HUF.setToolTipText("PLN to Hungarian forint");
        RUB=new JButton("RUB");
        RUB.setToolTipText("PLN to Russian rouble");
        AED=new JButton("AED");
        AED.setToolTipText("PLN to United Arab Emirates dirham");
        CNY=new JButton("CNY");
        CNY.setToolTipText("PLN to Renminbi");
        ILS=new JButton("ILS");
        ILS.setToolTipText("PLN to Israeli new shekel");
        TRY=new JButton("TRY");
        TRY.setToolTipText("PLN to Turkish lira");
        JPY= new JButton("JPY");
        JPY.setToolTipText("PLN to Japanese yen");
        for (JButton b : Arrays.asList(USD, EUR, AUD, CAD, GBP, CHF, SEK, DKK, BGN, NOK, RON, CZK, UAH, HUF,JPY,TRY,ILS,CNY,AED,RUB)) {
            Dimension dimension = new Dimension(570, 40);
            b.setPreferredSize(dimension);
            b.setMaximumSize(dimension);
            b.setHorizontalAlignment(JButton.CENTER);
            b.setLayout(new BoxLayout(b,BoxLayout.X_AXIS));
            jp.add(b);
            b.addActionListener(GUI::mainactionPerformed);
        }
        jp.add(HUF);
        jf.add(jp);
        jf.getContentPane().add(jp);
        jf.pack();


    }
    protected static void mainactionPerformed(ActionEvent e) {
        String click=e.getActionCommand();
        //System.out.println("Button = "+e.getActionCommand());
        logger.info("Button "+click+" chosen");
        currencyChosenEvent(click);

        }
        protected static void currencyChosenEvent(String curr){
            JFrame jframe=new JFrame(curr);
            jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jframe.setVisible(true);
            jframe.setResizable(false);
            jframe.setLocationRelativeTo(null);
            jframe.setPreferredSize(new Dimension(500,800));
            jframe.setSize(500,800);
            jframe.setMaximumSize(new Dimension(500,800));
            String[] columnNames = { "Lp", "kantor","kupno","sprzedaż" };
            //JTable jt= new JTable(data,columnNames);
            JPanel jp = new JPanel();
            jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
            jp.setBorder(new EmptyBorder(10, 10, 10, 10));
            jframe.add(jp);

        }
}

