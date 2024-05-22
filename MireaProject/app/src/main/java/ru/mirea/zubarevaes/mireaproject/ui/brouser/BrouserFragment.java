package ru.mirea.zubarevaes.mireaproject.ui.brouser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;
import ru.mirea.zubarevaes.mireaproject.R;

public class BrouserFragment extends Fragment {

    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_brouser, container, false);

        // Находим WebView по его id
        webView = rootView.findViewById(R.id.idWebView);

        // Включаем поддержку JavaScript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        // Загружаем страницу
        webView.loadUrl("http://developer.android.com");

        return rootView;
    }
}
