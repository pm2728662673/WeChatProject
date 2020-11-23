package cn.edu.xctf.pm.wechatproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @Author pangmin
 * @Date 2020/11/4 18:30
 **/
public class WhChatContentFragment extends Fragment {

    private View view;

    /**
     * 加载布局
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_frag, container, false);
        return view;
    }

    /**
     * 用于将新闻的标题和内容显示在界面上
     * @param newsTitle 新闻标题
     * @param newsContent 新闻内容
     */
    public void refresh(String newsTitle, String newsContent) {
        View visibilityLayout = view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView newsTitleText = view.findViewById(R.id.username_1);
        TextView newsContentText = view.findViewById(R.id.content);
        newsTitleText.setText(newsTitle); //刷新用户
        newsContentText.setText(newsContent); //刷新内容
    }
}
