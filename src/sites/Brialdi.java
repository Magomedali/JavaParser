package sites;

import java.io.File;
import java.io.IOException;
import java.sql.Array;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.company.Page;
/**
 * Created by Magomedali on 08.10.2016.
 */
public class Brialdi extends Page{

    protected Elements navigation_site;

    public  Brialdi(){
        super("http://www.brialdi.ru/");
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
        }
    }
}
