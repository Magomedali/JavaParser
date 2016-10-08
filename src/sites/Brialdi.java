package sites;

import java.io.File;
import java.io.IOException;
import java.sql.Array;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.company.Page;
import sites.BrialdiCatalog;
/**
 * Created by Magomedali on 08.10.2016.
 */
public class Brialdi extends Page{

    protected Elements navigation_site;

    public  Brialdi(){
        super("http://www.brialdi.ru");
    }

    //Возвращаем навигацию ссылки навигации
    public Elements getNavigation_site(){
        return this.navigation_site;
    }

    //Находим основную навигаию на сайте
    protected Elements findNavigation(){
        Elements li = this.content.getElementsByClass("top-menu").select("li.parent > a");
        return li;
    }

    public void start(){
        //Загружаем страницу
        this.load();
        //Получаем ссылки основной навигации
        this.navigation_site = this.findNavigation();
        //обходим в цикле все ссылки
        for(Element a : this.navigation_site){
            String suburl = a.attr("href");
            suburl = this.host.concat(suburl);
            //Загружаем каталог по ссылке из навигации
            BrialdiCatalog catalog = new BrialdiCatalog(suburl);
            this.ParseCatalogDo(catalog);


            break;
        }
    }

    public void ParseCatalogDo(BrialdiCatalog catalog){
        //Получаем товары из каталога
        Elements products = catalog.getProducts();
        for(Element product : products){
            String productLink = catalog.getProductLink(product);
            productLink = this.host.concat(productLink);
            System.out.println(productLink);
            //break;
        }
        String nextPageCatalog = catalog.getNextPageLink();
        if(nextPageCatalog !=""){
            nextPageCatalog = this.host.concat(nextPageCatalog);
            System.out.println("Next page: "+nextPageCatalog);
            System.out.println("-------------");
            System.out.println("-------------");
            catalog = new BrialdiCatalog(nextPageCatalog);
            ParseCatalogDo(catalog);
        }
    }
}
