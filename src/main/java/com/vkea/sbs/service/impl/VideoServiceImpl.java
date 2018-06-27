package com.vkea.sbs.service.impl;

import com.vkea.sbs.model.Program;
import com.vkea.sbs.model.ProgramListResponse;
import com.vkea.sbs.service.CacheService;
import com.vkea.sbs.service.VideoService;
import com.vkea.sbs.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Service
public class VideoServiceImpl implements VideoService {
    private static Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    WebUtil webUtil;

    @Autowired
    CacheService cacheService;

    public static String TOP_URL;

    @Value("${top.url}")
    private void setTopUrl(String topUrl) {
        TOP_URL = topUrl;
    }

    @Override
    public ProgramListResponse getTop() {
        HashMap<String, String> postBody = new HashMap<>();
        ProgramListResponse programListResponse = webUtil.postRequest(getUri("top"), postBody, ProgramListResponse.class);
        return programListResponse;
    }

    @Override
    public List<Program> getProgramDetailList(List<String> idList) {
        List<Program> result = new ArrayList<>();
        for (String id : idList)
            result.add(cacheService.getProgram(id));
        return result;
    }


    private static URI getUri(String key) {
        try {
//            throw new URISyntaxException("aa", "bb");

            return new URI(TOP_URL);
        } catch (URISyntaxException e) {
//            TODO: customize the exception
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    private ProgramListResponse createPage() {
        ProgramListResponse result = new ProgramListResponse();
        result.getBasicProgramList().add(createNewBasicProgram());
        result.getBasicProgramList().add(createNewBasicProgram());
        return result;
    }

    private Program createNewBasicProgram() {
        Program result = new Program();
        return result;
    }
}
