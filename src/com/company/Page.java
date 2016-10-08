package com.company;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import com.company.Page;
/**
 * Created by Magomedali on 08.10.2016.
 */
public abstract class Page{
    protected String host;
    protected Document content;

    public Page(String host){
        this.host = host;
    }

    public Document load(){
        try {
            this.content = Jsoup.connect(this.host).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.content;
    }

    public Document getContent(){
        return this.content;
    }
}
