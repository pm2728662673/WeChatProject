package cn.edu.xctf.pm.wechatproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author pangmin
 * @Date 2020/11/04 18:46
 */
public class WeChatContentActivity extends AppCompatActivity {

    /**
     * 显示界面
     * @param context
     * @param newsTitle
     * @param newsContent
     */
    public static void actionStart(Context context, String newsTitle,
                                   String newsContent) {
        Intent intent = new Intent(context, WeChatContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("content", newsContent);
        context.startActivity(intent);
    }

    /**
     * 加载布局
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        String newsTitle = getIntent().getStringExtra("news_title"); //获取传入的新闻标题
        String newsContent = getIntent().getStringExtra("content"); //获取传入的新闻内容
        WhChatContentFragment whChatContentFragment = (WhChatContentFragment)
                getSupportFragmentManager().findFragmentById(R.id.content_fragment);
        whChatContentFragment.refresh(newsTitle, newsContent); //刷新NewsContentFragment界面
    }
}