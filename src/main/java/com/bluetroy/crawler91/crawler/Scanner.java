package com.bluetroy.crawler91.crawler;

import com.bluetroy.crawler91.repository.pojo.KeyContent;
import com.bluetroy.crawler91.utils.ScannerUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

import static com.bluetroy.crawler91.utils.ContentUtils.getDetailContents;
import static com.bluetroy.crawler91.utils.ContentUtils.getMovieContents;

/**
 * @author heyixin
 */
@Log4j2
@Component
public class Scanner {
    private static final List<String> URLS_FOR_SCAN = new ArrayList<>();

    static {
        URLS_FOR_SCAN.add("http://91porn.com/v.php?category=hot&viewtype=basic");
    }

    public static List<String> getUrlsForScan() {
        return URLS_FOR_SCAN;
    }

    public void addUrlForScan(String url) {
        URLS_FOR_SCAN.add(url);
    }

    public void scanMovies() {
        scanMoviesByUrlList();
    }

    public void scanDownloadUrl() {
        scanFilteredMovieDownloadUrl();
    }

    private void scanFilteredMovieDownloadUrl() {
        LinkedBlockingDeque<KeyContent> keyContentQueue = getDetailContents();
        ScannerUtils.scanDownloadUrls(keyContentQueue);
    }

    private void scanMoviesByUrlList() {
        LinkedBlockingDeque<Future<String>> movieContents = getMovieContents();
        ScannerUtils.scanMovies(movieContents);
    }

}
