package io.github.it_is_final.java_rental_shop;

import java.util.*;

public class StoreInventory {
    private final Map<String, Video> videos;

    public StoreInventory() {
        videos = new HashMap<>();
    }

    public void addVideo(Video video) {
        videos.put(video.name(), video);
    }

    public void getVideoByTitle(String q) {
        videos.get(q);
    }

    public List<Video> searchVideos(String q) {
        return videos
                .values()
                .stream()
                .filter((v) -> v.name().contains(q))
                .toList();
    }
}
