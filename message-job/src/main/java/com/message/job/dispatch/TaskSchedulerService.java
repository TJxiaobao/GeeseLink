package com.message.job.dispatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ScheduledFuture;

@Service
@Slf4j
@DependsOn("threadPoolTaskScheduler")
public class TaskSchedulerService {

    @Resource
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private final Map<String, ScheduledFuture<?>> taskMap = new HashMap<>();

    public synchronized void startCronNewTask(String cron, Runnable task, String taskName) {
        //后面从数据库设置和读取
        if (taskMap.size() > threadPoolTaskScheduler.getPoolSize()) {
            log.error("定时任务大于" + threadPoolTaskScheduler.getPoolSize() + ",请稍后在设置");
        } else {
            if (ObjectUtils.isEmpty(cron) || taskMap.containsKey(taskName)) {
                log.error("定时任务开启失败");
            } else {
                // 第二个参数判断cron表达式是否成立，否则返回null指针异常
                ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(new Thread(task, taskName), triggerContext -> Objects.requireNonNull(new CronTrigger(cron).nextExecutionTime(triggerContext)));
                taskMap.put(taskName, future);
                log.info("定时任务开启成功");
            }
        }

    }

    public synchronized void startCronRetryTask(String cron, Runnable task, String taskName) {
        //后面从数据库设置和读取
        if (taskMap.size() > threadPoolTaskScheduler.getPoolSize()) {
            log.error("定时任务大于" + threadPoolTaskScheduler.getPoolSize() + ",请稍后在设置");
        }
        if (ObjectUtils.isEmpty(cron) || taskMap.containsKey(taskName)) {
            log.error("定时任务开启失败");
        } else {
            // 第二个参数判断cron表达式是否成立，否则返回null指针异常
            ScheduledFuture<?> future = threadPoolTaskScheduler.schedule(new Thread(task, taskName), triggerContext -> Objects.requireNonNull(new CronTrigger(cron).nextExecutionTime(triggerContext)));
            taskMap.put(taskName, future);
            log.info("定时任务开启成功");
        }
    }

    public synchronized void stopCron(String taskName) {
        ScheduledFuture<?> future = taskMap.get(taskName);
        if (future != null) {
            // cancel(false); 停止会等任务执行完,如果是true会直接停止
            future.cancel(false);
            taskMap.remove(taskName);
            log.info("定时任务 {} 已关闭", taskName);
        } else {
            log.error("找不到指定的定时任务 {}", taskName);
        }
    }

    public synchronized void stopCronAll() {
        for (String taskName : taskMap.keySet()) {
            ScheduledFuture<?> future = taskMap.get(taskName);
            if (future != null) {
                // cancel(false); 停止会等任务执行完,如果是true会直接停止
                future.cancel(false);
                taskMap.remove(taskName);
                log.info("定时任务 {} 已关闭", taskName);
            } else {
                log.error("找不到指定的定时任务 {}", taskName);
            }
        }

    }


}
