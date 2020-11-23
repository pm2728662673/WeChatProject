package cn.edu.xctf.pm.wechatproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author pangmin
 * @Date 2020/11/4 19:06
 **/
public class WeChatUsernameFragment extends Fragment {

    private boolean isTwoPane;

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
        View view = inflater.inflate(R.layout.wechat_title_frag, container, false);
        RecyclerView newsTitleRecyclerView = view.findViewById(R.id.username_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);
        return view;
    }

    private List<WeChat> getNews(){
        List<WeChat> weChatList = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            WeChat weChat = new WeChat();
            weChat.setTitle("老王" + i);
            weChat.setContent(getRandomLengthContent("你好" + i + ". "));
            weChatList.add(weChat);
        }
        return weChatList;
    }

    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }
    /**
     * 判断是单页模式还是双页模式
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.content_layout) != null) {
            isTwoPane = true; //可以找到news_content_layout布局时，为双页模式
        } else {
            isTwoPane = false; //找不到news_content_layout布局时，为单页模式
        }
    }



    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<WeChat> mWeChatList;

        class ViewHolder extends RecyclerView.ViewHolder {

            TextView newsTitleText;

            /**
             * 重写方法
             * @param view
             */
            public ViewHolder(View view) {
                super(view);
                newsTitleText = view.findViewById(R.id.username_1);
            }
        }

        /**
         * 构造器
         * @param weChatList
         */
        public NewsAdapter(List<WeChat> weChatList) {
            mWeChatList = weChatList;
        }

        /**
         * 重写onCreateViewHolder方法
         * @param parent
         * @param viewType
         * @return
         */
        @NonNull
        @Override
        public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.wechat_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WeChat weChat = mWeChatList.get(holder.getAdapterPosition());
                    if (isTwoPane) {
                        //如果是双页模式，则刷新WeChatContentFragment中的内容
                        WhChatContentFragment whChatContentFragment = (WhChatContentFragment)
                                getFragmentManager().findFragmentById(R.id.content_fragment);
                        whChatContentFragment.refresh(weChat.getTitle(), weChat.getContent());
                    } else {
                        //如果是单页模式，则直接启动WeChatContentActivity
                        WeChatContentActivity.actionStart(getActivity(),
                                weChat.getTitle(), weChat.getContent());
                    }
                }
            });
            return holder;
        }

        /**
         * 重写onBindViewHolder方法
         * @param holder
         * @param position
         */
        @Override
        public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
            WeChat weChat = mWeChatList.get(position);
            holder.newsTitleText.setText(weChat.getTitle());
        }

        /**
         * 重写getItemCount方法
         * @return
         */
        @Override
        public int getItemCount() {
            return mWeChatList.size();
        }
    }
}
