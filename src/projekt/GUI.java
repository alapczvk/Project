package projekt;

import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GUI{ //ta klasa dziedzicyz z klasy table info zeby miec dostep do funkcji tabelki wyswietlaacej
    private static Logger logger= Logger.getLogger(GUI.class); //w kazdej klasie tak robie zeby zbierac logi
    //private static JButton USD, EUR, AUD, CAD, GBP, CHF, SEK, DKK, BGN, NOK, RON, CZK, UAH, HUF,JPY,TRY,ILS,CNY,AED,RUB;
    private static JComboBox ccodes;
    private static JPanel jp;
    private static JFrame jf;
    protected static String[] currenciesCodes ={"USD", "EUR", "AUD", "CAD", "GBP", "CHF", "SEK", "DKK", "BGN", "NOK", "RON", "CZK", "UAH", "HUF","JPY","TRY","ILS","CNY","AED","RUB"};
    public static void createAndShowGUI() {
        jf= new JFrame("Virtual Exchange");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setPreferredSize(new Dimension(400,110));
        jp = new JPanel();//new BorderLayout()
        jp.setLayout(new BorderLayout());
        jp.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel l= new JLabel( "Choose currency");
        l.setHorizontalAlignment(JLabel.CENTER);
        l.setMaximumSize(new Dimension(400,40));
        l.setFont(new Font("Arial",Font.ROMAN_BASELINE,25));
        //tworzenie przycisków dla kazdej waluty do ktorej mozemy przekonwertowac z PLN
        ccodes= new JComboBox(currenciesCodes);
        ccodes.setSelectedItem(null);
        Dimension dimension=new Dimension(350,30);
        ccodes.setPreferredSize(dimension);
        ccodes.setMaximumSize(dimension);
        ccodes.setEditable(false);

       /* USD = new JButton("USD");
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
        jp.add(HUF);*/
        jp.add(l,BorderLayout.PAGE_START); //page start
        jf.add(jp);
        jp.add(ccodes,BorderLayout.LINE_START); //line start

        jf.getContentPane().add(jp);
        jf.pack();
       ccodes.addActionListener((e)->{
                                        String click= (String) ccodes.getSelectedItem();
                                        logger.info("Currency "+click+" chosen");
                                        currencyChosen(click);

                                        });
       ccodes.getSelectedItem();

    }
    protected static void buttonClickedListener(ActionEvent e) {
        //System.out.println("Button = "+e.getActionCommand());
        }

        protected static void currencyChosen(String curr){
            String[] columnNames = { "LP", "KANTOR","KUPNO","SPRZEDAŻ" };
            String data[][] = {{"100","Vinod","programmer","5000"},
                    {"101","Deepak","Content Writer","20000"},
                    {"102","Noor","Techniqual Writer","30000"},
                    {"104","Rinku","PHP programar","25000"}};
            DefaultTableModel model = new DefaultTableModel(data,columnNames);
            JTable jtable=new JTable(model);
            jtable.setAutoCreateRowSorter(true);
            jp.add(new JScrollPane(jtable),BorderLayout.PAGE_END); //page end
            jf.add(jp);
            jf.setSize(400,535);
            jf.setMaximumSize(new Dimension(400,535));
            jf.setPreferredSize(new Dimension(400,535));
            jf.setVisible(true);

        }
}

