package org.sunshine.lc.test;

import com.authine.h3yun.engine.controller.utils.YamlUtils;
import io.fabric8.kubernetes.api.model.Pod;
import org.junit.Test;

import java.io.IOException;

public class StringTest {

    @Test
    public void test() throws IOException {
        String yaml = "{\"apiVersion\":\"v1\",\"kind\":\"Pod\",\"metadata\":{\"labels\":{\"wayne-app\":\"h3yun-engine-dao-shard3-v1\"},\"name\":\"h3yun-engine-dao-shard3-v1\"},\"spec\":{\"containers\":[{\"env\":[{\"name\":\"DOTNET_ARGS\",\"value\":\"--urls=http://*:8080 --cp=/appconfig/appsettings.json\"},{\"name\":\"TARGET_BIN\",\"value\":\"bin/Release/netcoreapp3.1/h3yun-engine-dao.dll\"},{\"name\":\"SHARD_KEY\",\"value\":\"shard3\"}],\"image\":\"registry.harbor.h3yun.net:30002/dev-h3yun-engine/h3yun-engine-dao:v1479\",\"imagePullPolicy\":\"IfNotPresent\",\"livenessProbe\":{\"failureThreshold\":10,\"httpGet\":{\"path\":\"/v1/heartbeat/ping\",\"port\":8080},\"initialDelaySeconds\":30,\"periodSeconds\":10,\"timeoutSeconds\":1},\"name\":\"h3yun-engine-dao\",\"ports\":[{\"containerPort\":8080},{\"containerPort\":1234}],\"readinessProbe\":{\"failureThreshold\":10,\"httpGet\":{\"path\":\"/v1/heartbeat/ping\",\"port\":8080},\"initialDelaySeconds\":10,\"periodSeconds\":10,\"timeoutSeconds\":1},\"resources\":{\"limits\":{\"memory\":\"0.4Gi\",\"cpu\":\"1\"},\"requests\":{\"memory\":\"0.2Gi\",\"cpu\":\"0.05\"}},\"volumeMounts\":[{\"mountPath\":\"/appconfig\",\"name\":\"properties-mount\",\"readOnly\":true},{\"mountPath\":\"/data/logcollector\",\"name\":\"eventlog-mount\"}]}],\"hostIPC\":true,\"imagePullSecrets\":[{\"name\":\"h3yun-dockercfg\"}],\"volumes\":[{\"configMap\":{\"defaultMode\":420,\"name\":\"h3yun-engine-dao-configmap\"},\"name\":\"properties-mount\"},{\"hostPath\":{\"path\":\"/data/logcollector\"},\"name\":\"eventlog-mount\"}]}}";
        Pod pod = YamlUtils.fromYAML(yaml, Pod.class);
        System.out.println(pod);
    }
}
