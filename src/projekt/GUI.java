package projekt;

import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import static projekt.TableInfo.curr;
import static projekt.TableInfo.getData;

public class GUI{ //ta klasa dziedzicyz z klasy table info zeby miec dostep do funkcji tabelki wyswietlaacej
    private static String currCurrency=null;
    private static boolean firstTable=true;
    private static JTable jtable=new JTable();
    private static Logger logger= Logger.getLogger(GUI.class); //w kazdej klasie tak robie zeby zbierac logi
    //private static JButton USD, EUR, AUD, CAD, GBP, CHF, SEK, DKK, BGN, NOK, RON, CZK, UAH, HUF,JPY,TRY,ILS,CNY,AED,RUB;
    private static JComboBox ccodes;
    private static JPanel jp;
    private static JFrame jf;
    protected static String[] currenciesCodes ={"USD", "EUR", "AUD", "CAD", "GBP", "CHF", "SEK", "DKK", "BGN", "NOK", "RON", "CZK", "UAH", "HUF","JPY","TRY","ILS","CNY","AED","RUB"};
    public static void createAndShowGUI() {
        jf= new JFrame("Virtual Exchange");
        new Thread(()->{getData();
                        jf.setVisible(true);}
        ).start();//rozpoczecie scrapowania strony
        //getData();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setPreferredSize(new Dimension(500,110));
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
        Dimension dimension=new Dimension(465,30);
        ccodes.setPreferredSize(dimension);
        ccodes.setMaximumSize(dimension);
        ccodes.setEditable(false);
        jp.add(l,BorderLayout.PAGE_START); //page start
        jf.add(jp);
        jp.add(ccodes,BorderLayout.LINE_START); //line start

        jf.getContentPane().add(jp);
        jf.pack();
       ccodes.addActionListener((e)->{
                                        String click= (String) ccodes.getSelectedItem();
                                        if(click==currCurrency){
                                            return;
                                        }
                                        currCurrency=click;
                                        logger.info("Currency "+click+" chosen");
                                        currencyChosen(click);
                                        });
       ccodes.getSelectedItem();
    }
    protected static void buttonClickedListener(ActionEvent e) {
        //System.out.println("Button = "+e.getActionCommand());
        }

        protected static void currencyChosen(String chosenCurr){
            String[] columnNames = { "LP", "KANTOR","KUPNO","SPRZEDAŻ" };
            DefaultTableModel model = (DefaultTableModel) jtable.getModel();
            jtable.setEnabled(false);

            model.addTableModelListener(jtable);
            List<Currency> filterCurrency=(curr.stream().filter(currency->currency.getCode().equals(chosenCurr)).collect(Collectors.toList())); //araylista chosen currencies
            model.setColumnIdentifiers(columnNames);
            Vector data = model.getDataVector();
            data.clear();
            int ident=1;
            for(Currency c:filterCurrency){
                Object[] o=new Object[4];
                o[0]=ident;
                ident++;
                o[1]=c.getExName()+" "+c.getAddress();
                o[2]=String.format("%.3f", c.getBuyFor());
                o[3]= String.format("%.3f", c.getSellFor());
                model.addRow(o);
            }
            if(firstTable){
                jp.add(new JScrollPane(jtable),BorderLayout.PAGE_END); //page end
            }
            firstTable=false;
            TableRowSorter trs = new TableRowSorter(model);

            class IntComparator implements Comparator {
                public int compare(Object o1, Object o2) {
                    Integer int1 = (Integer)o1;
                    Integer int2 = (Integer)o2;
                    return int1.compareTo(int2);
                }

                public boolean equals(Object o2) {
                    return this.equals(o2);
                }
            }
            trs.setComparator(0, new IntComparator());
            jtable.setRowSorter(trs);
            jtable.setAutoCreateRowSorter(false);
            jtable.getTableHeader().setReorderingAllowed(false);
            jtable.repaint();
            jtable.revalidate();
//            jtable.setAutoCreateRowSorter(true);
            jtable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
            TableColumnModel colModel=jtable.getColumnModel();
            colModel.getColumn(0).setPreferredWidth(10);
            colModel.getColumn(1).setPreferredWidth(235);
            colModel.getColumn(2).setPreferredWidth(40);
            colModel.getColumn(3).setPreferredWidth(40);
            jf.setSize(500,535);
            jf.setMaximumSize(new Dimension(500,535));
            jf.setPreferredSize(new Dimension(500,535));
        }
}

