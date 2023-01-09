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

    public static void main(String[] args){
        int id=1;
        ArrayList<String> exchanges=getExchanges();
        ArrayList<Currency> curr = new ArrayList<Currency>();
        for(String exchange: exchanges){
            Document html= getURL(exchange);
            Elements records = html.select("#detal > div.table-responsive.table-responsive-md.p-2 > table > tbody > tr"); // a with href
            //logger.info(records);
            boolean f=true;

            for(Element record: records){
                //System.out.println(record);
                String currencyCode=record.select("td:nth-child(1) > span").text().substring(2); //kod
                Double buyFor= Double.parseDouble(record.select("td:nth-child(3) > div > span.h5.fw-normal.mb-0.me-1").text()); //kupno
                Double sellFor= Double.parseDouble(record.select("td:nth-child(4) > div > span.h5.fw-normal.mb-0.me-1").text());//sprzedaz
                String exName=record.selectXpath("/html/body/main/section[1]/div/h1/span[2]").text(); //nazwa kantoru
                if(Arrays.asList(currenciesCodes).contains(currencyCode)){
                    Currency currency=new Currency(id,currencyCode,buyFor,sellFor,exName);
                    curr.add(currency);
                }
                if (f){
                    logger.info("Data from "+exName+" "+id+" has been downloaded.");
                    f=false;
                }

                ///html/body/main/section[1]/div/h1/span[2]
            }

        id++;
        }

        //logger.info(getURL("https://kantor.live/kantory/krakow/603758-kantor-1913"));
    }
}
