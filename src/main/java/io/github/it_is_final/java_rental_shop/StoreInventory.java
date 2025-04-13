package io.github.it_is_final.java_rental_shop;

import java.util.*;

public class StoreInventory {
    private final List<Video> videos;

    public StoreInventory() {
        videos = new ArrayList<>();
    }

    public void addVideo(Video video) {
        videos.add(video);
        // Make binary search easier
        videos.sort(Comparator.comparing(Video::name));
    }

    public List<Video> searchVideosByTitle(String q) {
        return videos
                .stream()
                .filter((v) -> v.name().contains(q))
                .toList();
    }

    public List<Video> searchVideosByAuthor(String q) {
        return videos
                .stream()
                .filter((v) -> v.author().contains(q))
                .toList();
    }

    public Video getVideoByTitle(String t) {
        int l = 0;
        int r = videos.size() - 1;
        // Binary search implementation
        while (l <= r) {
            int m = (l + r) / 2;
            if (videos.get(m).name().compareTo(t) < 0) {
                l = m + 1;
            }
            if (videos.get(m).name().compareTo(t) > 0) {
                r = m - 1;
            }
            if (videos.get(m).name().compareTo(t) == 0) {
                return videos.get(m);
            }
        }
        return null;
    }
}
