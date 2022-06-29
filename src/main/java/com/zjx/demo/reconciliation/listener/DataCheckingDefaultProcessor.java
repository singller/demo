package com.zjx.demo.reconciliation.listener;

import com.google.common.base.Preconditions;
import com.zjx.demo.reconciliation.BasicCheckData;
import com.zjx.demo.reconciliation.TargetCheckData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zjx
 */
public class DataCheckingDefaultProcessor {

    private final DataCheckingOnLoadHashListener dataCheckingOnLoadHashListener;
    private final DataCheckingConsistenceListener dataCheckingConsistenceListener;

    public DataCheckingDefaultProcessor(DataCheckingOnLoadHashListener dataCheckingOnLoadHashListener,
                                        DataCheckingConsistenceListener dataCheckingConsistenceListener) {

        Preconditions.checkNotNull(dataCheckingOnLoadHashListener);
        Preconditions.checkNotNull(dataCheckingConsistenceListener);
        this.dataCheckingOnLoadHashListener = dataCheckingOnLoadHashListener;
        this.dataCheckingConsistenceListener = dataCheckingConsistenceListener;
    }

    /**
     * 执行对账
     */
    public void execute() {
        check();
    }

    /**
     * 执行对账
     */
    private void check() {
        // 对账前数据准备
        Map<String, BasicCheckData> basicCheckDataMap = dataCheckingOnLoadHashListener.loadBasicData2Map();
        Map<String, TargetCheckData> targetCheckDataMap = dataCheckingOnLoadHashListener.loadTargeDataMap();
        Preconditions.checkNotNull(basicCheckDataMap);
        Preconditions.checkNotNull(targetCheckDataMap);

        // 执行对账
        AtomicLong successCount = new AtomicLong();
        AtomicLong failureCount = new AtomicLong();
        handleCheckByHashStrategy(basicCheckDataMap, targetCheckDataMap, successCount, failureCount);

        // 需要二次校验则二次校验
        if (dataCheckingConsistenceListener.needDoubleCheck()) {
            AtomicLong doubleCheckSuccessCount = new AtomicLong();
            AtomicLong doubleCheckFailureCount = new AtomicLong();

            handleCheckByHashStrategy(basicCheckDataMap, targetCheckDataMap, doubleCheckSuccessCount, doubleCheckFailureCount);
        }

        // 数据修复
        dataCheckingConsistenceListener.fixData();
    }

    /**
     * hash结构对账逻辑
     *
     * @param basicCheckDataMap
     * @param targetCheckDataMap
     * @param successCount
     * @param failureCount
     */
    private void handleCheckByHashStrategy(Map<String, BasicCheckData> basicCheckDataMap,
                                           Map<String, TargetCheckData> targetCheckDataMap,
                                           AtomicLong successCount,
                                           AtomicLong failureCount) {
        for (Map.Entry<String, BasicCheckData> checkEntry : basicCheckDataMap.entrySet()) {
            String checkEntryKey = checkEntry.getKey();

            BasicCheckData basicCheckData = checkEntry.getValue();
            if (basicCheckData == null) {
                failureCount.incrementAndGet();
                continue;
            }

            TargetCheckData targetCheckData = targetCheckDataMap.get(checkEntryKey);
            if (targetCheckData == null) {
                failureCount.incrementAndGet();
                continue;
            }

            // 校验checkEntryKey是否与对账实体的id一致
            String basicCheckBizId = basicCheckData.getCheckBizId();
            String targetCheckBizId = targetCheckData.getCheckBizId();
            if (!isCheckBizIdEqual(checkEntryKey, basicCheckBizId, targetCheckBizId)) {
                throw new DataCheckRuntimeException("checkEntryKey must equals basicCheckBizId and checkEntryKey must equals targetCheckBizId!");
            }

            // 执行对账
            if (!dataCheckingConsistenceListener.isCheckConsistent(basicCheckData, targetCheckData)) {
                failureCount.incrementAndGet();
                continue;
            }

            successCount.incrementAndGet();
        }
    }

    private boolean isCheckBizIdEqual(String checkEntryKey, String basicCheckBizId, String targetCheckBizId) {
        return false;
    }

    public static void main(String[] args) throws IOException {
        //流程图
//        for (int i = 0; i < 500; i++) {
//            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .build();
//            Request request = new Request.Builder()
//                    .url("https://www.processon.com/diagraming/new?category=flow")
//                    .method("GET", null)
//                    .addHeader("authority", "www.processon.com")
//                    .addHeader("pragma", "no-cache")
//                    .addHeader("cache-control", "no-cache")
//                    .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\",\"Chromium\";v=\"98\",\"Google Chrome\";v=\"98\"")
//                    .addHeader("sec-ch-ua-mobile", "?0")
//                    .addHeader("sec-ch-ua-platform", "\"macOS\"")
//                    .addHeader("sec-fetch-dest", "document")
//                    .addHeader("sec-fetch-mode", "navigate")
//                    .addHeader("sec-fetch-site", "same-origin")
//                    .addHeader("sec-fetch-user", "?1")
//                    .addHeader("upgrade-insecure-requests", "1")
//                    .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.109 Safari/537.36")
//                    .addHeader("path", "/diagraming/new?category=flow")
//                    .addHeader("scheme", "https")
//                    .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
//                    .addHeader("accept-encoding", "gzip, deflate, br")
//                    .addHeader("accept-language", "zh-CN,zh;q=0.9,zh-TW;q=0.8,en-US;q=0.7,en;q=0.6")
//                    .addHeader("referer", "https://www.processon.com/diagrams")
//                    .addHeader("cookie", "_ga=GA1.2.456904191.1642071944; processon_userKey=5ed3499fe401fd0735a4e4df; _sid=ccb1e8576d2a22b8ccdbda46b345dae6; _gid=GA1.2.85939077.1647508911; JSESSIONID=64A0FD767A038BCDA20213523258F692.jvm1; _gat=1; zg_did=%7B%22did%22%3A%20%2217e531d6ba1375-0368e02ac51067-1d326253-384000-17e531d6ba213b4%22%7D; zg_3f37ba50e54f4374b9af5be6d12b208f=%7B%22sid%22%3A%201647522767163%2C%22updated%22%3A%201647524536243%2C%22info%22%3A%201647508911608%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22www.processon.com%22%2C%22cuid%22%3A%20%225ed3499fe401fd0735a4e4df%22%2C%22zs%22%3A%200%2C%22sc%22%3A%200%2C%22firstScreen%22%3A%201647522767163%7D")
//                    .build();
//            Response response = client.newCall(request).execute();
//            System.out.println(response);
//        }
        //
        for (int i = 0; i < 500; i++) {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://www.processon.com/mindmap/new?category=mind_free")
                    .method("GET", null)
                    .addHeader("authority", "www.processon.com")
                    .addHeader("pragma", "no-cache")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\",\"Chromium\";v=\"98\",\"Google Chrome\";v=\"98\"")
                    .addHeader("sec-ch-ua-mobile", "?0")
                    .addHeader("sec-ch-ua-platform", "\"macOS\"")
                    .addHeader("sec-fetch-dest", "document")
                    .addHeader("sec-fetch-mode", "navigate")
                    .addHeader("sec-fetch-site", "same-origin")
                    .addHeader("sec-fetch-user", "?1")
                    .addHeader("upgrade-insecure-requests", "1")
                    .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.109 Safari/537.36")
                    .addHeader("path", "/diagraming/new?category=flow")
                    .addHeader("scheme", "https")
                    .addHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                    .addHeader("accept-encoding", "gzip, deflate, br")
                    .addHeader("accept-language", "zh-CN,zh;q=0.9,zh-TW;q=0.8,en-US;q=0.7,en;q=0.6")
                    .addHeader("referer", "https://www.processon.com/diagrams")
                    .addHeader("cookie", "_ga=GA1.2.456904191.1642071944; processon_userKey=5ed3499fe401fd0735a4e4df; _sid=ccb1e8576d2a22b8ccdbda46b345dae6; _gid=GA1.2.85939077.1647508911; JSESSIONID=64A0FD767A038BCDA20213523258F692.jvm1; _gat=1; zg_did=%7B%22did%22%3A%20%2217e531d6ba1375-0368e02ac51067-1d326253-384000-17e531d6ba213b4%22%7D; zg_3f37ba50e54f4374b9af5be6d12b208f=%7B%22sid%22%3A%201647522767163%2C%22updated%22%3A%201647524536243%2C%22info%22%3A%201647508911608%2C%22superProperty%22%3A%20%22%7B%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22www.processon.com%22%2C%22cuid%22%3A%20%225ed3499fe401fd0735a4e4df%22%2C%22zs%22%3A%200%2C%22sc%22%3A%200%2C%22firstScreen%22%3A%201647522767163%7D")
                    .build();
            Response response = client.newCall(request).execute();
            System.out.println(response);
        }
//        String str = "{\"tranDateTimeFormat\":\"yyyy/MM/dd HH:mm:ss\"," +
//                "\"header\":\"H\"," +
//                "\"tail\":\"S\"," +
//                "\"delimiter\":\"\\|\"," +
//                "\"transactionNo\":\"30\"," +
//                "\"fiTransactionNo\":\"31\"," +
//                "\"tranSuccDate\":\"20\"," +
//                "\"tranSuccTime\":\"21\"," +
//                "\"orderAmount\":\"8\"," +
//                "\"serviceFee\":\"9\"," +
//                "\"channelInterest\":10," +
//                "\"channelVat\":12" +
//                "}";
//        System.out.println(str);


    }
}
