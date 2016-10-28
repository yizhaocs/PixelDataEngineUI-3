package com.adara.pixeldataengineui.service.errorscannerservice;

import com.adara.pixeldataengineui.util.DateUtil;
import com.adara.pixeldataengineui.util.ExecCommandUtil;
import com.adara.pixeldataengineui.util.HttpRequestUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yzhao on 10/20/16.
 */
public class ErrorScanner {
    private static Logger log = Logger.getLogger("ErrorScanner.class");
    private final ScheduledExecutorService refreshThread = Executors.newSingleThreadScheduledExecutor();
    private Date lastRefresh;
    private int refreshInterval; // seconds

    public void init() {
        // start background refresh thread
        refreshThread.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                Thread.currentThread().setName("ErrorScanner");
                try {
                    refresh();
                } catch (Exception e) {
                    log.error("ErrorScanner.refresh:", e);
                }

            }
        }, refreshInterval, refreshInterval, TimeUnit.SECONDS);

    }

    public void refresh() {
        long start = System.currentTimeMillis();

        errorScanRefresh();

        lastRefresh = new Date();
        log.info("ErrorScanner.refresh et:"	+ (System.currentTimeMillis() - start));
    }

    public int getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(int refreshInterval) {
        this.refreshInterval = refreshInterval;
    }

    public void destroy() {
        refreshThread.shutdownNow();
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }


    private void errorScanRefresh() {
        String host = "localhost";
//        String errorScanCommand = "/usr/bin/ssh " + host + " grep " + "[ERROR] PixelDataEngineDataService -- error parsing pixel data engine rule for gid " + "/opt/opinmind/logs/udcuv2/udcuv2.error.log";
        String[] localTestCommmand = {"/bin/sh", "-c", "grep \"error parsing pixel data engine rule\" /opt/opinmind/logs/udcuv2/*"};
        //String[] remoteTestCommmand = {"/usr/bin/ssh", "-c", "grep \"error parsing pixel data engine rule\" /opt/opinmind/logs/udcuv2/*"};
        String commandOutput = ExecCommandUtil.execWithOutput(null, localTestCommmand);
        if(commandOutput.length() > 18){
            // 2016-10-20 19:08:21,834
            String currentDate = DateUtil.getCurrentDateTime();
            String timeExample = "2016-10-20 19:08:21,834";

            String errorDate = DateUtil.convertStringToDate(commandOutput.substring(0, timeExample.length()));
            Long diff = DateUtil.DateDifferentComparison(errorDate, currentDate);
            try {
                if(diff <= 300000){ // 5 mins * 60seconds * in 1000 milliseconds
                    HttpRequestUtil.sendGet("http://localhost:8080/pdeui/sendalertemail");
                }
            }catch (ServletException e){
                log.error("ErrorScanner.errorScanRefresh ServletException:"	+ e);
            }catch (Exception e){
                log.error("ErrorScanner.errorScanRefresh Exception:"	+ e);
            }
        }

    }


}
