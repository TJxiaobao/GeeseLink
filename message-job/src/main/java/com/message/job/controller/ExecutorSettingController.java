package com.message.job.controller;

import com.message.common.http.Result;
import com.message.job.dispatch.TaskSchedulerService;
import com.message.job.service.ExecutorServiceThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/setting")
public class ExecutorSettingController {
    private String cron = "0/30 * * * * ?";

    @Autowired
    private TaskSchedulerService taskSchedulerService;

    private static final AtomicInteger threadCount = new AtomicInteger(0);

    @GetMapping("/init")
    public Result<String> init(@RequestParam(required = false) Integer count, @RequestParam(required = false) String cronStr) {
        if (Objects.isNull(count)) {
            count = 1;
        }
        int len = count;
        cron = cronStr == null ? cron : cronStr;
        while (count-- > 0) {
            int andIncrement = threadCount.getAndIncrement();
            System.out.println(andIncrement);
            // 注意第二个参数不是线程的命名,这里是池化
            taskSchedulerService.startCronNewTask(cron, new ExecutorServiceThread(), "TaskMapKey-" + andIncrement);
        }
        return Result.success("成功初始化" + len + "个定时线程", "累计已执行" + threadCount + "个线程");
    }

    @GetMapping("/shutdown")
    public Result<Boolean> shutdownByName(String name) {
        taskSchedulerService.stopCron(name);
        return Result.success("成功停止" + name + "线程", true);
    }

    @GetMapping("/shutdownAll")
    public Result<Boolean> shutdownAll() {
        taskSchedulerService.stopCronAll();
        return Result.success("成功停止所有线程", true);
    }
}
