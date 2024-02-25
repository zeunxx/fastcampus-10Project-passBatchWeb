package com.fastcampus.pass.passweb.controller;

import com.fastcampus.pass.passweb.service.packaze.PackageService;
import com.fastcampus.pass.passweb.service.pass.BulkPassService;
import com.fastcampus.pass.passweb.service.statistics.StatisticsService;
import com.fastcampus.pass.passweb.service.user.UserGroupMappingService;
import com.fastcampus.pass.passweb.utils.LocalDateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminViewController {

    private final BulkPassService bulkPassService;

    private final PackageService packageService;

    private final UserGroupMappingService userGroupMappingService;

    private final StatisticsService statisticsService;


    @GetMapping
    public ModelAndView registerBulkPass(ModelAndView modelAndView){
        // bulk pass 목록 조회
        modelAndView.addObject("bulkPass",bulkPassService.getAllBulkPasses());
        // bulk pass를 등록할때 필요한 package 값을 위해 모든 package 조회
        modelAndView.addObject("packages",packageService.getAllPackages());
        // bulk pass를 등록할때 필요한 userGroupId 값을 이해 모든 userGroupId 조회
        modelAndView.addObject("userGroupIds", userGroupMappingService.getAllUserGroupIds());
        // bulk pass request 제공
        modelAndView.addObject("request", new BulkPassRequest());
        modelAndView.setViewName("admin/bulk-pass");

        return modelAndView;
    }

    @PostMapping("/bulk-pass")
    public String addBulkPass(@ModelAttribute("request") BulkPassRequest request, Model model){
        // bulk pass 생성
        bulkPassService.addBulkPass(request);
        return "redirect:/admin/bulk-pass";
    }

    @GetMapping
    public ModelAndView home(ModelAndView modelAndView, @RequestParam("to") String toString){
        LocalDateTime to = LocalDateTimeUtils.parseDate(toString);

        // chartData를 조회한다.(라벨, 출석횟수, 취소횟수)
        modelAndView.addObject("chartData", statisticsService.makeChartData(to));
        modelAndView.setViewName("admin/index");
        return modelAndView;
    }
}
