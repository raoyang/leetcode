package org.sunshine.lc.test;

import com.authine.h3yun.engine.controller.constant.Constant;
import com.authine.h3yun.engine.controller.engine.entity.K8SEngineCfg;
import com.authine.h3yun.engine.controller.utils.YamlUtils;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@SpringBootTest
//@RunWith(SpringRunner.class)
public class ServiceTest {

    @Test
    public void test() throws IOException {

        System.setProperty("kubeconfig", "C:\\Users\\T87\\.kube\\config");
        //K8sEngine engine1 = getEngineWithJson("shard3", "v1");
        K8SEngineCfg engine2 = getEngineWithYaml("shard4", "v1");
        //System.out.println(engine1);
        System.out.println(engine2);

        //1. 构建json格式的引擎

        //2. 构建yaml格式的引擎

        /*
        KubernetesClient client = new DefaultKubernetesClient();
        client.pods().inNamespace("h3yun-engine").watch(new Watcher<Pod>() {
            @Override
            public void eventReceived(Action action, Pod resource) {
                try {
                    String shard = K8sPodUtils.getShard(resource);
                    if(shard.equals("shard3")){
                        System.out.println("发生的事件是:" + action);
                        System.out.println(resource);
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onClose(WatcherException cause) {
                System.out.println("watch close due to:" + cause);
            }
        });
        createPod();

        */
    }

    private void createPod(){
        Pod config = createPodWithConfig(null, null);
        try(KubernetesClient client = new DefaultKubernetesClient()) {
            Pod createOne = client.pods().inNamespace("h3yun-engine").create(config);
            System.out.println(createOne);
        }

        try {
            Thread.sleep(600000);
        }catch (Exception e){
            e.printStackTrace();
        }
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

    private void listEndPoints(){
        try(KubernetesClient client = new DefaultKubernetesClient()){
            Endpoints endpoints = client.endpoints().inNamespace("h3yun-engine").withName("h3yun-engine-dao-shard1").get();
            String ip = endpoints.getSubsets().get(0).getAddresses().get(0).getIp();
            int port = endpoints.getSubsets().get(0).getPorts().get(1).getPort();
            System.out.println(endpoints);
        }
    }

    private K8SEngineCfg getEngineWithJson(String shard, String version) throws IOException {
        K8SEngineCfg engine = new K8SEngineCfg();
        engine.setShard(shard);
        engine.setVersion(version);
        engine.setCreatetime(System.currentTimeMillis());
        Pod pod = createPodWithConfig(shard, version);
        engine.setPod(pod);
        return engine;
    }

    private K8SEngineCfg getEngineWithYaml(String shard, String version) throws IOException {
        K8SEngineCfg engine = new K8SEngineCfg();
        engine.setShard(shard);
        engine.setVersion(version);
        engine.setCreatetime(System.currentTimeMillis());
        Pod pod = createPodWithConfig(shard, version);
        System.out.println(YamlUtils.toYaml(pod));
        engine.setPod(pod);
        return engine;
    }
}
