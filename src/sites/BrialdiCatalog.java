package sites;

import java.io.File;
import java.io.IOException;
import java.sql.Array;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sites.Brialdi;
/**
 * Класс описывает парсер страницы каталога
 */
public class BrialdiCatalog extends Brialdi{

    protected Elements Products;
    public BrialdiCatalog(String url){
        this.host = url;
        this.load();
    }


    protected Elements findProducts(){
        this.Products = this.content.getElementsByClass("catalog-section-item");
        return this.Products;
    }

    public Elements getProducts(){
        return this.findProducts();
    }

    public String getProductLink(Element product){
        return product.select("div.section-item-name a").attr("href");
    }

    public String getNextPageLink(){
        Elements nextLink = this.content.getElementsByClass("pager_right");
        return nextLink.select("a:not(.p_last)").attr("href");
    }
}
