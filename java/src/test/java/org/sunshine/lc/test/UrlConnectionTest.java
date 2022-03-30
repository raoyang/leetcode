package org.sunshine.lc.test;

import com.authine.h3yun.engine.controller.constant.Constant;
import com.authine.h3yun.engine.controller.engine.entity.K8SEngineCfg;
import com.authine.h3yun.engine.controller.utils.JsonUtils;
import io.fabric8.kubernetes.api.model.*;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlConnectionTest {

    @Test
    public void test() throws Exception{
        urlTest();
    }

    private void urlTest() throws Exception {

        URL localURL = new URL("http://localhost:8080/v1/engine/create/yaml");
        String body = getBody();
        URLConnection connection = localURL.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;

        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(body.length()));

        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        try {
            outputStream = httpURLConnection.getOutputStream();
            outputStreamWriter = new OutputStreamWriter(outputStream);

            outputStreamWriter.write(body);
            outputStreamWriter.flush();

            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }

            inputStream = httpURLConnection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            reader = new BufferedReader(inputStreamReader);

            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }

        } finally {

            if (outputStreamWriter != null) {
                outputStreamWriter.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStreamReader != null) {
                inputStreamReader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }

        }

        String msg = resultBuffer.toString();
        System.out.println("resp:" + msg);
    }

    public String getBody() throws IOException{
        List<K8SEngineCfg> engines = new ArrayList<>();
        K8SEngineCfg engine2 = getEngineWithYaml("shard4", "v1");
        engines.add(engine2);
        return JsonUtils.object2String(engines);
    }

    private K8SEngineCfg getEngineWithYaml(String shard, String version) throws IOException {
        K8SEngineCfg engine = new K8SEngineCfg();
        engine.setShard(shard);
        engine.setVersion(version);
        engine.setCreatetime(System.currentTimeMillis());
        Pod pod = createPodWithConfig(shard, version);
        engine.setPod(pod);
        return engine;
    }

    private Pod createPodWithConfig(String shard, String version){
        Pod config = new Pod();

        //初始化metadata
        ObjectMeta metadata = new ObjectMeta();
        String podName = "h3yun-engine-dao-" + shard + "-" + version;
        metadata.setName(podName);
        Map<String, String> labels = new HashMap<>();
        String labelValue = "h3yun-engine-dao-" + shard + "-" + version;
        labels.put(Constant.DEFAULT_SHARD_LABEL, labelValue);
        metadata.setLabels(labels);
        config.setMetadata(metadata);

        PodSpec podSpec = new PodSpec();
        podSpec.setHostIPC(true);

        List<Volume> volumes = new ArrayList<>();
        Volume volume1 = new Volume();
        volume1.setName("properties-mount");
        ConfigMapVolumeSource configMapVolumeSource = new ConfigMapVolumeSource();
        configMapVolumeSource.setName("h3yun-engine-dao-configmap");
        configMapVolumeSource.setDefaultMode(420);
        volume1.setConfigMap(configMapVolumeSource);
        volumes.add(volume1);

        Volume volume2 = new Volume();
        volume2.setName("eventlog-mount");
        HostPathVolumeSource hostPathVolumeSource = new HostPathVolumeSource();
        hostPathVolumeSource.setPath("/data/logcollector");
        volume2.setHostPath(hostPathVolumeSource);
        volumes.add(volume2);

        podSpec.setVolumes(volumes);


        Map<String, Quantity> requests = new HashMap<>();
        requests.put("memory", new Quantity("0.2Gi"));
        requests.put("cpu", new Quantity("0.05"));

        Map<String, Quantity> limit = new HashMap<>();
        limit.put("memory", new Quantity("0.4Gi"));
        limit.put("cpu", new Quantity("1"));

        List<ContainerPort> containerPorts = new ArrayList<>();
        ContainerPort port1 = new ContainerPort();
        port1.setContainerPort(8080);
        ContainerPort port2 = new ContainerPort();
        port2.setContainerPort(1234);
        containerPorts.add(port1);
        containerPorts.add(port2);

        List<EnvVar> envs = new ArrayList<>();
        EnvVar env1 = new EnvVar();
        env1.setName("DOTNET_ARGS");
        env1.setValue("--urls=http://*:8080 --cp=/appconfig/appsettings.json");
        EnvVar env2 = new EnvVar();
        env2.setName("TARGET_BIN");
        env2.setValue("bin/Release/netcoreapp3.1/h3yun-engine-dao.dll");
        EnvVar env3 = new EnvVar();
        env3.setName("SHARD_KEY");
        env3.setValue("shard3");
        envs.add(env1);
        envs.add(env2);
        envs.add(env3);

        List<VolumeMount> vms = new ArrayList<>();
        vms.add(new VolumeMountBuilder().withName("properties-mount").withMountPath("/appconfig").withReadOnly(true).build());
        vms.add(new VolumeMountBuilder().withName("eventlog-mount").withMountPath("/data/logcollector").build());


        Container container = new ContainerBuilder().withImagePullPolicy("IfNotPresent")
                .withImage("registry.harbor.h3yun.net:30002/dev-h3yun-engine/h3yun-engine-dao:v1479")
                .withLivenessProbe(new ProbeBuilder()
                        .withFailureThreshold(10)
                        .withTimeoutSeconds(1).withPeriodSeconds(10)
                        .withInitialDelaySeconds(30)
                        .withHttpGet(new HTTPGetActionBuilder().withPath("/v1/heartbeat/ping").withPort(new IntOrString(8080)).build()).build())
                .withName("h3yun-engine-dao")
                .withResources(new ResourceRequirementsBuilder().withRequests(requests).withLimits(limit).build())
                .withReadinessProbe(new ProbeBuilder()
                        .withFailureThreshold(10)
                        .withTimeoutSeconds(1)
                        .withPeriodSeconds(10)
                        .withInitialDelaySeconds(10)
                        .withHttpGet(new HTTPGetActionBuilder().withPath("/v1/heartbeat/ping").withPort(new IntOrString(8080)).build()).build())
                .withPorts(containerPorts)
                .withEnv(envs)
                .withVolumeMounts(vms)
                .build();
        List<Container> containerList = new ArrayList<>();
        containerList.add(container);
        podSpec.setContainers(containerList);

        List<LocalObjectReference> localObjectReferenceList = new ArrayList<>();
        localObjectReferenceList.add(new LocalObjectReferenceBuilder().withName("h3yun-dockercfg").build());
        podSpec.setImagePullSecrets(localObjectReferenceList);

        config.setSpec(podSpec);
        return config;
    }
}
