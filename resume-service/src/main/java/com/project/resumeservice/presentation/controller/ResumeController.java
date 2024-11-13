package com.project.resumeservice.presentation.controller;

import com.project.resumeservice.application.service.ResumeService;
import com.project.resumeservice.domain.model.UserRole;
import com.project.resumeservice.presentation.request.ResumeCreateRequest;
import com.project.resumeservice.presentation.request.ResumeUpdateRequest;
import com.project.resumeservice.presentation.response.ApiSuccessResponse;
import com.project.resumeservice.presentation.response.ResumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ApiSuccessResponse<ResumeResponse> createResume(@RequestHeader(name = "X-UserId") Long userId,
                                                           @RequestHeader(name = "X-Role") UserRole userRole,
                                                           @RequestBody ResumeCreateRequest createRequest) {

        return ApiSuccessResponse.from(resumeService.createResume(userId, userRole, createRequest.toDto()));
    }

    @GetMapping("/{resumeId}")
    public ApiSuccessResponse<ResumeResponse> getResume(@PathVariable(name = "resumeId") Long resumeId) {

        return ApiSuccessResponse.from(resumeService.getResume(resumeId));
    }

    @GetMapping("/my")
    public ApiSuccessResponse<List<ResumeResponse>> getMyResume(@RequestHeader(name = "X-UserId") Long userId,
                                                               @RequestHeader(name = "X-Role") UserRole userRole) {

        return ApiSuccessResponse.from(resumeService.getMyResume(userId, userRole));
    }


    @PatchMapping("/{resumeId}")
    public ApiSuccessResponse<ResumeResponse> updateResume(@RequestHeader(name = "X-UserId") Long userId,
                                                           @RequestHeader(name = "X-Role") UserRole userRole,
                                                           @PathVariable(name = "resumeId") Long resumeId,
                                                           @RequestBody ResumeUpdateRequest updateRequest) {

        return ApiSuccessResponse.from(resumeService.updateResume(userId, userRole, resumeId, updateRequest.toDto()));
    }


}
