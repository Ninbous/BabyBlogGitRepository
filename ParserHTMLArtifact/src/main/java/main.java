import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) throws IOException {

        String[] urls = {
                //Главная
                "http://bbnew-1096.testbb.ru/",
                //КБ
                "http://bbnew-1096.testbb.ru/cb",
                "http://bbnew-1096.testbb.ru/cb/1-trimestr",
                "http://bbnew-1096.testbb.ru/cb/1-mesyac-beremennosti",
                "http://bbnew-1096.testbb.ru/cb/1-nedelya",
                "http://bbnew-1096.testbb.ru/cb/snimki-uzi/1-nedelya",
                "http://bbnew-1096.testbb.ru/cb/foto-zhivotov/1-nedelya",
                "http://bbnew-1096.testbb.ru/cb/sovety/1-nedelya/lenta",
                "http://bbnew-1096.testbb.ru/cb/sovety/1-nedelya/lenta/2",
                "http://bbnew-1096.testbb.ru/cb/sovety/1-nedelya",
                "http://bbnew-1096.testbb.ru/cb/sovety/pdelikatnaya-problema-vnbsp1-trimestre-beremennosti-gemorrojjp",
                "http://bbnew-1096.testbb.ru/cb/sovety/pdelikatnaya-problema-vnbsp1-trimestre-beremennosti-gemorrojjp/3",
                //Каталог сообществ
                "http://bbnew-1096.testbb.ru/community",
                "http://bbnew-1096.testbb.ru/community/group/qqqqq",
                //Поиск ?q = беременность
                "http://bbnew-1096.testbb.ru/search/all/?query=%D0%91%D0%B5%D1%80%D0%B5%D0%BC%D0%B5%D0%BD%D0%BD%D0%BE%D1%81%D1%82%D1%8C",
                "http://bbnew-1096.testbb.ru/search/all/2?query=%D0%91%D0%B5%D1%80%D0%B5%D0%BC%D0%B5%D0%BD%D0%BD%D0%BE%D1%81%D1%82%D1%8C&sort=relevance",
                //Приложения
                "http://bbnew-1096.testbb.ru/apps",
                "http://bbnew-1096.testbb.ru/apps/horo",
                "http://bbnew-1096.testbb.ru/apps/horo/2",
                //Роддома
                "http://bbnew-1096.testbb.ru/roddom",
                "http://bbnew-1096.testbb.ru/roddom/country/3159",
                "http://bbnew-1096.testbb.ru/roddom/city/4400",
                "http://bbnew-1096.testbb.ru/roddom/city/4400?page=2",
                //Страница роддома
                "http://bbnew-1096.testbb.ru/roddom/info/41",
                "http://bbnew-1096.testbb.ru/roddom/info/41/services",
                "http://bbnew-1096.testbb.ru/roddom/info/41/otzyv",
                "http://bbnew-1096.testbb.ru/roddom/info/41/otzyv?page=2",
                "http://bbnew-1096.testbb.ru/roddom/info/41/discuss",
                "http://bbnew-1096.testbb.ru/roddom/info/41/list_mothers",
                "http://bbnew-1096.testbb.ru/roddom/info/41/list_mothers?page=2",
                "http://bbnew-1096.testbb.ru/roddom/info/41/photos",
                //Статьи
                "http://bbnew-1096.testbb.ru/article",
                "http://bbnew-1096.testbb.ru/article/post/pkak-podobrat-cvet-volos-pravilnop",
                "http://bbnew-1096.testbb.ru/article/post/pkak-podobrat-cvet-volos-pravilnop/2",
                //Фотоконкурсы
                "http://bbnew-1096.testbb.ru/photoconcurs",
                "http://bbnew-1096.testbb.ru/photoconcurs/past",
                "http://bbnew-1096.testbb.ru/photoconcurs/archive",
                "http://bbnew-1096.testbb.ru/photoconcurs/31",
                "http://bbnew-1096.testbb.ru/photoconcurs/31/page/2",
                "http://bbnew-1096.testbb.ru/photoconcurs/31/rules",
                //Прямая речь
                "http://bbnew-1096.testbb.ru/all",
                "http://bbnew-1096.testbb.ru/all/2",
                "http://bbnew-1096.testbb.ru/all/novice",
                "http://bbnew-1096.testbb.ru/all/novice/2",
                "http://bbnew-1096.testbb.ru/all/monthlenta/0",
                "http://bbnew-1096.testbb.ru/all/monthlenta/0/2",
                //Помощь по сайту
                "http://bbnew-1096.testbb.ru/help",
                "http://bbnew-1096.testbb.ru/help/category/1",
                "http://bbnew-1096.testbb.ru/help/category/2",
                "http://bbnew-1096.testbb.ru/help/category/5",
                "http://bbnew-1096.testbb.ru/help/category/8",
                "http://bbnew-1096.testbb.ru/help/category/31",
                "http://bbnew-1096.testbb.ru/help/category/39",
                //Поиск подруг
                "http://bbnew-1096.testbb.ru/search",
                "http://bbnew-1096.testbb.ru/search/people/2?search=1&geo=&geo_id=0&name=%D0%A1%D0%B0%D1%88%D0%B0&child_w=0&preg=0&child=0",
                //КР
                "http://bbnew-1096.testbb.ru/cr",
                "http://bbnew-1096.testbb.ru/cr/cr0",
                "http://bbnew-1096.testbb.ru/cr/sovety/cr0",
                "http://bbnew-1096.testbb.ru/cr/sovety/cr0/lenta",
                "http://bbnew-1096.testbb.ru/cr/sovety/cr0/lenta/2",
                "http://bbnew-1096.testbb.ru/cr/sovety/cr0/pkogda-malysh-dolzhen-nachat-derzhat-golovku-samostoyatelnop",
                "http://bbnew-1096.testbb.ru/cr/sovety/cr0/pkogda-malysh-dolzhen-nachat-derzhat-golovku-samostoyatelnop/3",
                "http://bbnew-1096.testbb.ru/cr/gallery/cr0",
                "http://bbnew-1096.testbb.ru/cr/checklists/cr0",
                //Вопрос-Ответы
                "http://bbnew-1096.testbb.ru/questions",
                "http://bbnew-1096.testbb.ru/questions/new/2",
                "http://bbnew-1096.testbb.ru/questions/id1751365/64217",
                "http://bbnew-1096.testbb.ru/questions/id1751365/64217/3",
                //Тестдрайвы
                "http://bbnew-1096.testbb.ru/td/0",
                "http://bbnew-1096.testbb.ru/td/0/active",
                "http://bbnew-1096.testbb.ru/td/0/close",
                "http://bbnew-1096.testbb.ru/td/0?page=2",
                "http://bbnew-1096.testbb.ru/td/info/34",
                "http://bbnew-1096.testbb.ru/td/itog/34",
                "http://bbnew-1096.testbb.ru/td/rules/34",
                "http://bbnew-1096.testbb.ru/td/11",
                //Сообщества
                "http://bbnew-1096.testbb.ru/community/lenta/conception",
                "http://bbnew-1096.testbb.ru/community/lenta/conception/15829",
                "http://bbnew-1096.testbb.ru/community/rules/conception",
                "http://bbnew-1096.testbb.ru/community/lenta_cat/conception/16526",
                "http://bbnew-1096.testbb.ru/community/post/conception/1715598",
                //Дневники
                "http://bbnew-1096.testbb.ru/user/lenta/ashlyy",
                "http://bbnew-1096.testbb.ru/user/lenta/ashlyy/5",
                "http://bbnew-1096.testbb.ru/user/info/friends/ashlyy",
                "http://bbnew-1096.testbb.ru/user/info/ashlyy"
        };

        for(int i = 0; i<urls.length;i++) {

            List<Article> articleList = new ArrayList<Article>();
            List<Page> pageList = new ArrayList<>();

            String strUserId = "admin";
            String strPassword = "AdPass2017";

            String authString = strUserId + ":" + strPassword;

            String encodedString =
                    new String(Base64.encodeBase64(authString.getBytes()));

            Document doc = null;

            try {

                doc = Jsoup.connect(urls[i])
                        .header("Authorization", "Basic " + encodedString)
                        .get();

             //   System.out.println("Logged in using basic authentication");

            } catch (IOException e) {
                e.printStackTrace();
            }


            /*
            Elements metaDescription = doc.getElementsByAttributeValue("name", "description");

            for (Element aElem : metaDescription) {
                String content = aElem.attr("content");

                articleList.add(new Article(content));
            }

            System.out.print(urls[i] + " = " + "description: ");
            articleList.forEach(System.out::println);
            */

            /*
            Elements linkNext = doc.getElementsByAttributeValue("rel", "next");

            for (Element aElem : linkNext) {
                String content = aElem.attr("href");

                articleList.add(new Article(content));
            }

            System.out.print(urls[i] + " = " + "link[rel-\"next\"]: ");
            articleList.forEach(System.out::println);
            */

            Elements metaDescription = doc.getElementsByAttributeValue("name", "description");
            Elements linkNext = doc.getElementsByAttributeValue("rel", "next");
            Elements linkPrev = doc.getElementsByAttributeValue("rel", "prev");

            String DescriptionContent = "";
            String linkNextHref = "";
            String linkPrevHref = "";

            for (Element aElem : metaDescription) {
                DescriptionContent = aElem.attr("content");
            }
            for (Element aElem : linkPrev) {
                linkNextHref = aElem.attr("href");
            }
            for (Element aElem : linkPrev) {
                linkPrevHref = aElem.attr("href");
            }

            pageList.add(new Page(DescriptionContent,linkNextHref,linkPrevHref));

            System.out.print(urls[i] + " = ");
            pageList.forEach(System.out::println);

        }
    }
}

class Page {

    private String descriptionContent;
    private String linkNext;
    private String linkPrev;

    public Page(String descriptionContent, String linkNext, String linkPrev) {
        this.descriptionContent = descriptionContent;
        this.linkNext = linkNext;
        this.linkPrev = linkPrev;
    }

    public String getDescriptionContent() {
        return descriptionContent;
    }

    public void setDescriptionContent(String descriptionContent) {
        this.descriptionContent = descriptionContent;
    }

    public String getLinkNext() {
        return linkNext;
    }

    public void setLinkNext(String linkNext) {
        this.linkNext = linkNext;
    }

    public String getLinkPrev() {
        return linkPrev;
    }

    public void setLinkPrev(String linkPrev) {
        this.linkPrev = linkPrev;
    }

    @Override
    public String toString() {
        return "Page{" +
                "descriptionContent='" + descriptionContent + '\'' +
                ", linkNext='" + linkNext + '\'' +
                ", linkPrev='" + linkPrev + '\'' +
                '}';
    }
}

class Article {

    private String name;


    public Article( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{" +
                "content='" + name + '\'' +
                '}';
    }
}