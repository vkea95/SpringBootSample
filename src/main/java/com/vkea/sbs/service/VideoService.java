package com.vkea.sbs.service;

import com.vkea.sbs.model.Program;
import com.vkea.sbs.model.ProgramListResponse;

import java.util.List;

public interface VideoService {
    ProgramListResponse getTop();

    List<Program> getProgramDetailList(List<String> idList);

    List<Program> searchProgram(String genre);
}
