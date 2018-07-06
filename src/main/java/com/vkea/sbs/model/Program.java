package com.vkea.sbs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "video", type = "program")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Program {

    @JsonProperty("gid")
    private String id;

    private String title;

    private List<ImageInfo> imageInfoList;

    private Integer type;

    private List<String> genreList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ImageInfo> getImageInfoList() {
        return imageInfoList;
    }

    public void setImageInfoList(List<ImageInfo> imageInfoList) {
        this.imageInfoList = imageInfoList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<String> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<String> genreList) {
        this.genreList = genreList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", imageInfoList=" + imageInfoList +
                ", type=" + type +
                ", genreList=" + genreList +
                '}';
    }

    class Solution {
        public int trap(int[] height) {
            if (height == null || height.length <= 1) {
                return 0;
            }
            int result = 0;
            int n = height.length;
            int[] maxL = new int[n];
            int[] maxR = new int[n];
            maxL[0] = height[0];
            maxR[n - 1] = height[n - 1];
            for (int i = 1; i < n; i++) {
                maxL[i] = Math.max(maxL[i - 1], height[i]);
            }

            for (int i = n - 2; i >= 0; i--) {
                maxR[i] = Math.max(maxR[i + 1], height[i]);
            }
            for (int i = 1; i < n; i++) {
                int temp = Math.min(Math.min(maxL[i - 1], maxR[i - 1]), Math.min(maxL[i], maxR[i])) - height[i];//bug: wrong answer
                result += Math.max(0, temp);
            }
            return result;
        }
    }
}
