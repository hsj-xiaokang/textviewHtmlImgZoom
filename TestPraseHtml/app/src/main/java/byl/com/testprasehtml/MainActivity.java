package byl.com.testprasehtml;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import net.nightwhistler.htmlspanner.HtmlSpanner;
import net.nightwhistler.htmlspanner.LinkMovementMethodExt;
import net.nightwhistler.htmlspanner.MyImageSpan;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    HtmlSpanner htmlSpanner;
    ArrayList<String> imglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        imglist = new ArrayList<>();
        htmlSpanner = new HtmlSpanner(this, dm.widthPixels, handler);
        tv = (TextView) findViewById(R.id.tv);
        final String html = "" +
                "<h2>Hello world</h2>\n" +
                "<table>\n" +
                "    <tr>\n" +
                "        <th>Header 1</th>\n" +
                "        <th>Header 2</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>mo data</td>\n" +
                "        <td>mo problems</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<br/>\n" +
                "<ul>\n" +
                "    <li>cats</li>\n" +
                "    <li>dogs Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet Lorem ipsum dolor sit amet</li>\n" +
                "</ul>\n" +
                "<br/>\n" +
                "<ol>\n" +
                "    <li>first</li>\n" +
                "    <li>second\n" +
                "        <ol>\n" +
                "            <li>second - first\n" +
                "                <br/>\n" +
                "                newline\n" +
                "            </li>\n" +
                "        </ol>\n" +
                "    </li>\n" +
                "</ol>\n" +
                "<table>\n" +
                "    <tr>\n" +
                "        <th>Month</th>\n" +
                "        <th>Savings</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>January</td>\n" +
                "        <td>$1,000,000</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <TABLE>\n" +
                "                <TR>\n" +
                "                    <TH>Header 1</TH>\n" +
                "                    <TH>Header 2</TH>\n" +
                "                </TR>\n" +
                "                <TR>\n" +
                "                    <TH>Cell 1</TH>\n" +
                "                    <TH>Cell 2</TH>\n" +
                "                </TR>\n" +
                "            </TABLE>\n" +
                "        </td>\n" +
                "        <td>yo dawg</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<br/>\n" +
                "<br/>\n" +
                "<img src=\"http://cimage1.tianjimedia.com/uploadImages/thirdImages/2016/340/YM07F26994N5.jpg\"/>\n" +
                "<br/>\n" +
                "A very long text follows below and it contains bold parts. This can cause a crash\n" +
                "<a href=\"http://code.google.com/p/android/issues/detail?id=35466\">on some Android versions</a>\n" +
                "when using a normal TextView, but our implementation should workaround that bug.\n" +
                "<br/>\n" +
                "<br/>\n" +
                "<img src=\"http://img1.gtimg.com/news/pics/hv1/11/146/2165/140816366.jpg\"/>\n" +
                "<br/>\n" +
                "<p>\n" +
                "    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ut eros sed arcu auctor tincidunt\n" +
                "    id sit amet elit. Mauris in faucibus neque. Suspendisse facilisis urna nec nisi convallis tincidunt.\n" +
                "    Mauris at elit et arcu viverra auctor. Nullam et arcu ultricies, iaculis dolor efficitur, tristique eros.\n" +
                "    Interdum et malesuada <b>some bold text in here</b> fames ac ante ipsum primis in faucibus.\n" +
                "    Integer nec aliquet mi. Aenean ipsum odio,\n" +
                "    iaculis et est eget, condimentum bibendum dui. Aenean nec elementum tortor. Vestibulum tincidunt mi sit\n" +
                "    amet tortor semper tincidunt. Sed tincidunt metus et pretium semper. Phasellus neque est, congue nec\n" +
                "    metus ac, ullamcorper varius sapien. Integer nec tortor vel justo finibus gravida id quis purus.\n" +
                "    Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vestibulum\n" +
                "    aliquam convallis dapibus. Aenean suscipit, orci id elementum vehicula, odio arcu fringilla massa,\n" +
                "    vel imperdiet augue est non mi.\n" +
                "</p>\n" +
                "<br/>\n" +
                "<img src=\"http://img.mp.itc.cn/upload/20161207/61c74367751c4d0c97be95c7cc7e2314_th.jpg\"/>\n" +
                "<p>\n" +
                "    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam ut eros sed arcu auctor tincidunt\n" +
                "    id sit amet elit. Mauris in faucibus neque. Suspendisse facilisis urna nec nisi convallis tincidunt.\n" +
                "    Mauris at elit et arcu viverra auctor. Nullam et arcu ultricies, iaculis dolor efficitur, tristique eros.\n" +
                "    Interdum et malesuada fames ac ante ipsum primis in faucibus. Integer nec aliquet mi. Aenean ipsum odio,\n" +
                "    iaculis et est eget, condimentum bibendum dui. Aenean nec elementum tortor. Vestibulum tincidunt mi sit\n" +
                "    amet tortor semper tincidunt. Sed tincidunt metus et pretium semper. Phasellus neque est, congue nec\n" +
                "    metus ac, <b>ullamcorper varius sapien. Integer nec tortor vel justo finibus gravida id quis purus.</b>\n" +
                "    Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Vestibulum\n" +
                "    aliquam convallis dapibus. Aenean suscipit, orci id elementum vehicula, odio arcu fringilla massa,\n" +
                "    vel imperdiet augue est non mi.\n" +
                "</p>\n" +
                "<img src=\"http://img5.duitang.com/uploads/item/201408/12/20140812162508_CWKCR.thumb.700_0.jpeg\"/>\n" +
                "<p>\n" +
                "    Android will add extra space at the bottom of the textView by default fromHtml,\n" +
                "    use <code>setRemoveFromHtmlSpace(true)</code> on your <b>HtmlTextView</b>\n" +
                "    before setting text to prevent this from happening.\n" +
                "</p\n" +
                "</p>";

        new Thread(new Runnable() {
            @Override
            public void run() {
                final Spannable spannable = htmlSpanner.fromHtml(html);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(spannable);
                        tv.setMovementMethod(LinkMovementMethodExt.getInstance(handler, ImageSpan.class));
                    }
                });
            }
        }).start();
    }

    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1://获取图片路径列表
                    String url = (String) msg.obj;
                    Log.e("jj", "url>>" + url);
                    imglist.add(url);
                    break;
                case 2://图片点击事件
                    int position=0;
                    MyImageSpan span = (MyImageSpan) msg.obj;
                    for (int i = 0; i < imglist.size(); i++) {
                        if (span.getUrl().equals(imglist.get(i))) {
                            position = i;
                            break;
                        }
                    }
                    Log.e("jj","position>>"+position);
                    Intent intent=new Intent(MainActivity.this,ImgPreviewActivity.class);
                    Bundle b=new Bundle();
                    b.putInt("position",position);
                    b.putStringArrayList("imglist",imglist);
                    intent.putExtra("b",b);
                    startActivity(intent);
                    break;
            }
        }

        ;
    };
}
