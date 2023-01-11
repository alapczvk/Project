package projekt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TableInfo extends GUI{
    private static Logger logger= Logger.getLogger(TableInfo.class); //w kazdej klasie tak robie zeby zbierac logi
    private static int currlen=0;
    public static ArrayList<Currency> curr = new ArrayList<Currency>();
    protected static Document getURL(String url) {
        Connection connection = Jsoup.connect(url);
        logger.info("connecting with "+url);
        Document document=null;
        try{
            document = connection.get();
            logger.info("connected with "+url);
        }catch (java.io.IOException e){
            logger.warn(e.getMessage());
        }
        return document;
    }
    private static ArrayList<String> getExchanges(){
        BufferedReader reader = null;
        ArrayList<String> exOffices= new ArrayList<String>();
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Ola\\Desktop\\STUDIA\\JAVA\\projekt\\src\\projekt\\exchangeoffices.txt"));
            String line="";
            while((line = reader.readLine()) != null) {
            exOffices.add(line);
            }
           logger.info("Reading txt file");
        }
        catch (IOException e) {
           logger.error(e.getMessage());
            System.exit(1);
        }

        return exOffices;
    }
    private static void toThreads(ArrayList<String> exchanges){
        for(String exchange: exchanges){
            Document html= getURL(exchange);
            Elements records = html.select("#detal > div.table-responsive.table-responsive-md.p-2 > table > tbody > tr"); // a with href
            boolean f=true;

            for(Element record: records){
                String currencyCode=record.select("td:nth-child(1) > span").text().substring(2); //kod
                Double buyFor= Double.parseDouble(record.select("td:nth-child(3) > div > span.h5.fw-normal.mb-0.me-1").text()); //kupno
                Double sellFor= Double.parseDouble(record.select("td:nth-child(4) > div > span.h5.fw-normal.mb-0.me-1").text());//sprzedaz
                String exName=record.selectXpath("/html/body/main/section[1]/div/h1/span[2]").text(); //nazwa kantoru
                String address=record.select("#main > section.my-2.mt-4.kantor-section > div > div > div > div.col-12.col-lg-4.p-0.pe-md-2 > div.card.shadow.mt-0.mt-md-3 > div > div.media.my-4.d-flex.align-items-start > div > a > h2").text();
                //#main > section.my-2.mt-4.kantor-section > div > div > div > div.col-12.col-lg-4.p-0.pe-md-2 > div.card.shadow.mt-0.mt-md-3 > div > div.media.my-4.d-flex.align-items-start > div > a > h2
                if(Arrays.asList(currenciesCodes).contains(currencyCode)){
                    Currency currency=new Currency(currencyCode,buyFor,sellFor,exName,address);
                    curr.add(currency);
                }
                if (f){
                    logger.info("Data from "+exName+" "+address+" has been downloaded.");
                    f=false;
                }

            }

        }
    }
    public static void main(String[] args){
        ArrayList<String> exchanges=getExchanges();

         Thread t1=new Thread(()->{
                toThreads(new ArrayList<String> (exchanges.subList(0,5)));

            });

        Thread t2=new Thread(()->{
            toThreads(new ArrayList<String> (exchanges.subList(5,10)));

             });

        Thread t3=new Thread(()->{
            toThreads(new ArrayList<String>( exchanges.subList(10,15)));
            currlen+=5;
            });

        Thread t4=new Thread(()->{
            toThreads(new ArrayList<String> ( exchanges.subList(15,20)));

            });

        Thread t5=new Thread(()->{
            toThreads(new ArrayList<String> (exchanges.subList(20,25)));

             });

        Thread t6=new Thread(()->{
            toThreads(new ArrayList<String> (exchanges.subList(25,30)));

        });

        Thread t7=new Thread(()->{
            toThreads(new ArrayList<String> (exchanges.subList(30,35)));

        });

        Thread t8=new Thread(()->{
            toThreads(new ArrayList<String> (exchanges.subList(35,38)));
        });
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        for (Thread thread : Arrays.asList(t1,t2,t3,t4,t5,t6,t7,t8)) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        logger.info("Everything Downloaded");

    }
}
